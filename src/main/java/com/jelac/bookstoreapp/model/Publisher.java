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
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table
public class Publisher {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column
	private String name;
	
	@Column
	private String address;
	
	@Column
	private String telephone;
	
	@OneToMany(mappedBy="publisher", fetch=FetchType.LAZY, cascade=CascadeType.ALL)
	private List<Book> books  = new ArrayList<>();
	
	
	public Publisher() {
		super();
		
	}


	public Publisher(String name, String address, String telephone) {
		super();
		this.name = name;
		this.address = address;
		this.telephone = telephone;
	}
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	
	public List<Book> getBooks() {
		return books;
	}


	public void setBooks(List<Book> books) {
		this.books = books;
	}
	
	public void addBook(Book book) {
		
		if(!this.books.contains(book))
			this.books.add(book);
		
		if(!book.getPublisher().equals(this))
			book.setPublisher(this);
	}
	
	public void removeBook(Book book) {
		
		if(this.books.contains(book)) {
			this.books.remove(book);
		}
		
		/*
		if(book.getPublisher().equals(this)) {
			book.setPublisher(null);
		}
		*/
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
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
		Publisher other = (Publisher) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	
	

}
