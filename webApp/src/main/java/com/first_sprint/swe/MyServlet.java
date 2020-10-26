package com.first_sprint.swe;

import java.io.IOException;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;  
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
@WebServlet({"/register", "/checkUsername"})
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserManager userManager;
    private Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        userManager = new UserManager();
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path = request.getServletPath();
    	try {
    		switch (path) {
          case "/register":
            registerHandle(request, response);
            break;
          case "/checkUsername":
            checkUsername(request, response);
            break;
          default:
        	  def(request, response);
            break;
    		}
    	} catch (SQLException ex) {
			throw new ServletException(ex);
		}
        
    	
    }
    private void def(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/index.html");
		dispatcher.forward(request, response);
	}
    
    private void registerHandle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    	
    	response.setContentType("text/html");
        User user = new User(request.getParameter("usr"), request.getParameter("psw"));
        String isLog = request.getParameter("login");
        try {
       if (isLog.equals("1")) {
    		User fullUser = userManager.getInfo(user);
    		RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
    		request.setAttribute("nickname", fullUser.getNickname());
  	        request.setAttribute("mobile", fullUser.getMobilePhone());
  	        request.setAttribute("country", fullUser.getCountry());
  	        request.setAttribute("city", fullUser.getCity());
  	        request.setAttribute("name", fullUser.getName());
  	        request.setAttribute("surname", fullUser.getSurname());
  	      rd.forward(request, response);
   		
       } else { 
    	   //create new guest entity instance in db
    	   //push user values to database
    	   userManager.insertUser(user);
    	   RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
    	   request.setAttribute("nickname", user.getNickname());
 	       request.setAttribute("mobile", "");
 	       request.setAttribute("country", "");
 	       request.setAttribute("city", "");
 	       request.setAttribute("name", "");
 	       request.setAttribute("surname", "");
	       rd.forward(request, response);
	    }
       
       }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
        
    }
    
    
    //----------------------------------------------------------------------------------------------------------------\\
    private void checkUsername(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
    	Answer an = new Answer();
    	String usr = request.getParameter("usr");
    	String pass = " ";
    	
    	if (request.getParameterMap().containsKey("pass")) 
    		pass = request.getParameter("pass");
    	
    	PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
    	//search DB on this "usr" value and print true if registered and false if not
		try {
			if(pass.equals(" ") && userManager.checkUsername(usr) || !pass.equals(" ") &&  userManager.checkPassword(usr, pass)) {
				an.setAnswer("true");
				} else {
					an.setAnswer("false");	
				}
			out.append(gson.toJson(an));
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
}

class Answer{
	 String answ;
	
	public void setAnswer(String str) {
		this.answ = str;
	}
}