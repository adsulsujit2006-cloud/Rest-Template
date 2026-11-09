package com.bookmyshow_services.services;

import java.util.List;

import com.bookmyshow_services.dto.response.MovieResponse;
import com.bookmyshow_services.modal.BookingDetails;

public interface MovieServices {

    public List getAllMovies();
    
    public String getMovie(Long id);
    public String bookMyTicket(BookingDetails bookingDetails);
    
}