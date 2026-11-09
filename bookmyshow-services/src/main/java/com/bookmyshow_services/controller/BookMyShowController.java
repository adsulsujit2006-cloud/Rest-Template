package com.bookmyshow_services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bookmyshow_services.dto.response.MovieResponse;
import com.bookmyshow_services.modal.BookingDetails;
import com.bookmyshow_services.services.MovieServices;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/bookmyshow")
@Slf4j
public class BookMyShowController {

	@Autowired
	public MovieServices movieServices;

	@GetMapping("/movies")
	public ResponseEntity<List<MovieResponse>> getAllMovies() {
		log.info("REST Request : Get All Movies with third party services ");
		return ResponseEntity.ok(movieServices.getAllMovies());
	}
	
	@GetMapping("/movie/{id}")
	public ResponseEntity<String> getMovie(@PathVariable long id){
		
		return ResponseEntity.ok(movieServices.getMovie(id));
		
	}
	
	@PostMapping("/booking")
	public ResponseEntity<String> bookTicket(@RequestBody BookingDetails bookingDetails){
		
		return ResponseEntity.ok(movieServices.bookMyTicket(bookingDetails));
	}
	
	
	

}