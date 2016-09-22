package org.chen.hibernate1_1;

import java.util.List;

import org.chen.Entity.Customer;
import org.chen.Entity.Order1;
import org.chen.Service.BusinessService;
import org.chen.Service.OrderAndCustomerService;
import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * Hello world!
 *
 */
public class App {
	private static SessionFactory sessionFactory = null;
	static {
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder().configure().build();
		try {
			sessionFactory = new MetadataSources(registry)
					.addAnnotatedClass(Order1.class)
					.buildMetadata()
					.buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
	}

	/*
	 * 程序入口
	 */
	public static void main(String[] args) {
		try {
			if (sessionFactory == null) {
				System.out.println("Hello World!");
			} else {
				System.out.println("Hello Hibernate!");
				BusinessService service = new BusinessService(sessionFactory);
				//service.test();
				Customer c = service.findCustomerByID(1);
				if (c != null) {
					OrderAndCustomerService s = new OrderAndCustomerService(sessionFactory);
					s.saveOrdersByCustomer(c);
					List<?> ls = s.findOrdersByCustomer(c);
					s.printOrders(ls);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		} finally {
			if (sessionFactory != null) {
				sessionFactory.close();
			}
		}
	}
}
