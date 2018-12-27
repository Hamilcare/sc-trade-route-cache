package com.barnab2.cache.sccache.service;

import java.io.IOException;
import java.net.MalformedURLException;

public interface RequestService {

	public String getObjects();
	
	public void initObjects() throws MalformedURLException, IOException;
	public String lookForTrips (String query);
}
