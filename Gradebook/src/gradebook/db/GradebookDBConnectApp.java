/**
 * 
 */
package gradebook.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Samue
 *
 */
public class GradebookDBConnectApp {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String username = "gradebook-admin";
		    String password = "Grad3B00k!";
		    String dbName = "gradebook";
		    String hostname = "localhost";
		    Connection conn = null;
		try {
		   StringBuffer connectionString = new StringBuffer("jdbc:mysql://");
		   connectionString.append(hostname+"/");
		   connectionString.append(dbName);
		   connectionString.append("?user="+username);
		    connectionString.append("&password="+password);
		    System.out.println("Connection String: "+connectionString.toString());
		   
		    conn = DriverManager.getConnection(connectionString.toString());
		   
		    if(conn != null)
		        System.out.println("Connected to DB !!");
		} 
		catch (Exception ex) { System.out.println("SQLException: " + ex.getMessage()); }
		finally {
		try { conn.close();}
		 catch (SQLException e) { e.printStackTrace();  }

	}

}}
