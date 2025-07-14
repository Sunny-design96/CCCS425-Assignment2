package com.assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CourseList {
	
	private static Courses courses;
	private static CourseList instance = new CourseList();
	private Map<Integer, Course> courseMap = new HashMap<>();
	
	
	private CourseList( ) {}
	
	public static CourseList getInstance() {
		return instance;
	}
	
	public Course getCourseByID(int id) {
		return courseMap.get(id);
	}
	
	public Courses getAllCourses() {
		if(courses == null) {
			courses = new Courses();
		}
		return courses;
	}
	
	public void addCourse(Course course) {
		courseMap.put(course.getId(), course);
		getAllCourses().getCourses().add(course);
	}
	
	public void updateCourse(int id, Course updateCourse) {
		courseMap.put(id,  updateCourse);
	}
	
	public boolean deleteCourse(int id) {
		Course removed = courseMap.remove(id);
		if(removed != null) {
			getAllCourses().getCourses().removeIf(c -> c.getId() == id);
			return true;
		}
		return false;
	}
}
