package com.barnab2.cache.sccache.service.impl;

import com.barnab2.cache.sccache.controllers.RequestController;
import com.barnab2.cache.sccache.service.RequestService;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class RequestServiceImpl implements RequestService{
	
	private static final Logger logger = Logger.getLogger(RequestServiceImpl.class);
	
	@Value("${com.sc.back.url}")
	private String backendURL;
	
	public static String worldObjectsJson;
	
	HashMap<String, String> cachedQueries = new HashMap<>(10);
	
	public String getObjects() {
		return worldObjectsJson;
	}
	
	public void initObjects() throws IOException {
		logger.info("Getting world objects");
		RestTemplate restTemplate = new RestTemplate();
		String worldObjects = restTemplate.getForObject(this.backendURL+"/object", String.class);
		worldObjectsJson = worldObjects;
		logger.info("world objects stored :" + worldObjectsJson);
	}
	
	@Override
	public String lookForTrips(String query) {
		String response = cachedQueries.get(query);
		if(response != null) {
			logger.info("query was in cache ! "+ query);
			return response;
		} else {
			logger.info("cache miss : "+ query);
			RestTemplate restTemplate = new RestTemplate();
			String queryResponse = restTemplate.getForObject(this.backendURL+"/trip?"+query, String.class);
			logger.info("Caching response of "+ query);
			cachedQueries.put(query, queryResponse);
			return queryResponse;
		}
		
	}
	
}
