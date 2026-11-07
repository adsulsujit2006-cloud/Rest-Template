package com.bookmyshow_services.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MovieResponse {
	  private Long id;
	    private String title;
	    private String description;
	    private String genre;
	    private String director;
	    private Integer releaseYear;
	    private Integer durationMinutes;
	    private Double rating;
	    private Double price;
	    private Integer availableSeats;
}
