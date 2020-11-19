package com.first_sprint.swe;

import java.io.IOException;

import javax.servlet.http.*;


import java.io.PrintWriter;

import javax.servlet.ServletException;
import java.sql.SQLException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.*;  
import com.google.gson.Gson;

import javax.servlet.annotation.WebServlet;
@WebServlet({"/register", "/checkUsername", "/edit", "/deleteBooking", "/employee", "/editEmployee", "/addSeason", "/deleteSeason", "/logout"})
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserManager userManager;
    private ReservationManager reservationManager;
    private EmployeeManager employeeManager;
    private ManagerControl managerControl;
    private Gson gson = new Gson();
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() {
        super();
        userManager = new UserManager();
        reservationManager = new ReservationManager();
         employeeManager = new EmployeeManager();
         managerControl = new ManagerControl();
         
      
        // TODO Auto-generated constructor stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	doGet(request, response);
        
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path = request.getServletPath();
    	System.out.println(request.getRequestURL() + "__queries__ " + request.getQueryString());
    	try {
    		switch (path) {
    			case "/register":
    				registerHandle(request, response);
    				break;
    			case "/deleteBooking":
    				removeReservation(request, response);
    			case "/checkUsername":
    				checkUsername(request, response);
    				break;
    			case "/edit": 
    				editUser(request, response);
    				break;
    			case "/employee":
    				getEmployeeInfo(request, response);
    				break;
    			case "/editEmployee":
    				editEmployee(request, response);
    				break;
    			case "/deleteSeason":
    				deleteSeason(request, response);
    				break;
    			case "/addSeason":
    				addSeason(request, response);
    				break;
    			case "/logout":
    				logout(request, response);
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
		System.out.println("here in the def function");
	}
    private void deleteSeason(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    	String seasonName = request.getParameter("ses");
    	String hotelID = request.getParameter("hotel");
    	managerControl.removeSeason(seasonName, hotelID);
    }
    private void addSeason(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    	Season season = new Season(request.getParameter("name"), request.getParameter("start-date"), 
    			request.getParameter("end-date"), request.getParameter("coeff1"), request.getParameter("coeff2"), "1");
    	managerControl.insertSeason(season);
    	
    }
    private void registerHandle(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    	
    	response.setContentType("text/html");
        User user = new User(request.getParameter("usr"));
        String isLog = request.getParameter("login");
        try {
       if (isLog.equals("1")) {
    		user = userManager.getInfo(user);
       } else { 
    	   user.setPassword(request.getParameter("psw"));
    	   userManager.insertUser(user);
	    }
       
       HttpSession session=request.getSession(); 
       session.setAttribute("username", user.getNickname());
       //max interval of inactivity = 20 minutes
       session.setMaxInactiveInterval(1200000);
       RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
       request.setAttribute("profile", user);
	   rd.forward(request, response);
      
       }
	    catch (Exception e) {
	        e.printStackTrace();
	    }
        
    }
    
    private void removeReservation(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException{
    	String res = request.getParameter("res");
    	System.out.println(res);
    	reservationManager.remove(res);	
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
    private void editUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	response.setContentType("text/html");
        User user = new User(request.getParameter("nickname"), 
        	
        		request.getParameter("first_name"),
        		request.getParameter("last_name"),
        		request.getParameter("idtype"),
        		request.getParameter("idnumber"),
        		request.getParameter("country"),
        		request.getParameter("city"),
        		
        		request.getParameter("street"),
        		request.getParameter("mobile"),
        		request.getParameter("home")
        		);
        try {
        	userManager.editUser(user);
        	RequestDispatcher rd = request.getRequestDispatcher("profile.jsp");
            request.setAttribute("profile", user);
     	   rd.forward(request, response);
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    private void getEmployeeInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	response.setContentType("text/html");
    	int id  = Integer.parseInt(request.getParameter("id"));
    	Employee employee = employeeManager.getFullInfo(id);
    	RequestDispatcher rd = request.getRequestDispatcher("employee.jsp");
    	request.setAttribute("employee", employee);
 	   rd.forward(request, response);
 	   System.out.println("reached here");
    }
    private void editEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	response.setContentType("text/html");
    	Employee employee = new Employee();
    	employee.setEmployeeID(Integer.parseInt(request.getParameter("id")));
    	employee.setSalaryPerHour(Integer.parseInt(request.getParameter("salary")));
    	boolean monday = false, tuesday = false, wednesday = false, thursday = false, friday = false, saturday = false, sunday = false;
    	
    	if (request.getParameter("monday").toUpperCase().equals("T"))  {
    		monday = true;
    	} 
    	if (request.getParameter("tuesday").toUpperCase().equals("T"))  {
    		tuesday = true;
    	} if (request.getParameter("wednesday").toUpperCase().equals("T"))  {
    		wednesday = true;
    	} if (request.getParameter("thursday").toUpperCase().equals("T"))  {
    		thursday = true;
    	} if (request.getParameter("friday").toUpperCase().equals("T"))  {
    		friday = true;
    	} if (request.getParameter("saturday").toUpperCase().equals("T"))  {
    		saturday = true;
    	} if (request.getParameter("sunday").toUpperCase().equals("T"))  {
    		sunday = true;
    	} 
    	employee.setMonday(monday);
    	employee.setTuesday(tuesday);
    	employee.setWednesday(wednesday);
    	employee.setThursday(thursday);
    	employee.setThursday(thursday);
    	employee.setFriday(friday);
    	employee.setSaturday(saturday);
    	employee.setSunday(sunday);
    	employee.setFromTime(request.getParameter("fromTime"));
    	employee.setToTime(request.getParameter("toTime"));
    	employeeManager.editEmployee(employee);
    	PrintWriter out = response.getWriter();
        try { 
            response.sendRedirect("manageSchedule.jsp");
        }
        finally {            
            out.close();
        }
    }
    private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
    	//finish session
    	try { 
    		HttpSession session = request.getSession();
    		session.invalidate();
            response.sendRedirect("index.jsp");
        } catch(Exception e) {
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