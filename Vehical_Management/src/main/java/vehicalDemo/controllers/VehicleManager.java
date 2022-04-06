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

import vehicalDemo.data.SoldVehicle;
import vehicalDemo.data.SoldVehicleRepository;
import vehicalDemo.data.Vehicle;
import vehicalDemo.data.VehicleRepository;

@Controller
public class VehicleManager {

	@Autowired
	HttpSession session;

	@Autowired
	VehicleRepository vr;

	@Autowired
	SoldVehicleRepository svr;


	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/manageVehicle")
	public ModelAndView dash(HttpServletRequest request, ModelMap model) {

		String goal = request.getParameter("goal");
		
		if (goal == null)
			throw new RuntimeException("not a proper goal");

		switch (goal) {
		case "add": {
			String brand = request.getParameter("brand");
			String vehicleModel = request.getParameter("model");
			int price = Integer.parseInt(request.getParameter("price"));
			vr.save(new Vehicle(brand, vehicleModel, price));
			model.addAttribute("message", "vehicle added succesfully");
			break;
		}

		case "remove": {
			int vehicleId = Integer.parseInt(request.getParameter("id"));
			Vehicle v = vr.findById(vehicleId).get();

			for (SoldVehicle sv : v.getSoldVehicles()) {
				sv.setVehicle(null);
				svr.save(sv);

			}

			vr.delete(v);
			model.addAttribute("message", "vehicle removed succesfully");
			break;
		}

		case "edit": {

			int vehicleId = Integer.parseInt(request.getParameter("id"));
			String brand = request.getParameter("brand");
			String vehicleModel = request.getParameter("model");
			int price = Integer.parseInt(request.getParameter("price"));
			Vehicle v = vr.findById(vehicleId).get();
			v.setBrand(brand);
			v.setModel(vehicleModel);
			v.setPrice(price);
			vr.save(v);
			model.addAttribute("message", "vehicle edited succesfully");
			break;
		}
		}
		
		return new ModelAndView("redirect:/dashboard?section=vehicles", model);

	}

}

