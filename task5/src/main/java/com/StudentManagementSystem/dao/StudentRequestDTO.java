package com.StudentManagementSystem.dao;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class StudentRequestDTO {
	
	@NotBlank(message = "Name must not be empty")
	private String name;
	
	@Min(value = 1, message = "Age must be positive")
	private int age;
	
	@Pattern(regexp = "^[A-D][+-]?$", message = "Grade must be A+, A, B C D etc..")
	private String grade;
	
	private String address;

}
