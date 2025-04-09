package com.gcu.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.gcu.business.OrdersBusinessService;
import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.business.SecurityBusinessService;
import com.gcu.model.*;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController 
{
	@Autowired
	private OrdersBusinessServiceInterface service;
	@Autowired
	private SecurityBusinessService security;
	
	private static final Logger logger = LoggerFactory.getLogger(OrdersBusinessService.class);
	
	@GetMapping("/")
	public String display(Model model)
	{
		logger.info("display() - Entering method. Getting page attributes.");
		
		// Display Login Page
		model.addAttribute("title", "Login Form");
		model.addAttribute("loginModel", new LoginModel());
		
		logger.info("display() - Page is now loaded. Exiting method.");
		
		return "login";
	}
	
	@PostMapping("/doLogin")
	public String doLogin(@Valid LoginModel loginModel, BindingResult bindingResult, Model model)
	{
		logger.info("doLogin() - Entering method. Attempting to log user in.");
		
		//Check for Validation Errors
		if (bindingResult.hasErrors())
		{
			logger.info("doLogin() - Incorrect information entered. Restarting method.");
			
			model.addAttribute("title", "Login Form");
			return "login";
		}
		
		// Interface test
		service.test();
		security.authenticate(loginModel.getUsername(), loginModel.getPassword());
		
		List<OrderModel> orders = service.getOrders();
		
		model.addAttribute("title", "My Orders");
		model.addAttribute("orders", orders);
		
		logger.info("doLogin() - User got logged in. Exiting method.");
		
		// Go to Login Page
		return "orders";
	}
}
