package com.example.demo_jfp.imperative;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import com.example.demo_jfp.entities.Person.Gender;
import com.example.demo_jfp.entities.Person.Person;

public class DemoImperative {
	
	public static void main(String[] args) {
		List<Person> people = List.of(
				new Person("John", Gender.MALE)
				, new Person("Maria", Gender.FEMALE)
				, new Person("Ashia", Gender.FEMALE)
				, new Person("Alex", Gender.MALE)
				, new Person("Alice", Gender.FEMALE)
				);
		
		// Create a list of people and print female people from it
		// Imperative approach
		System.out.println("\n// Imperative approach");
		List<Person> females = new ArrayList<Person>();
		for (Person person : people) {
			if(Gender.FEMALE.equals(person.getGender())) {
				females.add(person);
			}
		}
		
		for (Person female : females) {
			System.out.println(female);
		}
		
		// Declarative approach
		System.out.println("\n// Declarative approach 1");
		people.stream()
			.filter(person -> Gender.FEMALE.equals(person.getGender()))
			.collect(Collectors.toList())
			.forEach(System.out::println);
		
		System.out.println("\n// Declarative approach 2");
		people.stream()
			.filter(person -> Gender.FEMALE.equals(person.getGender()))
			.forEach(System.out::println);		
		
		System.out.println("\n// Declarative approach 3");
		List<Person> females2 = people.stream()
			.filter(person -> Gender.FEMALE.equals(person.getGender()))
			.collect(Collectors.toList());
		females2.forEach(System.out::println);
		
		System.out.println("\n// Declarative approach 4");
		Predicate<Person> personPredicate = person -> Gender.FEMALE.equals(person.getGender());
		people.stream()
			.filter(personPredicate)
			.forEach(System.out::println);
	}

}
