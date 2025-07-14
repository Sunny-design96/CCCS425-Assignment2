package com.assignment1;

import java.util.List;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.MediaType;


@Path("enrollments")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class EnrollmentService {
	
	private static EnrollmentList enrollmentList = EnrollmentList.getInstance();
	private static StudentList studentList = StudentList.getInstance();
	private static CourseList courseList = CourseList.getInstance();
	
	
	@POST
	public Response enrollStudent(Enrollment enrollment) {
		int studentId = enrollment.getStudentId();
		int courseId = enrollment.getCourseId();
		
		if(studentList.getStudent(studentId) == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Student with ID " + studentId + " not found.").build();
		}
		if(courseList.getCourseByID(courseId) == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Course with ID " + courseId + " not found.").build();
		}
		enrollmentList.addEnrollment(enrollment);
		return Response.status(Response.Status.CREATED)
				.entity(enrollment).build();
	}
	
	@GET
	public Response getEnrollmentByStudent(@QueryParam("studentId") int studentId) {
		if(studentList.getStudent(studentId) == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Student with ID " + studentId + " not found").build();
		}
		List<Enrollment> studentEnrollments = enrollmentList.getEnrollmentsByStudentId(studentId);
		return Response.ok(studentEnrollments).build();
	}

}
