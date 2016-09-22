package org.chen.Entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="ORDERS")
public class Order1 implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5596582663333548343L;
	@Id
	@GeneratedValue
	@Column(name="ID")
	private Long id;
	@Column(name="ORDER_NUMBER")
	private String orderNumber;
	
	@ManyToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="CUSTOMER_ID" , nullable=false)
	private Customer customer;
	
	public Order1() {
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
