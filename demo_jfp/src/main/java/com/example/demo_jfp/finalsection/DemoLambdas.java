package com.example.demo_jfp.finalsection;

import java.util.function.BiFunction;
import java.util.function.Function;

public class DemoLambdas {
	
	public static void main(String[] args) {
		
		// Lambdas
		System.out.println("Lambdas 01");
		Function<String, String> upperCase1 = string -> string.toUpperCase(); 
		
		Function<String, String> upperCase2 = String::toUpperCase; 
		
		Function<String, String> upperCase3 = string -> {
			System.out.println("Some extra code here...");
			return string.toUpperCase();
		};
		
		BiFunction<String, Integer, String> upperCaseName = (name, age) -> {
			if(name == null || name.isBlank()) {
				throw new IllegalStateException("name is null or blank!");
			}
			System.out.println(age);
			return name.toUpperCase();
		};
		
		String upperCasedName = upperCaseName.apply("daniel", 18); 
		System.out.println(upperCasedName);
		
	}

}
