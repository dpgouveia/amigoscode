package com.example.demo_jfp.entities.Customer;

import java.time.LocalDate;
import java.time.Period;

public class CustomerValidatorService {
	
	private boolean isEmailValid(String email) {
		return email.contains("@");
	}
	
	private boolean isPhoneNumberValid(String phone) {
		return phone.startsWith("+55");
	}
	
	private boolean isAnAdult(LocalDate dateOfBirth) {
		return Period.between(dateOfBirth, LocalDate.now()).getYears() > 18;
	}
	
	public boolean isValid(Customer customer) {
		return isEmailValid(customer.getEmail()) 
				&& isPhoneNumberValid(customer.getPhone()) 
				&& isAnAdult(customer.getDateOfBith()) ;	
	}

}
