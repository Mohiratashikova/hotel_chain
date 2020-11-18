<!DOCTYPE html PUBLIC"-//W3C//DTD XHTML 1.0 Transitional//EN""http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="java.util.ArrayList"%>
    <%@ page import="com.first_sprint.swe.Employee" %>

        <html xmlns="http://www.w3.org/1999/xhtml">

        <head>
            <meta name="viewport" http-equiv="Content-Type" content="width=device-width, initial-scale=1.0" />
            <title>Employee Management Page</title>


            <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.11.2/css/all.min.css">
            <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin=" anonymous"></script>
            <style>
                body {
                    display: flex;
                    align-items: center;
                    justify-content: center;
                    height: 100vh;
                  
                    
                }
                
                .profile {
                    width: 60%;
                    margin: 0 auto;
                    border: 2px solid #555;
                    padding: 10px;
                }
                
                .schedule {
                    display: flex;
                }
                input {
                width: 50%;
                }
            </style>
        </head>

        <body>
            <%Employee employee = (Employee)request.getAttribute("employee");%>
                <div class="profile">
                <form action="editEmployee" method="post">
                    <h1>Employee</h1>
                    <p>ID:
                        <input value="<%= employee.getEmployeeID() %>" name="id" readonly></input>
                    </p>
                    <p>Name:
                        <input value="<%= employee.getName() %>" name="name" readonly></input>
                    </p>
                    <p>Surname:
                        <input value="<%= employee.getSurname() %>" name="surname" readonly></input>
                    </p>
                    <p>Job Title:
                        <input value="<%= employee.getJobTitle() %>" name ="job" readonly></input>
                    </p>
                    <p>Manager ID:
                        <input value="<%= employee.getManagerID() %>" name="managerID" readonly></input>
                    </p>
                    
                        <div class="schedule">
                            <div class="monday">
                                 <p>M</p>
                                <input type="text" name="monday" id="monday" 
                                value="<% if (employee.isMonday()) out.print("T"); else out.print("F");%>" pattern="[T/t/F/f]" title="only T/F allowed">
                            </div>
                            <div class="tuesday">
                               <p>Tu</p>
                                <input type="text" name="tuesday" id="tuesday"
                                 value="<% if (employee.isTuesday()) out.print("T"); else out.print("F"); %>" pattern="[T/t/F/f]" title="only T/F allowed">
                            </div>
                            <div class="wednesday">
                                <p>W</p>
                                <input type="text" name="wednesday" id="wednesday" 
                                value="<% if (employee.isWednesday()) out.print("T"); else out.print("F"); %>" pattern="[T/t/F/f]" title="only T/F allowed">
                            </div>
                            <div class="thursday">
                                <p>Th</p>
                                <input type="text" name="thursday" id="thursday"
                                 value="<% if (employee.isThursday()) out.print("T"); else out.print("F"); %>" pattern="[T/t/F/f]" title="only T/F allowed">
                            </div>
                            <div class="friday">
                                <p>Fr</p>
                                <input type="text" name="friday" id="friday"
                                 value="<% if (employee.isFriday()) out.print("T"); else out.print("F"); %>" pattern="[T/t/F/f]" title="only T/F allowed">
                            </div>
                            <div class="saturday">
                                <p>St</p>
                                <input type="text" name="saturday" id="saturday"
                                 value="<% if (employee.isSaturday()) out.print("T"); else out.print("F"); %>" pattern="[T/t/F/f]" title="only T/F allowed">
                            </div>
                            <div class="sunday">
                                 <p>Sn</p>
                                <input type="text" name="sunday" id="sunday"
                                 value="<% if (employee.isSunday()) out.print("T"); else out.print("F"); %>" pattern="[T/t/F/f]" title="only T/F allowed">
                            </div>
                        </div>
                        <p>Salary per hour:
                            <input type="text" value="<%= employee.getSalaryPerHour() %>"
                             pattern="[0-9]+" name="salary" title="Only numbers allowed">
                        </p>
                      <p>Working hours:
                      from: 
                            <input type="time" value="<%= employee.getFromTime() %>"
                             name="fromTime" >
                            to:  <input type="time" value="<%= employee.getToTime() %>"
                             name="toTime" >
                        </p>
                        <button type="submit">Save</button>
                        <button type="reset">Reset</button>
                    </form>
                </div>
        </body>

        </html>