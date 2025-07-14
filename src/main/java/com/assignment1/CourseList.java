package com.assignment1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class CourseList {

	private Map<Integer, Course> courses = new HashMap<>();
	private static int idCounter = 1;
	
	
	public List<Course> getAllCourses() {
		return new ArrayList<>(courses.values());
	}
	
	public Course getCourseByID(int id) {
		return courses.get(id);
	}
	
	public void addCourse(Course course) {
		course.setId(idCounter++);
		courses.put(course.getId(), course);
	}
	
	public boolean updateCourse(int id, Course updateCourse) {
		if(!courses.containsKey(id)) return false;
		courses.put(id, updateCourse);
		return true;
	}
	
	public boolean deleteCourse(int id) {
		return courses.remove(id) != null;
	}
}
