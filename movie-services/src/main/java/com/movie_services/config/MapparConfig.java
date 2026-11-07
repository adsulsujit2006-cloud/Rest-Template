package com.movie_services.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MapparConfig {
	
	@Bean
	public ModelMapper getBeen() {
		
		return new ModelMapper();
	}

}
