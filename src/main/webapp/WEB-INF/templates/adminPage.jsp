<%@ page import="ua.nure.kovteba.finaltask.entity.User" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.Flight" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.Request" %>
<%@ page import="java.util.List" %>
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
<!--parent container-->
<div class="container-fluid html">
    <div class="row">
        <div class="col-md-12 header">
            <img src="webresources/img/logo.png" class="logo">
        </div>
    </div>
    <div class="row base">
        <!--    left link bar    -->
        <div class="col-md-2">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link active btn-light" id="v-pills-flights-tab" data-toggle="pill" href="#v-pills-flights"
                   role="tab" aria-controls="v-pills-flights" aria-selected="true">Flights</a>
                <a class="nav-link btn-light" id="v-pills-dispatchers-tab" data-toggle="pill"
                   href="#v-pills-dispatchers" role="tab" aria-controls="v-pills-dispatchers" aria-selected="false">Dispatchers</a>
                <a class="nav-link btn-light" id="v-pills-drivers-tab" data-toggle="pill" href="#v-pills-drivers"
                   role="tab" aria-controls="v-pills-drivers" aria-selected="false">Drivers</a>
                <a class="nav-link btn-light" id="v-pills-cars-tab" data-toggle="pill" href="#v-pills-cars" role="tab"
                   aria-controls="v-pills-cars" aria-selected="false">Cars</a>
            </div>
        </div>
        <!--    main container    -->
        <div class="col-md-10">
            <div class="tab-content" id="v-pills-tabContent">
                <div class="tab-pane fade show active" id="v-pills-flights" role="tabpanel"
                     aria-labelledby="v-pills-flights-tab">
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
                            List<Flight> list = (List<Flight>) request.getAttribute("flightList");
                            if (list != null) {
                                for (Flight flight : list) { %>
                        <tr>
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
                    <!--                    -->
                    <hr class="hrBeetwen">
                    <!--                    -->
                    <div class="accordion" id="accordionExample">
                        <div class="card">
                            <div class="card-header" id="headingOne">
                                <h5 class="mb-0">
                                    <button class="btn btn-link" type="button" data-toggle="collapse"
                                            data-target="#collapseOne" aria-expanded="true" aria-controls="collapseOne">
                                        Requests
                                    </button>
                                </h5>
                            </div>
                            <div id="collapseOne" class="collapse" aria-labelledby="headingOne"
                                 data-parent="#accordionExample">
                                <div class="card-body">
                                    <table class="table">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Driver</th>
                                            <th scope="col">Car Class</th>
                                            <th scope="col">Load Copacity</th>
                                            <th scope="col">Seats</th>
                                            <th scope="col">Luggage</th>
                                            <th scope="col">Air</th>
                                            <th scope="col">Navigator</th>
                                            <th scope="col">List Car</th>
                                            <th scope="col">Approve</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <tr>
                                            <form>
                                                <th scope="row">1</th>
                                                <td>Mark</td>
                                                <td>Economy</td>
                                                <td>1000</td>
                                                <td>20</td>
                                                <td>True</td>
                                                <td>True</td>
                                                <td>True</td>
                                                <td>
                                                    <select>
                                                        <option disabled selected>Choose car</option>
                                                        <option>Пункт 1</option>
                                                        <option>Пункт 2</option>
                                                    </select>
                                                </td>
                                                <td>
                                                    <button class="btn btn-success">APPROVE</button>
                                                </td>
                                            </form>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--                -->
                <div class="tab-pane fade" id="v-pills-dispatchers" role="tabpanel"
                     aria-labelledby="v-pills-dispatchers-tab">
                    s;kjlbfs;dkjnfsd
                </div>
                <div class="tab-pane fade" id="v-pills-drivers" role="tabpanel" aria-labelledby="v-pills-drivers-tab">
                    w;dkfjns;dkvn
                </div>
                <div class="tab-pane fade" id="v-pills-cars" role="tabpanel" aria-labelledby="v-pills-cars-tab">
                    s;dlknsd;lknc
                </div>
            </div>
        </div>
    </div>
</div>
<script src="webresources/jquery/jquery.js"></script>
<script src="webresources/bootstrap/js/bootstrap.js"></script>
<script src="webresources/bootstrap/js/bootstrap.bundle.js"></script>
<script src="webresources/js/script.js"></script>
</body>
</html>