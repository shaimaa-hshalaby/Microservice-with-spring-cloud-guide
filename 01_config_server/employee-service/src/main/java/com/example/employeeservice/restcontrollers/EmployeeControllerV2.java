package com.example.employeeservice.restcontrollers;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.employeeservice.config.MyProperties;
import com.example.employeeservice.config.MyProperties.Address;

@RestController
@RequestMapping("/employee/v2")
public class EmployeeControllerV2 {
	
	private final MyProperties myProp;
	
	public EmployeeControllerV2(MyProperties myProp) {
		this.myProp = myProp;
	}
	
	@GetMapping("/name")
	public String getEmployeeName() {
		return this.myProp.getName();
		
	}
	
	@GetMapping("/title")
	public String getEmployeeTitle() {
		return this.myProp.getTitle();
		
	}
	
	@GetMapping("/address")
	public Address getEmployeeCountry(){
		return this.myProp.getAddress();
		
	}


}
