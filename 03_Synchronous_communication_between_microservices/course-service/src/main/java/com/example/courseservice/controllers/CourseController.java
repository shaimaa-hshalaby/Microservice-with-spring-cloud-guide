package com.example.courseservice.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.courseservice.entity.Course;
import com.example.courseservice.service.CourseService;

@RestController
@RequestMapping("/api/course")
public class CourseController {
	
	@Autowired
	private CourseService courseService;
	
	@GetMapping
	public List<Course> getAllCourses() {
		return courseService.getAllCourses();
	}
	
	@GetMapping("/{id}")
	public Course getCourseById(@PathVariable int id) {
		return courseService.getCourseById(id);
	}
	
	@PostMapping
	public void createCourse(@RequestBody Course course) {
		courseService.createCourse(course);
	}
	
	@PutMapping
	public void updateCourse(@RequestBody Course course) {
		courseService.updateCourse(course);
	}
	
	@DeleteMapping("/{id}")
	public void deleteCourse(@PathVariable int id) {
		courseService.deleteCourse(id);
	}

}
