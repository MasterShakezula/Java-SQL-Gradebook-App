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
		
		if (args.length == 0)
		{
			System.out.println("Please provide a valid section or student");
			System.exit(0);
		}
		String input = args[0];
		DBConnection dbConn = new DBConnection();
		try
		{
			boolean inputFound = false;
			dbConn.openConnection();
			inputFound = dbConn.getStudentEnrollment(input);
			if (!inputFound) inputFound = dbConn.getSectionEnrollment(input);
			if (!inputFound) System.out.println(input + " is not a valid section or student");
				
		}
		finally
		{
			dbConn.closeConnection();
		}
		/* Old-code. Keep just in case
		 * // TODO Auto-generated method stub String username = "gradebook-admin";
		 * String password = "Grad3B00k!"; String dbName = "gradebook"; String hostname
		 * = "localhost"; Connection conn = null; try { StringBuffer connectionString =
		 * new StringBuffer("jdbc:mysql://"); connectionString.append(hostname+"/");
		 * connectionString.append(dbName); connectionString.append("?user="+username);
		 * connectionString.append("&password="+password);
		 * System.out.println("Connection String: "+connectionString.toString());
		 * 
		 * conn = DriverManager.getConnection(connectionString.toString());
		 * 
		 * if(conn != null) System.out.println("Connected to DB !!"); } catch (Exception
		 * ex) { System.out.println("SQLException: " + ex.getMessage()); } finally { try
		 * { conn.close();} catch (SQLException e) { e.printStackTrace(); }
		 * 
		 * }
		 */

}
	}

