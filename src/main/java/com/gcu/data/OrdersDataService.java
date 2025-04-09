package com.gcu.data;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Service;

import com.gcu.business.OrdersBusinessService;
import com.gcu.data.repository.OrdersRepository;
import com.gcu.model.OrderModel;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrdersDataService implements DataAccessInterface<OrderModel>
{
	@SuppressWarnings("unused")
	private DataSource dataSource;
	private JdbcTemplate jdbcTemplateObject;
	
	/**
	 * Non-default constructor for constructor injection
	 * @param dataSource
	 */
	public OrdersDataService(OrdersRepository ordersRepository, DataSource dataSource)
	{
		this.dataSource = dataSource;
		this.jdbcTemplateObject = new JdbcTemplate(dataSource);
	}
	
	/**
	 * Get a list of everything
	 * @return
	 */
	public List<OrderModel> findAll()
	{
		log.info("OrdersDataService getting all orders.");
		String sql = "SELELT * FROM ORDERS";
		List<OrderModel> orders = new ArrayList<OrderModel>();
		try
		{
			// Execute SQL Query and loop over results set
			SqlRowSet srs = jdbcTemplateObject.queryForRowSet(sql);
			while(srs.next())
			{
				orders.add(new OrderModel(srs.getLong("ID"), 
								srs.getString("ORDER_NO"), 
								srs.getString("PRODUCT_NAME"),
								srs.getFloat("PRICE"),
								srs.getInt("QUANTITY")));
			}
		}
		catch (Exception e)
		{
			log.error("Error occured while gettings orders", e);
		}
		
		return orders;
	}
	
	public OrderModel findByID(int id)
	{
		return null;
	}
	
	/**
	 * Create a new item
	 * @param order
	 * @return
	 */
	public boolean create(OrderModel order)
	{
		log.info("Creating an order");
		// Example of "overriding the CrudRepository save() because it simply is never called
		// You can inject a dataSource and use the jdbcTemplate to provide a customized implementation of a save() method
		String sql = "INSERT INTO ORDERS(ORDER_NO, PRODUCT_NAME, PRICE, QUANTITY) VALUES(?, ?, ?, ?)";
		try
		{
			// Execute SQL insert
			jdbcTemplateObject.update(sql, 
										order.getOrderNo(), 
										order.getProductName(),
										order.getPrice(),
										order.getQuantity());
			
		}
		catch (Exception e)
		{
			log.error("Error occured while creating order", e);
			return false;
		}
		
		return true;
	}
	
	public boolean update(OrderModel order)
	{
		return true;
	}
	
	public boolean delete(OrderModel order)
	{
		return true;
	}
}
