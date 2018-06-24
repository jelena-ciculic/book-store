package com.jelac.bookstoreapp.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Book {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable=false)
	private String title;
	
	@Column(nullable=false)
	private Integer edition;
	
	@ManyToOne(fetch=FetchType.EAGER)
	private Author author;
	
	@Column(nullable=false, unique=true)
	private String ISBN;
	
	private Integer votes;
	
	@ManyToOne(fetch=FetchType.LAZY)
	private Publisher publisher;
	
	@OneToMany(mappedBy="book", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Vote> votesList = new ArrayList<>();
	


	public Book(String title, Integer edition, Author author, String iSBN, Publisher publisher) {
		super();
		this.title = title;
		this.edition = edition;
		this.author = author;
		this.ISBN = iSBN;
		this.votes=0;
		this.publisher = publisher;
	}

	public Book() {
		this.votes=0;
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Integer getEdition() {
		return edition;
	}

	public void setEdition(Integer edition) {
		this.edition = edition;
	}
	
	

	public Author getAuthor() {
		return author;
	}

	public void setAuthor(Author author) {
		
		this.author = author;
		
		if(!author.getBooks().contains(this) && this!=null)
			author.getBooks().add(this);
	}

	public String getISBN() {
		return ISBN;
	}

	public void setISBN(String iSBN) {
		ISBN = iSBN;
	}

	public Integer getVotes() {
		return votes;
	}

	public void setVotes(Integer votes) {
		this.votes = votes;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
		
		if(!publisher.getBooks().contains(this))
			publisher.getBooks().add(this);
	}
	
	

	public List<Vote> getVotesList() {
		return votesList;
	}


	
	public void setVotesList(List<Vote> votesList) {
		this.votesList = votesList;
	}

	public void addVote(Vote vote) {
		
		this.votesList.add(vote);
		
		if(!vote.getBook().equals(this))
			vote.setBook(this);
				
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ISBN == null) ? 0 : ISBN.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (ISBN == null) {
			if (other.ISBN != null)
				return false;
		} else if (!ISBN.equals(other.ISBN))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Book [id=" + id + ", title=" + title + ", edition=" + edition + ", author=" + author + ", ISBN=" + ISBN
				+ ", votes=" + votes + ", publisher=" + publisher + ", votesList=" + votesList + "]";
	}
	
	
	
	
	
	

}
