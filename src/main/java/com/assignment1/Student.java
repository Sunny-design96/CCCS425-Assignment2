package com.assignment1;

import jakarta.xml.bind.annotation.XmlRootElement;
import jakarta.validation.constraints.*;
import jakarta.json.bind.annotation.JsonbProperty;

@XmlRootElement
public class Student {
	@NotNull
	@JsonbProperty("id")
    private Integer id;
	
	@NotNull
	@JsonbProperty("name")
    private String name;
	
	@NotNull
    @Email
	@JsonbProperty("email")
    private String email;

    public Student() {}

    public Student(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    // Getters & Setters
    public Integer getId() { return id; }
    public void setId( Integer id) { this.id = id; }

    public String getName() { return name; }
    public void setName( String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail( String email) { this.email = email; }

    @Override
    public String toString() {
        return "Student{id=" + id + ", name='" + name + "', email='" + email + "'}";
    }
}
