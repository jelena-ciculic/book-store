package com.jelac.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jelac.bookstoreapp.model.Vote;

@Repository
public interface VoteRepository extends JpaRepository<Vote, Long>{
	
	List<Vote> findByBookId(Long id);

}
