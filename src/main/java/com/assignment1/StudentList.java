package com.assignment1;

import java.util.HashMap;
import java.util.Map;

public class StudentList {
	
	private static Students students;
	private static StudentList instance = new StudentList();
	private Map<Integer, Student> studentMap = new HashMap<>();
	
	
	private StudentList() {}
	
	
	public static StudentList getInstance() {
		return instance;
	}
	
	public Student getStudent(int id) {
		Student s = studentMap.get(id);
		if(s != null) return s;
		
		for(Student student : getStudents().getStudents().values()) {
			if(student.getId() == id) {
				studentMap.put(id, student);
				return student;
			}
		}
		return null;
	}
	
	
	public Students getStudents() {
		if(students == null) {
			return students = new Students();
		}
		return students;
	}
	
	public void addStudent(Student student) {
		studentMap.put(student.getId(), student);
	}
	
	public void deleteStudent(int id) {
		studentMap.remove(id);
	}
	
}
