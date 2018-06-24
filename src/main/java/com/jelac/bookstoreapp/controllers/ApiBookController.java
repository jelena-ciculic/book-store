
package com.jelac.bookstoreapp.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jelac.bookstoreapp.dto.BookDTO;
import com.jelac.bookstoreapp.model.Book;
import com.jelac.bookstoreapp.model.Vote;
import com.jelac.bookstoreapp.service.BookService;
import com.jelac.bookstoreapp.suport.BookDTOToBook;
import com.jelac.bookstoreapp.suport.BookToBookDTO;

@RestController
@RequestMapping(value="/api/books")
public class ApiBookController {
	
	@Autowired
	private BookService bookService;
	@Autowired
	private BookToBookDTO bookToBookDTO;
	@Autowired
	private BookDTOToBook bookDtoToBook;
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<BookDTO>> getAll(@RequestParam (required=false) String title,
												@RequestParam(required=false) String author,
												@RequestParam(required=false) Integer minVotes,
												@RequestParam(defaultValue="0") int page){
		
		
		
		
		
		Page <Book> books;
		
		if(title !=null || author !=null || minVotes !=null) {
			
			books = bookService.filter(title, author, minVotes, page);
			
		}else {
			
			books = bookService.findAll(page);
		}
			
		
		if(books==null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		HttpHeaders headers = new HttpHeaders();
		headers.add("totalPages", Integer.toString(books.getTotalPages()));
		
		return new ResponseEntity<List<BookDTO>>(
												bookToBookDTO.convert(books.getContent()),
												headers, HttpStatus.OK);
	}
	
	

	
	@RequestMapping( value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<BookDTO> getOne(@PathVariable Long id) {
		
		Book book = bookService.findOne(id);
		
		if(book == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<BookDTO>(bookToBookDTO.convert(book),HttpStatus.OK);
		
	}
	
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<BookDTO> addBook(@RequestBody @Validated BookDTO book){
		
		Book toSave  = bookDtoToBook.convert(book);
		toSave.setVotes(0);
		
		Book saved = bookService.save(toSave);
		
		return new ResponseEntity<BookDTO>(bookToBookDTO.convert(saved),HttpStatus.CREATED);
		
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<BookDTO> updateBook(
									@PathVariable Long id,
									@RequestBody  @Validated BookDTO book){
		
		
		
		if(id != book.getId())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Book updated = bookDtoToBook.convert(book);
		Book saved = bookService.save(updated);
		
			
		
		return new ResponseEntity<BookDTO>(bookToBookDTO.convert(saved),HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}/vote",method=RequestMethod.PUT, consumes="application/json")
	public ResponseEntity<BookDTO> addVote(
									@PathVariable Long id,
									@RequestBody @Validated BookDTO bookDTO){
		
		
		if( id != bookDTO.getId())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Book book = bookService.findOne(bookDTO.getId());
		
		
		Vote vote = new Vote(book);
		book.addVote(vote);
		book.setVotes(book.getVotesList().size());
		
		Book savedBook = bookService.save(book);
		
		return new ResponseEntity<BookDTO>(bookToBookDTO.convert(savedBook),HttpStatus.OK);
	}
	
	
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<BookDTO> delete(@PathVariable Long id){
		
		bookService.remove(id);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}
	
	
	@RequestMapping(value="/max_votes")
	public ResponseEntity<BookDTO> getMaxVotes(){
		
		List<Book> books =bookService.maxVotes();
		
		if(books == null)
			
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(books.isEmpty())
			
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		
		Book retVal = books.get(0);
		
		return new ResponseEntity<BookDTO>(bookToBookDTO.convert(retVal), HttpStatus.OK);
	}
	
	@RequestMapping(value="/top_10", method=RequestMethod.GET)
	public ResponseEntity<List<BookDTO>> getTop10(){
		
		List<Book> books = bookService.top10List();
		
		if(books == null)
			return  new ResponseEntity<List<BookDTO>>(HttpStatus.NOT_FOUND);
		
		if(books.isEmpty())
			return  new ResponseEntity<List<BookDTO>>(HttpStatus.NO_CONTENT);
		
		
		return new ResponseEntity<List<BookDTO>>(bookToBookDTO.convert(books), HttpStatus.OK);
		
		
		
		
	
	}
	
	
	
	@ExceptionHandler
	public ResponseEntity<Void> validationHandler(
					DataIntegrityViolationException e){
		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	}

}
