package com.movie_services.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.movie_services.dto.request.MovieRequest;
import com.movie_services.dto.response.MovieResponse;
import com.movie_services.services.MovieServices;

@RestController
@RequestMapping("/movie")
public class MovieController {

	@Autowired
	private MovieServices movieServices;
	
	@PostMapping("/register")
	public ResponseEntity<MovieRequest> registerMovie(@RequestBody MovieRequest movieRequest){
		
		MovieRequest movie= movieServices.registerMovie(movieRequest);
		return new ResponseEntity<MovieRequest>(movie,HttpStatus.CREATED);
	}
	@GetMapping("/")
	public ResponseEntity<List<MovieResponse>> getAllMovie(){
		List<MovieResponse> movie = movieServices.getAllMovie();
		
		return new ResponseEntity<List<MovieResponse>>(movie,HttpStatus.OK);
	}
	@GetMapping("/{id}")
	public ResponseEntity<MovieResponse> getMovieDetails(@PathVariable long id) throws Exception{
		MovieResponse movie= movieServices.getMovieById(id);
		
		return new ResponseEntity<MovieResponse>(movie,HttpStatus.OK);
	}
	
}
