package com.example.demo_uit.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	@Query(nativeQuery = true, value = "" + 
			"select case when exists (select * from student_uit.dbo.student s where s.email = ?) then cast(1 as bit) else cast(0 as bit) end")
	public boolean selectExistByEmail(String email);
	
}
