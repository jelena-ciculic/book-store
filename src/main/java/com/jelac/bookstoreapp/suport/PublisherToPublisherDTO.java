package com.jelac.bookstoreapp.suport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jelac.bookstoreapp.dto.PublisherDTO;
import com.jelac.bookstoreapp.model.Publisher;

@Component
public class PublisherToPublisherDTO implements Converter<Publisher, PublisherDTO> {

	@Override
	public PublisherDTO convert(Publisher pub) {
		
		
		PublisherDTO pubDTO =  new PublisherDTO();
		
		pubDTO.setId(pub.getId());
		pubDTO.setName(pub.getName());
		pubDTO.setAddress(pub.getAddress());
		pubDTO.setTelephone(pub.getTelephone());
		
		
		return pubDTO;
	}
	
	public List<PublisherDTO> convert(List<Publisher> list){
		
		List<PublisherDTO> retVal = new ArrayList<>();
		
		for (Publisher publisher : list) {
			
			retVal.add(convert(publisher));
			
		}
		
		return retVal;
	}

}
