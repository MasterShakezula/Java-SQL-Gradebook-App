package gradebook.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConnection {
	
	private String username = "gradebook-admin";
	private String password = "Grad3B00k!";
	private String dbName = "gradebook";
	private String hostname = "localhost";
	 
	private Connection conn = null; 
	
	public void openConnection() {
		if(conn == null) {
			 StringBuffer connectionString = new StringBuffer("jdbc:mysql://");
			 connectionString.append(hostname+"/");
			 connectionString.append(dbName);
			 connectionString.append("?user="+username);
			 connectionString.append("&password="+password);
			 
			 try {
				 conn = DriverManager.getConnection(connectionString.toString());
			 } catch (SQLException e) {
				 e.printStackTrace();
			 }
		}
	}
	
	public void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public boolean getStudentEnrollment(String studentId) {
		
		boolean studentFound = false;
		if(conn == null)
			openConnection();
		
		try {
			String selectQuery = "SELECT E.Section_No, E.Course, Course_Name, Final_Grade, Total_Points FROM ENROLLMENT E INNER JOIN SECTION S "
					+ "ON E.Section_No = S.Section_No INNER JOIN COURSE C ON S.Course = C.Course_Id WHERE E.Student = ?";
			PreparedStatement prepSelectStmt = conn.prepareStatement(selectQuery);
			prepSelectStmt.setString(1, studentId);
			ResultSet rs = prepSelectStmt.executeQuery();
			if(rs.next() ) {
				studentFound = true;
				System.out.println("Section, Course, Course_Name, Final_Grade, Total_Points");
				System.out.println(rs.getString("E.Section_No")+","+rs.getString("E.Course")+","+rs.getString("Course_Name")+","
						+rs.getString("Final_Grade")+","+rs.getFloat("Total_Points"));
			}
			while(rs.next()) {
				System.out.println(rs.getString("E.Section_No")+","+rs.getString("E.Course")+","+rs.getString("Course_Name")+","
						+rs.getString("Final_Grade")+","+rs.getFloat("Total_Points"));
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return studentFound;
	}
	
	public boolean getSectionEnrollment(String sectionId) {
		boolean sectionFound = false;
		if(conn == null)
			openConnection();
		
		try {
			String selectQuery = "SELECT E.Student, Last_Name, First_Name, Final_Grade, Total_Points "
					+ "FROM ENROLLMENT E INNER JOIN STUDENT S ON E.Student = S.Panther_Id WHERE Section_No = ?";
			PreparedStatement prepSelectStmt = conn.prepareStatement(selectQuery);
			prepSelectStmt.setString(1, sectionId);
			ResultSet rs = prepSelectStmt.executeQuery();
			if(rs.next() ) {
				sectionFound = true;
				System.out.println("Student, Name, Final_Grade, Total_Points");
				System.out.println(rs.getString("Student")+","+rs.getString("Last_name")+","+rs.getString("First_Name")+","
						+rs.getString("Final_Grade")+","+rs.getFloat("Total_Points"));
			}
			while(rs.next()) {
				System.out.println(rs.getString("Student")+","+rs.getString("Last_name")+","+rs.getString("First_Name")+","
						+rs.getString("Final_Grade")+","+rs.getFloat("Total_Points"));
			}
		}
		catch(Exception ex) {
			System.out.println(ex.getMessage());
		}
		return sectionFound;
	}
}
