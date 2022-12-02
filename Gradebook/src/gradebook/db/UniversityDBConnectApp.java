package gradebook.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class UniversityDBConnectApp {

	public static void main(String[] args) {
		
		 if(args.length == 0) {
			 System.out.println("Please provided a valid section or student");
			 System.exit(0);
		 }
		 
		 String input = args[0];
		 DBConnection dbConn = new DBConnection();
		 
		 try {
			 boolean inputFound = false;
			 dbConn.openConnection();
			 
			 inputFound = dbConn.getStudentEnrollment(input);
			 
			 if(!inputFound)
				 inputFound = dbConn.getSectionEnrollment(input);
			 
			 if(!inputFound)
				 System.out.println(input+" is not a valid section or student");
		 }
		 finally {
			 dbConn.closeConnection();
		 }
	}
}
