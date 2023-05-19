package com.example.employeeservice.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 
 * @author Shaimaa
 * add the properties prefix as a value 
 * for the @ConfigurationProperties annotation
 */
@ConfigurationProperties("employee")
public class MyProperties {
	
	// binded to employee.name
	private String name;
	
	// binded to employee.title
	private String title;
	
	// binded to employee.address
	private Address address;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public static class Address{
		private String country;
		private String city;
		public String getCountry() {
			return country;
		}
		public void setCountry(String country) {
			this.country = country;
		}
		public String getCity() {
			return city;
		}
		public void setCity(String city) {
			this.city = city;
		}
		
	}

}
