package com.programadorItaloBack.app.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.programadorItaloBack.app.controller.models.entity.Costumer;
import com.programadorItaloBack.app.controller.models.entity.Factura;
import com.programadorItaloBack.app.models.services.ICostumerService;
import com.programadorItaloBack.app.models.services.IFacturaService;
import com.programadorItaloBack.app.models.services.IReqCostumerService;

@Controller
@RequestMapping("/factura")
@SessionAttributes("factura")
public class FacturaController {

	@Autowired
	private ICostumerService costumerService;

	@Autowired
	private IReqCostumerService reqCostumerService;

	@Autowired
	private IFacturaService facturaService;

	@GetMapping("/form/{costumerId}")
	public String create(@PathVariable(value = "costumerId") Long costumerId, Map<String, Object> model,
			RedirectAttributes flash) {

		Costumer costumer = costumerService.FinById(costumerId);
		if (costumer == null) {
			flash.addFlashAttribute("error", "El cliente no existe en la base de datos");
			return "redirect:/costumer";
		}

		Factura factura = new Factura();
		factura.setCostumer(costumer);
		model.put("costumers", reqCostumerService.findAll());
		model.put("numCostumerByContact", reqCostumerService.numBycontact());
		model.put("factura", factura);

		return "windows/factura/form";
	}

	@GetMapping("/facturaShow/{idFact}/{idCost}")
	public String show(@PathVariable(value = "idFact") Long idFact, @PathVariable(value = "idCost") Long idCos,
			Map<String, Object> model) {
		Costumer costumer = null;
		Factura factura = null;

		if (idFact > 0) {
			factura = facturaService.FinById(idFact);
			costumer = costumerService.FinById(idCos);
		}

		model.put("factura", factura);
		model.put("costumer", costumer);
		model.put("costumersBycontacted", reqCostumerService.getContacted());
		model.put("numCostumerByContact", reqCostumerService.numBycontact());

		return "windows/factura/seefactura";
	}

	@RequestMapping(value = "/facturaNew", method = RequestMethod.POST)
	public String createNew(@Valid Factura facturaNew, BindingResult result, Model model, SessionStatus status) {
		
		if (result.hasErrors()) {
			return "windows/factura/form";
		}

		facturaService.save(facturaNew);
		status.setComplete();

		return "redirect:/costumer";
	}

}
