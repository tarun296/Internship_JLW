package com.StudentManagementSystem.task5;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.StudentManagementSystem.controller"})
public class Task5Application {

	public static void main(String[] args) {
		SpringApplication.run(Task5Application.class, args);
	}

}
