package com.jelac.bookstoreapp.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jelac.bookstoreapp.model.Author;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long>{
	
	
	@Query("SELECT a FROM Author a WHERE "
			+ "(:name is NULL OR a.firstName like :name )"
			+ " OR (:name IS NULL OR a.lastName like :name) ")
	List<Author> findByName(@Param ("name" ) String name);
	
	

}
