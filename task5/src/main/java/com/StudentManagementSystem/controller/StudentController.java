package com.StudentManagementSystem.controller;


import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.StudentManagementSystem.model.Student;
import com.StudentManagementSystem.service.StudentService;
import com.StudentManagementSystem.service.StudentServiceImpl;

import java.util.List;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentController {
	   
	   @Qualifier
	   private final StudentService studentService = new StudentServiceImpl();

	    @PostMapping
	    public ResponseEntity<Student> addStudent(@RequestBody Student student) {
	        Student savedStudent = studentService.addStudent(student);
	        return ResponseEntity.ok(savedStudent);
	    }

	    @GetMapping
	    public ResponseEntity<List<Student>> getAllStudents() {
	        List<Student> students = studentService.getAllStudents();
	        return ResponseEntity.ok(students);
	    }

	    @GetMapping("/{id}")
	    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
	        Student student = studentService.getStudentById(id);
	        return ResponseEntity.ok(student);
	    }

	    @PutMapping("/{id}")
	    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student student) {
	        Student updatedStudent = studentService.updateStudent(id, student);
	        return ResponseEntity.ok(updatedStudent);
	    }

	    @DeleteMapping("/{id}")
	    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
	        studentService.deleteStudent(id);
	        return ResponseEntity.noContent().build();
	    }
}

