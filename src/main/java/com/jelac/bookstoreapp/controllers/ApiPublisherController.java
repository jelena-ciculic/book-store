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

import com.jelac.bookstoreapp.dto.BookDTO;
import com.jelac.bookstoreapp.dto.PublisherDTO;
import com.jelac.bookstoreapp.model.Book;
import com.jelac.bookstoreapp.model.Publisher;
import com.jelac.bookstoreapp.service.BookService;
import com.jelac.bookstoreapp.service.PublisherService;
import com.jelac.bookstoreapp.suport.BookDTOToBook;
import com.jelac.bookstoreapp.suport.BookToBookDTO;
import com.jelac.bookstoreapp.suport.PublisherDtoToPublisher;
import com.jelac.bookstoreapp.suport.PublisherToPublisherDTO;

@RestController
@RequestMapping(value="/api/publishers")
public class ApiPublisherController {
	
	@Autowired
	private PublisherService publisherService;
	
	@Autowired 
	private BookService bookService;
	
	@Autowired
	private PublisherToPublisherDTO pubToPubDto;
	
	@Autowired
	private BookToBookDTO bookToBookDto;
	
	@Autowired
	private PublisherDtoToPublisher pubDTOTopub;
	
	@Autowired
	private BookDTOToBook bookDtoToBook;
	
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<PublisherDTO>> getAll(@RequestParam(required=false) String name){
		
		
		
		List<Publisher> publishers;
		if(name != null)
			publishers = publisherService.filter(name);
		else
			publishers = publisherService.findAll();
		
		if(publishers == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(publishers.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		
				
		
		return new ResponseEntity<List<PublisherDTO>>(pubToPubDto.convert(publishers), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{id}", method= RequestMethod.GET)
	public ResponseEntity<PublisherDTO> getOne(@PathVariable Long id){
		
		Publisher publisher = publisherService.findOne(id);
		
		if(publisher == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		return new ResponseEntity<PublisherDTO>(pubToPubDto.convert(publisher), HttpStatus.OK);
	}
	
	@RequestMapping(method=RequestMethod.POST, consumes="application/json")	
	public ResponseEntity<PublisherDTO> addPublisher(@RequestBody PublisherDTO pubDTO){
		
		Publisher pub = pubDTOTopub.convert(pubDTO);
		publisherService.save(pub);
		
		return new ResponseEntity<>(HttpStatus.CREATED);
	}
	

	@RequestMapping( value="/{id}", method=RequestMethod.PUT, consumes="application/json")	
	public ResponseEntity<PublisherDTO> updatePublisher(@PathVariable Long id,
										@RequestBody PublisherDTO pubDTO){
		
		if(id !=pubDTO.getId())
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		
		Publisher pub = pubDTOTopub.convert(pubDTO);
		publisherService.save(pub);
		
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<PublisherDTO> deletePublisher(@PathVariable Long id){
		
		publisherService.remove(id);
		
		return new ResponseEntity<PublisherDTO>(HttpStatus.NO_CONTENT);
	}
	
	
	
	
	@RequestMapping(value="/{publisher_id}/books", method=RequestMethod.GET)
	public ResponseEntity<List<BookDTO>>getAllByPubliser(@PathVariable Long publisher_id){
		
		List<Book> books = bookService.findByPublisherId(publisher_id);
		
		if(books == null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		
		if(books.isEmpty())
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
		return new ResponseEntity<List<BookDTO>>(bookToBookDto.convert(books), HttpStatus.OK);
	}
	
	@RequestMapping(value="/{publisherId}/new_book",method=RequestMethod.POST, consumes="application/json")
	public ResponseEntity<BookDTO> addBook(@RequestBody BookDTO newBook, @PathVariable Long publisherId){
		
		Publisher publisher = publisherService.findOne(publisherId);
		
		if(publisher ==null)
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			
			PublisherDTO pubDTO = pubToPubDto.convert(publisher);
			newBook.setPublisher(pubDTO);
			
			Book book = bookDtoToBook.convert(newBook);
			book.setVotes(0);
			publisher.getBooks().add(book);
			publisherService.save(publisher);
			Book persisted = bookService.save(book);
		
	
           return new ResponseEntity<BookDTO>(bookToBookDto.convert(persisted), HttpStatus.CREATED);	
						
	}
	
	
		

}
