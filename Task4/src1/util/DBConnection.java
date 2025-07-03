package util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {
	private static Connection connection;
	public static Connection getConnection() {
		try {
			//load driver
			Class.forName("com.mysql.cj.jdbc.Driver");
			 //get connection
			String url="jdbc:mysql://localhost:3306/studentdb";
			String username="root";
			String password="tarun07";
			connection=DriverManager.getConnection(url,username,password);
		}catch (Exception ex) {
			ex.printStackTrace();
		
		}
		return connection;
	}

}
