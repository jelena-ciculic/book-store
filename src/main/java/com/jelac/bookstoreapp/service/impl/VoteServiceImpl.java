package com.jelac.bookstoreapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import com.jelac.bookstoreapp.model.Vote;
import com.jelac.bookstoreapp.repository.VoteRepository;
import com.jelac.bookstoreapp.service.VoteService;

@Service
public class VoteServiceImpl implements VoteService{
	
	@Autowired
	private VoteRepository voteRepository;

	@Override
	public List<Vote> findAll() {
		
		return voteRepository.findAll();
	}

	@Override
	public Vote save(Vote vote) {
		
		
		return voteRepository.save(vote);
	}

	@Override
	public List<Vote> findByBokId(Long id) {
		
		return voteRepository.findByBookId(id);
	}



}
