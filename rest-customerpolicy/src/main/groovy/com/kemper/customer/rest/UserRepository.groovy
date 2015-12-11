package com.kemper.customer.rest

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct

import org.springframework.stereotype.Component

@Component
class UserRepository {

	private Map<String, User> userMap
	private Map<String, Policy> policyMap
	
	@PostConstruct
	init() {
		userMap = new HashMap<String, User>()
		policyMap = new HashMap<String, Policy>()
		
		//add user
		userMap.put("rodvina", new User(userId: "rodvina",
								policies: [new Policy(policyNumber: "AB 343",
														lob: "auto",
														balanceDue: 32.33,
														dueDate: new Date().parse("dd.MM.yyy", '18.12.2015'),
														lastBillAmt: 400.33 ),
										   new Policy(policyNumber: "CC 343",
													   lob: "home",
													   balanceDue: 25.43,
													   dueDate: new Date().parse("dd.MM.yyy", '18.12.2015'),
													   lastBillAmt: 778.33 )
										   ])
		)
		
		policyMap.put("AB 343", new Policy(policyNumber: "AB 343", 
//												lob: "auto", 
//												balanceDue: 32.33, 
//												dueDate: new Date().parse("dd.MM.yyy", '18.12.2015'),
//												lastBillAmt: 400.33,
												vehicles: [new Vehicle(id: "v0100", make: "Acura", model: "MDX", year: "2015",  vin: "239j38983983d"),
														   new Vehicle(id: "v0200", make: "BMW", model: "X6", year: "2015",  vin: "644j38934343d")
														  ],
											 	drivers: [new Driver(firstName: "Rodney", lastName: "Odvina", gender: "M", id: "34"),
														 ])
									 )
		policyMap.put("CC 343", new Policy(policyNumber: "CC 343"))
		
	}
	
	User getPoliciesByUser(user) {
		return userMap.get(user);
	}
	
	Policy getPolicyDetail(policyNum) {
		return policyMap.get(policyNum)
	}
	
	void addVehicle(policyNum, Vehicle vehicle) {
		policyMap.get(policyNum).getVehicles().add(vehicle)
	}
	
	void removeVehicle(policyNum, vehicleId) {
		policyMap.get(policyNum).getVehicles().removeIf{it.getId() == vehicleId}
	}
	
	void addDriver(policyNum, Driver driver) {
		policyMap.get(policyNum).getDrivers().add(driver)
	}
	
	void updateDriver(policyNum, Driver driver) {
		policyMap.get(policyNum).getDrivers().removeIf{it.getId() == driver.getId()}
		policyMap.get(policyNum).getDrivers().add(driver)
	}
	
	void removeDriver(policyNum, driverId) {
		policyMap.get(policyNum).getDrivers().removeIf{it.getId() == driverId}
	}
}
