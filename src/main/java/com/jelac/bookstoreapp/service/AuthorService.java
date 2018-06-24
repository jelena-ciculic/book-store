package com.jelac.bookstoreapp.service;

import java.util.List;

import com.jelac.bookstoreapp.model.Author;

public interface AuthorService {
	
	List<Author> findAll();
	Author findOne(Long id);
	Author save(Author author);
	void remove(Long id);
	List<Author> findByName(String name);
	

}
