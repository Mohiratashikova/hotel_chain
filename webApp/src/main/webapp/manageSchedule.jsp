<!DOCTYPE html>
<%@page import="java.util.ArrayList"%>
    <%@page import="com.first_sprint.swe.EmployeeManager" %>
        <%@page import="com.first_sprint.swe.Employee" %>

            <html lang="en">

            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>Schedule Management</title>
                <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.13.0/css/all.min.css">
                <link rel="stylesheet" href="styles.css">
                <script src="https://code.jquery.com/jquery-3.5.1.js" integrity="sha256-QWo7LDvxbWT2tbbQ97B53yJnYU3WhH/C8ycbRAkjPDc=" crossorigin=" anonymous"></script>
                <style>
                    table {
                        font-family: arial, sans-serif;
                        border-collapse: collapse;
                        width: 100%;
                    }
                    
                    td,
                    th {
                        border: 1px solid #dddddd;
                        text-align: left;
                        padding: 8px;
                    }
                    
                    tr:nth-child(even) {
                        background-color: #dddddd;
                    }
                    tr {
                    cursor: pointer;}
                    .linkHome {
                    color: #fff;
                    font-size: 25px;
                    text-decoration: none;
                    background-color: #000;
                    border-radius: 10px;
                    padding: 5px;
                    margin-top: 10px;
                    
                    }
                </style>
            </head>

            <body>

                <%	
			EmployeeManager employeeManager = new EmployeeManager();
			ArrayList<Employee> employees = employeeManager.getAllEmployees(); %>
                    <table>
                        <tr>
                            <th>ID</th>
                            <th>Job Title</th>
                            <th>Name</th>
                            <th>Surname</th>
                        </tr>
                        <%for(Employee employee : employees){%>
                            <tr id="<%=employee.getEmployeeID()%>" onclick="manageEmployee(<%=employee.getEmployeeID()%>)">
                                <td>
                                    <%=employee.getEmployeeID()%>
                                </td>
                                <td>
                                    <%=employee.getJobTitle()%>
                                </td>
                                <td>
                                    <%=employee.getName()%>
                                </td>
                                <td>
                                    <%=employee.getSurname()%>
                                </td>
                            </tr>
                            <%}%>
                    </table>
                    <a href="index.html" class="linkHome">Go back</a>
                    <script src="manager.js"></script>
            </body>

            </html>