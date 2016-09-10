package com.chen.hibernate;

import java.io.File;
import java.io.FileNotFoundException;

import org.hibernate.SessionFactory;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

public class sessionfactory {
	private static SessionFactory sessionFactory = null;

	public void setUp() throws Exception {
		File f = new File(
				"/home/chen/git/project/hibernate/hibernate0-0/src/main/java/com/chen/hibernate/hibernate.cfg.xml");
		if(!f.exists()){
			return;
		}
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				.configure(f).build();

		try {
			sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		sessionfactory s = new sessionfactory();
		try {
			s.setUp();
			if(sessionfactory.sessionFactory != null){
				
				
				sessionfactory.sessionFactory.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
