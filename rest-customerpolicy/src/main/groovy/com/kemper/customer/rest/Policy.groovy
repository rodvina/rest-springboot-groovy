package com.kemper.customer.rest

import java.io.Serializable;

public class Policy implements Serializable {

	String id
	String policyNumber
	String lob
	BigDecimal balanceDue
	Date dueDate
	BigDecimal lastBillAmt
	List<Vehicle> vehicles
	List<Driver> drivers

}
