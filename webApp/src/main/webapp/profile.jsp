<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<html xmlns="http://www.w3.org/1999/xhtml">

<head>
    <meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1.0" />
    <title>Profile Page</title>
    <link rel=" stylesheet " href="./profile.css ">

    <link rel="stylesheet " href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css ">
    <script src="https://code.jquery.com/jquery-3.5.1.js " integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin=" anonymous "></script>

</head>

<body>

    <div class="container">
        <div class="profile-header">
            <div class="profile-img">
                <img src="./images/105.jpg " width="200px " alt=" ">
            </div>
            <div class="profile-nav-info ">
                <h3 class="user-name ">
                    <%= request.getAttribute("nickname") %>

                </h3>
                <div class="address ">

                    <p id="state " class="state ">
                        <%= request.getAttribute("city") %>, </p>
                    <span id="country" class="country"> <%= request.getAttribute("country") %></span>
                </div>
            </div>

        </div>
        <div class="main-bd">
            <div class="left-side">
                <div class="profile-side">

                    <p id="name" class="user-mail">
                        <!-- <i class="fa fa-envelope "></i> -->
                        <%= request.getAttribute("name") %>
                            <%= request.getAttribute("surname") %>
                    </p>
                    <p id="mobile" class="mobile-no"><i class="fa fa-phone"></i>
                        <%= request.getAttribute("mobile") %>
                    </p>
                    <div class="profile-btn ">

                        <button class="createbtn " onclick='tabs(1)'>
                            Edit
                        </button>
                        <button class="createbtn " onclick="logout()">Log Out</button>
                    </div>

                </div>
            </div>
            <div class="right-side ">
                <div class="nav ">
                    <ul>
                        <li onclick="tabs(0)" class="user-booking ">Bookings</li>
                        <li onclick="tabs(1)" class="user-settings ">Settings</li>

                    </ul>

                </div>
                <div class="profile-body ">
                    <div class="profile-booking tab">
                        <h1>Your Bookings</h1>
                        <div id="d1" class="booking">

                            <h3>California Hotel </h3>
                            <p>single</p>
                            <p>12.09.2020 <br> 19.09.2020</p>
                            <button id="delete1">Delete Booking</button>

                        </div>
                        <div id="d2" class="booking">

                            <h3>Dubai Hotel</h3>
                            <p>single</p>
                            <p>12.08.2020 <br> 19.08.2020</p>
                            <button id="delete2">Delete Booking</button>

                        </div>
                        <div id="d3" class="booking">

                            <h3>Astana Hotel</h3>
                            <p>single</p>
                            <p>12.07.2020 <br> 19.07.2020</p>
                            <button id="delete3">Delete Booking</button>

                        </div>
                    </div>
                    <div class="profile-settings tab">
                        <h1>Edit Profile</h1>
                        <div class="regform">
                            <div class="mane">
                                <form id="form" action="/first_sprint/edit" method="post">
                                    <div id="name">
                                        <h2 class="name">Name</h2>
                                        <input class="firstname" type="text" name="nickname" value='<%= request.getAttribute("nickname") %>' style="display:none;">
                                        <input class="firstname" type="text" name="first_name" placeholder="First Name" id="nameInput" pattern="[a-zA-Z0-9]{1,}" title="Only alphanumeric characters" required>

                                        <input type="text" class="lastname" name="last_name" placeholder="Last Name" id="lastnameInput" pattern="[a-zA-Z0-9]{1,}" title="Only alphanumeric characters" required>


                                    </div>

                                    <div class="id">
                                        <h2 class="name">Identification</h2><br>

                                        <select class="option" id="idtype" name="idtype">
                                        <option value="passport">Passport</option>
                                        <option value="id">ID</option>
                                        
                                    </select>
                                        <input type="text" name="idnumber" class="idnumber" placeholder="Enter Number " id="idInput" pattern="[0-9]{8,}" title="Eight or more numeric characters" required>

                                    </div>
                                    <div class="address">
                                        <h2 class="name">Address</h2>
                                        <input type="text" name="country" id="country" placeholder="Country" pattern="[a-zA-Z]{1,}" title="Only alphabet characters" required>

                                        <input type="text" name="city" id="city" placeholder="City" pattern="[a-zA-Z]{1,}" title="Only alphabet characters" required>

                                        <input type="text" name="street" id="street" placeholder="Street" pattern="[a-zA-Z0-9 ]+" title="Only alphanumeric characters and space" required>

                                    </div>

                                    <div class="phone">
                                        <h2 class="name">Phone Number</h2>
                                        <input type="tel" name="home" id="home" placeholder="Home Phone: 87081112233" pattern="[0-9]{11}" title="Only 11 numbers" required>

                                        <input type="tel" name="mobile" id="mobile" placeholder="Mobile Phone: 87081112233" pattern="[0-9]{11}" title="Only 11 numbers" required>

                                    </div>

                                    <button>Save</button>
                                    <button class="clear">Clear</button>
                                </form>
                            </div>
                        </div>

                    </div>
                </div>
            </div>
        </div>
    </div>



</body>
<script src="./profile.js "></script>

</html>