<!DOCTYPE jsp>
<%@page import="java.util.ArrayList"%>
<%@page import="com.first_sprint.swe.Season" %>
<%@page import="java.sql.*" %>

<%String SELECT_SEASON_BY_HOTEL = "SELECT * FROM SEASON WHERE HotelID = 1"; %>

<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>ManagerSeasonsRateManagement</title>
</head>

<style>
   * {
		margin:0;
 		padding: 0;
 		font-family: sans-serif; 
 		box-sizing: border-box;
   }
   table{
   		position: absolute;
   		left: 50%;
   		top: 50%;
 		transform: translate(-50%,-50%);
 		border-collapse: collapse;
 		border-spacing: 0;
 		 
 		border: 1px solid #bdc3c7;
 		background-color: #D6FCFF;
 		color: #3ACFE4;
   }
   h1{
   		text-align: center; 
   		color: #fff; 
   		background-color: #3ACFE4;
   		font-weight: 600; 
   		padding: 10px 0px;
   }
   
   #header {
   		background-color: #3ACFE4;
 		color: #fff;	
   }
   table th{
		padding: 12px;
 		text-align: left;
 		border-bottom: 1px solid #ddd;
   }
   table td{
		padding: 12px;
 		text-align: left;
 		border-bottom: 1px solid #ddd;
   }
   .button{
   		cursor: pointer; 
  		color: #3ACFE4;
  		padding: 32px 16px;
  		text-align: center;
  		background-color: #D6FCFF;
  		font-size: 18px;
  		border: 2px solid #D6FCFF;
  		width: 100%;
   }
</style>
<%	String jdbcURL = "jdbc:mysql://localhost:3306/mydb";
		String jdbcUsername = "root";
		String jdbcPassword = "password";
		Connection connect = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connect = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Season> seasons = new ArrayList<Season>();

		Statement st = connect.createStatement();
		ResultSet rs = st.executeQuery(SELECT_SEASON_BY_HOTEL);
		
		String name = "";
		String start_date = "";
		String end_date = "";
		String coefficient_weekend = "";
		String coefficient_working = "";
		String HotelID = "";
		while(rs.next()) {
			name = rs.getString("name");
			start_date = rs.getString("start_date");
			end_date = rs.getString("end_date");
			coefficient_weekend = rs.getString("week_day");
			coefficient_working = rs.getString("working_day");
			HotelID = rs.getString("HotelID");
			seasons.add(new Season(name, start_date, end_date, coefficient_weekend, coefficient_working, HotelID));
		}

	%>
<body>
	<div>
		<h1>Manager Seasons Rate Management</h1>
		<!-- version1: here i used specifially table syntax, but i dont know if it is convinient for you -->
		<table id="table" border="1">
			
			<tr id="header">
				<th>Name</th>
				<th>Weekend Coefficient</th>
				<th>Working days Coefficient</th>
				<th>Start-Date</th>
				<th>End-Date</th>
				<th>Delete</th>
			</tr>	
			<!-- need to add a loop -->
			<!-- you can also use db data inside those tr, td -->
			<%for(Season sesn : seasons){%>
			<tr>
				<td><%=sesn.getName()%></td>
				<td><%=sesn.getCoefficientWeekEnd()%></td>
				<td><%=sesn.getCoefficientWorking()%></td>
				<td><%=sesn.getStartdate()%></td>
				<td><%=sesn.getEnddate()%></td>		
				<td><button class="button" onclick="removeS('<%=sesn.getHotelID()%>', '<%=sesn.getName()%>')" id="id">Remove</button></td>
			</tr>
			<%}%>
		</table> 
		
		<!-- versio2: the same structure as yours, you just need to add a loop. but this is bad for a design
		<div id="id" class="booking1">
			<p>Name</p>
			<p>Coefficient</p>
			<p>Period1<br>Period2</p>
			<button id="id" onclick="removeB(id)">Delete</button>
		</div>		-->
	   <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin=" anonymous"></script>
	   <script src="managerSeasons.js"></script>
	</div>
</body>
</html>