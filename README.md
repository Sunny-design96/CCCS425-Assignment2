API Overview
The purpose of this API is to provide a technical method of managing students, courses, and enrollments. The API is built using the REST framework JAX-RS, and a ConcurrentHashMap is used for in-memory storage of the lists of students and courses maintained by the API. The Jackson framework is used for JSON serialization and deserialization. The API endpoints accept and produce data in JSON format. 

Setup Instructions
Pre-requisites: Java JDK 11+, Apache Tomcat 10+, Apache Maven 3.6+, Postman or curl for testing.
Download the ZIP file for this assignment and extract it.
Open the project from the ZIP file into eclipse. 
In eclipse, configure the project server to be apache tomcat 10.1.36. 
Click on the root of the project and select Run as > Maven Install. This step will generate the WAR file in the target/ directory.
Click on the root of the project and select Run as > Run on server > select Tomcat 10.1.36.
Create the requests in postman for testing. The base URL for the requests is: http://localhost:8080/jersey-assignment/api/.

List of Endpoints + Example Requests
1. Student Management
• GET /students – List all students
• GET /students/{id} – Retrieve a student by ID
• POST /students – Create a new student
• PUT /students/{id} – Update student info
• DELETE /students/{id} – Delete a student
3. Course Management
• GET /courses – List all courses
• GET /courses/{id} – Retrieve a course by ID
• POST /courses – Create a new course
• PUT /courses/{id} – Update course info
• DELETE /courses/{id} – Delete a course
4. Enrollments
• POST /enrollments – Enroll a student in a course (use studentId and courseId)
• GET /enrollments?studentId=# – List courses a student is enrolled in

Names + Roles
This project was completed by Gabriela Vasquez and Lavanna Laass. The Student endpoints and ReadMe were assigned to Lavanna, and Gabriela completed the Course endpoints, Enrollment endpoints, and all test documentation.
