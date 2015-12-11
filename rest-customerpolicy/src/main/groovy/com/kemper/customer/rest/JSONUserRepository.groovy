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
			
		userpolicy = slurper.parse(userPolicyFile.getInputStream())
		policydetail = slurper.parse(policyDetailFile.getInputStream())
		
	}
	
	/**
	 * Returns list of users from userpolicy
	 */
	def getUsers() {
		return userpolicy.collect { it.userId }
	}
	
	/**
	 * Returns userpolicy by userId
	 * @param userId
	 * @return
	 */
	def getPoliciesByUser(userId) {
		logger.info "getting policies for userId:"+userId
		return userpolicy.find { it.userId.equals(userId) }
		
	}
	
	/**
	 * Returns policydetail by policynumber
	 * @param policyNum
	 * @return
	 */
	def getPolicyDetail(policyNum) {
		return policydetail.find { it.policyNumber.equals(policyNum) }
	}
	
	/**
	 * Adds vehicle to policydetail and returns all vehicles
	 * @param policyNum
	 * @param vehicle
	 * @return
	 */
	def addVehicle(policyNum, vehicle) {
		vehicle.id = UUID.randomUUID().toString()
		logger.info "adding vehicle:"+vehicle+" for policy:"+policyNum
		def output = getPolicyDetail(policyNum).vehicles.plus(vehicle)
		
		logger.info("after add, vehicles:"+JsonOutput.toJson(output))
		getPolicyDetail(policyNum).vehicles = output
		return output
		
	}
	
	/**
	 * Removes vehicle by id and returns all vehicles
	 * @param policyNum
	 * @param vehicleId
	 * @return
	 */
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
