package com.example.controller;

import java.io.Serializable;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.example.model.Color;
import com.example.model.Response;
import com.example.model.User;
import com.example.repository.ColorRepository;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin")
public class ColorController implements Serializable{
	
	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/color", method = RequestMethod.GET)
	public ModelAndView color(){
		ModelAndView modelAndView = new ModelAndView();
		Color color = new Color();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("color", color);
		modelAndView.addObject("colors", colorRepository.findAll());
		modelAndView.setViewName("admin/color");
		return modelAndView;
	}
	
	@GetMapping(value="/color/all")
	@ResponseBody
	public Response  allColors(){
		Response response = new Response("Done", colorRepository.findAll());
		return response;
	}
	
	
	@PostMapping(value = "/color/save")
	@ResponseBody
	public Response saveColor(@RequestBody Color color, BindingResult bindingResult) {
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			System.out.println("Color code form save methods "+color.getCode());
			
			color.setUser(user);
			color.setEntryDate(new Date());
			colorRepository.save(color);
			Response response = new Response("Done", color);
		return response;
	}
	
	
	@RequestMapping(value = "/color/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editColor(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("color",colorRepository.findOne(id));
		modelAndView.addObject("colors", colorRepository.findAll());
		
		
		modelAndView.setViewName("admin/color");
		return modelAndView;
	}
	@RequestMapping(value = "/color/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteColor(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		colorRepository.delete(id);
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("color", new Color());
		modelAndView.addObject("colors", colorRepository.findAll());
		
		
		modelAndView.setViewName("admin/color");
		return modelAndView;
	}
}
