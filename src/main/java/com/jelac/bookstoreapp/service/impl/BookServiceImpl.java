package com.jelac.bookstoreapp.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.jelac.bookstoreapp.model.Book;
import com.jelac.bookstoreapp.repository.BookRepository;
import com.jelac.bookstoreapp.service.BookService;

@Service
public class BookServiceImpl  implements BookService {
	
	@Autowired
	private BookRepository bookRepository;
	@Override
	public Page<Book> findAll(int page) {
		
		return bookRepository.findAll(new PageRequest(page, 5));
	}

	@Override
	public Book findOne(Long id) {
	
		return bookRepository.getOne(id);
	}

	@Override
	public Book save(Book book) {
		
		bookRepository.save(book);
		
		
		return book;
	}

	@Override
	public void remove(Long id) {
		
		Book book = bookRepository.getOne(id);
		
		if(book == null)
			throw new IllegalArgumentException("Tried to remove non-exsistant book!");
		
		bookRepository.delete(book);
		
		
		
		
	}

	@Override
	public List<Book> findByAuthorId(Long id) {
		
		return bookRepository.findByAuthorId(id);
	}

	@Override
	public List<Book> findByPublisherId(Long id) {
	
		return bookRepository.findByPublisherId(id);
	}

	/*
	@Override
	public List<Book> findByMaxVotes() {
		
		return bookRepository.findAllByOrderByVotesDesc();
	}
*/
	@Override
	public List<Book> maxVotes() {
		
		return bookRepository.maxVotes();
	}

	@Override
	public Page<Book> filter(String title, String author, Integer minVotes, int page) {
		
		return bookRepository.search(title, author, minVotes, new PageRequest(page, 5));
	}

	@Override
	public List<Book> top10List() {
		
		return bookRepository.findTop10ByOrderByVotesDesc();
	}

}
