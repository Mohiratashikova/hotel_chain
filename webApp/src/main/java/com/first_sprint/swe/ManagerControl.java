package com.first_sprint.swe;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ManagerControl {
	private UserManager userManager;
	
	private static final String ADD_SEASON = "INSERT INTO season VALUES(?, ?, ?, ?, ?, ?)";
	private static final String REMOVE_SEASON = "DELETE FROM season WHERE name = ? and HotelID = ?";
	
	public ManagerControl() {
		userManager = new UserManager();
	}
	
	public void insertSeason(Season ses) {
		try(Connection connection = userManager.getConnection();
				PreparedStatement preparedStatement1 = connection.prepareStatement(ADD_SEASON)){
			preparedStatement1.setString(1, ses.getName());
			preparedStatement1.setString(2, ses.getStartdate());
			preparedStatement1.setString(3, ses.getEnddate());
			preparedStatement1.setString(4, ses.getCoefficientWorking());
			preparedStatement1.setString(5, ses.getCoefficientWeekEnd());
			preparedStatement1.setString(6, ses.getHotelID());
			
			preparedStatement1.execute();			
		}catch (SQLException e) {
			printSQLException(e);
		}
	}
	public void removeSeason(String name, String hotelID) {
		try(Connection connection = userManager.getConnection();
				PreparedStatement preparedStatement1 = connection.prepareStatement(REMOVE_SEASON)){
			preparedStatement1.setString(1, name);
			preparedStatement1.setString(2, hotelID);
			preparedStatement1.execute();			
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
