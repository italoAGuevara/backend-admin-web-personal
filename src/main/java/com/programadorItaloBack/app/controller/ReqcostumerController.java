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
import org.springframework.web.bind.support.SessionStatus;

import com.programadorItaloBack.app.controller.models.entity.ReqCostumer;
import com.programadorItaloBack.app.models.services.IReqCostumerService;

@Controller
public class ReqcostumerController {
	
	@Autowired
	private IReqCostumerService costumerService;
	
	@GetMapping("/listRequest")
	public String list(Model model) {
		model.addAttribute("costumers", costumerService.findAll());
		model.addAttribute("costumersBycontacted", costumerService.getContacted());
		model.addAttribute("numCostumerByContact", costumerService.numBycontact());			
		return "windows/costumers/listRequest";
	}
	
	@RequestMapping("/listRequest/{id}")
	public String update(@PathVariable(value = "id") Long id, Map<String, Object> model) {
		
		ReqCostumer reqCostumer = null;
		
		if (id > 0) {
			reqCostumer = costumerService.findByIdApi(id);
		} else {
			return "redirect:/listRequest";
		}
		model.put("costumersBycontacted", costumerService.getContacted());
		model.put("numCostumerByContact", costumerService.numBycontact());
		model.put("reqCostumer", reqCostumer);

		return "windows/costumers/seeReqCostumer";
	}
	
	@RequestMapping(value="/listRequest", method = RequestMethod.POST)
	public String save(@Valid ReqCostumer reqCostumer, BindingResult result, Model model, SessionStatus status) {

		// Verifica si existe errores al llenar los campos
		if (result.hasErrors()) {
			return "redirect:/listRequest/1";
		}

		costumerService.save(reqCostumer);
		status.setComplete();

		return "redirect:/listRequest";
	}
	@RequestMapping(value="/deleteReqCostumer/{id}")
	public String delete(@PathVariable(value="id") Long id) {
		
		if(id > 0) {
			costumerService.delete(id);
		}
		return "redirect:/listRequest";
	}
	
}
