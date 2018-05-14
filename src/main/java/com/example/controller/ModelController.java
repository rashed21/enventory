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

import com.example.model.Model;
import com.example.model.User;
import com.example.repository.BrandNameRepository;
import com.example.repository.ModelRepository;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin")
public class ModelController {
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private BrandNameRepository brandNameRepository;
	
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/model", method = RequestMethod.GET)
	public ModelAndView brandType(){
		ModelAndView modelAndView = new ModelAndView();
		Model model = new Model();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("model", model);
		modelAndView.addObject("models", modelRepository.findAll());
		modelAndView.addObject("brandNames", brandNameRepository.findAll());
		modelAndView.setViewName("admin/model");
		return modelAndView;
	}
	
	@RequestMapping(value = "/model/save", method = RequestMethod.POST)
	public ModelAndView saveModel(@Valid Model model, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			System.out.println("Brand name is "+model.getBrandName().getName());
			model.setUser(user);
			model.setEntryDate(new Date());
			modelRepository.save(model);
			modelAndView.addObject("successMessage", "Model Add Success");
			modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
			model =null;
			modelAndView.addObject("brandNames", brandNameRepository.findAll());
			modelAndView.addObject("model",new Model());
			modelAndView.addObject("models", modelRepository.findAll());
			
			
			modelAndView.setViewName("admin/model");
		return modelAndView;
	}
	
	@RequestMapping(value = "/model/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editModel(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("model",modelRepository.findOne(id));
		modelAndView.addObject("models", modelRepository.findAll());
		modelAndView.addObject("brandNames", brandNameRepository.findAll());
		
		
		modelAndView.setViewName("admin/model");
		return modelAndView;
	}
	@RequestMapping(value = "/model/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteModel(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelRepository.delete(id);
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("model", new Model());
		modelAndView.addObject("models", modelRepository.findAll());
		modelAndView.addObject("brandNames", brandNameRepository.findAll());
		
		
		modelAndView.setViewName("admin/model");
		return modelAndView;
	}
}
