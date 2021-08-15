package com.example.demo_uit.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		super();
		this.studentRepository = studentRepository;
	}
	
	public List<Student> getAllStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		
		if(student.getId() != null) {
			throw new StudentException("Student ID provided by external provider is not NULL (this field must be null for insert operations)!");
		}
		
		if(student.getEmail() == null) {
			throw new StudentException("Student Email provided by external provider is null!");
		}
		
		if(student.getEmail().length() <= 0) {
			throw new StudentException("Student Email provided by external provider is not valid!");
		}
		
		if(studentRepository.selectExistByEmail(student.getEmail())) {
			throw new StudentException("Student Email already exists in student database!");
		}
		
		if(student.getName() == null) {
			throw new StudentException("Student Name provided by external provider is null!");
		}
		
		if(student.getName().length() <= 0) {
			throw new StudentException("Student Name provided by external provider is not valid!");
		}
				
		studentRepository.save(student);
		
	}

	public void deleteStudent(Long id) {
		
		if(!studentRepository.existsById(id)) {
			throw new StudentException("Student ID provided by external provider does not exist in database!");
		}
		
		studentRepository.deleteById(id);
	}
	
}
