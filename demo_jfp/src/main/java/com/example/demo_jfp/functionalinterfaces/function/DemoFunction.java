package com.example.demo_jfp.functionalinterfaces.function;

import java.util.function.BiFunction;
import java.util.function.Function;

public class DemoFunction {

	public static void main(String[] args) {
		
		// Function approach
		System.out.println("\n// Function approach");
		int i = incrementByOneFunction(0);
		System.out.println(i);
		
		i = incrementByOneFunction(1);
		System.out.println(i);
		
		// Functional interface approach
		System.out.println("\n//Functional interface approach 1");
		Function<Integer, Integer> incrementByOneFunction = 
				number -> ++number;
		System.out.println(incrementByOneFunction.apply(0));
		System.out.println(incrementByOneFunction.apply(1));
		
		System.out.println("\n//Functional interface approach 2");
		Function<Integer, Integer> multiplyByTen = 
				number -> number * 10; 
		System.out.println(multiplyByTen.apply(2));
		
		System.out.println("\n//Functional interface approach 3 -- Function<T,R>");
		System.out.println(incrementByOneFunction.andThen(multiplyByTen).apply(2));
		
		System.out.println("\n//Functional interface approach 4 -- BiFunction<T,U,R>");
		BiFunction<Integer, Integer, Integer> incrementByOneAndMultiply =
				(numberToIncrementByOne, numberToMultiply) -> (numberToIncrementByOne + 1) * numberToMultiply;
		System.out.println(incrementByOneAndMultiply.apply(4, 100));
		
	}
	
	public static int incrementByOneFunction(int number) {
		return number + 1;
	}
	
	public static int incrementByOneAndMultiply(int numberToIncrementByOne, int numberToMultiply) {
		return (numberToIncrementByOne + 1) * numberToMultiply;
	}
	
}
