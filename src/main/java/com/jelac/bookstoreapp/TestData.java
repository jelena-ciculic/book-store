package com.jelac.bookstoreapp;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jelac.bookstoreapp.model.Author;
import com.jelac.bookstoreapp.model.Book;
import com.jelac.bookstoreapp.model.Publisher;
import com.jelac.bookstoreapp.model.Vote;
import com.jelac.bookstoreapp.service.AuthorService;
import com.jelac.bookstoreapp.service.BookService;
import com.jelac.bookstoreapp.service.PublisherService;

@Component
public class TestData {
	
	@Autowired
	private PublisherService publisherService;
	@Autowired 
	private AuthorService authorService;
	@Autowired
	private BookService bookService;
	
	
	@PostConstruct
	public void init() {
		
		Publisher delfi = new Publisher("Delfi", "delfi@gmail.com", "061/456-789");
		publisherService.save(delfi);
		
		Publisher nolit = new Publisher("Nolit", "nolit@gmail.com", "061/222-333");
		publisherService.save(nolit);
		
		Publisher geopoetica = new Publisher("Geopoetica", "geopoetica@gmail.com", "061/222-111");
		publisherService.save(geopoetica);
			
		
		Author philipRoth = new Author("Philip", "Roth");
		authorService.save(philipRoth);
		
		Author oscarWilde = new Author("Oscar", "Wilde");
		authorService.save(oscarWilde);
		
		Author harukiMurakami = new Author("Haruki", "Murakami");
		authorService.save(harukiMurakami);
		
		Author franzKafka = new Author("Franz", "Kafka");
		authorService.save(franzKafka);
		
		Author albertCamus = new Author("Albert", "Kamus");
		authorService.save(albertCamus);
		
		Author janeAustin = new Author("Jane", "Austin");
		authorService.save(janeAustin);
		
		
		Author stephenKing  = new Author("Stephen","King");
		authorService.save(stephenKing);
		
		Author fridrihNice = new Author("Friedrich", "Nietzsche");
		authorService.save(fridrihNice);
		
		
		
		
		Book book1 = new Book("Sputnik Sweetheart", 2013, harukiMurakami, "097856", geopoetica);
		harukiMurakami.addBook(book1);
		geopoetica.addBook(book1);
	
		Vote vote1 = new Vote(book1);
		book1.addVote(vote1);
		book1.setVotes(book1.getVotesList().size());
		
		bookService.save(book1);
		
		Book book2 = new Book("1Q84", 2016, harukiMurakami, "123456", geopoetica);
		harukiMurakami.addBook(book2);
		geopoetica.addBook(book2);
	
		Vote vote2 = new Vote(book2);
		book2.addVote(vote2);
		book2.setVotes(book2.getVotesList().size());
		
		Vote vote3 = new Vote(book2);
		book2.addVote(vote3);
		book2.setVotes(book2.getVotesList().size());
	
		bookService.save(book2);
		
		Book book3 = new Book("Kafka on the Shore", 2013, harukiMurakami, "09785611", geopoetica);
		harukiMurakami.addBook(book3);
		geopoetica.addBook(book3);
		
		bookService.save(book3);
		
		Book book4 = new Book("American Pastoral", 2015, philipRoth, "1978500", nolit);
		philipRoth.addBook(book4);
		philipRoth.addBook(book4);
		
		bookService.save(book4);
		
		Book book5 = new Book("The Human Stain", 2009, philipRoth, "22189760", delfi);
		philipRoth.addBook(book5);
		philipRoth.addBook(book5);
		
		bookService.save(book5);
		
		Book book6 = new Book("The Goust Writer", 2009, philipRoth, "0973652", geopoetica);
		philipRoth.addBook(book6);
		philipRoth.addBook(book6);
		
		bookService.save(book6);
		
		Book book7 = new Book("The Picture of Dorian Gray", 2018, oscarWilde, "14675523", delfi);
		oscarWilde.addBook(book7);
		oscarWilde.addBook(book7);
		
		bookService.save(book7);
		
		Book book8 = new Book("The Trial", 2016, franzKafka, "01133287", delfi);
		franzKafka.addBook(book8);
		franzKafka.addBook(book8);
		
		bookService.save(book8);
		
		Book book9 = new Book("The Castle", 2012, franzKafka, "011334478", nolit);
		franzKafka.addBook(book9);
		franzKafka.addBook(book9);
		
		bookService.save(book9);
		
		Book book10 = new Book("The Stranger", 2006, albertCamus, "55438197", geopoetica);
		albertCamus.addBook(book10);
		albertCamus.addBook(book10);
		
		bookService.save(book10);
		
		Book book11 = new Book("Thus Spoke Zarathustra", 2016, fridrihNice, "557891", nolit);
		fridrihNice.addBook(book11);
		nolit.addBook(book11);
		bookService.save(book11);
		
		Book book12 = new Book("The Will to Power", 2009, fridrihNice, "2445099", nolit);
		fridrihNice.addBook(book12);
		nolit.addBook(book12);
		bookService.save(book12);
		
		Book book13 = new Book("The Antichrist", 20011, fridrihNice, "2445098", delfi);
		fridrihNice.addBook(book13);
		nolit.addBook(book13);
		bookService.save(book13);
		
		
	
	}

}
