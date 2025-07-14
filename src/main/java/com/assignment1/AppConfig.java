package com.assignment1;

import jakarta.ws.rs.ApplicationPath;
import org.glassfish.jersey.jsonb.JsonBindingFeature;
import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath("/api")
public class AppConfig extends ResourceConfig {
    public AppConfig() {
        packages("com.assignment1");
        register(JsonBindingFeature.class);
        register(StudentService.class);
        register(CourseService.class);
        register(EnrollmentService.class);
    }
    
}
