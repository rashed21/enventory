package com.example.controller;

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

import com.example.model.BrandType;
import com.example.model.User;
import com.example.service.BrandTypeService;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin")
public class BrandTypeController {
	
	@Autowired
	private BrandTypeService bandTypeService;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/brandInfo", method = RequestMethod.GET)
	public ModelAndView brandType(){
		ModelAndView modelAndView = new ModelAndView();
		BrandType brandType = new BrandType();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("brandType", brandType);
		modelAndView.addObject("brands", bandTypeService.getAllBrandType());
		modelAndView.setViewName("admin/brandInfo");
		return modelAndView;
	}
	
	@RequestMapping(value = "/brandType/save", method = RequestMethod.POST)
	public ModelAndView saveBrandInfo(@Valid BrandType brandType, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			BrandType type = new BrandType();
			brandType.setUser(user);
			bandTypeService.saveBrandTypeName(brandType);
			modelAndView.addObject("successMessage", "User has been registered successfully");
			modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
			modelAndView.addObject("brandType", type);
			modelAndView.addObject("brands", bandTypeService.getAllBrandType());
			
			
			modelAndView.setViewName("admin/brandInfo");
		return modelAndView;
	}
	
	@RequestMapping(value = "/brandType/edit/{id}", method = RequestMethod.GET)
	public ModelAndView updateBrand(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("brandType", bandTypeService.getBrandBybTypeId(id));
		modelAndView.addObject("brands", bandTypeService.getAllBrandType());
		modelAndView.setViewName("admin/brandInfo");
		return modelAndView;
	}
	
	@RequestMapping(value = "/brandType/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteBrand(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		
		bandTypeService.deleteBybTypeId(id);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		BrandType type = new BrandType();
		modelAndView.addObject("brandType", type);
		modelAndView.addObject("brands", bandTypeService.getAllBrandType());
		modelAndView.setViewName("admin/brandInfo");
		return modelAndView;
	}
}
