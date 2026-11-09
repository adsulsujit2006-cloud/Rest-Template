package com.movie_services.services;

import java.util.List;

import com.movie_services.dto.request.MovieRequest;
import com.movie_services.dto.response.MovieResponse;

public interface MovieServices {

	public MovieRequest registerMovie(MovieRequest movieRequest);
	
	public List<MovieResponse> getAllMovie();
	
	public MovieResponse getMovieById(long id) throws Exception;
	
	public void updateMovie(Long movieId, Long seats);
}
