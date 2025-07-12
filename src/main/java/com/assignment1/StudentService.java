package com.assignment1;

import java.io.InputStream;

import java.util.HashMap;
import java.util.Map;

import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.databind.ObjectMapper;

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

@Path("/students")
public class StudentService {
	StudentList studentList = new StudentList();
	public static org.everit.json.schema.Schema schema = null;
	public StudentService() {
		 try (InputStream schemaStream = StudentService.class.getClassLoader().getResourceAsStream("student_schema.json")) {
			 if (schemaStream == null) {
		            throw new RuntimeException("Could not find student_schema.json in classpath");
		        }
	            JSONObject rawSchema = new JSONObject(new JSONTokener(schemaStream));
	            schema = SchemaLoader.load(rawSchema);
	        } catch (Exception e) {
	            e.printStackTrace();
	            throw new RuntimeException("Failed to load JSON schema", e);
	            
	        }
	}
	
    @GET
    @Path("/getTest")
    @Produces(MediaType.TEXT_PLAIN)
    public String getTest() {
        return "Student API is running!";
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getStudents() {
    	Students students = studentList.getStudents();
    	if(students.size() != 0) {
    		return Response.ok(students).build();
    	}else {
    		Map<String, String> message = new HashMap<>();
    		message.put("message","No students registered.");
    		return Response.ok(message).build();
    	}
    }
    
   @GET
   @Path("/{id}")
   @Produces(MediaType.APPLICATION_JSON)
   public Response getStudent(@PathParam("id") int id) {
	   Students students = studentList.getStudents();
	   if(students.getStudent(id)!=null) {
		   return Response.ok(students.getStudent(id)).build();
	   }else {
		   return Response.status(Response.Status.NOT_FOUND)
				 .entity("Student with ID " + id + " not found")
   				 .build();
	   }
	   
   }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) { 
    	try {
    		//deserialize the student object
    		ObjectMapper mapper = new ObjectMapper();
    		String jsonStr = mapper.writeValueAsString(student);
    		JSONObject jsonObject = new JSONObject(jsonStr);
    		
    		//validate the student object
            schema.validate(jsonObject);
            Map<String, String> map = new HashMap<>();
            map.put("status", "success");
            map.put("message", "JSON data received and validated");
            map.put("student created", student.toString());
            
            //write the student object to the list of students
            Students students = studentList.getStudents();
            students.addStudent(student);
            return Response.ok(map, MediaType.APPLICATION_JSON).build();
    	}catch(Exception e) {
    		 return Response.status(Response.Status.BAD_REQUEST)
    	                .entity("Missing required data, please try again.").build();
    	}
    }
    
    @PUT
	@Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateStudent(@PathParam("id") int id, Student updatedStudent) {
    	try {
    		//deserialize the student object
    		ObjectMapper mapper = new ObjectMapper();
    		String jsonStr = mapper.writeValueAsString(updatedStudent);
    		JSONObject jsonObject = new JSONObject(jsonStr);
    		
    		//validate the student object
            schema.validate(jsonObject);
    	}catch(Exception e) {
   		 return Response.status(Response.Status.BAD_REQUEST)
   	                .entity("Missing required data, please try again.").build();
    	}
    	Students students = studentList.getStudents();
   		boolean updated = students.updateStudent(id, updatedStudent);
		if(updated) {
			return Response.ok(updatedStudent).build();
		}else {
			return Response.status(Response.Status.NOT_FOUND)
					.entity("Student with ID " + id + " not found")
					.build();
		}
	}
    
    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response deleteStudent(@PathParam("id") int id) {
    	Students students = studentList.getStudents();
    	boolean deleted = students.deleteStudent(id);
    	if(deleted) {
    		return Response.ok("Student with ID " + id + " has been deleted").build();
    	}else {
    		return Response.status(Response.Status.NOT_FOUND)
    				.entity("Student with ID " + id + " not found")
    				.build();
    	}
    }
    
}

