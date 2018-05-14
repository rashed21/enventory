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

import com.example.model.BrandName;
import com.example.model.User;
import com.example.repository.BrandNameRepository;
import com.example.service.BrandTypeService;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin")
public class BrandNameController {
	
	@Autowired
	private BrandNameRepository brandNameRepository;
	
	@Autowired
	private BrandTypeService bandTypeService;
	
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/brandName", method = RequestMethod.GET)
	public ModelAndView brandType(){
		ModelAndView modelAndView = new ModelAndView();
		BrandName brandName = new BrandName();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("brandName", brandName);
		modelAndView.addObject("brandNames", brandNameRepository.findAll());
		modelAndView.addObject("brands", bandTypeService.getAllBrandType());
		modelAndView.setViewName("admin/brandName");
		return modelAndView;
	}
	
	@RequestMapping(value = "/brandName/save", method = RequestMethod.POST)
	public ModelAndView saveBrandName(@Valid BrandName brandName, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			brandName.setUser(user);
			brandName.setEntryDate(new Date());
			brandNameRepository.save(brandName);
			modelAndView.addObject("successMessage", "BrandName Add Success");
			modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
			brandName =null;
			modelAndView.addObject("brandName",new BrandName());
			modelAndView.addObject("brandNames", brandNameRepository.findAll());
			modelAndView.addObject("brands", bandTypeService.getAllBrandType());
			
			modelAndView.setViewName("admin/brandName");
		return modelAndView;
	}
	
	@RequestMapping(value = "/brandName/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editBrandName(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("brandName",brandNameRepository.findOne(id));
		modelAndView.addObject("brandNames", brandNameRepository.findAll());
		modelAndView.addObject("brands", bandTypeService.getAllBrandType());
		
		modelAndView.setViewName("admin/brandName");
		return modelAndView;
	}
	@RequestMapping(value = "/brandName/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteBrandName(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		brandNameRepository.delete(id);
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("brandName", new BrandName());
		modelAndView.addObject("brandNames", brandNameRepository.findAll());
		modelAndView.addObject("brands", bandTypeService.getAllBrandType());
		
		modelAndView.setViewName("admin/brandName");
		return modelAndView;
	}
}
