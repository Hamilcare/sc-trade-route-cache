package com.barnab2.cache.sccache.controllers;

import java.net.http.HttpRequest;
import java.util.HashMap;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.barnab2.cache.sccache.service.RequestService;


@RestController
@RequestMapping("/middle")
@CrossOrigin
public class RequestController {
	
	@Autowired
	RequestService requestService;
	
	private static final Logger logger = Logger.getLogger(RequestController.class);
	
	
	
	
	@RequestMapping("/greetings")
	public String greeting() {
		return "greetings";
	}
	
	@RequestMapping("/object")
	public String getObject() {
		logger.info("Requesting world objects");
		return requestService.getObjects();
	}
	@CrossOrigin
    @GetMapping("/trip")
	public String getTrips(HttpServletRequest request) {
		String queryParam = request.getQueryString();
		String query = request.getRequestURL().toString() + "?" + request.getQueryString(); 
		logger.info("Requesting trip : "+ query);
		
		return this.requestService.lookForTrips(queryParam);
	}
	


}
