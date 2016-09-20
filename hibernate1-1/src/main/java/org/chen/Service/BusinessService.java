package org.chen.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.chen.Entity.Customer;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public class BusinessService {
	public SessionFactory s_factory;

	public BusinessService(SessionFactory s_factory) {
		super();
		this.s_factory = s_factory;
	}

	/*
	 * 持久化一个Customer对象
	 */
	public void saveCustomer(Customer customer) throws Exception {
		Session s = s_factory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			s.save(customer);
			System.err.println("save the customer : " + customer.getId());
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}
	}

	/*
	 * 删除一个Customer对象
	 */
	public void deleteCusotomer(Customer c) throws Exception {
		Session s = s_factory.openSession();
		Transaction tx = null;
		try {
			tx = s.beginTransaction();
			System.err.println("delete the customer : " + c.getId());
			s.delete(c);
			tx.commit();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			throw e;
		} finally {
			s.close();
		}
	}

	/*
	 * 打印Customer对象信息
	 */
	public void printCustomer(Customer c) {
		System.err.println("the info below is " + c.getName() + "'s base information");
		System.out.println(c.getEmail());
		System.out.println(c.getPhone());
		System.out.println(c.getMarried() != 0 ? "Married" : "Not Married");
		System.out.println(c.getSex());
		System.out.println(c.getAddress());
		System.out.println(c.getDescription());
		System.err.println("end;");
	}

	/*
	 * 查找出所有的记录
	 */
	public List<Customer> findAllCustomers() {
		List<Customer> ls = new ArrayList<Customer>();
		Session s = s_factory.openSession();
		try {
			ls = s.createQuery("from Customer t", Customer.class).getResultList();
			for (Iterator<Customer> itor = ls.iterator(); itor.hasNext();) {
				Customer e = (Customer) itor.next();
				printCustomer(e);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ls;
	}

	/*
	 * test 函数
	 */
	public void test() throws Exception {
		Customer c = new Customer();
		c.setName("Tomy");
		c.setEmail("tomy@163.com");
		c.setPhone((int) (Math.random() % 1000));
		c.setSex('M');
		c.setMarried(0);
		c.setAddress("zondy company");
		c.setDescription("He is a funny man");
		c.setPassword("pass");
		saveCustomer(c);
		findAllCustomers();
	}
}
