package com.assignment1;

import java.util.List;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("courses")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class CourseService {

	private static CourseList courseList = new CourseList();
	
	// Get all courses.
	@GET
	public Response getAllCourses() {
		List<Course> courses = courseList.getAllCourses();
		return Response.ok(courses).build();
	}
	
	// Get one course by ID.
	@GET
	@Path("/{id}")
	public Response getCourseByID(@PathParam("id") int id) {
		Course course = courseList.getCourseByID(id);
		if(course == null) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Course not found.").build();
		}
		return Response.ok(course).build();
	}
	
	// Post a new course.
	@POST
	public Response addCourse(Course course) {
		if(course == null || course.getId() <= 0 || course.getName() == null || course.getInstructor() == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Validation error.").build();
		}
		
		courseList.addCourse(course);
		return Response.status(Response.Status.CREATED)
				.entity("Course created.").build();
	}
	
	// Update a course.
	@PUT
	@Path("/{id}")
	public Response updateCourse(@PathParam("id") int id, Course course) {
		if(course == null || course.getId() <= 0 || course.getName() == null || course.getInstructor() == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Validation error.").build();
		}
		
		boolean updated = courseList.updateCourse(id, course);
		if(!updated) {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Course not found").build();
		}
		return Response.ok("Course updated.").build();
	}
	
	// Delete a course.
	@DELETE
	@Path("/{id}")
	public Response deleteCourse(@PathParam("id") int id) {
		boolean deleted = courseList.deleteCourse(id);
		
		if(deleted) {
			return Response.noContent().build();
		}
		return Response.status(Response.Status.NOT_FOUND)
				.entity("Course not found.").build();
	}
	
}
