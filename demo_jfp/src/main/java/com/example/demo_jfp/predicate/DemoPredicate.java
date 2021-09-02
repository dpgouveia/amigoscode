package com.example.demo_jfp.predicate;

import java.util.function.Predicate;

public class DemoPredicate {
	
	public static void main(String[] args) {
		
		// Normal java function
		System.out.println("\n// Normal java function");
		String phone1 = "07000000000";
		String phone2 = "0700000000";
		String phone3 = "09009877300";
		String phone4 = "07009877300";
		System.out.println(phone1 + ": " + isPhoneNumberValid(phone1));
		System.out.println(phone2 + ": " + isPhoneNumberValid(phone2));
		System.out.println(phone3 + ": " + isPhoneNumberValid(phone3));
		
		// Predicate functional interface 1
		System.out.println("\n// Predicate functional interface 1");
		Predicate<String> isPhoneNumberValidPredicate = phoneNumber -> phoneNumber != null && phoneNumber.startsWith("07") && phoneNumber.length() == 11;
		System.out.println(phone1 + ": " + isPhoneNumberValidPredicate.test(phone1));
		System.out.println(phone2 + ": " + isPhoneNumberValidPredicate.test(phone2));
		System.out.println(phone3 + ": " + isPhoneNumberValidPredicate.test(phone3));
		
		// Predicate functional interface 2
		System.out.println("\n// Predicate functional interface 2");
		Predicate<String> containsNumber3 = phoneNumber -> phoneNumber.contains("3");
		System.out.println("Validate with phone number is valid AND contains number 3");
		System.out.println(phone1 + ": " + isPhoneNumberValidPredicate.and(containsNumber3).test(phone1));
		System.out.println(phone2 + ": " + isPhoneNumberValidPredicate.and(containsNumber3).test(phone2));
		System.out.println(phone3 + ": " + isPhoneNumberValidPredicate.and(containsNumber3).test(phone3));
		System.out.println(phone4 + ": " + isPhoneNumberValidPredicate.and(containsNumber3).test(phone4));
		
		System.out.println("Validate with phone number is valid OR contains number 3");
		System.out.println(phone1 + ": " + isPhoneNumberValidPredicate.or(containsNumber3).test(phone1));
		System.out.println(phone2 + ": " + isPhoneNumberValidPredicate.or(containsNumber3).test(phone2));
		System.out.println(phone3 + ": " + isPhoneNumberValidPredicate.or(containsNumber3).test(phone3));
		System.out.println(phone4 + ": " + isPhoneNumberValidPredicate.or(containsNumber3).test(phone4));
				
	}
	
	public static boolean isPhoneNumberValid(String phoneNumber) {
		return phoneNumber != null && phoneNumber.startsWith("07") && phoneNumber.length() == 11;
	}

}

