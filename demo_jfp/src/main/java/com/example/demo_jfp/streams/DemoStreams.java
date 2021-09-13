package com.example.demo_jfp.streams;

import java.util.List;
import java.util.function.Function;
import java.util.function.IntConsumer;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

import com.example.demo_jfp.entities.Person.Gender;
import com.example.demo_jfp.entities.Person.Person;

public class DemoStreams {
	
	public static void main(String[] args) {
		
		// DemoStreams 01
		System.out.println("\n\n// DemoStreams 01");
		List<Person> people = List.of(
				  new Person("John", Gender.MALE)
				, new Person("Maria", Gender.FEMALE)
				, new Person("Ashia", Gender.FEMALE)
				, new Person("Alex", Gender.MALE)
				, new Person("Alice", Gender.NOT_SPECIFIED)
		);
		
		System.out.println("\nPrinting gender List from People");
		people.stream().map(person -> person.getGender())
						.collect(Collectors.toList())
						.forEach(gender -> System.out.println(gender));
		
		System.out.println("\nPrinting gender Set from People");
		people.stream().map(person -> person.getGender())
						.collect(Collectors.toSet())
						.forEach(gender -> System.out.println(gender));
		
		System.out.println("\nPrinting name Set from People");
		people.stream().map(person -> person.getName())
						.collect(Collectors.toSet())
						.forEach(name -> System.out.println(name));
		
		System.out.println("\nPrinting length name Set from People");
		people.stream().map(person -> person.getName())
						.mapToInt(length -> length.length())
						.forEach(length -> System.out.println(length));
		
		System.out.println("\nExtracting parameters as local variables we get this....(functions) ");
		Function<Person, String> mapper = person -> person.getName();
		ToIntFunction<String> mapper2 = length -> length.length();
		IntConsumer action = length -> System.out.println(length);
		people.stream().map(mapper)
						.mapToInt(mapper2)
						.forEach(action);
		
		// DemoStreams approach 02
		System.out.println("\n\n// DemoStreams 02");
		Predicate<Person> predicate = person -> Gender.FEMALE.equals(person.getGender());
		System.out.println("allMatch: " + people.stream().allMatch(predicate));
		System.out.println("anyMatch 01: " + people.stream().anyMatch(predicate));
		
		List<Person> people2 = List.of(
				  new Person("John", Gender.MALE)
				, new Person("Alex", Gender.MALE)
				, new Person("Alice", Gender.NOT_SPECIFIED)
		);
		System.out.println("anyMatch 02: " + people2.stream().anyMatch(predicate));
		
		List<Person> people3 = List.of(
				  new Person("John", Gender.MALE)
				, new Person("Maria", Gender.FEMALE)
				, new Person("Ashia", Gender.FEMALE)
				, new Person("Alex", Gender.MALE)
				, new Person("Alice", Gender.FEMALE)
		);
		Predicate<Person> predicate2 = person -> Gender.NOT_SPECIFIED.equals(person.getGender());
		System.out.println("nonMatch: " + people3.stream().noneMatch(predicate2));
		System.out.println("nonMatch: " + people.stream().noneMatch(predicate2));
							
	}

}
