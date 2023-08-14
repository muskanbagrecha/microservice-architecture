package com.wf.moviecatalogservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.wf.moviecatalogservice.model.CatalogItem;
import com.wf.moviecatalogservice.model.Movie;
import com.wf.moviecatalogservice.model.Rating;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class MovieInfoService {
	
	@Autowired
	WebClient.Builder webClient;
	
	@CircuitBreaker(name = "catalogService", fallbackMethod = "getFallbackMovieInfo")
	public CatalogItem getMovieInfo(Rating rating) {
		Movie movie = webClient.build()
				.get()
				.uri("http://movie-info-service/api/v1/movies/"+rating.getMovieId())
				.retrieve().bodyToMono(Movie.class)
				.block();
		
		return new CatalogItem(movie.getName(), movie.getDescription(), rating.getRating());
	}
	
	
	public CatalogItem getFallbackMovieInfo(Rating rating, Throwable t) {
		return new CatalogItem("No movie found", "No desc", rating.getRating());
	}
	

}
