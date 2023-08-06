package com.wf.movieinfoservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.wf.movieinfoservice.config.ApiConfiguration;
import com.wf.movieinfoservice.model.Movie;
import com.wf.movieinfoservice.model.MovieSummary;

@RestController
@RequestMapping("/api/v1/movies")
public class MovieInfoController {

	@Autowired
	private RestTemplate restTemplate;

	@Autowired
	private ApiConfiguration apiConfiguration;
	
	@RequestMapping("/{movieId}")
	public Movie getMovieInfo(@PathVariable int movieId) {
		String req = "https://apis.themoviedb.org/3/movie/"+ movieId + "?api_key=" + apiConfiguration.getApiKey();
		System.out.println(req);
		MovieSummary movieSummary = restTemplate.getForObject(req, MovieSummary.class);
		System.out.println(movieSummary);
		return new Movie(movieSummary.getId(), movieSummary.getTitle(), movieSummary.getOverview());
	}
	
}
