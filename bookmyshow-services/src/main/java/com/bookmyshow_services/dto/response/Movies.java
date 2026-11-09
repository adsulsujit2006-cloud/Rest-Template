package com.bookmyshow_services.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movies {

    private Long movieId;

    private String movieName;

    private String theatreName;

    private String city;

    private Double price;

    private Long availableSeats;

}