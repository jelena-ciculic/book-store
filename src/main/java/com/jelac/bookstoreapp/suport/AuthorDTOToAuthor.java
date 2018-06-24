package com.jelac.bookstoreapp.suport;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jelac.bookstoreapp.dto.AuthorDTO;
import com.jelac.bookstoreapp.model.Author;
import com.jelac.bookstoreapp.service.AuthorService;
import com.jelac.bookstoreapp.service.BookService;

@Component
public class AuthorDTOToAuthor implements Converter<AuthorDTO, Author>{
	
	@Autowired 
	private BookService bookService;
	@Autowired
	private AuthorService authorService;

	@Override
	public Author convert(AuthorDTO authorDto) {
		
		Author author;
		
		if(authorDto.getId()==null) {
			
			author = new Author();
			author.setFirstName(authorDto.getFirstName());
			author.setLastName(authorDto.getLastName());
		
		}else {
			
			author = authorService.findOne(authorDto.getId());
			author.setId(authorDto.getId());
			author.setFirstName(authorDto.getFirstName());
			author.setLastName(authorDto.getLastName());
			author.setBooks(bookService.findByAuthorId(authorDto.getId()));
			
		}
		
		return author;
	}
	
	

}
