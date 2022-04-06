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
import vehicalDemo.data.SoldVehicle;
import vehicalDemo.data.SoldVehicleRepository;

@Controller
public class CustomerManager {

	@Autowired
	HttpSession session;

	@Autowired
	CustomerRepository cr;

	@Autowired
	SoldVehicleRepository svr;


	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/manageCustomer")
	public ModelAndView dash(HttpServletRequest request, ModelMap model) {

		String goal = request.getParameter("goal");
		
		if (goal == null)
			throw new RuntimeException("not a proper goal");

		switch (goal) {
		case "add": {
			String name = request.getParameter("name");
			String location = request.getParameter("location");
			cr.save(new Customer(name, location));
			model.addAttribute("message", "customer added succesfully");
			break;
		}

		case "remove": {
			int customerId = Integer.parseInt(request.getParameter("id"));
			Customer c = cr.findById(customerId).get();

			for (SoldVehicle sv : c.getSoldVehicles()) {
				sv.setCustomer(null);
				svr.save(sv);

			}
			cr.delete(c);
			model.addAttribute("message", "customer removed succesfully");
			break;
		}

		case "edit": {
			int id = Integer.parseInt(request.getParameter("id"));
			String name = request.getParameter("name");
			String location = request.getParameter("location");
			Customer c = cr.findById(id).get();
			c.setName(name);
			c.setLocation(location);
			cr.save(c);
			model.addAttribute("message", "customer added succesfully");
			break;
		}
		}
		
		return new ModelAndView("redirect:/dashboard?section=customers", model);

	}

}

