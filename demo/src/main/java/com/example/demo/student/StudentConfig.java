package com.example.demo.student;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {

	@Bean
	CommandLineRunner commandLineRunner(StudentRepository repository) {
		return args -> {
			Student s1 = new Student(1L, "Daniel Gouveia", LocalDate.of(1984, Month.MARCH, 2), "daniel.gouveia@gmail.com");
			Student s2 = new Student("Alex ", LocalDate.of(2004, Month.MARCH, 2), "alex@gmail.com");
			
			repository.saveAll(List.of(s1, s2));
		};	
	}
	
}
