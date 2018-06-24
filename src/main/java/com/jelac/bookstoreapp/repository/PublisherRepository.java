package com.jelac.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jelac.bookstoreapp.model.Publisher;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Long> {
	
	@Query("SELECT p FROM Publisher p WHERE (:name IS NULL OR (p.name LIKE :name OR p.address LIKE :name))")
	List<Publisher> search(@Param ("name")String name);

}
