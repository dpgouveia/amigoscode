package com.example.demo_uit.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class StudentRepositoryTest {

	@Autowired
	private StudentRepository underTest;

	@Test
	void checkIfStudentEmailExists() {
		// Dado que...
		String email = "kelia@gmail.com";
		Student student = new Student(
									"Jamila" 
									, email
									, Gender.FEMALE);
		underTest.save(student);
		
		// Quando...
		boolean expected = underTest.selectExistByEmail(email);
				
		// Entao...
		assertThat(expected).isTrue();
	}

}
