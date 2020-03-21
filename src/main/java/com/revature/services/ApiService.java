package com.revature.services;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class ApiService {
	
	public JsonNode getFromApi(String url) {
		RestTemplate restTemplate = new RestTemplate();
		
		ResponseEntity<String> response
		  = restTemplate.getForEntity(url, String.class);
		assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
		
		ObjectMapper mapper = new ObjectMapper();
		JsonNode root = null;
		try {
			root = mapper.readTree(response.getBody());			
		} 
		catch (JsonProcessingException e) {e.printStackTrace();} 
		catch (IOException e) {e.printStackTrace();}
		
		return root;
	}
	
	public Map<String, String> searchWikipedia(String query, int limit, int offset) {
		
		Map<String, String> resources = new LinkedHashMap<>();
		
		// Encode the query for the URL
		String encodedQuery = "";
		try {
			encodedQuery = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// Fetch from the Wikipedia API
		// Pagination params: srlimit, sroffset
		JsonNode results = getFromApi("https://en.wikipedia.org/w/api.php?action=query&list=search&srsearch=" + encodedQuery + "&format=json&srlimit=" + limit + "&sroffset=" + offset);
		
		// Navigate through the JSON
		results = results.path("query").path("search");
		
		// Loop through the results
		for (int i=0;i<results.size();i++) {
			
			String pageTitle = results.get(i).path("title").asText();
			
			resources.put(pageTitle, "https://en.wikipedia.org/wiki/"+pageTitle);
		}

		return resources;
	}
	
	public Map<String, String> searchGoogleBooks(String query, int limit, int offset) {
		
		Map<String, String> resources = new LinkedHashMap<>();
		
		// Encode the query for the URL
		String encodedQuery = "";
		try {
			encodedQuery = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// Fetch from the Google Books API
		// Pagination params: maxResults, startIndex
		JsonNode results = getFromApi("https://www.googleapis.com/books/v1/volumes?q=" + encodedQuery + "&maxResults=" + limit + "&startIndex=" + offset);
		
		// Navigate through the JSON
		results = results.path("items");
		
		// Loop through the results
		for (int i=0;i<results.size();i++) {
			
			String resTitle = results.get(i).path("volumeInfo").path("title").asText();
			String url = results.get(i).path("volumeInfo").path("infoLink").asText();
			
			resources.put(resTitle, url);
		}

		return resources;
	}
	
	// TODO: Add youtube implementation. Might require a google API Key
	public Map<String, String> searchYoutube(String query) {
		
		Map<String, String> resources = new LinkedHashMap<>();
		
		// Encode the query for the URL
		String encodedQuery = "";
		try {
			encodedQuery = URLEncoder.encode(query, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}
		
		// Fetch from the Google Books API
		// Pagination params: maxResults, startIndex
		JsonNode results = getFromApi("https://www.googleapis.com/youtube/v3/search?q=" + encodedQuery);
		
		// Navigate through the JSON
		results = results.path("items");
		
		// Loop through the results
		for (int i=0;i<results.size();i++) {
			
			String resTitle = results.get(i).path("volumeInfo").path("title").asText();
			String url = results.get(i).path("volumeInfo").path("infoLink").asText();
			
			resources.put(resTitle, url);
		}
		
		System.out.println(resources);

		return resources;
	}
	

}
