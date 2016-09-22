package org.chen.Service;

import java.util.Iterator;
import java.util.List;

import org.chen.Entity.Customer;
import org.chen.Entity.Order1;
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

	public void printOrders(List<?> orders) {
		for (Iterator<?> itor = orders.iterator(); itor.hasNext();) {
			Order1 o = (Order1) itor.next();
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
			Order1 o = new Order1();
			o.setOrderNumber("very much");
			o.setCustomer(c);
			Order1 o1 = new Order1();
			o1.setOrderNumber("very litter");
			o1.setCustomer(c);
			s.save(o);
			s.save(o1);
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
		List<Order1> ls = null;
		try {
			tx = s.beginTransaction();
			Query<Order1> q = s.createQuery("from Order1 t where t.customer.id = :a", Order1.class);
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
