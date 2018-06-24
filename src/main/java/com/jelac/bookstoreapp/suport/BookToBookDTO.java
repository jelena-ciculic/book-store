package com.jelac.bookstoreapp.suport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jelac.bookstoreapp.dto.BookDTO;
import com.jelac.bookstoreapp.model.Book;

@Component
public class BookToBookDTO implements Converter<Book, BookDTO>{
	
	@Autowired
	private AuthorToAuthorDTO authToAuthDTO;
	@Autowired
	private PublisherToPublisherDTO pubToPubDTO;
	
	

	@Override
	public BookDTO convert(Book book) {
		
		BookDTO bookDTO = new BookDTO();
		bookDTO.setId(book.getId());
		bookDTO.setTitle(book.getTitle());
		bookDTO.setEdition(book.getEdition());
		bookDTO.setAuthor(authToAuthDTO.convert(book.getAuthor()));
		bookDTO.setISBN(book.getISBN());
		bookDTO.setVotes(book.getVotes());
		bookDTO.setPublisher(pubToPubDTO.convert(book.getPublisher()));
		
		
		return bookDTO;
	}
	
	public List<BookDTO> convert(List<Book> books){
		
		List<BookDTO> retVal = new ArrayList<>();
		
		for (Book book : books) {
			
			retVal.add(convert(book));
			
		}
		
		
		return retVal;
	}

}
