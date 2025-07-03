package service;

import java.util.List;
import java.util.Optional;

import model.Student;

public interface StudentService {
	  void addStudent(Student student);
	    List<Student> getAllStudents();
	    void updateStudent(Student student);
	    void deleteStudent(int id);
	    Optional<Student> getStudentById(int id);

}
