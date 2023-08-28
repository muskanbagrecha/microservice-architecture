package com.wf.ratingsdataservice.service;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientException;
import org.springframework.web.util.UriComponentsBuilder;

import com.wf.ratingsdataservice.model.MovieResponse;
import com.wf.ratingsdataservice.model.MovieResponse.MovieIdentifier;

@Service
public class RatingService {

	@Autowired
	WebClient.Builder webClient;
	
	public MovieIdentifier movieLookup(String auth, String movieName) throws WebClientException {
			MovieResponse movieResponse = null;
			if(StringUtils.isNotBlank(movieName)) {
				String request = UriComponentsBuilder.fromHttpUrl("https://api.themoviedb.org/3/search/movie")
						.queryParam("query", movieName)
						.queryParam("language", "en-US")
						.queryParam("page", 1)
						.toUriString();
				movieResponse = webClient.build()
						.get()
						.uri(request)
						.header("Authorization", auth)
						.retrieve()
						.bodyToMono(MovieResponse.class)
						.block();
				
			}
			if(movieResponse!=null && !movieResponse.getResults().isEmpty()) {
				MovieIdentifier movieIdentifier = movieResponse.getResults().get(0);
				return movieIdentifier;
			}
			return null;
		
		
	}
}
