package org.chen.Entity;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class Customer implements Serializable{
	private Long id;
	private String name;
	private String email;
	private String password;
	private int 	phone;
	private int		married;
	private String address;
	private char sex;
	private String description;
	private byte[]	image;
	private Date	birthday;
	private Timestamp registeredTime;
	
	private Set<Order1> orders = new HashSet<Order1>();
	
	public Set<Order1> getOrders() {
		return orders;
	}

	public void setOrders(Set<Order1> orders) {
		this.orders = orders;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Customer() {
		// TODO Auto-generated constructor stub
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public char getSex() {
		return sex;
	}
	public void setSex(char sex) {
		this.sex = sex;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public Timestamp getRegisteredTime() {
		return registeredTime;
	}
	public void setRegisteredTime(Timestamp registeredTime) {
		this.registeredTime = registeredTime;
	}
	public int getMarried() {
		return married;
	}
	public void setMarried(int married) {
		this.married = married;
	}
}
