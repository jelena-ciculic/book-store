package com.jelac.bookstoreapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jelac.bookstoreapp.model.Author;
import com.jelac.bookstoreapp.repository.AuthorRepository;
import com.jelac.bookstoreapp.service.AuthorService;

@Service
@Transactional
public class AuthorServiceImpl implements AuthorService{
	
	@Autowired
	private AuthorRepository authorRepository;

	@Override
	public List<Author> findAll() {
		
		return authorRepository.findAll();
	}

	@Override
	public Author findOne(Long id) {
		
		return authorRepository.getOne(id);
	}

	@Override
	public Author save(Author author) {
		
		return authorRepository.save(author);
		
		
	}

	@Override
	public void remove(Long id) {
		
		Author author = authorRepository.getOne(id);
		
		if(author == null || id == null)
			throw new IllegalArgumentException("Tried to remove non-exsistant author!");
		
		authorRepository.delete(author);
		
	}

	@Override
	public List<Author>findByName(String name) {
		
		return authorRepository.findByName(name);
	}

}
