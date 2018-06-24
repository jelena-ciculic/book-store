
package com.jelac.bookstoreapp.service;

import java.util.List;

import com.jelac.bookstoreapp.model.Publisher;

public interface PublisherService {
	
	List<Publisher> findAll();
	Publisher findOne(Long id);
	void save(Publisher publisher);
	void remove(Long id);
	List<Publisher> filter(String anme);

}
