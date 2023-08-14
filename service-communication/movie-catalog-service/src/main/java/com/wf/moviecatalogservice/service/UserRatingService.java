package com.wf.moviecatalogservice.service;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;

import com.wf.moviecatalogservice.model.Rating;
import com.wf.moviecatalogservice.model.UserRating;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

@Service
public class UserRatingService {
	
	@Autowired
	WebClient.Builder webClient;
	
	@CircuitBreaker(name = "catalogService", fallbackMethod = "getFallbackUserRatings")
	public UserRating getUserRating(@PathVariable int userId) {
		UserRating userRating = webClient.build()
				.get()
				.uri("http://ratings-data-service/api/v1/ratings/user/"+userId)
				.retrieve().bodyToMono(UserRating.class)
				.block();
		return userRating;
	}
	
	public UserRating getFallbackUserRatings(int userId, Throwable t) {
		UserRating userRating = new UserRating();
		userRating.setUserRatings(Arrays.asList(new Rating(0, 0)));
		return userRating;
	}

}
