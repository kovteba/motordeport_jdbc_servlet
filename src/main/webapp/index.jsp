<%@ page import="ua.nure.kovteba.finaltask.entity.User" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.Flight" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.Request" %>
<%@ page import="java.util.List" %>
<html lang="en">
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ADMIN PAGE</title>
    <link type="text/css" rel="stylesheet" href="webresources/bootstrap/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="webresources/bootstrap/css/bootstrap-grid.css">
    <link type="text/css" rel="stylesheet" href="webresources/bootstrap/css/bootstrap-reboot.css">
    <link type="text/css" rel="stylesheet" href="webresources/css/style.css">


</head>
<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row">
                <div class="col-md-12 header">
                    <img src="webresources/img/logo.png" class="logo">
                </div>
            </div>

            <div class="logIn">
                <form action="indexServlet" method="post">
                    <input type="text" name="phoneNumber" placeholder="Phone Number">
                    <input type="password" name="password" placeholder="Password">
                    <input type="submit">
                </form>
            </div>




            <table class="table-hover table-dark tableFlights">
                <thead>
                <tr>
                    <th scope="col">NUMBER</th>
                    <th scope="col">START DATE</th>
                    <th scope="col">END DATE</th>
                    <th scope="col">
                        <div class="navbar">
                            <div class="tableFlights" data-toggle="dropdown">STATUS</div>
                            <ul class="dropdown-menu tableFlights">
                                <li>
                                    <form action="admin">
                                        <input type="hidden" value="OPEN">
                                        <input class="dropdown-item" type="submit" value="OPEN">
                                    </form>
                                </li>
                                <li>
                                    <form action="admin">
                                        <input type="hidden" value="CLOSED">
                                        <input class="dropdown-item" type="submit" value="CLOSED">
                                    </form>
                                </li>
                                <li>
                                    <form action="admin">
                                        <input type="hidden" value="CANCELED">
                                        <input class="dropdown-item" type="submit" value="CANCELED">
                                    </form>
                                </li>
                                <li>
                                    <form action="admin">
                                        <input type="hidden" value="INPROGRESS">
                                        <input class="dropdown-item" type="submit" value="IN PROGRESS">
                                    </form>
                                </li>
                            </ul>
                        </div>
                    </th>
                    <th scope="col">CAR INFO</th>
                </tr>
                </thead>
                <tbody>
                <%
                    List<Flight> list = (List<Flight>) request.getAttribute("flightsList");
                    if (list != null) {
                        for (Flight flight : list) { %>
                <tr>
                    <td><%=flight.getFlightNumber()%>
                    </td>
                    <td><%=flight.getStartDate().toLocalDate()%>
                    </td>
                    <td><%=flight.getEndDate().toLocalDate()%>
                    </td>
                    <td><%=flight.getFlightStatus()%>
                    </td>
                    <td><%=flight.getCar().getCarNumber()%>
                    </td>
                </tr>
                <%
                        }
                    }
                %>
                </tbody>
            </table>

        </div>
    </div>
</div>


<script src="webresources/jquery/jquery.js"></script>
<script src="webresources/bootstrap/js/bootstrap.js"></script>
<script src="webresources/bootstrap/js/bootstrap.bundle.js"></script>
<script src="webresources/js/script.js"></script>
</body>
</html>