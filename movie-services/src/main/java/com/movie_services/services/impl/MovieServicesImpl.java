package com.movie_services.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.movie_services.dto.request.MovieRequest;
import com.movie_services.dto.response.MovieResponse;
import com.movie_services.modal.Movie;
import com.movie_services.repository.MovieRepository;
import com.movie_services.services.MovieServices;

@Service
public class MovieServicesImpl implements MovieServices{

	@Autowired
	private MovieRepository movieRepository;
	
	@Autowired
	private ModelMapper modelMapper;
    @Override
    @Transactional
    public MovieRequest registerMovie(MovieRequest movieRequest) {
        Movie movie = modelMapper.map(movieRequest, Movie.class);
        Movie savedMovie = movieRepository.save(movie);
        return movieRequest;
    }
    @Override
    @Transactional(readOnly = true)
    public List<MovieResponse> getAllMovie() {
        List<Movie> movies = movieRepository.findAll();

        List<MovieResponse> responseList = new ArrayList<>();
        for (Movie movie : movies) {
            MovieResponse response = modelMapper.map(movie, MovieResponse.class);
            responseList.add(response);
        }
        return responseList;
   
	}
	@Override
	public MovieResponse getMovieById(long id) throws Exception {
		  Movie movie = movieRepository.findById(id)
		            .orElseThrow(() -> new Exception("Movie not found with id: " + id));
		    return modelMapper.map(movie, MovieResponse.class);
	}

}
