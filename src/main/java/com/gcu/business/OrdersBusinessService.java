package com.gcu.business;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.gcu.data.entity.OrderEntity;
import com.gcu.data.repository.OrdersRepository;
import com.gcu.model.OrderModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class OrdersBusinessService implements OrdersBusinessServiceInterface
{
	@Autowired
	OrdersRepository service;
	
	@Override
	public void test() 
	{
		log.info("test() - Entering and exiting.");
	}

	@Override
	public List<OrderModel> getOrders() 
	{
		log.info("getOrders() - Entering method. Requesting to get orders.");
		
		var ordersDomain = new ArrayList<OrderModel>();
		
		var ordersEntity = service.findAll();
		
		for (OrderEntity entity : ordersEntity)
		{
			ordersDomain.add(new OrderModel(entity.getId(), entity.getOrderNo(), entity.getProductName(), entity.getPrice(), entity.getQuantity()));
		}
		
		log.info("getOrders() - Acquired orders. Exiting method");
		
		// Return list of orders
		return ordersDomain;
	}

	@Override
	public void init()
	{
		log.info("init() - Entering and exiting.");
	}

	@Override
	public void destroy()
	{
		log.info("destroy() - Entering and exiting.");
	}
	
}
