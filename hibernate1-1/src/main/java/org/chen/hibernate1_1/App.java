package org.chen.hibernate1_1;

import org.chen.Service.BusinessService;
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
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure()
				.build();
		try {
			sessionFactory = new MetadataSources(registry)
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
		try{
			if (sessionFactory == null) {
				System.out.println("Hello World!");
			}else{
				System.out.println("Hello Hibernate!");
				BusinessService service = new BusinessService(sessionFactory);
				service.test();
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			if(sessionFactory != null){
				sessionFactory.close();
			}
		}
	}
}
