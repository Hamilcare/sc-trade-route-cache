package com.barnab2.cache.sccache.init;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import com.barnab2.cache.sccache.service.RequestService;

@Component
public class DataInit implements ApplicationRunner {

	@Autowired
	RequestService requestService;
	
	@Override
	public void run(ApplicationArguments args) throws Exception {
		requestService.initObjects();
	}

}
