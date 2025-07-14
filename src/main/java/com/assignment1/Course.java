package com.assignment1;

public class Course {
	
	private int id;
	private String name;
	private String instructor;
	private int duration;
	
	
	public Course() {}
	

	public Course(int id, String name, String instructor, int duration) {
		this.id = id;
		this.name = name;
		this.instructor = instructor;
		this.duration = duration;
	}
	
	// Getters
	
	public int getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public String getInstructor() {
		return instructor;
	}
	
	public int getDuration() {
		return duration;
	}
	
	// Setters
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public void setInstructor(String instructor) {
		this.instructor = instructor;
	}
	
	public void setDuration(int duration) {
		this.duration = duration;
	}
	
}
