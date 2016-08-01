package SQL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class UpdateData {
	
	static Connection conn = null;
	static Scanner scan = new Scanner(System.in);
	static ArrayList<String> columns;
	static ArrayList<String> colValues;

	public static void main(String[] args){
		try {
		String userName = "root";
		String password = "";
		String url = "jdbc:mysql://localhost:3306/test?useSSL=false";
		
			conn = DriverManager.getConnection(url,userName,password);
			System.out.println("Enter the ID of the row you want to edit.");
			int id = scan.nextInt();
			
			//get all the columns in the table
			columns = new ArrayList<String>();
			DatabaseMetaData md = conn.getMetaData();
			ResultSet rs = md.getColumns(null, null, "test_table", "%");
			while(rs.next()){
				columns.add(rs.getString(4));
			}
			//get the values we want to change the columns to
			System.out.println("Enter the new values below");
			colValues = new ArrayList<String>();
			for(int i = 0; i < columns.size(); i++){
				System.out.println(columns.get(i) + ": ");
				String change = scan.next();
				colValues.add(change);
			}
			//create the query
			Statement stmt = conn.createStatement();
			for(int i = 0; i< columns.size(); i++){
				String query = "UPDATE test_table  SET " + columns.get(i) + " = '" + colValues.get(i) + "' WHERE "
						+ " id = " +id;
				stmt.executeUpdate(query);
			}
			stmt.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
