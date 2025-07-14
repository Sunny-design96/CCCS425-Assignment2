package com.assignment1;

public class Enrollment {

	private int studentId;
	private int courseId;
	
	
	public Enrollment() {}
	
	public Enrollment(int studentId, int courseId) {
		this.studentId = studentId;
		this.courseId = courseId;
	}
	
	// Getters
	public int getStudentId() {
		return studentId;
	}
	
	public int getCourseId() {
		return courseId;
	}
	
	
	// Setters
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}
	
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	
}
