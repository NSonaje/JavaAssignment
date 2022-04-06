package vehicalDemo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import vehicalDemo.data.BillOfMaterial;
import vehicalDemo.data.BillOfMaterialRepository;
import vehicalDemo.data.ServiceRecord;
import vehicalDemo.data.ServiceRecordRepository;
import vehicalDemo.data.SoldVehicle;
import vehicalDemo.data.SoldVehicleRepository;

@Controller
public class ServiceRecordManager {

	@Autowired
	HttpSession session;

	@Autowired
	SoldVehicleRepository svr;

	@Autowired
	BillOfMaterialRepository bomr;

	@Autowired
	ServiceRecordRepository srr;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/manageService")
	public ModelAndView dash(HttpServletRequest request, ModelMap model) throws ParseException {

		switch (request.getParameter("goal")) {

		case "toServicing": {
			int serviceId = Integer.parseInt(request.getParameter("serviceId"));
			ServiceRecord sr = srr.findById(serviceId).get();
			sr.setServiceStatus("servicing");
			srr.save(sr);
			return new ModelAndView("redirect:/dashboard?section=serviceRecords", model);
		}

		case "toServiced": {
			int serviceId = Integer.parseInt(request.getParameter("serviceId"));
			ArrayList<HashMap<String, String>> materials = extractMaterials(request);
			logger.info("got bill of materials: {}", materials);

			for (HashMap<String, String> material : materials) {
				String partName = material.get("partName");
				int partQuantity = Integer.parseInt(material.get("partQuantity"));
				int partPrice = Integer.parseInt(material.get("partPrice"));
				bomr.save(new BillOfMaterial(partName, partQuantity, partPrice, serviceId));
			}
			ServiceRecord sr = srr.findById(serviceId).get();
			sr.setServiceStatus("serviced");
			srr.save(sr);
			break;

		}

		case "add": {
			int regNo = Integer.parseInt(request.getParameter("regNo"));
			SoldVehicle sv = svr.findById(regNo).get();
			String date = request.getParameter("dueDate");
			java.util.Date jDate = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(date);
			java.sql.Date dueDate = new java.sql.Date(jDate.getTime());
			ServiceRecord sr = new ServiceRecord(sv, "unserviced", dueDate);
			srr.save(sr);
			break;
		}
		case "remove": {
			int serviceId = Integer.parseInt(request.getParameter("id"));
			srr.deleteById(serviceId);
			break;
		}
		case "edit": {
			int serviceId = Integer.parseInt(request.getParameter("id"));
			int regNo = Integer.parseInt(request.getParameter("regNo"));
			String date = request.getParameter("dueDate");
			java.util.Date jDate = (java.util.Date) new SimpleDateFormat("yyyy-MM-dd").parse(date);
			java.sql.Date dueDate = new java.sql.Date(jDate.getTime());
			ServiceRecord sr = srr.findById(serviceId).get();
			SoldVehicle sv = svr.findById(regNo).get();
			sr.setDueDate(dueDate);
			sr.setSoldVehicle(sv);
			srr.save(sr);
			break;
		}
		}

		return new ModelAndView("redirect:/dashboard?section=serviceRecords", model);

	}

	// extracts the bill of materials as an array from request.
	private ArrayList<HashMap<String, String>> extractMaterials(HttpServletRequest request) {
		HashMap<Integer, HashMap<String, String>> materials = new HashMap<>();
		Enumeration<String> params = request.getParameterNames();

		while (params.hasMoreElements()) {
			String param = params.nextElement();
			if (!Character.isDigit(param.charAt(0)))
				continue;

			int key = Integer.parseInt(param.split("_")[0]);
			String param_name = param.split("_")[1];

			if (materials.get(key) == null)
				materials.put(key, new HashMap<String, String>());

			materials.get(key).put(param_name, request.getParameter(param));
		}
		return new ArrayList<HashMap<String, String>>(materials.values());

	}
}
