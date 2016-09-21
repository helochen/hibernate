package org.chen.Service;

import java.util.Iterator;
import java.util.List;

import org.chen.Entity.Customer;
import org.chen.Entity.Order;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class OrderAndCustomerService {

	public SessionFactory s_factory;

	public OrderAndCustomerService(SessionFactory s_factory) {
		super();
		this.s_factory = s_factory;
	}

	public void printOrders(List orders) {
		for (Iterator<?> itor = orders.iterator(); itor.hasNext();) {
			Order o = (Order) itor.next();
			System.out.println(o.getOrderNumber());
			System.out.println(o.getCustomer().getName());
			System.err.println(o.getId());
		}
	}

	public void saveOrdersByCustomer(Customer c) {
		Session s = s_factory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Order o = new Order();
			o.setOrderNumber("very much");
			o.setCustomer(c);
			Order o1 = new Order();
			o1.setOrderNumber("very litter");
			o1.setCustomer(c);
			s.saveOrUpdate(o);
			s.saveOrUpdate(o1);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null)
				tx.rollback();
		} finally {
			s.close();
		}
	}

	public List<?> findOrdersByCustomer(Customer c) {
		Session s = s_factory.openSession();
		Transaction tx = null;
		List<Order> ls = null;
		try {
			tx = s.beginTransaction();
			Query<Order> q = s.createQuery("from Order t where t.customer.id = :a", Order.class);
			q.setParameter("a", c.getId());
			ls = q.getResultList();
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
		} finally {
			s.close();
		}
		return ls;
	}

}
