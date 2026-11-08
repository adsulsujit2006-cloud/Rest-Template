package com.bookmyshow_services.services;

import java.util.List;

import com.bookmyshow_services.dto.response.MovieResponse;

public interface MovieServices {

    public List getAllMovies();
    
    public String getMovie(Long id);
}