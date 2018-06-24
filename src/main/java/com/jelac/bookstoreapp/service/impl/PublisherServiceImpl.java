
package com.jelac.bookstoreapp.service.impl;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jelac.bookstoreapp.model.Publisher;
import com.jelac.bookstoreapp.repository.PublisherRepository;
import com.jelac.bookstoreapp.service.PublisherService;

@Service
@Transactional
public class PublisherServiceImpl implements PublisherService {
	
	@Autowired
	private PublisherRepository publisherRepo;

	@Override
	public List<Publisher> findAll() {
		
		return publisherRepo.findAll();
	}

	@Override
	public Publisher findOne(Long id) {
		
		
		
		return publisherRepo.getOne(id);
	}

	

	@Override
	public void save(Publisher publisher) {
		
		publisherRepo.save(publisher);
		
	}

	@Override
	public void remove(Long id) {
		
		Publisher pub = publisherRepo.getOne(id);
		if(pub ==null) 
			throw new IllegalArgumentException("Tried to remove non-exsistant publisher!");
		
		publisherRepo.delete(pub);
		
		
	}

	@Override
	public List<Publisher> filter(String name) {
		
		return publisherRepo.search(name);
	}
	
	

}
