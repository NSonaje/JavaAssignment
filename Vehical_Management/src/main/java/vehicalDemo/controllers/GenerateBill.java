package vehicalDemo.controllers;

import java.text.ParseException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
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
import vehicalDemo.data.SoldVehicleRepository;

@Controller
public class GenerateBill {

	@Autowired
	HttpSession session;

	@Autowired
	SoldVehicleRepository svr;

	@Autowired
	BillOfMaterialRepository bomr;

	@Autowired
	ServiceRecordRepository srr;


	@Autowired
	EntityManager em;

	private final Logger logger = LoggerFactory.getLogger(getClass());

	@PostMapping("/generateBill")
	public ModelAndView dash(HttpServletRequest request, ModelMap model) throws ParseException {


		int serviceId = Integer.parseInt(request.getParameter("id"));
		ServiceRecord sr = srr.findById(serviceId).get();

		TypedQuery<BillOfMaterial> query = em.createQuery("select bom from BillOfMaterial bom where serviceId=?1",
				BillOfMaterial.class);

		query.setParameter(1, serviceId);

		List<BillOfMaterial> boms = query.getResultList();

		model.addAttribute("boms", boms);
		model.addAttribute("sr", sr);

		return new ModelAndView("bill", model);

	}

}
