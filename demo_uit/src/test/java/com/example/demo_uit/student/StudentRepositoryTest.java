package com.example.demo_uit.student;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class StudentRepositoryTest {

	@Autowired
	private StudentRepository underTest;

	// After each test do the following tasks...
	@AfterEach
	void tearDown() {
		underTest.deleteAll();
	}
	
	@Test
	void checkIfStudentEmailExists() {

		// Dado que...
		String email = "kelia@gmail.com";
		Student student = new Student(
									"Kelia" 
									, email
									, Gender.FEMALE);
		underTest.save(student);
		
		// Quando...
		boolean expected = underTest.selectExistByEmail(email);
				
		// Entao...
		assertThat(expected).isTrue();
	}
	
	@Test
	void checkIfStudentEmailDoesNotExists() {

		// Dado que...		
		String email = "kelia@gmail.com";
		
		// Quando...
		boolean expected = underTest.selectExistByEmail(email);
				
		// Entao...
		assertThat(expected).isFalse();
	}

}
