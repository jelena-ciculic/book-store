package com.jelac.bookstoreapp.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.jelac.bookstoreapp.model.Book;

@Repository
public interface BookRepository extends JpaRepository <Book,Long>{
	
	List<Book> findByAuthorId(Long id);
	List<Book> findByPublisherId(Long id);
	
	List<Book>findTop10ByOrderByVotesDesc();
	
	//List<Book>findAllByOrderByVotesDesc();
	
	@Query("SELECT b FROM Book b WHERE b.votes=(SELECT max(b.votes) FROM Book b)")
	List<Book> maxVotes();
	
	@Query("SELECT b FROM Book b WHERE "
									+ "(:title IS NULL OR b.title LIKE :title) AND "
									+ "(:author IS NULL OR (b.author.firstName LIKE :author OR b.author.lastName LIKE :author))"
									+ " AND "
									+ "(:minVotes IS NULL OR b.votes > :minVotes)")
	Page<Book> search(
			@Param("title") String title,
			@Param("author") String author,
			@Param("minVotes") Integer minVotes,
			Pageable page);
					
}
