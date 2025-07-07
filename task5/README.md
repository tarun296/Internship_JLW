
Student Management System
-------------------------
This is a simple Spring Boot CRUD application for managing student records. The application uses Spring Data JPA, MySQL database, and RESTful APIs. It allows you to add, view, update, and delete student information.

 Technologies Used
Java 21

Spring Boot 3.5.3

Spring Data JPA

MySQL Database

Spring Boot DevTools

Lombok

Maven

Postman (for API testing)

🗂️ Project Structure
text
Copy
Edit
src/main/java
           └── com.StudentManagementSystem
           |-- controller
           |-- DAO
           |-- service
           |-- model
           |-- repository
           |-- exception
           
src/main/resources
            └── application.properties
              
 
 Setup Instructions
Clone the project:

bash
Copy
Edit
git clone <your-github-repo-url>
Import the project into Eclipse as a Maven project.

Create a database in MySQL:

sql
Copy
Edit
CREATE DATABASE student_management;
Configure the application.properties:

properties
spring.datasource.url=jdbc:mysql://localhost:3306/student_management
spring.datasource.username=your_mysql_username
spring.datasource.password=your_mysql_password
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
Run the project:

Run Studentmanagement3Application.java
📮 API Endpoints
Method	Endpoint	Description
POST	/students	Add a new student
GET	/students	Get all students
GET	/students/{id}	Get student by ID
PUT	/students/{id}	Update student
DELETE	/students/{id}	Delete student

🧪 API Testing (Postman)
Make sure the application is running on http://localhost:8080.

Use the above endpoints to test using Postman.

Content-Type for POST and PUT: application/json

📂 Example JSON for POST/PUT
json
Copy
Edit
{
    "name": "tharun",
    "age": 22,
    "grade": "A",
    "address": "India"
}
✅ Prerequisites
Java 22

Maven

MySQL Database

Postman

Lombok Plugin

✨ Features
Add student

View all students

View student by ID

Update student

Delete student.
