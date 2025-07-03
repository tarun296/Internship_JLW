# Advanced Student Management CRUD System - Java + MySQL

##  Technologies Used
- Java (JDK 22)
- MySQL 8+
- JDBC
- JUnit 4
- Logging (java.util.logging)

##  Project Architecture
- **Model Layer**: Student.java
- **DAO Layer**: Handles Database interactions
- **Service Layer**: Business logic and validations
- **Test Layer**: JUnit test cases
- **Utility Layer**: JDBC connection & Logger

## MySQL Database Setup

1. Start MySQL Workbench.
2. Run the SQL file `studentdb` to create database and tables:

```sql
CREATE DATABASE studentdb;

USE studentdb;

CREATE TABLE students (
  id INT PRIMARY KEY,
  name VARCHAR(100),
  email VARCHAR(100),
  subject VARCHAR(100)
); 

select * from studentdb.students;






