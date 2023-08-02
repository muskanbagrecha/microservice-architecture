package com.wf.moviecatalogservice.controller;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.wf.moviecatalogservice.model.CatalogItem;
import com.wf.moviecatalogservice.model.Movie;
import com.wf.moviecatalogservice.model.UserRating;

@RestController
@RequestMapping("/api/v1/catalog")
public class MovieCatalogController {
 
	@Autowired
	RestTemplate restTemplate;
	
	@Autowired
	WebClient.Builder webClientBuilder;
		
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable int userId) {
		
		UserRating ratings = webClientBuilder.build().get().uri("http://ratings-data-service/api/v1/ratings/user/"+userId).retrieve().bodyToMono(UserRating.class).block();
		
		
		return ratings.getUserRatings().stream().map(rating -> {
			
//			Using synchronous rest template
//			Movie movie = restTemplate.getForObject("http://localhost:8082/api/v1/movies/" + rating.getMovieId(), Movie.class);
			
//		Using Async WebClient builder
			Movie movie = webClientBuilder.build().get().uri("http://movie-info-service/api/v1/movies/"+rating.getMovieId()).retrieve().bodyToMono(Movie.class).block();
			return new CatalogItem(movie.getDescription(), movie.getName(), rating.getRating());
		}).collect(Collectors.toList());

	}
}
