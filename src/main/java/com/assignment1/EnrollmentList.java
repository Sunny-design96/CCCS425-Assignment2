package com.assignment1;

import java.util.ArrayList;
import java.util.List;

public class EnrollmentList {
	
	private static EnrollmentList instance = new EnrollmentList();
	private List<Enrollment> enrollments = new ArrayList<>();
	
	private EnrollmentList() {}
	
	public static EnrollmentList getInstance() {
		return instance;
	}
	
	public void addEnrollment(Enrollment enrollment) {
		enrollments.add(enrollment);
	}
	
	public List<Enrollment> getAllEnrollments() {
		return new ArrayList<>(enrollments);
	}
	
	public List<Enrollment> getEnrollmentsByStudentId(int studentId) {
		List<Enrollment> studentEnrollments = new ArrayList<>();
		for(Enrollment enrollment : enrollments) {
			if(enrollment.getStudentId() == studentId) {
				studentEnrollments.add(enrollment);
			}
		}
		return studentEnrollments;
	}

}
