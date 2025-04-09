package com.gcu;

import org.springframework.context.annotation.*;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.gcu.business.*;

@Configuration
public class SpringConfig 
{	
	@Bean(name = "ordersBusinessService", initMethod = "init", destroyMethod = "destroy")
	// @Scope(value = "prototype", proxyMode = ScopedProxyMode.TARGET_CLASS)
	// @RequestScope
	// @SessionScope
	public OrdersBusinessServiceInterface getOrdersBusiness()
	{
		return new OrdersBusinessService();
	}	
}
