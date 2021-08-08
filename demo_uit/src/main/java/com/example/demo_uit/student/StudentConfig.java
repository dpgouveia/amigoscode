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
			
			Student s1 = new Student("Daniel", "daniel@gmail.com");
			Student s2 = new Student("Kelia", "kelia@gmail.com");
			Student s3 = new Student("João", "joao@gmail.com");
			Student s4 = new Student("Sapeca", null);
			Student s5 = new Student("Diana", null);
			Student s6 = new Student("Charles", null);
			Student s7 = new Student("Sansa", null);
			
			studentRepository.saveAll(List.of(s1, s2, s3, s4, s5, s6, s7));
			
		};
	}

}

