package com.example.demo_uit.student;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.BeforeEach;
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
	@Mock private StudentRepository studentRepository;
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
	void testAddNewStudentWhenIdIsNotNull() {
		// Dado que...
		Long id = 1L;
		String name = "Kelia";
		String email = "kelia@gmail.com";
		Gender gender = Gender.FEMALE;
		Student newStudent = new Student(id, name, email, gender);

		// quando
		// entao
		assertThatThrownBy(() -> underTest.addNewStudent(newStudent)).isInstanceOf(StudentException.class)
				.hasMessageContaining("Student ID provided by external provider is not NULL (this field must be null for insert operations)!");

		verify(studentRepository, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	void testAddNewStudentWhenEmailIsNull() {
		// Dado que...
		String name = "Kelia";
		String email = null;
		Gender gender = Gender.FEMALE;
		Student newStudent = new Student(name, email, gender);

		// quando
		// entao
		assertThatThrownBy(() -> underTest.addNewStudent(newStudent)).isInstanceOf(StudentException.class)
				.hasMessageContaining("Student Email provided by external provider is null!");

		verify(studentRepository, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	void testAddNewStudentWhenEmailIsInvalid() {
		// Dado que...
		String name = "Kelia";
		String email = "";
		Gender gender = Gender.FEMALE;
		Student newStudent = new Student(name, email, gender);

		// quando
		// entao
		assertThatThrownBy(() -> underTest.addNewStudent(newStudent)).isInstanceOf(StudentException.class)
				.hasMessageContaining("Student Email provided by external provider is not valid!");

		verify(studentRepository, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	void testAddNewStudentWhenEmailExists() {
		// Dado que...
		String name = "Kelia";
		String email = "kelia@gmail.com";
		Gender gender = Gender.FEMALE;
		Student newStudent = new Student(name, email, gender);
		BDDMockito.given(studentRepository.selectExistByEmail(newStudent.getEmail())).willReturn(true);

		// quando
		// entao
		assertThatThrownBy(() -> underTest.addNewStudent(newStudent)).isInstanceOf(StudentException.class)
				.hasMessageContaining("Student Email already exists in student database!");

		verify(studentRepository, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	void testAddNewStudentWhenNameIsNull() {
		// Dado que...
		String name = null;
		String email = "kelia@gmail.com";
		Gender gender = Gender.FEMALE;
		Student newStudent = new Student(name, email, gender);

		// quando
		// entao
		assertThatThrownBy(() -> underTest.addNewStudent(newStudent)).isInstanceOf(StudentException.class)
				.hasMessageContaining("Student Name provided by external provider is null!");

		verify(studentRepository, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	void testAddNewStudentWhenNameIsInvalid() {
		// Dado que...
		String name = "";
		String email = "kelia@gmail.com";
		Gender gender = Gender.FEMALE;
		Student newStudent = new Student(name, email, gender);

		// quando
		// entao
		assertThatThrownBy(() -> underTest.addNewStudent(newStudent)).isInstanceOf(StudentException.class)
				.hasMessageContaining("Student Name provided by external provider is not valid!");

		verify(studentRepository, Mockito.never()).save(Mockito.any());
	}
	
	@Test
	// @Disabled // anotacao usada para nao executar o teste
	void testAddNewStudent() {
		// Dado que...
		String name = "Kelia";
		String email = "kelia@gmail.com";
		Gender gender = Gender.FEMALE;
		Student newStudent = new Student(name, email, gender);

		// quando
		underTest.addNewStudent(newStudent);

		// entao
		ArgumentCaptor<Student> ac = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(ac.capture());

		assertThat(ac.getValue()).isEqualTo(newStudent);
	}

	@Test
	void testDeleteStudent() {
		// Dado que...
		Long id = 1L;
		BDDMockito.given(studentRepository.existsById(id)).willReturn(true);
		
		// quando
		underTest.deleteStudent(1L);
		
		// entao
		ArgumentCaptor<Long> ac = ArgumentCaptor.forClass(Long.class);
		verify(studentRepository).deleteById(ac.capture());

		assertThat(ac.getValue()).isEqualTo(id);
	}
	
	@Test
	void testDeleteStudentWheIdDoesNotExist() {
		// Dado que...
		Long id = 1L;
		BDDMockito.given(studentRepository.existsById(id)).willReturn(false);
		
		// quando e entao
		assertThatThrownBy(() -> underTest.deleteStudent(id))
			.isInstanceOf(StudentException.class)
			.hasMessageContaining("Student ID provided by external provider does not exist in database!");

		verify(studentRepository, Mockito.never()).deleteById(Mockito.any());
		
	}

}
