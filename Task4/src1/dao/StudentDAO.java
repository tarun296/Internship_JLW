package dao;

import java.util.List;

import model.Student;

public interface StudentDAO {

	void addStudent(Student student);
	List<Student> getAllStudents();
	void updateStudent(Student student);
	void deleteStudent(int id);
	Student getStudentById(int id);

}
