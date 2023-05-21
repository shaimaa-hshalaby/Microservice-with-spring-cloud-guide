package com.example.employeeservice.restcontrollers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	@Value("${employee.name}")
	private String employeeName;
	
	@Value("${employee.title}")
	private String employeeTitle;
	
	@GetMapping("/name")
	public String getEmployeeName() {
		return this.employeeName;
		
	}
	
	@GetMapping("/title")
	public String getEmployeeTitle() {
		return this.employeeTitle;
		
	}


}
