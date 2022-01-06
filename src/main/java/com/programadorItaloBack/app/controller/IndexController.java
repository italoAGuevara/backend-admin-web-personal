package com.programadorItaloBack.app.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.programadorItaloBack.app.models.services.IReqCostumerService;

@Controller
public class IndexController {
	
	@Autowired
	private IReqCostumerService reqCostumerService;
	
	@GetMapping(value={ "", "/","/home", "/admi"})
	public String home(Model model) {		
		model.addAttribute("costumersBycontacted", reqCostumerService.getContacted());
		model.addAttribute("numCostumerByContact", reqCostumerService.numBycontact());
		return "redirect:/costumer";
	}
	
	@GetMapping(value="/tasks")
	public String task(Model model) {
		model.addAttribute("costumersBycontacted", reqCostumerService.getContacted());
		model.addAttribute("numCostumerByContact", reqCostumerService.numBycontact());		
		return "windows/tasks/tasks";
	}
	
	@RequestMapping(value="/deleteReqC/{id}")
	public String delete(@PathVariable(value="id") Long id) {
		
		if(id > 0) {
			reqCostumerService.delete(id);
		}
		return "redirect:/";
	}
	
}
