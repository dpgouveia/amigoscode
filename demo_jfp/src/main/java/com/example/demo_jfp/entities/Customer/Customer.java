package com.example.demo_jfp.entities.Customer;

import java.time.LocalDate;

public class Customer {

	private String name;
	private String phone;
	private String email;
	private LocalDate dateOfBith;
	
	public Customer() {
		super();
	}
	
	public Customer(String name, String phone) {
		super();
		this.name = name;
		this.phone = phone;
	}
	
	public Customer(String name, String phone, String email, LocalDate dateOfBith) {
		super();
		this.name = name;
		this.phone = phone;
		this.email = email;
		this.dateOfBith = dateOfBith;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public LocalDate getDateOfBith() {
		return dateOfBith;
	}

	public void setDateOfBith(LocalDate dateOfBith) {
		this.dateOfBith = dateOfBith;
	}

	@Override
	public String toString() {
		return "Customer [name=" + name + ", phone=" + phone + ", email=" + email + ", dateOfBith=" + dateOfBith + "]";
	}
		
}
