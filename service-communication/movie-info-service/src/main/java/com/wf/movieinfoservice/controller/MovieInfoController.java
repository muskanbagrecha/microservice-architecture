package com.wf.movieinfoservice.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wf.movieinfoservice.model.Movie;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieInfoController {

	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable int movieId) {
		return new Movie(1, "Swadesh", "Best SRK movie");
	}
	
}
