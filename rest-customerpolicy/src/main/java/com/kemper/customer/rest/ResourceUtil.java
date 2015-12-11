package com.kemper.customer.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

//@Component
class ResourceUtil {
	private static final Logger logger = LoggerFactory.getLogger(ResourceUtil.class);
 
	 
	@Value("classpath:userpolicy.json")
	private Resource userPolicyFile;
	
	@Value("classpath:policydetail.json")
	private Resource policyDetailFile;

	public Resource getUserPolicyFile() {
		return userPolicyFile;
	}

	public Resource getPolicyDetailFile() {
		return policyDetailFile;
	}

}
