package com.example.demo_jfp.finalsection;

import java.util.function.Function;

public class DemoPrimitives {

	public static void main(String[] args) {
		
		// Primitives
		System.out.println("//Primitives 01");
		Integer integerObject = null;
		int integerPrimitive = 0; 
//		Function<Integer, int> next = value -> ++value; // we are not allowed to use primitives in functional interfaces
		
		Function<Integer, Integer> next = value -> ++value;
		System.out.println(next.apply(1));
		
	}
	
}
