package com.jelac.bookstoreapp.suport;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jelac.bookstoreapp.dto.BookDTO;
import com.jelac.bookstoreapp.model.Book;
import com.jelac.bookstoreapp.service.BookService;
import com.jelac.bookstoreapp.service.VoteService;

@Component
public class BookDTOToBook implements Converter<BookDTO,Book>{
	
	@Autowired
	private AuthorDTOToAuthor authDtoToAuth;
	@Autowired
	private PublisherDtoToPublisher pubDtoToPub;
	@Autowired
	private BookService bookService;
	@Autowired VoteService voteService;

	@Override
	public Book convert(BookDTO bookDto) {
		
		Book book;
		
		if(bookDto.getId() == null) {
			
			book = new Book();
			book.setTitle(bookDto.getTitle());
			book.setEdition(bookDto.getEdition());
			book.setAuthor(authDtoToAuth.convert(bookDto.getAuthor()));
			book.setISBN(bookDto.getISBN());
			book.setVotes(bookDto.getVotes());
			book.setPublisher(pubDtoToPub.convert(bookDto.getPublisher()));
			
			
		}else {
			
			book = bookService.findOne(bookDto.getId());
			book.setId(bookDto.getId());
			book.setTitle(bookDto.getTitle());
			book.setEdition(bookDto.getEdition());
			book.setAuthor(authDtoToAuth.convert(bookDto.getAuthor()));
			book.setISBN(bookDto.getISBN());
			book.setVotes(bookDto.getVotes());
			book.setPublisher(pubDtoToPub.convert(bookDto.getPublisher()));
			book.setVotesList(voteService.findByBokId(bookDto.getId()));
		}
		
		return book;
	}

}
