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

<%String token = (String) request.getAttribute("token");%>
<input type="hidden" name="token" id="token" value="<%=token%>">

<%--GET ALL FLIGHTS--%>
<%List<Flight> flightList = (List<Flight>) request.getAttribute("flightsList");%>

<div class="container-fluid">
    <div class="row">
        <div class="col-md-12">
            <div class="row header">
                <div class="col-md-4"><img src="webresources/img/logo.png" class="logo"></div>
                <div class="col-md-4 time">TIME</div>

                <div class="logIn" id="logIn">
                    <form method="post">
                        <input  type="text" id="phoneNumber" name="phoneNumber" placeholder="Phone Number">
                        <input type="password" id="password" name="password" placeholder="Password">
                        <button type="submit" id="logInBtn" class="logInBtn">submit</button>
                    </form>
                </div>


                <div class="col-md-4 logIn" id="logOut">
                    <form method="post" action="logOut">
                        <input type="hidden" name="token" value="<%=token%>">
                        <input type="submit" value="log out">
                    </form>
                </div>



            </div>
        </div>
    </div>
</div>


<div class="row">
    <div class="col-md-12">
<%--        <div class="logIn">--%>
<%--            <form class="logIn" id="logIn" action="logIn" method="post">--%>
<%--                <input type="text" id="phoneNumber" name="phoneNumber" placeholder="Phone Number">--%>
<%--                <input type="password" id="password" name="password" placeholder="Password">--%>
<%--                <button type="submit" id="logInBtn" class="logInBtn">submit</button>--%>
<%--            </form>--%>
<%--        </div>--%>
    </div>

    <div class="col-md-6">

    </div>
</div>

<div class="container-fluid html base">
    <div class="row">
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
                if (flightList != null) {
                    for (Flight flight : flightList) {
            %>
            <tr>
                <td><%=flight.getFlightNumber()%></td>
                <td><%=flight.getStartDate().toLocalDate()%></td>
                <td><%=flight.getEndDate().toLocalDate()%></td>
                <td><%=flight.getFlightStatus()%></td>
                <td><%=flight.getCar().getCarNumber()%></td>
            </tr>
            <%
                    }
                }
            %>
            </tbody>
        </table>
    </div>
</div>







<script src="webresources/jquery/jquery.js"></script>
<script src="webresources/bootstrap/js/bootstrap.js"></script>
<script src="webresources/bootstrap/js/bootstrap.bundle.js"></script>
<script src="webresources/js/script.js"></script>
</body>
</html>