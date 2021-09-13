package com.example.demo_jfp.combinatorpattern;

import static com.example.demo_jfp.entities.Customer.CustomerRegistrationValidator.isAnAdult;
import static com.example.demo_jfp.entities.Customer.CustomerRegistrationValidator.isEmailValid;
import static com.example.demo_jfp.entities.Customer.CustomerRegistrationValidator.isPhoneValid;

import java.time.LocalDate;

import com.example.demo_jfp.entities.Customer.Customer;
import com.example.demo_jfp.entities.Customer.CustomerRegistrationValidator;
import com.example.demo_jfp.entities.Customer.CustomerValidationResult;
import com.example.demo_jfp.entities.Customer.CustomerValidatorService;

public class DemoCombinatorPattern {
	
	public static void main(String[] args) {
		
		// Combining commom validation in imperative approach
		System.out.println("\n\n// Combining commom validation in imperative approach");
		Customer alice = new Customer("Alice"
										, "+55 11 9 1234 5678"
										, "alice@gmail.com"
										, LocalDate.of(2000, 1, 1));
		Customer john = new Customer("John"
										, "55 11 9 5678 1234"
										, "john@gmail.com"
										, LocalDate.of(2010, 1, 1));
		Customer erick = new Customer("Erick"
										, "+55 11 9 1234 5678"
										, "erick@gmail.com"
										, LocalDate.of(2020, 1, 1));

		System.out.println("Alice is valid: " + new CustomerValidatorService().isValid(alice));
		System.out.println("John is valid: " + new CustomerValidatorService().isValid(john));
		
		// Combinator Pattern Approach 01
		System.out.println("\n\n// Combinator Pattern Approach 01");
		CustomerValidationResult result = null;
		result = isEmailValid()
					.and(isPhoneValid())
					.and(isAnAdult())
					.apply(alice);
		
		System.out.println("Alice Result: " + result);
		if(result != CustomerValidationResult.SUCESS) {
			throw new IllegalStateException(result.name());
		} 
		
		result = isEmailValid()
				.and(isPhoneValid())
				.and(isAnAdult())
				.apply(john);
		System.out.println("John Result: " + result);
		
		result = isEmailValid()
				.and(isPhoneValid())
				.and(isAnAdult())
				.apply(erick);
		System.out.println("Erick Result: " + result);
		
		System.out.println("Declaring CustomerRegistrationValidator without applying");
		CustomerRegistrationValidator crv = isEmailValid()
				.and(isPhoneValid())
				.and(isAnAdult());
		System.out.println("New Erick's Result: " + result);
		
	}

}
