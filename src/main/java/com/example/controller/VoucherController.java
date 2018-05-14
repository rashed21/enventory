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

import com.example.model.Voucher;
import com.example.model.User;
import com.example.repository.VoucherRepository;
import com.example.repository.WholesellerRepository;
import com.example.service.UserService;

@Controller
@RequestMapping("/admin")
public class VoucherController {
	
	@Autowired
	private WholesellerRepository wholesellerRepository;
	
	@Autowired
	private VoucherRepository voucherRepository;
	
	@Autowired
	private UserService userService;

	@RequestMapping(value="/voucher", method = RequestMethod.GET)
	public ModelAndView brandType(){
		ModelAndView modelAndView = new ModelAndView();
		Voucher voucher = new Voucher();
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("voucher", voucher);
		modelAndView.addObject("vouchers", voucherRepository.findAll());
		modelAndView.addObject("wholesellers", wholesellerRepository.findAll());
		modelAndView.setViewName("admin/voucher");
		return modelAndView;
	}
	
	@RequestMapping(value = "/voucher/save", method = RequestMethod.POST)
	public ModelAndView saveVoucher(@Valid Voucher voucher, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			User user = userService.findUserByEmail(auth.getName());
			
			voucher.setUser(user);
			voucher.setEntryDate(new Date());
			voucher.setDue(voucher.getTotalValue()-voucher.getPayment());
			voucherRepository.save(voucher);
			modelAndView.addObject("successMessage", "Voucher Add Success");
			modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
			voucher =null;
			modelAndView.addObject("voucher",new Voucher());
			modelAndView.addObject("vouchers", voucherRepository.findAll());
			modelAndView.addObject("wholesellers", wholesellerRepository.findAll());
			
			
			
			modelAndView.setViewName("admin/voucher");
		return modelAndView;
	}
	
	@RequestMapping(value = "/voucher/edit/{id}", method = RequestMethod.GET)
	public ModelAndView editVoucher(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("voucher",voucherRepository.findOne(id));
		modelAndView.addObject("vouchers", voucherRepository.findAll());
		modelAndView.addObject("wholesellers", wholesellerRepository.findAll());
		
		
		modelAndView.setViewName("admin/voucher");
		return modelAndView;
	}
	@RequestMapping(value = "/voucher/delete/{id}", method = RequestMethod.GET)
	public ModelAndView deleteVoucher(@PathVariable Integer id) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		User user = userService.findUserByEmail(auth.getName());
		voucherRepository.delete(id);
		modelAndView.addObject("userName", user.getName() + " " + user.getLastName());
		modelAndView.addObject("voucher", new Voucher());
		modelAndView.addObject("vouchers", voucherRepository.findAll());
		modelAndView.addObject("wholesellers", wholesellerRepository.findAll());
		
		
		modelAndView.setViewName("admin/voucher");
		return modelAndView;
	}
}
