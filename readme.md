## # rest-customerpolicy # ##

### Commands

#### Build and Run
`gradlew build && java -jar build\libs\rest-customerpolicy-1.0.0.jar`


#### Check out service
GET list of users - `http://localhost:8080/users`

		[
		"swilliams0",
		"gjenkins1",
		"ljohnston2",
		"aflores3",
		"rmendoza4",
		"jmills5",
		"mfisher6",
		"jbryant7",
		"pthompson8",
		"dprice9"
		]

REST URIs

* GET `http://localhost:8080/users/{user}/policies`
* GET `http://localhost:8080/users/{user}/policies/{policyNum}`
* POST `http://localhost:8080/users/{user}/policies/{policyNum}/drivers`
* PUT `http://localhost:8080/users/{user}/policies/{policyNum}/drivers/{id}`
* DELETE `http://localhost:8080/users/{user}/policies/{policyNum}/drivers/{id}`
* POST `http://localhost:8080/users/{user}/policies/{policyNum}/vehicles`
* DELETE `http://localhost:8080/users/{user}/policies/{policyNum}/vehicles/{id}`