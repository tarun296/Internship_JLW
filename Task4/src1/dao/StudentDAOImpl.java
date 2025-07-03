package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Student;
import util.DBConnection;

public class StudentDAOImpl implements StudentDAO {
	
	@Override
	public void addStudent(Student std) {
		 String query="insert into students (id, name, email, subject) values(?,?,?,?)";
		 try (Connection connection = DBConnection.getConnection();
		      PreparedStatement pstm=connection.prepareStatement(query)){
		      pstm.setInt(1,std.getId());
		      pstm.setString(2, std.getName());
		      pstm.setString(3, std.getEmail());
		      pstm.setString(4, std.getSubject());
		     pstm.executeUpdate();

		 }catch (Exception ex) {
			 ex.printStackTrace();
		 }
	}
	
	 public List<Student> getAllStudents() {
	        List<Student> students = new ArrayList<>();
	        String query = "SELECT * FROM students";
	        try (Connection conn = DBConnection.getConnection();
	             Statement stmt = conn.createStatement();
	             ResultSet rs = stmt.executeQuery(query)) {
	            while (rs.next()) {
	                students.add(new Student(rs.getInt("id"), rs.getString("name"),
	                                         rs.getString("email"), rs.getString("subject")));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return students;
	    }

	    public void updateStudent(Student std) {
	        String sql = "UPDATE students SET name=?, email=?, course=? WHERE id=?";
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(sql)) {
	            pstmt.setString(1, std.getName());
	            pstmt.setString(2, std.getEmail());
	            pstmt.setString(3, std.getSubject());
	            pstmt.setInt(4, std.getId());
	            pstmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public void deleteStudent(int id) {
	        String query = "DELETE FROM students WHERE id=?";
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(query)) {
	            pstmt.setInt(1, id);
	            pstmt.executeUpdate();
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

	    public Student getStudentById(int id) {
	        String query = "SELECT * FROM students WHERE id=?";
	        try (Connection connection = DBConnection.getConnection();
	             PreparedStatement pstmt = connection.prepareStatement(query)) {
	            pstmt.setInt(1, id);
	            ResultSet rs = pstmt.executeQuery();
	            if (rs.next()) {
	                return new Student(rs.getInt("id"), rs.getString("name"),
	                                   rs.getString("email"), rs.getString("subject"));
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

}
