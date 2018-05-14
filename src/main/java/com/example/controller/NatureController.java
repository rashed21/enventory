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

import com.example.model.Nature;
import com.example.model.User;
import com.example.repository.NatureRepository;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin")
public class NatureController {
	
	@Autowired
	private NatureRepository natureRepository;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/nature", method = RequestMethod.GET)
	public ModelAndView brandType(){
		ModelAndView modelAndView = new ModelAndView();
		Nature nature = new Nature();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("nature", nature);
		modelAndView.addObject("natures", natureRepository.findAll());
		modelAndView.setViewName("admin/nature");
		return modelAndView;
	}
	
	@RequestMapping(value = "/nature/save", method = RequestMethod.POST)
	public ModelAndView saveNature(@Valid Nature nature, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			nature.setUser(user);
			nature.setEntryDate(new Date());
			natureRepository.save(nature);
			modelAndView.addObject("successMessage", "Nature Add Success");
			modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
			nature =null;
			modelAndView.addObject("nature",new Nature());
			modelAndView.addObject("natures", natureRepository.findAll());
			
			
			modelAndView.setViewName("admin/nature");
		return modelAndView;
	}
	
	@RequestMapping(value = "/nature/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editNature(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("nature",natureRepository.findOne(id));
		modelAndView.addObject("natures", natureRepository.findAll());
		
		
		modelAndView.setViewName("admin/nature");
		return modelAndView;
	}
	@RequestMapping(value = "/nature/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteNature(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		natureRepository.delete(id);
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("nature", new Nature());
		modelAndView.addObject("natures", natureRepository.findAll());
		
		
		modelAndView.setViewName("admin/nature");
		return modelAndView;
	}
}
