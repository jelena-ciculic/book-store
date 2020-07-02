package com.jelac.bookstoreapp.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

public class BookDTO {

	private Long id;
	@NotBlank
	private String title;
	@Min(0)
	@Max(9999)
	private Integer edition;

	private AuthorDTO author;
	@Size(max=16)
	private String ISBN;
	
	private Integer votes;

	private PublisherDTO publisher;
	

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

	public AuthorDTO getAuthor() {
		return author;
	}

	public void setAuthor(AuthorDTO author) {
		this.author = author;
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

	public PublisherDTO getPublisher() {
		return publisher;
	}

	public void setPublisher(PublisherDTO publisher) {
		this.publisher = publisher;
	}
	
	

}
