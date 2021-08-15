package com.example.demo_uit.student;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long>{

	/*
	 * Queries em modo nativo comecam a dar problema durante o teste unitario, por causa da referencia ao nome do banco de dados 
	 * e/ou schema durante a utilizacao do banco h2 em memoria (nao consegue achar a referencia pois nao existe la)
	 * 
	 * Neste caso parece ser melhor usar o JPQL (ou ajustar a query nativa)
	 * 
	 */
	
	//@Query(nativeQuery = true, value = "" 
	//								+ "select case when exists (select * from student_uit.dbo.student s where s.email = ?) then cast(1 as bit) else cast(0 as bit) end")
	@Query(nativeQuery = false, value = "" 
									+ "select (case when count(s) > 0 then true else false end) from Student s where s.email = ?1")
	public boolean selectExistByEmail(String email);
	
}
