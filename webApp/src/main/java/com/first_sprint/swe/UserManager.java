package com.first_sprint.swe;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
//import java.sql.Statement;
import java.sql.SQLException;
import java.sql.PreparedStatement;

public class UserManager {
	private String jdbcURL = "jdbc:mysql://localhost:3306/mydb";
	private String jdbcUsername = "root";
	private String jdbcPassword = "root";
	
	private static final String INSERT_USERS_SQL = "INSERT INTO GUEST (username, password) VALUES (?, ?);";
	private static final String SELECT_DATA_BY_USR = "select username, mobile_phone, country, name, surname, city from GUEST where username = ?";
	private static final String FIND_USERNAME = "select count(*) from GUEST where username = ?";
	private static final String CHECK_PASSWORD = "select count(*) from GUEST where username = ? and password = ?";
	//private static final String UPDATE_USERS_SQL = "update users set name = ?,email= ?, country =? where id = ?;";

	public UserManager() {
	}

	protected Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return connection;
	}
	
	public void insertUser(User user) throws SQLException {
		System.out.println(INSERT_USERS_SQL);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
			preparedStatement.setString(1, user.getNickname());
			preparedStatement.setString(2, user.getPassword());
			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			printSQLException(e);
		}
	}
	
	
	public User getInfo(User user) throws SQLException {
		User retUser = null;
		System.out.println(SELECT_DATA_BY_USR);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_DATA_BY_USR)) {
			preparedStatement.setString(1, user.getNickname());
			
			System.out.println(preparedStatement);
			
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String username = rs.getString("username");
				String mobile_phone = rs.getString("mobile_phone");
				String country = rs.getString("country");
				String city = rs.getString("city");
				String name = rs.getString("name");
				String surname = rs.getString("surname");
				retUser = new User(username, name, surname, city, country, mobile_phone);
			}
		} catch (SQLException e) {
			printSQLException(e);
		}
		return retUser;
	}
	
	public boolean checkUsername(String usr) throws SQLException {
		System.out.println(FIND_USERNAME);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(FIND_USERNAME)) {
			preparedStatement.setString(1, usr);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
		    int rowCount = rs.getInt(1);
		    if (rowCount >= 1) {
		    	return true;
		    }
		} catch (SQLException e) {
			printSQLException(e);
		}
		return false;
	}
	public boolean checkPassword(String usr, String pass) throws SQLException {
		System.out.println(CHECK_PASSWORD);
		// try-with-resource statement will auto close the connection.
		try (Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CHECK_PASSWORD)) {
			preparedStatement.setString(1, usr);
			preparedStatement.setString(2, pass);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			rs.next();
		    int rowCount = rs.getInt(1);
		    if (rowCount >= 1) {
		    	return true;
		    }
		} catch (SQLException e) {
			printSQLException(e);
		}
		return false;
	}
	private void printSQLException(SQLException ex) {
		for (Throwable e : ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
}
