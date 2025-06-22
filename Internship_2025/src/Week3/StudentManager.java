package Week3;

import java.io.*;
import java.util.*;

public class StudentManager {
	private ArrayList<Student> students;
	private HashMap<Integer, Student> studentMap;
	private TreeSet<Student> sortedStudents;
	
	public StudentManager() {
		students = new ArrayList<>();
		studentMap =  new HashMap<>();
		sortedStudents = new TreeSet<>();
	}
	
	public void addStudent(Student student) {
		if(studentMap.containsKey(student.getId())) {
			System.out.println("Student with this ID already exists.");
			return;
		}
		students.add(student);
		studentMap.put(student.getId(), student);
		sortedStudents.add(student);
		System.out.println("Student added Successfully.");
	}
	
	public void removeStudent(int id) {
		Student student = studentMap.remove(id);
		if(student != null) {
			students.remove(student);
			sortedStudents.remove(student);
			System.out.println("Student removed.");
		} else {
			System.out.println("Student ID not found.");
		}
	}
	
	public void updateStudent(int id, String name, int age, String grade, String address) {
		Student student = studentMap.get(id);
		if(student != null) {
			sortedStudents.remove(student);
			student.setName(name);
			student.setAge(age);
			student.setGrade(grade);
			student.setAddress(address);
			sortedStudents.add(student);
			System.out.println("Student updated.");
		} else {
			System.out.println("Student not found.");
		}
	}
	
	public Student searchStudent(int id) {
		return studentMap.get(id);
	}
	
	public void displayAllStudents() {
		if(sortedStudents.isEmpty()) {
			System.out.println("No Student records found.");
		} else {
			sortedStudents.forEach(System.out::println);
		}
	}
	
	public void loadFormFile(String filename) {
		try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(filename))) {
            students = (ArrayList<Student>) is.readObject();
            studentMap.clear();
            sortedStudents.clear();
            for (Student s : students) {
                studentMap.put(s.getId(), s);
                sortedStudents.add(s);
            }
            System.out.println("Data loaded successfully.");
        } catch (FileNotFoundException e) {
            System.out.println("No previous data found. Starting fresh.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
	}
	
	public void saveToFile(String filename) {
		 try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(filename))) {
	            os.writeObject(students);
	            System.out.println("Data saved successfully.");
	        } catch (IOException e) {
	            System.out.println("Error saving data: " + e.getMessage());
	        }
	} 

}
