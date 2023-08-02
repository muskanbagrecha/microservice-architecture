package com.wf.ratingsdataservice.model;

import java.util.List;

public class UserRating {

	private List<Rating> userRating;

	public UserRating() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRating(List<Rating> userRatings) {
		super();
		this.userRating = userRatings;
	}

	public List<Rating> getUserRatings() {
		return userRating;
	}

	public void setUserRatings(List<Rating> userRatings) {
		this.userRating = userRatings;
	}
	
	
}
