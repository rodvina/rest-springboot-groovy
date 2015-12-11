package com.kemper.customer.rest

import groovy.json.JsonOutput
import groovy.json.JsonSlurper

import javax.annotation.PostConstruct

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ClassPathResource
import org.springframework.core.io.Resource
import org.springframework.stereotype.Component

@Component
class JSONUserRepository {
	private static final Logger logger = LoggerFactory.getLogger(JSONUserRepository.class);
 
	@Value("classpath:userpolicy.json")
	private Resource userPolicyFile;
	
	@Value("classpath:policydetail.json")
	private Resource policyDetailFile;
	
	def userpolicy, policydetail
	
	
	@PostConstruct
	init() {
		
		JsonSlurper slurper = new JsonSlurper()
		//getFile() method will not work when run from executable jar because this method
		//expects the file to reside on the file system, not in jar.  That's why it only
		//works when running from STS, not from executable jar.
		//use getInputStream() instead
//		userpolicy = slurper.parse(userPolicyFile.getFile())
//		policydetail = slurper.parse(policyDetailFile.getFile())
			
		userpolicy = slurper.parse(userPolicyFile.getInputStream())
		policydetail = slurper.parse(policyDetailFile.getInputStream())
		
	}
	
	def getPoliciesByUser(userId) {
		logger.info "getting policies for userId:"+userId
		return userpolicy.find { it.userId.equals(userId) }
		
	}
	
	def getPolicyDetail(policyNum) {
		return policydetail.find { it.policyNumber.equals(policyNum) }
	}
	
	def addVehicle(policyNum, vehicle) {
		vehicle.id = UUID.randomUUID().toString()
		logger.info "adding vehicle:"+vehicle+" for policy:"+policyNum
		def output = getPolicyDetail(policyNum).vehicles.plus(vehicle)
		
		logger.info("after add, vehicles:"+JsonOutput.toJson(output))
		getPolicyDetail(policyNum).vehicles = output
		return output
		
	}
	
	def removeVehicle(policyNum, vehicleId) {
		logger.info "removing vehicleId:"+vehicleId+" for policy:"+policyNum
		return getPolicyDetail(policyNum).vehicles.removeAll { it.id.equals(vehicleId) }
	}
	
	def addDriver(policyNum, def driver) {
		driver.id = UUID.randomUUID().toString()
		logger.info "adding driver:"+driver+" for policy:"+policyNum
		def output = getPolicyDetail(policyNum).drivers.plus(driver)
		
		logger.info("after add, drivers:"+JsonOutput.toJson(driver))
		getPolicyDetail(policyNum).drivers = output
		return output

	}
	
	void updateDriver(policyNum, def driver) {
		def idx = getPolicyDetail(policyNum).drivers.findIndexOf{ it.id.equals(driver.id) }
		getPolicyDetail(policyNum).drivers[idx] = driver

	}
	
	def removeDriver(policyNum, driverId) {
		logger.info "removing driverId:"+driverId+" for policy:"+policyNum
		return getPolicyDetail(policyNum).drivers.removeAll { it.id.equals(driverId) }
	}
}
