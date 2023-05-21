package com.example.courseservice.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.courseservice.entity.Course;


public interface CourseService {
	
	public Course getCourseById(int id);
	public List<Course> getAllCourses();
	public void createCourse(Course course);
	public void updateCourse(Course course);
	public void deleteCourse(int id);

}
