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

import com.programadorItaloBack.app.models.services.ICostumerService;
import com.programadorItaloBack.app.models.services.IReqCostumerService;

@Controller
@SessionAttributes("costumer")
public class CostumerController {

	@Autowired
	private ICostumerService costumerService;

	@Autowired
	private IReqCostumerService reqCostumerService;

	@GetMapping(value = "/costumer")
	public String costumerList(Model model) {
		model.addAttribute("listCostumers", costumerService.findAll());
		model.addAttribute("costumersBycontacted", reqCostumerService.getContacted());
		model.addAttribute("numCostumerByContact", reqCostumerService.numBycontact());
		return "windows/costumers/costumer";
	}

	@GetMapping("/costumerNew")
	public String create(Model model) {
		Costumer costumer = new Costumer();
		model.addAttribute("costumer", costumer);
		model.addAttribute("costumersBycontacted", reqCostumerService.getContacted());
		model.addAttribute("numCostumerByContact", reqCostumerService.numBycontact());
		return "windows/costumers/createNew";
	}

	@GetMapping("/costumerEdit/{id}")
	public String update(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		Costumer costumer = null;

		if (id > 0) {
			costumer = costumerService.FinById(id);
		} else {
			return "redirect:/costumerNew";
		}

		model.put("costumer", costumer);
		model.put("costumersBycontacted", reqCostumerService.getContacted());
		model.put("numCostumerByContact", reqCostumerService.numBycontact());

		return "windows/costumers/editCostumer";
	}

	@RequestMapping(value = "/costumerNew", method = RequestMethod.POST)
	public String createNew(@Valid Costumer costumer, BindingResult result, Model model, RedirectAttributes flash,
			SessionStatus status) {

		if (result.hasErrors()) {
			model.addAttribute("costumerNew", costumer);
			model.addAttribute("costumersBycontacted", reqCostumerService.getContacted());
			model.addAttribute("numCostumerByContact", reqCostumerService.numBycontact());
			return "windows/costumers/createNew";
		}

		costumerService.save(costumer);
		status.setComplete();

		return "redirect:/costumer";
	}

	@RequestMapping(value = "/deleteCostumer/{id}")
	public String delete(@PathVariable(value = "id") Long id) {

		if (id > 0) {
			costumerService.delete(id);
		}
		return "redirect:/costumer";
	}

}
