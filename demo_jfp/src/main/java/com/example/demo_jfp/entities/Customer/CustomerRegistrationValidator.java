package com.example.demo_jfp.entities.Customer;

import java.time.LocalDate;
import java.time.Period;
import java.util.function.Function;

public interface CustomerRegistrationValidator extends Function<Customer, CustomerValidationResult> {

	public static CustomerRegistrationValidator isEmailValid() {
		return customer -> {
			System.out.println("Running isEmailValid() step ...");
			return customer.getEmail().contains("@") ?
					CustomerValidationResult.SUCESS : CustomerValidationResult.EMAIL_NOT_VALID;
		};
				
	}
	
	public static CustomerRegistrationValidator isPhoneValid() {
		return customer -> customer.getPhone().startsWith("+55") ? 
				CustomerValidationResult.SUCESS : CustomerValidationResult.PHONE_NUMBER_NOT_VALID;
	}
	
	public static CustomerRegistrationValidator isAnAdult() {
		return customer -> Period.between(customer.getDateOfBith(), LocalDate.now()).getYears() >= 18 ?
					CustomerValidationResult.SUCESS : CustomerValidationResult.IS_NOT_AN_ADULT;
	}
	
	default CustomerRegistrationValidator and(CustomerRegistrationValidator other) {
		return customer -> { 
			CustomerValidationResult result = this.apply(customer);
			return result.equals(CustomerValidationResult.SUCESS) ? other.apply(customer) : result;  
		};
	}
		
}
