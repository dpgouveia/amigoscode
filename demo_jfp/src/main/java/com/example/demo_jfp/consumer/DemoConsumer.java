package com.example.demo_jfp.consumer;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

import com.example.demo_jfp.consumer.Customer.Customer;

public class DemoConsumer {
	
	public static void main(String[] args) {
		
		// Normal java function
		System.out.println("\n// Normal java function");
		Customer maria = new Customer("Maria", "99999"); 
		greetCustomer(maria);
		
		// Consumer functional interface 1
		System.out.println("\n// Consumer functional interface 1");
		Consumer<Customer> greetCostumerConsumer = 
				customer -> System.out.println("Hello " + customer.getName() + " thanks for registering phone number " + customer.getPhone());
		greetCostumerConsumer.accept(maria);
		
		// Consumer functional interface 2
		System.out.println("\n// Consumer functional interface 2");
		BiConsumer<Customer, Boolean> greetCostumerConsumerV2 = 
				(customer, showPhoneNumber) -> System.out.println("Hello " + customer.getName() + " thanks for registering phone number " + (showPhoneNumber ? customer.getPhone() : "*****"));
		greetCostumerConsumerV2.accept(maria, true);
		greetCostumerConsumerV2.accept(maria, false);
		
		Customer joao = new Customer("Joao", "88888");
		greetCustomerV2(joao, true);
		greetCustomerV2(joao, false);
				
	}
	
	public static void greetCustomer(Customer customer) {
		System.out.println("Hello " + customer.getName() + " thanks for registering phone number " + customer.getPhone());
	}
	
	public static void greetCustomerV2(Customer customer, Boolean showPhoneNumber) {
		System.out.println("Hello " + customer.getName() + " thanks for registering phone number " + (showPhoneNumber ? customer.getPhone() : "*****"));
	}

}
