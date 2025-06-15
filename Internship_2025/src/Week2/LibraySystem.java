package Week2;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;

abstract class Member {
	UUID memberID;
	String name, email, phone;
	int maxBooksAllowed;
	List<Book> currentlyIssuedBooks = new ArrayList<>();
	
	Member(String name, String email, String phone){
		this.memberID = UUID.randomUUID();
		this.name = name;
		this.email = email;
		this.phone = phone;
	}
	
	abstract int getMaxAllowedDays();
	abstract String getMemberType();
}

class StudentMember extends Member {
	StudentMember(String name, String email, String phone){
		super(name, email, phone);
		this.maxBooksAllowed = 3;
	}
	
	int getMaxAllowedDays() {return 14; }
	String getMemberType() {return "Student"; }
	
}

class TeacherMember extends Member {

	TeacherMember(String name, String email, String phone) {
		super(name, email, phone);
		this.maxBooksAllowed = 5;
	}
	int getMaxAllowedDays() {return 30; }
	String getMemberType() {return "Teacher"; }
}

class GuestMember extends Member {

	GuestMember(String name, String email, String phone) {
		super(name, email, phone);
		this.maxBooksAllowed = 1;
	}
	int getMaxAllowedDays() {return 7; }
	String getMemberType() {return "Guest"; }
}

class Librarian extends Member {

	Librarian(String name, String email, String phone) {
		super(name, email, phone);
		this.maxBooksAllowed = Integer.MAX_VALUE;
	}
	int getMaxAllowedDays() {return Integer.MAX_VALUE; }
	String getMemberType() {return "Librarian"; }
}

class Book {
	UUID bookID;
	String title;
	String author;
	String genre;
	boolean isIssued;
	Member issuedTo;
	LocalDate dueDate;
	Queue<Member> reservationQueue = new LinkedList<>();
	
	Book(String title, String author, String genre){
		this.bookID = UUID.randomUUID();
		this.title = title;
		this.author = author;
		this.genre = genre;
		this.isIssued = false;
	}
	
	@Override
	public String toString() {
		return String.format("%s by %s [%s] %s",
				title, author, genre, (isIssued ? "(Issued)" : "(Available)"));
	}
}

class Library {
	Map<UUID, Book> catalog = new HashMap<>();
	Map<String, Member> members = new HashMap<>();
	
	void addBook(Book book) {
		catalog.put(book.bookID, book);
		System.out.println("Book added: " + book);
	}
	
	void removeBook(UUID bookID) {
		Book book = catalog.get(bookID);
		if(book == null) {
			System.out.println("Book is not found");
		} else if (book.isIssued) {
			System.out.println("Connot remove an issued book");
		} else {
			catalog.remove(bookID);
			System.out.println("Removed book: " + book);
		}
	}
	
	void registerMember(Member member) {
		if(members.containsKey(member.email) || members.containsKey(member.phone)) {
			System.out.println("Member already registered");
		} else {
			members.put(member.email, member);
			members.put(member.phone, member);
			System.out.printf("%s registered: %s (%s)\n",
					member.getMemberType(), member.name, member.email);
		}
	}
	
	void issueBook(UUID bookID, String contact) {
		Book book = catalog.get(bookID);
		Member member = members.get(contact);
		if(book == null || member == null) {
			System.out.println("Book or member not found");
			return;
		}
		if(book.isIssued) {
			System.out.println("Book is alredy issued");
		} else if (member.currentlyIssuedBooks.size() > member.maxBooksAllowed) {
			System.out.println("Member reached Limit");
		} else {
			book.isIssued =  true;
			book.issuedTo = member;
			book.dueDate = LocalDate.now().plusDays(member.getMaxAllowedDays());
			member.currentlyIssuedBooks.add(book);
			System.out.printf("Issued \"%s\" to %s; due %s\n",
					book.title, member.name, book.dueDate);
		}
	}
	
	void returnBook(UUID bookID, String contact) {
		Book book = catalog.get(bookID);
		Member member = members.get(contact);
		if(book == null || member == null || 
			!book.isIssued || !book.issuedTo.equals(member)) {
			System.out.println("Return not valid");	
			return;
		}
		member.currentlyIssuedBooks.remove(book);
		book.isIssued = false;
		book.issuedTo = null;
		book.dueDate = null;
		System.out.println("Book is returned: " + book.title);
		
        if (!book.reservationQueue.isEmpty()) {
            Member next = book.reservationQueue.poll();
            issueBook(bookID, next.email);
        }
		
	}
	
