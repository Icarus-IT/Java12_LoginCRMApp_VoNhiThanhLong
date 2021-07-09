package cybersoft.java12.crmapp.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import cybersoft.java12.crmapp.dbconnection.MySqlConnection;


public class UserRepository {
	public boolean login(String email, String password) {
		
		try {
			Connection connection = MySqlConnection.getConnection();
			String query = "SELECT email,password from user where email=? and password=?";
			PreparedStatement st = connection.prepareStatement(query);
			st.setString(1, email);
			st.setString(2, password);
			ResultSet resultSet = st.executeQuery();
			if (resultSet.next()) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Connection to database has been disconnected!!");
		}
		return false;
	}
}
