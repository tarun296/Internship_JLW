package com.StudentManagementSystem.service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.StudentManagementSystem.model.Student;
import com.StudentManagementSystem.repository.StudentRepository;
import lombok.RequiredArgsConstructor;

@Service("studentService")
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {
	
	    private final StudentRepository studentRepository = null;

	    @Override
	    public Student addStudent(Student student) {
	        return studentRepository.save(student);
	    }

	    @Override
	    public List<Student> getAllStudents() {
	        return studentRepository.findAll();
	    }

	    @Override
	    public Student getStudentById(Long id) {
	        return studentRepository.findById(id)
	                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
	    }

	    @Override
	    public Student updateStudent(Long id, Student student) {
	        Student existingStudent = getStudentById(id);
	        existingStudent.setName(student.getName());
	        existingStudent.setAge(student.getAge());
	        existingStudent.setGrade(student.getGrade());
	        existingStudent.setAddress(student.getAddress());
	        return studentRepository.save(existingStudent);
	    }

	    @Override
	    public void deleteStudent(Long id) {
	        studentRepository.deleteById(id);
	    }

}
