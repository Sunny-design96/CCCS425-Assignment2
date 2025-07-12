package com.assignment1;

public class StudentList {
	private static Students students;
	
	public Students getStudents() {
		if(students == null) {
			return students = new Students();
		}
		return students;
	}
}
