package com.flip.service;

import java.util.List;

import com.flip.entity.Order;

public interface OrderService {
	
	public List<Order> getOrders();

	public void saveOrder(Order theOrder);

	public Order getOrder(int theId);

	public void deleteOrder(int theId);


}
