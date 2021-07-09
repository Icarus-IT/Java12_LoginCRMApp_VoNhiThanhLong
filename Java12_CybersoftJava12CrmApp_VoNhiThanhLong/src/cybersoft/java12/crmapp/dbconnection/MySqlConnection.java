package cybersoft.java12.crmapp.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MySqlConnection {
	private static Connection connection=null;
	private static final String URL  = "jdbc:mysql://localhost:3306/crm";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "123456LoUgo!!!";
	public static Connection getConnection() {
		if (connection != null) {
			return connection;
		}
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			if (connection == null) {
				return connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			}
			return connection;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can not find MySQL Driver!!!");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Can not connect to database due to: invalid url or invalid credential");
		}
		return null;
	}
}
