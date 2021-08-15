package com.example.demo_uit;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;

class FirstDemoTest {

	private Calculator underTest = new Calculator(); 
	
	@Test
	void itShouldAddTwoNumbers() {
		
		// Dado que ...
		int numberOne = 20;
		int numberTwo = 30;
		
		// Quando ...
		int result = underTest.add(numberOne, numberTwo);
		
		// Entao....
		int expectedResult = 50;
		assertThat(result).isEqualTo(expectedResult);
		
	}
	
	class Calculator {
		int add(int a, int b) {
			return a+b;
		}
	}

}
