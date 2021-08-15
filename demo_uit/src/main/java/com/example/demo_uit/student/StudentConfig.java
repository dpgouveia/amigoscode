package com.example.demo_uit.student;

import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StudentConfig {
	
	@Bean
	public CommandLineRunner commandLineRunner(StudentRepository studentRepository) {
		return args -> {
			
			Student s1 = new Student("Daniel", "daniel@gmail.com", Gender.MALE);
			Student s2 = new Student("Kelia", "kelia@gmail.com", Gender.FEMALE);
			Student s3 = new Student("João", "joao@gmail.com", Gender.MALE);
			Student s4 = new Student("Jonas", "jonas@gmail.com");
			Student s5 = new Student("Joana", "joca@gmail.com");
			studentRepository.saveAll(List.of(s1, s2, s3, s4, s5));
			
		};
	}

}

