package com.StudentManagementSystem.dao;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentResponseDTO {
	private Long id;
	private String name;
	private int age;
	private String grade;
	private String address;

}
