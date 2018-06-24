package com.jelac.bookstoreapp.service;

import java.util.List;

import com.jelac.bookstoreapp.model.Vote;

public interface VoteService {
	
	List<Vote> findAll();
	Vote save(Vote vote);
	List<Vote> findByBokId(Long id);

}
