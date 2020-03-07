package com.flip.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateObjectRetrievalFailureException;

import com.flip.entity.Order;
import com.flip.entity.Stock;

public class OrderDaoImpl implements OrderDao{	

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Order> getOrders() {

		Session currentSession = sessionFactory.getCurrentSession();
		try {
			Query<Order> theQuery = 
					currentSession.createQuery("from Orders order by id",
							Order.class);

			List<Order> orders = theQuery.getResultList();

			if(orders!=null) {
				return orders;
			}
		}
		catch (HibernateObjectRetrievalFailureException e) {
			System.out.println("No orders Present");
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public void saveOrder(Order theOrder) {

		Session currentSession = sessionFactory.getCurrentSession();
		//checking if the item is in stock or not
		String item = theOrder.getItem();
		int orderQuantity= theOrder.getQuantity();
		try {

			Query<Stock> theQuery = 
					currentSession.createQuery("from Stock s where item=:item");
			theQuery.setParameter("item", item);

			Stock stock = (Stock) theQuery.getSingleResult();
			int availableStock = stock.getQuantity();

			if(availableStock!=0 && availableStock>=orderQuantity) {
				currentSession.saveOrUpdate(theOrder);
			}
		}
		catch (HibernateException e) {
			System.out.println("Item is Out of Stock");
			e.printStackTrace();
		}




	}

	@Override
	public Order getOrder(int theId) {

		Session currentSession =sessionFactory.getCurrentSession();

		Order theOrder = currentSession.get(Order.class,theId);		

		return theOrder;
	}

	@Override
	public void deleteOrder(int theId) {

		Session currentSession =sessionFactory.getCurrentSession();

		Query theQuery = 
				currentSession.createQuery("delete from Orders where id=:orderId");
		theQuery.setParameter("orderId", theId);

		try{
			theQuery.executeUpdate();				
		}
		catch (HibernateException e) {
			System.out.println("No such order Present");
			e.printStackTrace();
		}
	}



}
