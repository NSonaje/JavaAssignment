package vehicalDemo.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import vehicalDemo.data.Customer;
import vehicalDemo.data.CustomerRepository;
import vehicalDemo.data.ServiceRecord;
import vehicalDemo.data.ServiceRecordRepository;
import vehicalDemo.data.SoldVehicle;
import vehicalDemo.data.SoldVehicleRepository;
import vehicalDemo.data.User;
import vehicalDemo.data.UserRepository;
import vehicalDemo.data.Vehicle;
import vehicalDemo.data.VehicleRepository;

@Controller
public class Dashboard {

	@Autowired
	HttpServletRequest request;

	@Autowired
	HttpSession session;

	@Autowired
	UserRepository ur;

	@Autowired
	ServiceRecordRepository srr;

	@Autowired
	CustomerRepository cr;

	@Autowired
	VehicleRepository vr;

	@Autowired
	SoldVehicleRepository svr;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@GetMapping("/dashboard")
	public ModelAndView dash(ModelMap model) {

		String section = (String) request.getParameter("section");
		request.setAttribute("message", (String) request.getParameter("message"));

		// determine the type of user
		User user = (User) session.getAttribute("user");

		// handle  non login access to URI
		if (user == null)
			return new ModelAndView("login", model);

		model.addAttribute("user", user);

		if (user.getUserType().equals("admin_user")) {
			logger.info("admin logged in...");
			
			if (section != null)
			switch(section) {
			case "customers":
				List<Customer> customers = cr.findAll();
				model.addAttribute("customers", customers);
				break;
			case "vehicles":
				List<Vehicle> vehicles = vr.findAll();
				model.addAttribute("vehicles", vehicles);
			case "soldVehicles":
				List<SoldVehicle> soldVehicles = svr.findAll();
				model.addAttribute("soldVehicles", soldVehicles);
			case "serviceRecords":
				List<ServiceRecord> serviceRecords = srr.findAll();
				model.addAttribute("serviceRecords", serviceRecords);
			default:
				logger.info("unknown section requested");
			}
			
			
			model.addAttribute("section", section);
			return new ModelAndView("dashboard_admin_user", model);
		
		}

		/* not an admin, assume service_user */
		logger.info("service user logged in...");

		// show service records list. (later sorted by due date)
		logger.info("fetching service records... found: {}", srr.count());
		List<ServiceRecord> srs = srr.findAll();
		model.addAttribute("serviceRecords", srs);
		return new ModelAndView("dashboard_service_user", model);

	}
}

