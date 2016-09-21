package org.chen.Entity;

import java.io.Serializable;

public class Order implements Serializable{

	private Long id;
	private String orderNumber;
	private Customer customer;
	
	public Order() {
		// TODO Auto-generated constructor stub
	}
	public Long getId() {
		return id;
	}
	private void setId(Long id) {
		this.id = id;
	}
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
}
