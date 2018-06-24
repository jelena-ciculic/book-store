package com.jelac.bookstoreapp.suport;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jelac.bookstoreapp.dto.PublisherDTO;
import com.jelac.bookstoreapp.model.Publisher;
import com.jelac.bookstoreapp.service.BookService;
import com.jelac.bookstoreapp.service.PublisherService;

@Component
public class PublisherDtoToPublisher implements Converter<PublisherDTO, Publisher> {
	
	@Autowired
	private PublisherService pubService;
	@Autowired BookService bookService;

	@Override
	public Publisher convert(PublisherDTO pubDto) {
		
		Publisher publisher;
		
		if(pubDto.getId() == null) {
			publisher = new Publisher();
			publisher.setName(pubDto.getName());
			publisher.setAddress(pubDto.getAddress());
			publisher.setTelephone(pubDto.getTelephone());
		}else {
			publisher = pubService.findOne(pubDto.getId());
			pubDto.setId(pubDto.getId());
			publisher.setName(pubDto.getName());
			publisher.setAddress(pubDto.getAddress());
			publisher.setTelephone(pubDto.getTelephone());
			publisher.setBooks(bookService.findByPublisherId(pubDto.getId()));
			
		}
			
		return publisher;
	}

}
