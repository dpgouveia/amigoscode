package com.example.demo_uit.student;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	
	private final StudentService studentService;
	
	@Autowired	
	public StudentController(StudentService studentService) {
		super();
		this.studentService = studentService;
	}

	@GetMapping(path = "/api/student/")
	public List<Student> getAllStudents() {
		return studentService.getAllStudents();
	}
	
	@PostMapping(path = "/api/student/")
	public void addNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	@DeleteMapping(path = "/api/student/{studentId}")
	public void deleteStudent(@PathVariable(name = "studentId") Long id) {
		studentService.deleteStudent(id);
	}
	

}
