package com.wf.moviecatalogservice.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import com.wf.moviecatalogservice.model.CatalogItem;
import com.wf.moviecatalogservice.model.UserRating;
import com.wf.moviecatalogservice.service.MovieInfoService;
import com.wf.moviecatalogservice.service.UserRatingService;

@RestController
@RequestMapping("/api/v1/catalog")
public class MovieCatalogController {
	
	@Autowired
	WebClient.Builder webClient;
	
	@Autowired
	MovieInfoService movieInfoService;
	
	@Autowired
	UserRatingService userRatingService;
	
		
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable int userId) {
		
		UserRating userRating = userRatingService.getUserRating(userId);

		
		return userRating.getUserRatings().stream()
				.map(rating ->  movieInfoService.getMovieInfo(rating))
				.collect(Collectors.toList());

	}



	

	

	

}
