package com.gcu.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import com.gcu.business.OrdersBusinessServiceInterface;
import com.gcu.model.OrderModel;

@RestController
@RequestMapping("/service")
public class OrdersRestController 
{
	@Autowired
	OrdersBusinessServiceInterface service;
	
	@GetMapping(path = "/getjson", produces = {MediaType.APPLICATION_JSON_VALUE})
	public List<OrderModel> getOrdersAsJson()
	{
		return service.getOrders();
	}
	
	@GetMapping(path = "/getxml", produces = {MediaType.APPLICATION_XML_VALUE})
	public List<OrderModel> getOrdersAsXml()
	{
		return service.getOrders();
	}
}
