package com.ideas2it.employeeManagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Employee Management Spring Boot application.
 *
 * <p>
 * This is the entry point of the application. It contains the main method
 * which is used to run the Spring Boot application.
 * </p>
 */
@SpringBootApplication
public class EmployeeManagementApplication {

	/**
	 * Main method to run the Employee Management application.
	 */
	public static void main(String[] args) {
		SpringApplication.run(EmployeeManagementApplication.class, args);
		System.out.println("Executing...");
	}

}
