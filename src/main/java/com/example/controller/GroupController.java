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

import com.example.model.Group;
import com.example.model.User;
import com.example.repository.GroupRepository;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin")
public class GroupController {
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/group", method = RequestMethod.GET)
	public ModelAndView brandType(){
		ModelAndView modelAndView = new ModelAndView();
		Group group = new Group();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("group", group);
		modelAndView.addObject("groups", groupRepository.findAll());
		modelAndView.setViewName("admin/group");
		return modelAndView;
	}
	
	@RequestMapping(value = "/group/save", method = RequestMethod.POST)
	public ModelAndView saveGroup(@Valid Group group, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			group.setUser(user);
			group.setEntryDate(new Date());
			groupRepository.save(group);
			modelAndView.addObject("successMessage", "Group Add Success");
			modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
			group =null;
			modelAndView.addObject("group",new Group());
			modelAndView.addObject("groups", groupRepository.findAll());
			
			
			modelAndView.setViewName("admin/group");
		return modelAndView;
	}
	
	@RequestMapping(value = "/group/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editGroup(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("group",groupRepository.findOne(id));
		modelAndView.addObject("groups", groupRepository.findAll());
		
		
		modelAndView.setViewName("admin/group");
		return modelAndView;
	}
	@RequestMapping(value = "/group/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteGroup(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		groupRepository.delete(id);
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("group", new Group());
		modelAndView.addObject("groups", groupRepository.findAll());
		
		
		modelAndView.setViewName("admin/group");
		return modelAndView;
	}
}
