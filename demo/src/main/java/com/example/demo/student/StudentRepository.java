package com.example.demo.student;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

//          select * from student.dbo.student s where s.email = ?
	@Query("select s from Student s where s.email = ?1")
	Optional<Student> findByEmail(String email);
	
}
