package com.assignment1;

import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.xml.validation.Schema;

import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/submitJSON")
public class StudentService {
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
    @Produces(MediaType.TEXT_PLAIN)
    public String getTest() {
        return "Student API is running!";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) { 
    	try {
    		ObjectMapper mapper = new ObjectMapper();
    		String jsonStr = mapper.writeValueAsString(student);
    		JSONObject jsonObject = new JSONObject(jsonStr);

            schema.validate(jsonObject);
            Map<String, String> map = new HashMap<>();
            map.put("status", "success");
            map.put("message", "JSON data received and validated");
            return Response.ok(map, MediaType.APPLICATION_JSON).build();
    	}catch(Exception e) {
    		 return Response.status(Response.Status.BAD_REQUEST)
    	                .entity("Missing name or email.").build();
    	}
     
        
    }
}
