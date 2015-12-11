package com.kemper.customer.rest;


import java.lang.reflect.Method;

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController

@RestController
public class UserController {
	
	@Autowired
	JSONUserRepository userRepository;

	@RequestMapping("/users")
	def getUsers() {
		return userRepository.getUsers()
	}
	
    @RequestMapping("/users/{userId}/policies")
    def getPoliciesForUser(@PathVariable String userId) {
		return userRepository.getPoliciesByUser(userId)
    }
	
	@RequestMapping("/users/{userId}/policies/{policyNum}")
	def getPolicyDetail(@PathVariable String userId, @PathVariable String policyNum) {
		return userRepository.getPolicyDetail(policyNum)
	}
	
	@RequestMapping(value = "/users/{userId}/policies/{policyNum}/vehicles", method = RequestMethod.POST)
	def addVehicle(@PathVariable String userId, @PathVariable String policyNum, 
				@RequestBody def vehicle) {
		return userRepository.addVehicle(policyNum, vehicle)
	}
				
	@RequestMapping(value = "/users/{userId}/policies/{policyNum}/vehicles/{vehicleId}", method = RequestMethod.DELETE)
	def removeVehicle(@PathVariable String userId, @PathVariable String policyNum,
				@PathVariable def vehicleId) {
		return userRepository.removeVehicle(policyNum, vehicleId)
	}
	
	@RequestMapping(value = "/users/{userId}/policies/{policyNum}/drivers", method = RequestMethod.POST)
	def addDriver(@PathVariable String userId, @PathVariable String policyNum,
				@RequestBody def driver) {
		return userRepository.addDriver(policyNum, driver)
	}
				
	@RequestMapping(value = "/users/{userId}/policies/{policyNum}/drivers/{driverId}",  method = RequestMethod.PUT)
	public Policy updateDriver(@PathVariable String userId, @PathVariable String policyNum, 
								@PathVariable String driverId, @RequestBody def driver) {
		return userRepository.updateDriver(policyNum, driver) 
	}
								
	@RequestMapping(value = "/users/{userId}/policies/{policyNum}/drivers/{driverId}", method = RequestMethod.DELETE)
	def removeDriver(@PathVariable String userId, @PathVariable String policyNum,
				@PathVariable def driverId) {
		return userRepository.removeDriver(policyNum, driverId)
	}
	
}
