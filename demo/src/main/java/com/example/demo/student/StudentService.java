package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

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

	public List<Student> getStudents() {
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> studentOptional = studentRepository.findByEmail(student.getEmail());
		if (studentOptional.isPresent()) {
			throw new StudentException("Student email already exists in student database!");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		if (!studentRepository.existsById(id)) {
			throw new StudentException("Student does not exists in student database!");
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long id, String name, String email) {

		if (id == null) {
			throw new StudentException("Student ID provided from external source is null!");
		}

		Optional<Student> studentFindById = studentRepository.findById(id);
		if (studentFindById.isEmpty()) {
			throw new StudentException("Student does not exists in student database!");
		}
		Student student = studentFindById.get();
		
		if (email != null) {
			if (email.length() <= 0) {
				throw new StudentException("Student email provided from external source is invalid!");
			} else {
				Optional<Student> studentOptional = studentRepository.findByEmail(email);
				if (studentOptional.isPresent()) {
					throw new StudentException("Student email provided from external source already exists in student database!");
				} else if (!student.getEmail().equals(email)) {
					student.setEmail(email);
				}
			}
		}

		if (name != null) {
			if (name.length() <= 0) {
				throw new StudentException("Student name provided from external source is invalid!");
			} else if (!student.getName().equals(name)) {
				student.setName(name);
			}
		}

		studentRepository.save(student);
	}
}