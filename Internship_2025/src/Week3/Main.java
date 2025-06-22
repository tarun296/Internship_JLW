package Week3;

import java.util.Scanner;

public class Main {
	public static void main(String[] arg) {
		Scanner sc = new Scanner(System.in);
		StudentManager sm = new StudentManager();
		sm.loadFormFile("students.dat");
		
		while (true) {
			System.out.println("\n === Student Management System ===");
			System.out.println("1. Add Student");
			System.out.println("2. Remove Student");
			System.out.println("3. Update Student");
			System.out.println("4. Search Student");
			System.out.println("5. Display All Students");
			System.out.println("6. Exit & Save");
			System.out.print("Enter choice: ");
			
			int choice = sc.nextInt();
			sc.nextLine(); 
			
			switch (choice) {
			case 1:
				try {
					System.out.print("Enter ID: ");
					int id = sc.nextInt();
					sc.nextLine();
					if(id <= 0) throw new IllegalArgumentException("ID must be positive value.");
					
					System.out.print("Enter Name: ");
					String name = sc.nextLine().trim();
					if(name.isEmpty()) throw new IllegalArgumentException("Name con't be empty.");
					
					System.out.print("Enter Age: ");
					int age = sc.nextInt();
					if(age <= 0) throw new IllegalArgumentException("Age must be positive.");
					
					System.out.print("Enter grade: ");
					String grade = sc.nextLine().trim();
					sc.nextLine();
					
					System.out.print("Enter address: ");
					String address = sc.nextLine().trim();
					if(address.isEmpty()) throw new IllegalArgumentException("Address con't be empty.");
					
					sm.addStudent(new Student(id, name, age, grade, address));
				} catch (Exception e) {
					System.out.println("Invalid input: " + e.getMessage());
					sc.nextLine();
				}
				break;
				
			case 2:
				System.out.print("Enter ID to remove: ");
				int removeId = sc.nextInt();
				sm.removeStudent(removeId);
				break;
				
			case 3:
				System.out.print("Enter ID to update: ");
				int updateId = sc.nextInt();
				sc.nextLine();
				System.out.print("New Name: ");
				String newName = sc.nextLine();
				System.out.print("New Age: ");
				int newAge = sc.nextInt();
				sc.nextLine();
				System.out.print("New Grade: ");
				String newGrade = sc.nextLine();
				sc.nextLine();
				System.out.print("New Address: ");
				String newAddress = sc.nextLine();
				sm.updateStudent(updateId, newName, newAge, newGrade, newAddress);
				break;
				
			case 4:
				System.out.print("Enter ID to search: ");
				int searchId = sc.nextInt();
				Student found = sm.searchStudent(searchId);
				if(found != null)
					System.out.println(found);
				else 
					System.out.println("Student not found.");
				break;
				
			case 5:
				sm.displayAllStudents();
				break;
				
			case 6:
				sm.saveToFile("students.dat");
				System.out.println("Exited System.");
				return;
				
			default:
				System.out.println("Invalid options. Try again...");
			}
		}
		
		
	}

}
