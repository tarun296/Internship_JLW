package test;

import java.util.Optional;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import model.Student;
import service.StudentService;
import service.StudentServiceImpl;

public class StudentServiceTest {
	
	 private StudentService studentService;

	    @Before
	    public void setUp() {
	        studentService = new StudentServiceImpl();
	    }

	    @Test
	    public void testAddStudent() {
	        Student student = new Student(10, "Tharun", "Tharun@gmail.com", "Kannada");
	        studentService.addStudent(student);

	        Optional<Student> fetchedStudent = studentService.getStudentById(101);
	        assertTrue(fetchedStudent.isPresent());
	        assertEquals("Tharun", fetchedStudent.get().getName());
	    }

	    @Test(expected = IllegalArgumentException.class)
	    public void testAddStudentWithEmptyName() {
	        Student student = new Student(11, "Prasanth", "Prasanth@gmail.com", "English");
	        studentService.addStudent(student);
	    }

	    @Test(expected = Exception.class)
	    public void testDeleteNonExistentStudent() {
	        studentService.deleteStudent(9999);
	    }

}
