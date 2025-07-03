package model;

public class Student {
	private int id;
    private String name;
    private String email;
    private String subject;

    public Student(int id, String name, String email, String subject) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.subject = subject;
    }

	// Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getSubject() { return subject; }

    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setSubject(String subject) { this.subject = subject; }

    @Override
    public String toString() {
        return "Student [id=" + id + ", name=" + name + ", email=" + email + ", subject=" + subject + "]";
    }
	
}
