package com.example.demo_jfp.finalsection;

import java.util.function.Consumer;

public class DemoCallbacks {
	
	public static void main(String[] args) {
		
		// Java script callbacks in java
		System.out.println("\n\n//Java script callbacks in java 01");
		hello("Daniel", "Montana", null);
		hello("Daniel", 
				null,
				value -> System.out.println("No lastname provided for " + value));
		hello("Daniel",
				"Montana",
				value -> System.out.println("No lastname provided for " + value));
		
		System.out.println("\n\n//Java script callbacks in java 02");
		hello2("Daniel",
				"Montana",
				null);
		hello2("Daniel",
				null,
				() -> System.out.println("No lastname provided"));
		hello2("Daniel",
				"Montana",
				() -> System.out.println("No lastname provided"));
	}
	
	public static void hello(String firstName, String lastName, Consumer<String> callback) {
		System.out.println(firstName);
		if(lastName != null) {
			System.out.println(lastName);
		} else {
			callback.accept(firstName);
		}
	}
	
	public static void hello2(String firstName, String lastName, Runnable callback) {
		System.out.println(firstName);
		if(lastName != null) {
			System.out.println(lastName);
		} else {
			callback.run();
		}
	}
	
	/*
	 * function hello(firstName, lastName, callback) {
	 * 		console.log(firstName);
	 * 		if(lastName) {
	 * 			console.log(lastName);
	 * 		} else {
	 * 			callback();
	 * 		}
	 * }
	 * 
	 * hello("Daniel", null, function() {console.log("lastName not provided");})
	 * 
	 * hello("Daniel", "Gouveia", function() {console.log("lastName not provided");})
	 * 
	 */

}
