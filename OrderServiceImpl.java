package com.flip.service;


import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flip.dao.OrderDao;
import com.flip.entity.Order;


@Service
public class OrderServiceImpl implements OrderService {
	
	@Autowired
	private OrderDao orderDao;	

	@Override
	@Transactional
	public List<Order> getOrders() {
		
		return orderDao.getOrders();
	}

	@Override
	@Transactional
	public void saveOrder(Order theOrder) {
		
		orderDao.saveOrder(theOrder);
	}

	@Override
	@Transactional
	public Order getOrder(int theId) {
		
		return orderDao.getOrder(theId);
	}

	@Override
	public void deleteOrder(int theId) {
		
		orderDao.deleteOrder(theId);
	}

	
}
