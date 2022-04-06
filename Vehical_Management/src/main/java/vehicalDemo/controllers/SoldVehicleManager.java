package vehicalDemo.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import vehicalDemo.data.Customer;
import vehicalDemo.data.CustomerRepository;
import vehicalDemo.data.ServiceRecord;
import vehicalDemo.data.ServiceRecordRepository;
import vehicalDemo.data.SoldVehicle;
import vehicalDemo.data.SoldVehicleRepository;
import vehicalDemo.data.Vehicle;
import vehicalDemo.data.VehicleRepository;

@Controller
public class SoldVehicleManager {

	@Autowired
	HttpSession session;

	@Autowired
	CustomerRepository cr;

	@Autowired
	VehicleRepository vr;

	@Autowired
	SoldVehicleRepository svr;

	@Autowired
	ServiceRecordRepository srr;


	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/manageSoldVehicle")
	public ModelAndView dash(HttpServletRequest request, ModelMap model) {

		String goal = request.getParameter("goal");
		
		if (goal == null)
			throw new RuntimeException("not a proper goal");

		switch (goal) {
		case "add": {

			int regNo = Integer.parseInt(request.getParameter("regNo"));
			int vehicleId = Integer.parseInt(request.getParameter("vehicleId"));
			int customerId = Integer.parseInt(request.getParameter("customerId"));

			Vehicle v = vr.findById(vehicleId).get();
			Customer c = cr.findById(customerId).get();
			svr.save(new SoldVehicle(regNo, v, c));
			model.addAttribute("message", "sold added succesfully");
			break;
		}

		case "remove": {
			int regNo = Integer.parseInt(request.getParameter("regNo"));
			SoldVehicle sv = svr.findById(regNo).get();

			for (ServiceRecord sr : sv.getServiceRecords()) {
				sr.setSoldVehicle(null);
				srr.save(sr);
			}

			svr.delete(sv);
			model.addAttribute("message", "sold vehicle removed succesfully");
			break;
		}

		case "edit": {
			int regNo = Integer.parseInt(request.getParameter("regNo"));
			int vehicleId = Integer.parseInt(request.getParameter("customerId"));
			int customerId = Integer.parseInt(request.getParameter("vehicleId"));

			logger.info("{} {} {}", regNo, vehicleId, customerId);

			SoldVehicle sv = svr.findById(regNo).get();

			Customer c = cr.findById(customerId).get();
			Vehicle v = vr.findById(vehicleId).get();

			sv.setCustomer(c);
			sv.setVehicle(v);
			svr.save(sv);
			model.addAttribute("message", "sold vehicle edited succesfully");
			break;
		}
		}
		
		return new ModelAndView("redirect:/dashboard?section=soldVehicles", model);

	}

}

