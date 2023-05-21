package com.example.courseservice.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;


/*
 * @Data is a Lombok library annotations 
 * that is Equivalent to @Getter @Setter 
 * @RequiredArgsConstructor 
 * @ToString @EqualsAndHashCode.  
 */
@Data
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "course_title")
	private String courseTitle;
	
	@Column(name = "course_description")
	private String courseDescription;
	
	@Column(name = "instructor_id")
	private int instructorId;

}
