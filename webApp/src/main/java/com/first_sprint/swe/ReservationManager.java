package com.first_sprint.swe;

import java.sql.Connection;

//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
//import java.sql.ResultSet;

public class ReservationManager {
	private UserManager userManager;
	
	private static final String DELETE_FROM_RESERVATION = "DELETE FROM RESERVATION WHERE ReservationID = ?";
	private static final String DELETE_FROM_ROOM_HISTORY = "DELETE FROM ROOM_has_RESERVATION WHERE ReservationID = ?";
	
	public ReservationManager() {
		userManager = new UserManager();
	}
	
	public void remove(String id) {
		try(Connection connection = userManager.getConnection();
				PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_FROM_ROOM_HISTORY);
				PreparedStatement preparedStatement2 = connection.prepareStatement(DELETE_FROM_RESERVATION)){
			preparedStatement1.setString(1, id);
			preparedStatement2.setString(1, id);
			preparedStatement1.executeUpdate();
			preparedStatement2.executeUpdate();
			
		}catch (SQLException e) {
			printSQLException(e);
		}
		
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