	 void reserveBook(UUID bookID, String contact) {
	        Book book = catalog.get(bookID);
	        Member member = members.get(contact);
	        if (book == null || member == null) {
	            System.out.println("Book or member not found");
	            return;
	        }
	        if (!book.isIssued) {
	            System.out.println("Book is already available; no need to reserve.");
	        } else {
	            book.reservationQueue.add(member);
	            System.out.printf("%s reserved \"%s\"\n", member.name, book.title);
	        }
	 }
	 
	 void searchBooks(String keyword) {
	        catalog.values().stream()
	            .filter(book -> book.title.contains(keyword) ||
	                         book.author.contains(keyword) ||
	                         book.genre.contains(keyword))
	            .forEach(System.out::println);
	 }
	 
	 void viewIssuedBooks(String contact) {
	        Member member = members.get(contact);
	        if (member == null) {
	            System.out.println("Member not found");
	            return;
	        }
	        if (member.currentlyIssuedBooks.isEmpty()) {
	            System.out.println("No books issued");
	            return;
	        }
	        for (Book book : member.currentlyIssuedBooks) {
	            long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), book.dueDate);
	            System.out.printf("\"%s\" due in %d day(s)\n", book.title, daysLeft);
	        }
	    }

	    void viewOverdues() {
	        catalog.values().stream()
	            .filter(book -> book.isIssued && book.dueDate.isBefore(LocalDate.now()))
	            .forEach(book -> System.out.printf("OVERDUE: \"%s\" (issued to %s)\n",
	                book.title, book.issuedTo.name));
	    }
}


public class LibraySystem {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Library library = new Library();
		
		library.registerMember(new Librarian("Prasanth", "prasanth@mg.com", "1234"));
		
		while (true) {
			System.out.println("\n  Library Menu ");
			System.out.println("1. Add Book  2. Register Member");
            System.out.println("3. Issue Book  4. Return Book");
            System.out.println("5. Reserve Book  6. Search Books");
            System.out.println("7. View Issued  8. View Overdues");
            System.out.println("9. Exit");
            System.out.print("Choice: ");
            int choice = scanner.nextInt(); scanner.nextLine();
            
            switch (choice) { 
            case 1 -> {
            	 System.out.print("Title: ");
                 String title = scanner.nextLine();
                 System.out.print("Author: ");
                 String author = scanner.nextLine();
                 System.out.print("Genre: ");
                 String genre = scanner.nextLine();
                 library.addBook(new Book(title,author,genre));
            }
            
            case 2 -> {
                System.out.print("Type (student/teacher/guest): ");
                String type = scanner.nextLine().toLowerCase();
                System.out.print("Name: ");
                String name = scanner.nextLine();
                System.out.print("Email: ");
                String email = scanner.nextLine();
                System.out.print("Phone: ");
                String phone = scanner.nextLine();
                Member member = switch (type) {
                    case "student" -> new StudentMember(name,email,phone);
                    case "teacher" -> new TeacherMember(name,email,phone);
                    case "guest"   -> new GuestMember(name,email,phone);
                    default -> {
                        System.out.println("Invalid type"); yield null;
                    }
                };
                if (member != null) library.registerMember(member);
            }
            case 3, 4, 5 -> {
                System.out.print("Book ID: ");
                try {
                    UUID bid = UUID.fromString(scanner.nextLine().trim());
                    System.out.print("Your email or phone: ");
                    String contact = scanner.nextLine().trim();
                    if (choice == 3) library.issueBook(bid, contact);
                    else if (choice == 4) library.returnBook(bid, contact);
                    else library.reserveBook(bid, contact);
                } catch (IllegalArgumentException ex) {
                    System.out.println("Wrong Book ID format");
                }
            }
            case 6 -> {
                System.out.print("Keyword: ");
                String keyword = scanner.nextLine();
                library.searchBooks(keyword);
            }
            case 7 -> {
                System.out.print("Your contact (email/phone): ");
                String contact = scanner.nextLine();
                library.viewIssuedBooks(contact);
            }
            case 8 -> library.viewOverdues();
            case 9 -> {
                System.out.println("Exiting…");
                scanner.close();
                return;
            }
            default -> System.out.println("Invalid choice");
            }
		}
	}

}
