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
		log.info("Hello from the OrdersBusinessService.");
	}

	@Override
	public List<OrderModel> getOrders() 
	{
		log.info("OrdersBusinessService requesting to get orders.");
		
		var ordersDomain = new ArrayList<OrderModel>();
		
		var ordersEntity = service.findAll();
		
		for (OrderEntity entity : ordersEntity)
		{
			ordersDomain.add(new OrderModel(entity.getId(), entity.getOrderNo(), entity.getProductName(), entity.getPrice(), entity.getQuantity()));
		}
		
		// Return list of orders
		return ordersDomain;
	}

	@Override
	public void init()
	{
		log.info("This is init from the OrdersBusinessService.");
	}

	@Override
	public void destroy()
	{
		log.trace("----In destroy()");
		log.info("This is destroy from the OrdersBusinessService.");
	}
	
}
