package com.first_sprint.swe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;

import kz.edu.nu.cs.exercise.PagedHelper;

@Path("/hotels")
public class HotelService {
	public HotelService() {
		
	}

	Connection establishConnection() {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydb", "root", "Serious2604");
			return conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	 @GET
	    public Response check(@QueryParam("user") String username, @QueryParam("pass") String password) {
	        Gson gson = new Gson();
	        String result = "0";
			Connection conn = establishConnection();
			try {
				Statement stm = conn.createStatement();
				ResultSet res = stm.executeQuery("select * from guest");
				while(res.next()) {
					if(res.getString("username").equals(username) && res.getString("password").contentEquals(password)) {
						result = "1";
						break;
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Respond r = new Respond();
			r.setRes(result);
	        return Response.ok(gson.toJson(r)).build();
	    }
	
}
