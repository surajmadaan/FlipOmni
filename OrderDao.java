package com.flip.dao;

import java.util.List;

import com.flip.entity.Order;

public interface OrderDao {
	
	public List<Order> getOrders();

	public void saveOrder(Order theOrder);

	public Order getOrder(int theId);

	public void deleteOrder(int theId);


}
