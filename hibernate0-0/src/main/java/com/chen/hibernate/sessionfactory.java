package com.chen.hibernate;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.List;

import com.chen.hibernate.Employee;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.junit.Test;

public class sessionfactory {
	private static SessionFactory sessionFactory = null;

	@Test
	public void setUp() throws Exception {
		System.out.println(sessionfactory.class.getClassLoader().getResource("").getPath());
		final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
				// .configure(f)
				.configure().build();
		try {
			sessionFactory = new MetadataSources(registry).addAnnotatedClass(Employee.class).buildMetadata()
					.buildSessionFactory();
		} catch (Exception e) {
			StandardServiceRegistryBuilder.destroy(registry);
			e.printStackTrace();
		}
	}

	/*
	 * AnnotationConfiguration过期了，没有实现的接口，现在不可用
	 */
	public void create() throws Exception {
		try {
			sessionFactory = new AnnotationConfiguration().configure().addAnnotatedClass(Employee.class)
					.buildSessionFactory();
		} catch (Exception e) {
			// TODO: handle exception
			System.err.println(e.getMessage());
			throw e;
		}
	}

	/*
	 * 列出所有的employee
	 */
	public void listEmployees() {
		Session s = sessionfactory.sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			List<Employee> Employees = s.createQuery("from Employee").getResultList();
			for (Iterator<Employee> itor = Employees.iterator(); itor.hasNext();) {
				Employee e = (Employee) itor.next();
				System.out.print("First name: " + e.getFirstName());
				System.out.print("Last name: " + e.getLastName());
				System.out.print("salary : " + e.getSalary());
				System.out.println();
			}
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	/*
	 * 添加一个employee ，ID由数据库生成
	 */
	public Integer addEmplyee(String fname, String lname, int salary) {
		Session s = sessionFactory.openSession();
		Transaction tx = null;
		Integer eId = null;
		try {
			tx = s.beginTransaction();
			Employee e = new Employee(fname, lname, salary);
			eId = (Integer) s.save(e);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
		return eId;
	}

	/*
	 * 更新数据库employee
	 */
	public void updateEmployee(Integer eId, int salary) {
		Session s = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Employee e = (Employee) s.get(Employee.class, eId);
			e.setSalary(salary);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	/*
	 * 删除一个Employee对象
	 */
	public void deleteEmployee(Integer eId) {
		Session s = sessionFactory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			Employee e = (Employee) s.get(Employee.class, eId);
			s.delete(e);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			if (tx != null)
				tx.rollback();
			e.printStackTrace();
		} finally {
			s.close();
		}
	}

	public static void main(String[] args) throws FileNotFoundException {
		sessionfactory s = new sessionfactory();
		try {
			s.setUp();
			// s.create();
			if (sessionfactory.sessionFactory != null) {
				Integer[] a = new Integer[3];

				a[0] = s.addEmplyee("Zara", "Ali", 1000);
				a[1] = s.addEmplyee("Daisy", "Das", 15000);
				a[2] = s.addEmplyee("John", "Paul", 10000);

				System.out.println("列出 all Employee");
				s.listEmployees();
				System.out.println("更新Employee");
				s.updateEmployee(a[0], 9000);
				System.out.println("删除一个记录");
				s.deleteEmployee(a[1]);
				System.out.println("删除后结果");
				s.listEmployees();

				System.out.println("准备关闭factory");
				sessionfactory.sessionFactory.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
