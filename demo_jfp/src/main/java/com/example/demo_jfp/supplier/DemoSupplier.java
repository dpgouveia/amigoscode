package com.example.demo_jfp.supplier;

import java.util.List;
import java.util.function.Supplier;

public class DemoSupplier {

	public static void main(String[] args) {
		// Normal java function
		System.out.println("\n// Normal java function");
		System.out.println(getDbConnectionUrl());
		
		// Supplier functional interface 1
		System.out.println("\n// Supplier functional interface 1");
		Supplier<String> getDbConnectionUrl = () -> "jdbc://localhost:5432/users";
		System.out.println(getDbConnectionUrl.get());
		
		Supplier<List<String>> getDbConnectionUrls = () -> List.of(
															"jdbc://localhost:5432/users",
															"jdbc://localhost:5432/customers");
		System.out.println(getDbConnectionUrls.get());
		
	}
	
	public static String getDbConnectionUrl() {
		return "jdbc://localhost:5432/users";
	}
	
}
