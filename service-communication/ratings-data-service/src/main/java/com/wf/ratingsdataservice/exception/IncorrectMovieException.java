package com.wf.ratingsdataservice.exception;

public class IncorrectMovieException extends RuntimeException {

	public IncorrectMovieException(String message) {
		super("This movie does not exist in our database");
		// TODO Auto-generated constructor stub
	}

}
