package com.example.demo_uit.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

// Annotation to auto set AutoCloseable for this test class (no manual setup needed)
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

	// Since StudentRepository was tested successfully (isolated) we dont need
	// to autowire it on our student service test class because it needs to access
	// the database (h2 or whatever db we setup in our solution)
	// So.... to test the service we will use a "mock" implementation of
	// StudentRepository
	@Mock
	private StudentRepository studentRepository;
	private StudentService underTest;

	@BeforeEach
	void setUp() {
		underTest = new StudentService(studentRepository);
	}

	// Manual setup AutoCloseable mock
//	private AutoCloseable ac;
//	
//	// Run tasks before test
//	@BeforeEach
//	void setUp() {
//		ac = MockitoAnnotations.openMocks(this);
//		underTest = new StudentService(studentRepository);
//	}
//	
//	@AfterEach
//	void tearDown() {
//		try {
//			ac.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	@Test
	void testGetAllStudents() {
		// Dado que (no pre conditions for unit test)

		// Quando
		underTest.getAllStudents();

		// Entao (cenario ok)
		verify(studentRepository).findAll();

		// Entao (cenario erro)
//		verify(studentRepository).deleteAll();
	}

	@Test
	// @Disabled // anotacao usada para nao executar o teste
	void testAddNewStudent() {
		// Dado que...
		String email = "kelia@gmail.com";
		Student student = new Student("Kelia", email, Gender.FEMALE);
		
		// quando
		underTest.addNewStudent(student);
		
		// entao
		ArgumentCaptor<Student> ac = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(ac.capture());
		
		assertThat(ac.getValue()).isEqualTo(student);
	}
	
	@Test
	void testAddNewStudentWhenEmailExists() {
		// Dado que...
		String email = "kelia@gmail.com";
		Student student = new Student("Kelia", email, Gender.FEMALE);
		BDDMockito.given(studentRepository.selectExistByEmail(student.getEmail())).willReturn(true);
		
		// quando
		// entao
		assertThatThrownBy(() ->underTest.addNewStudent(student))
							.isInstanceOf(StudentException.class)
							.hasMessageContaining("Student Email already exists in student database!");
//							.hasMessageContaining("shit");

		verify(studentRepository, Mockito.never()).save(Mockito.any());
	}

	@Test
	@Disabled
	void testDeleteStudent() {
		fail("Not yet implemented");
	}

}
