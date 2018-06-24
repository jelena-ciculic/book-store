package com.jelac.bookstoreapp.suport;

import java.util.ArrayList;
import java.util.List;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.jelac.bookstoreapp.dto.AuthorDTO;
import com.jelac.bookstoreapp.model.Author;

@Component
public class AuthorToAuthorDTO implements Converter<Author, AuthorDTO> {

	@Override
	public AuthorDTO convert(Author author) {

		AuthorDTO authorDTO  = new AuthorDTO();
		authorDTO.setId(author.getId());
		authorDTO.setFirstName(author.getFirstName());
		authorDTO.setLastName(author.getLastName());
		return authorDTO;
	}
	
	
	public List<AuthorDTO> convert(List<Author> authors){
		
		List<AuthorDTO> retVal = new ArrayList<>();
		
		for (Author author : authors) {
			retVal.add(convert(author));
		}
		
		return retVal;
	}

}
