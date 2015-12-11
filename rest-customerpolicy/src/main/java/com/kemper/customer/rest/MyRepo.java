package com.kemper.customer.rest;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

//@Component
class MyRepo {
	private static final Logger logger = LoggerFactory.getLogger(MyRepo.class);
 
	@Autowired
	ResourceUtil util;
	
//	@PostConstruct
//	void init() {
//		
//		try {
//			util.getUserPolicyFile().getFile();
//			util.getPolicyDetailFile().getFile();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
}
