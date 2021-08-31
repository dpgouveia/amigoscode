package com.amigoscode.testing.utils;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class PhoneNumberValidatorTest {

	private PhoneNumberValidator underTest;
	
	@BeforeEach
	void setUp() {
		underTest = new PhoneNumberValidator();
	}
	
	@ParameterizedTest
	@CsvSource({""
				+ "+445000000000, true"
				, "null, false"
				, "+4470000000888, false"
				, "+447000000088, false"
				, "4450000000000, false"
				, "+44500000000, false"
	})
	@DisplayName("Should return sucess when phone number is valid")
	void validatePhoneNumber(String input, String expected) {
				
		// Quando
		Boolean isValid = underTest.test(input);
		
		// Entao
		assertThat(isValid).isEqualTo(Boolean.valueOf(expected));	
	}
	
	@Test
	@DisplayName("Should fail when phone number is null")
	void validatePhoneNumberIsNull() {
		
		// Dado que
		String phoneNumber = null;
		
		// Quando
		Boolean isValid = underTest.test(phoneNumber);
		
		// Entao
		assertThat(isValid).isFalse();	
	}
	
	@Test
	@DisplayName("Should fail when phone number does not start with +445")
	void validatePhoneNumberDoesNotStartWithPlusSign() {
		
		// Dado que
		String phoneNumber = "445000000000";
		
		// Quando
		Boolean isValid = underTest.test(phoneNumber);
		
		// Entao
		assertThat(isValid).isFalse();	
	}
	
	@Test
	@DisplayName("Should fail when legth is bigger than 13")
	void validatePhoneNumberWhenIncorrectAndHasLengthBiggerThan13() {
		
		// Dado que
		String phoneNumber = "+447000000088878";
		
		// Quando
		Boolean isValid = underTest.test(phoneNumber);
		
		// Entao
		assertThat(isValid).isFalse();
	}
	
	@Test
	@DisplayName("Should fail when legth is lower than 13")
	void validatePhoneNumberWhenIncorrectAndHasLengthLowerThan13() {
		
		// Dado que
		String phoneNumber = "+4450000000";
		
		// Quando
		Boolean isValid = underTest.test(phoneNumber);
		
		// Entao
		assertThat(isValid).isFalse();
	}
	
	

}
