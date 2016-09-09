package com.chen.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class sessionfactory {
	private static SessionFactory sessionFactory = null;
	
	public void setUp() throws Exception{
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure().build();
		
		try{
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		}catch(Exception e){
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}
}
