package com.example.demo_jfp.optionals;

import java.util.Optional;
import java.util.function.Supplier;

public class DemoOptionals {
	
	public static void main(String[] args) {
		
		// Optionals 01
		System.out.println("\n\n// Optionals 01");
		Object value = null;
		value = Optional.ofNullable(null)
				.orElseGet(() -> "Default value");
		System.out.println("Object value 01: " + value);
		
		value = Optional.ofNullable("Hello World")
				.orElseGet(() -> "Default value");
		System.out.println("Object value 02: " + value);
		
		Supplier<IllegalStateException> exceptionSupplier = () -> new IllegalStateException("You are not supposed to be here!");
		value = Optional.ofNullable("Non empty string")
				.orElseThrow(exceptionSupplier);
		System.out.println("Object value 03: " + value);
		
		Optional.ofNullable("john@gmail.com")
				.ifPresent(email -> System.out.println("Sending email to " + email));
		
		Optional.ofNullable(null)
				.ifPresentOrElse(
						email -> System.out.println("Sending email to " + email),
						() -> System.out.println("Cannot send email"));	
	}

}
