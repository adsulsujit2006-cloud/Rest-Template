package com.bookmyshow_services.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.bookmyshow_services.exception.IllegalArgumentException;

import com.bookmyshow_services.exception.ResourceNotFoundException;
import com.bookmyshow_services.services.MovieServices;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServicesImpl implements MovieServices{
	String url = "http://localhost:2000/movie/";
	
	@Autowired
	private RestTemplate restTemplate;
	
	

	@Override
	public List getAllMovies() {
		log.info("Check url is null or not :{}"+url);
		if(url==null) {
			throw new ResourceNotFoundException("Enter appropate url :"+url);
		}
		List movie= restTemplate.getForObject(url, List.class);
		
		if(movie==null) {
			log.info("check move for third partiy api is null or not : "+movie);
			throw new ResourceNotFoundException("Movie not found : "+movie);
		}
		
		return movie;
	}



	@Override
	public String getMovie(Long id) {
		if (id == null || id <= 0) {
			log.info("Check valied id or not :{}",id);
            throw new IllegalArgumentException("Invalid movie id: " + id + ". Id must be greater than 0");
        }
		String movie= restTemplate.getForObject(url+id, String.class);
		if(movie==null) {
			log.info("check move for third partiy api is null or not : "+movie);
			throw new ResourceNotFoundException("Movie not found : "+movie);
		}
		
		return movie;
	}

	
}
