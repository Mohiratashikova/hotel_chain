<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
<%@page import="com.first_sprint.swe.Reservation" %>
<%@ page import="java.sql.*" %>

<%String SELECT_RESERVATION_BY_USR = "SELECT * FROM RESERVATION WHERE GuestID = ?"; %>
<%String SELECT_RESERVATION2_BY_USR = "SELECT * FROM ROOM_has_RESERVATION WHERE GuestID = ?"; %>
<%String SELECT_HOTELNAME_BY_HOTELID = "SELECT name FROM HOTEL WHERE HotelID = ?";%>
<html lang="en">
<head>
	<meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
	<title>Desk Clerk Management</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
    <link rel="stylesheet" href="styles.css">
	<script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin=" anonymous"></script>
</head>

<body>
	<section class="rooms" id="services">
        <div class="container">
            <h5 class="section-head">
                <span class="heading">Desk Clerk Management</span>
            </h5>
        </div>
    </section>
	<section class="booking" id="reser">
		<div class="container">
			<form action="" class="book-form">
				<div class="input-group">
					<label for="destination" class="input-label">Guest ID</label>
					<input type="text" class="input" name = "guest" id="guestId">
				</div>
				<input type="submit" class="btn form-btn btn-purple" value="Search">
					<span class="dots"><i class="fas fa-ellipsis-h"></i></span>
				
				<button type="button" onclick="window.location.href='reservation.html'" class="btn form-btn btn-purple">Create Booking
					<span class="dots"><i class="fas fa-ellipsis-h"></i></span>
				</button>
			</form>
		</div>

	</section>
	<%	String jdbcURL = "jdbc:mysql://localhost:3306/mydb";
		String jdbcUsername = "root";
		String jdbcPassword = "Mohikohhi12m$";
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
		ArrayList<Reservation> reservation = new ArrayList<Reservation>();
		PreparedStatement preparedStatement1 = connect.prepareStatement(SELECT_RESERVATION_BY_USR);
		PreparedStatement preparedStatement2 = connect.prepareStatement(SELECT_RESERVATION2_BY_USR);
		PreparedStatement preparedStatement3 = connect.prepareStatement(SELECT_HOTELNAME_BY_HOTELID);
		preparedStatement1.setString(1, request.getParameter("guest"));
		preparedStatement2.setString(1, request.getParameter("guest"));

		ResultSet rs1 = preparedStatement1.executeQuery();
		ResultSet rs2 = preparedStatement2.executeQuery();

		String checkin = "";
		String checkout = "";
		String id = "";
		String hotel = "";
		String room_type = "";

		while(rs1.next()) {
			rs2.next();
			checkin = rs1.getString("checkin");
			checkout = rs1.getString("checkout");
			id = rs1.getString("ReservationID");
			preparedStatement3.setString(1, rs2.getString("HotelID"));
			ResultSet rs3 = preparedStatement3.executeQuery();
			rs3.next();
			hotel = rs3.getString("name");
			room_type = rs2.getString("room_type_name");
			reservation.add(new Reservation(checkin, checkout, id, hotel, room_type));
		}

	%>
	<div class="profile-booking tab" id = "BookingsDiv">
		<h1><%=request.getParameter("guest")  %></h1>
		<%for(Reservation rsrv : reservation){%>
		<div id="<%=rsrv.getID()%>" class="booking1">
			<h3><%=rsrv.getHotel()%> </h3>
			<p><%=rsrv.getRoomType()%></p>
			<p><%=rsrv.getCheckin()%> <br><%=rsrv.getCheckout()%></p>
			<button id="<%=rsrv.getID() %>" onclick="removeB(id)">Delete Booking</button>
		</div>
		<%}%>
	</div>
    <script src="deskclerk.js"></script>
    
<ul id="my-list"></ul>
</body>
</html>