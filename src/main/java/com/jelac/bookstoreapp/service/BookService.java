package com.jelac.bookstoreapp.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.jelac.bookstoreapp.model.Book;

public interface BookService {
	
	/**
	 * Returns Page object
	 * @param page is current page 
	 * @return Page
	 */
	Page <Book> findAll(int page);
	/**
	 * Returns a book with specified ID
	 * @param id is ID of book
	 * @return Book if book with such ID exists,
	 * {@code null} if book is not found
	 */
	Book findOne(Long id);
	/**
	 * Persists a book.If book's ID is null, anew ID  will be assigned automatically.
	 * @param book
	 * @return Book state after persisting
	 */
	Book save (Book book);
	
	/**
	 * Deletes book with specified ID
	 * @param id ID of the book to be removed.
	 */
	void remove(Long id);
	
	/**
	 * Returns list of all existing books with specified author's ID
	 * @param id is ID of author
	 * @return List<Book>
	 */
	List<Book> findByAuthorId(Long id);
	/**
	 * Returns list of all existing books with specified publisher's ID
	 * @param id is ID of publisher
	 * @return List<Book>
	 */
	List<Book> findByPublisherId(Long id);
	
	/**
	 * Returns list of books with maximal number of votes
	 * @return List<Book>
	 */
	List<Book> maxVotes();
	
	/**
	 * Returns list of 10 books with highest number of votes
	 * @return List<Book>
	 */
	List<Book> top10List();
	/**
	 * Returns list of books who's title equals string given in the {@code title}parameter,
	 * or who's authors's first name equals string given in the {@code author} parameter,
	 * or who's authors's last name equals string given in the {@code author} parameter,
	 * or  who's votes  is  higher then integer given in the {@code minVotes} parameter,
	 * @param title
	 * @param author
	 * @param minVotes
	 * @param page
	 * @return Page 
	 */
	Page<Book> filter(String title, String author, Integer minVotes, int page);
	

}
