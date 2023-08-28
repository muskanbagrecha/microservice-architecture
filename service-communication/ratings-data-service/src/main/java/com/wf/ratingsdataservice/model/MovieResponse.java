package com.wf.ratingsdataservice.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class MovieResponse {
	
	private List<MovieIdentifier> results;

	public List<MovieIdentifier> getResults() {
		return results;
	}

	public void setResults(List<MovieIdentifier> results) {
		this.results = results;
	}

	
	public static class MovieIdentifier{
		private int id;

		public int getId() {
			return id;
		}

		public void setId(int id) {
			this.id = id;
		}

		public MovieIdentifier(int id) {
			super();
			this.id = id;
		}

		public MovieIdentifier() {
			super();
			// TODO Auto-generated constructor stub
		}
		
		
		
	}
	
}
