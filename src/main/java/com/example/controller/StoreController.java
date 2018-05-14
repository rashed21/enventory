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

import com.example.model.Store;
import com.example.model.User;
import com.example.repository.ColorRepository;
import com.example.repository.GroupRepository;
import com.example.repository.ModelRepository;
import com.example.repository.NatureRepository;
import com.example.repository.StoreRepository;
import com.example.repository.VoucherRepository;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin")
public class StoreController {
	
	@Autowired
	private StoreRepository storeRepository;
	
	@Autowired
	private ColorRepository colorRepository;
	
	@Autowired
	private GroupRepository groupRepository;
	
	@Autowired
	private ModelRepository modelRepository;
	
	@Autowired
	private NatureRepository natureRepository;
	
	@Autowired
	private VoucherRepository voucherRepository;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/store", method = RequestMethod.GET)
	public ModelAndView brandType(){
		ModelAndView modelAndView = new ModelAndView();
		Store store = new Store();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("store", store);
		modelAndView.addObject("stores", storeRepository.findAll());
		modelAndView.addObject("colors", colorRepository.findAll());
		modelAndView.addObject("groups", groupRepository.findAll());
		modelAndView.addObject("models", modelRepository.findAll());
		modelAndView.addObject("natures", natureRepository.findAll());
		modelAndView.addObject("vouchers", voucherRepository.findAll());
		
		modelAndView.setViewName("admin/store");
		return modelAndView;
	}
	
	@RequestMapping(value = "/store/save", method = RequestMethod.POST)
	public ModelAndView saveStore(@Valid Store store, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			store.setUser(user);
			store.setEntryDate(new Date());
			storeRepository.save(store);
			modelAndView.addObject("successMessage", "Store Add Success");
			modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
			store =null;
			modelAndView.addObject("store",  new Store());
			modelAndView.addObject("stores", storeRepository.findAll());
			modelAndView.addObject("colors", colorRepository.findAll());
			modelAndView.addObject("groups", groupRepository.findAll());
			modelAndView.addObject("models", modelRepository.findAll());
			modelAndView.addObject("natures", natureRepository.findAll());
			modelAndView.addObject("vouchers", voucherRepository.findAll());
			
			
			modelAndView.setViewName("admin/store");
		return modelAndView;
	}
	
	@RequestMapping(value = "/store/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editStore(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("store",storeRepository.findOne(id));
		modelAndView.addObject("stores", storeRepository.findAll());
		modelAndView.addObject("colors", colorRepository.findAll());
		modelAndView.addObject("groups", groupRepository.findAll());
		modelAndView.addObject("models", modelRepository.findAll());
		modelAndView.addObject("natures", natureRepository.findAll());
		modelAndView.addObject("vouchers", voucherRepository.findAll());
		
		
		modelAndView.setViewName("admin/store");
		return modelAndView;
	}
	@RequestMapping(value = "/store/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteStore(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		storeRepository.delete(id);
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("store", new Store());
		modelAndView.addObject("stores", storeRepository.findAll());
		modelAndView.addObject("colors", colorRepository.findAll());
		modelAndView.addObject("groups", groupRepository.findAll());
		modelAndView.addObject("models", modelRepository.findAll());
		modelAndView.addObject("natures", natureRepository.findAll());
		modelAndView.addObject("vouchers", voucherRepository.findAll());
		
		
		modelAndView.setViewName("admin/store");
		return modelAndView;
	}
}
