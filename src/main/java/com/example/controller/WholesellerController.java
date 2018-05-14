package com.example.controller;

import java.util.Date;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Wholeseller;
import com.example.model.User;
import com.example.repository.WholesellerRepository;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin")
public class WholesellerController {
	
	@Autowired
	private WholesellerRepository wholesellerRepository;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/wholeseller", method = RequestMethod.GET)
	public ModelAndView brandType(){
		ModelAndView modelAndView = new ModelAndView();
		Wholeseller wholeseller = new Wholeseller();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("wholeseller", wholeseller);
		modelAndView.addObject("wholesellers", wholesellerRepository.findAll());
		modelAndView.setViewName("admin/wholeseller");
		return modelAndView;
	}
	
	@RequestMapping(value = "/wholeseller/save", method = RequestMethod.POST)
	public ModelAndView saveWholeseller(@Valid Wholeseller wholeseller, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			wholeseller.setUser(user);
			wholeseller.setEntryDate(new Date());
			wholesellerRepository.save(wholeseller);
			modelAndView.addObject("successMessage", "Wholeseller Add Success");
			modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
			wholeseller =null;
			modelAndView.addObject("wholeseller",new Wholeseller());
			modelAndView.addObject("wholesellers", wholesellerRepository.findAll());
			
			
			modelAndView.setViewName("admin/wholeseller");
		return modelAndView;
	}
	
	@RequestMapping(value = "/wholeseller/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editWholeseller(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("wholeseller",wholesellerRepository.findOne(id));
		modelAndView.addObject("wholesellers", wholesellerRepository.findAll());
		
		
		modelAndView.setViewName("admin/wholeseller");
		return modelAndView;
	}
	@RequestMapping(value = "/wholeseller/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteWholeseller(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		wholesellerRepository.delete(id);
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("wholeseller", new Wholeseller());
		modelAndView.addObject("wholesellers", wholesellerRepository.findAll());
		
		
		modelAndView.setViewName("admin/wholeseller");
		return modelAndView;
	}
}
