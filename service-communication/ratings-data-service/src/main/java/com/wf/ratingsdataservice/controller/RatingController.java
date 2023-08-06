package com.wf.ratingsdataservice.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.ratingsdataservice.model.Rating;
import com.wf.ratingsdataservice.model.UserRating;

@RestController
@RequestMapping("/api/v1/ratings/")
public class RatingController {
	
	//take a user Id and return list of ratings given by the user. 
	@RequestMapping("/user/{userId}")
	public UserRating getRatingsForUser(@PathVariable String userId) {
		List<Rating> ratings = Arrays.asList(new Rating(123, 3), new Rating(124, 4));
		UserRating userRating = new UserRating(ratings);
		return userRating;
	}
	
}
 