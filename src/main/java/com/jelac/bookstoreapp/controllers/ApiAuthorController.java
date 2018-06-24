
package com.jelac.bookstoreapp.controllers;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jelac.bookstoreapp.dto.AuthorDTO;
import com.jelac.bookstoreapp.dto.BookDTO;
import com.jelac.bookstoreapp.model.Author;
import com.jelac.bookstoreapp.model.Book;
import com.jelac.bookstoreapp.service.AuthorService;
import com.jelac.bookstoreapp.service.BookService;
import com.jelac.bookstoreapp.suport.AuthorDTOToAuthor;
import com.jelac.bookstoreapp.suport.AuthorToAuthorDTO;
import com.jelac.bookstoreapp.suport.BookDTOToBook;
import com.jelac.bookstoreapp.suport.BookToBookDTO;

@RestController
@RequestMapping(value="/api/authors")
public class ApiAuthorController {
	
	@Autowired
	private AuthorService authorService;
	
	@Autowired
	private BookService bookService;
	
	@Autowired
	private  AuthorToAuthorDTO authToAuthDTO;
	
	@Autowired
	private BookToBookDTO bookToBookDto;
	
	@Autowired
	private BookDTOToBook bookDtoToBook;
	
	@Autowired
	private AuthorDTOToAuthor authADTOToAuth;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<AuthorDTO>> getAll(
										   @RequestParam(required=false) String name){
		
		List<Author> authors;
		
		if(name !=null)
			authors = authorService.findByName(name);
		else
			authors = authorService.findAll();
		
		if(authors == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(authors.isEmpty())
			 return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
		return new ResponseEntity<List<AuthorDTO>>(authToAuthDTO.convert(authors),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<AuthorDTO> getOne(@PathVariable Long id){
		
		Author author = authorService.findOne(id);
		
		if(author==null)
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		
		return new ResponseEntity<AuthorDTO>(authToAuthDTO.convert(author),HttpStatus.OK);
		
	}
	
	@RequestMapping(method=RequestMethod.POST,consumes="application/json")
	public ResponseEntity<AuthorDTO> addAuthor(
									 @RequestBody AuthorDTO authorDto){
		
		Author author = authADTOToAuth.convert(authorDto);
		Author persisted = authorService.save(author);
		
		return new ResponseEntity<AuthorDTO>(authToAuthDTO.convert(persisted),HttpStatus.CREATED);
	}
	
	
	@RequestMapping(value="/{id}",consumes="application/json",method=RequestMethod.PUT)
	public ResponseEntity<AuthorDTO> updateAuthor(
									 @PathVariable Long id,
									 @RequestBody AuthorDTO authorDTO){
		
		if(id != authorDTO.getId())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Author author = authADTOToAuth.convert(authorDTO);
		Author persisted = authorService.save(author);
		
		return new ResponseEntity<AuthorDTO>(authToAuthDTO.convert(persisted),HttpStatus.OK);
						
	}
	
	@RequestMapping(value="/{id}",method=RequestMethod.DELETE)
	public ResponseEntity<AuthorDTO> deleteAuthor(@PathVariable Long id){
		
		
		authorService.remove(id);
		
		return new ResponseEntity <>(HttpStatus.NO_CONTENT);
	}
	
	
	@RequestMapping(value="/{author_id}/books", method=RequestMethod.GET)
	public ResponseEntity<List<BookDTO>> getAllBooksByAuthor(
										 @PathVariable Long author_id){
		
		List<Book> books = bookService.findByAuthorId(author_id);
		
		if(books==null)
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(books.isEmpty())
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
		
		return new ResponseEntity<List<BookDTO>>(bookToBookDto.convert(books), HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{authorId}/new_book",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO newBook, @PathVariable Long authorId){
		
		Author author = authorService.findOne(authorId);
		
		if(author ==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
			AuthorDTO authDto = authToAuthDTO.convert(author);
			newBook.setAuthor(authDto);
			Book book = bookDtoToBook.convert(newBook);
			book.setVotes(0);
			author.getBooks().add(book);
			author.getBooks().add(book);
			authorService.save(author);
			Book persisted = bookService.save(book);
		
	
           return new ResponseEntity<BookDTO>(bookToBookDto.convert(persisted), HttpStatus.CREATED);	
						
	}
	

	

}
