package com.wf.ratingsdataservice.model;

public class MovieRatingRequest {

	private String movieName;
	private int rating;
	
	public MovieRatingRequest(String movieName, int rating) {
		super();
		this.movieName = movieName;
		this.rating = rating;
	}

	public String getMovieName() {
		return movieName;
	}

	public void setMovieName(String movieName) {
		this.movieName = movieName;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public MovieRatingRequest() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
