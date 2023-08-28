package com.wf.ratingsdataservice.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClientException;

import com.wf.ratingsdataservice.exception.IncorrectMovieException;
import com.wf.ratingsdataservice.model.MovieRatingRequest;
import com.wf.ratingsdataservice.model.MovieResponse.MovieIdentifier;
import com.wf.ratingsdataservice.model.Rating;
import com.wf.ratingsdataservice.model.UserRating;
import com.wf.ratingsdataservice.service.RatingService;

@RestController
@RequestMapping("/api/v1/ratings/")
public class RatingController {
	
	private List<Rating> ratings = new ArrayList<>();
	
	@Autowired
	private RatingService ratingService;
	
	//take a user Id and return list of ratings given by the user. 
	@RequestMapping("/user/{userId}")
	public UserRating getRatingsForUser(@PathVariable String userId) {
//		List<Rating> ratings = Arrays.asList(new Rating(123, 3), new Rating(124, 4));
		UserRating userRating = new UserRating(ratings);
		return userRating;
	}
	
	
	@RequestMapping("/addMovieRating")
	public ResponseEntity<MovieIdentifier> addMovieRating(@RequestHeader("Authorization") String auth, @RequestBody MovieRatingRequest movieRatingRequest){
		try {
			String movieName = movieRatingRequest.getMovieName();
			int rating = movieRatingRequest.getRating();
			MovieIdentifier movieIdentifier = ratingService.movieLookup(auth, movieName);
			if(movieIdentifier==null) {
	            throw new IncorrectMovieException("Movie not found");
			}
			ratings.add(new Rating(movieIdentifier.getId(), rating));
			return ResponseEntity.ok(movieIdentifier);
		}
		catch (IncorrectMovieException ime) {
	        return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
	    }
		catch(WebClientException webex) {
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
 