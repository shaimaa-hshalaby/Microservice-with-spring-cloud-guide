package com.example.courseservice.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.courseservice.entity.Course;
import com.example.courseservice.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {
	
	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course getCourseById(int id) {
		Optional<Course> result= courseRepository.findById(id);
		return result.get();
	}
	
	@Override
	public List<Course> getAllCourses() {
		List<Course> courses = courseRepository.findAll();
		return courses;
	}

	@Override
	public void createCourse(Course course) {
		course.setId(0);
		courseRepository.save(course);

	}

	@Override
	public void updateCourse(Course course) {
		courseRepository.save(course);

	}

	@Override
	public void deleteCourse(int id) {
		courseRepository.deleteById(id);

	}

	

}
