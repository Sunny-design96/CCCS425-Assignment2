package com.assignment1;

import java.util.HashMap;
import java.util.Map;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.Response.Status;

@Path("/submitJSON")
public class StudentService {

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getTest() {
        return "Student API is running!";
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createStudent(Student student) { 
        if (student.getName() == null || student.getEmail() == null) {
            return Response.status(Response.Status.BAD_REQUEST)
                .entity("Missing name or email.").build();
        }
        Map<String, String> map = new HashMap<>();
        map.put("status", "success");
        map.put("message", "JSON data received and validated");
        return Response.ok(map, MediaType.APPLICATION_JSON).build();
    }
}
