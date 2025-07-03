package test;

import java.util.Optional;

import model.Student;
import service.StudentService;
import service.StudentServiceImpl;

public class StudentCRUDTest {
	
	public static void main(String[] args) {

        StudentService studentService = new StudentServiceImpl();

        // 1️⃣ Get student by ID
        Optional<Student> student = studentService.getStudentById(10);
        if (student.isPresent()) {
            System.out.println("Student fetched: " + student.get());
        } else {
            System.out.println("Student not found with ID 10");
        }

        // 2️⃣ Update student subject
        if (student.isPresent()) {
            Student existingStudent = student.get();
            existingStudent.setSubject("English");
            studentService.updateStudent(existingStudent);
            System.out.println("Student updated: " + existingStudent);
        }

        // 3️⃣ Delete student by ID
        try {
            studentService.deleteStudent(10);
            System.out.println("Student with ID 10 deleted successfully.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        // 4️⃣ Display all students
        System.out.println("\nAll students in DB:");
        studentService.getAllStudents().forEach(System.out::println);
        
    }

}
