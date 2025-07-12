package com.assignment1;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

public class Students {
	private ConcurrentMap<Integer, Student> students;
	private AtomicInteger mapKey;
	
	public Students() {
		students = new ConcurrentHashMap<Integer, Student>();
		mapKey = new AtomicInteger();
	}
	
	public ConcurrentMap<Integer, Student> getStudents() {
		return students;
	}
	
	public Student getStudent(int id) {
		return students.get(id);
	}
	
	public int addStudent(Student a) {
		int id = mapKey.incrementAndGet();
		a.setId(id);
		students.put(id, a);
		return id;
	}
	
	public boolean updateStudent(int id, Student student) {
		Student current = students.get(id);
		if(current!=null) {
			current.setEmail(student.getEmail());
			current.setName(student.getName());
			return true;
		}
		return false;
	}
	
	public boolean deleteStudent(int id) {
		Student current = students.get(id);
		if(current != null) {
			students.remove(id);
			return true;
		}
		return false;
	}
	
	public int size() {
		return students.size();
	}
}
