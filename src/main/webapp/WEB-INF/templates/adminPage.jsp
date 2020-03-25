<%@ page import="ua.nure.kovteba.finaltask.entity.User" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.Flight" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.Request" %>
<%@ page import="java.util.List" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.Car" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>ADMIN PAGE</title>
    <link type="text/css" rel="stylesheet" href="webresources/css/reset.css">
    <link type="text/css" rel="stylesheet" href="webresources/bootstrap/css/bootstrap.css">
    <link type="text/css" rel="stylesheet" href="webresources/bootstrap/css/bootstrap-grid.css">
    <link type="text/css" rel="stylesheet" href="webresources/bootstrap/css/bootstrap-reboot.css">
    <link type="text/css" rel="stylesheet" href="webresources/css/style.css">
</head>
<body>
<!--parent container-->
<div class="container-fluid html perent">
    <%String token = (String) request.getAttribute("token");%>
    <input type="hidden" name="token" value="<%=token%>">
    <div class="row header">
        <div class="col-md-4"><img src="webresources/img/logo.png" class="logo"></div>
        <div class="col-md-4 time">TIME</div>
        <div class="col-md-4 logIn">LOG IN</div>
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
                <!----------------------------START BLOCK FLIGHTS IN ADMIN PAGE------------------------------------->
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
                            List<Flight> flightList = (List<Flight>) request.getAttribute("flightsList");
                            if (flightList != null) {
                                for (Flight flight : flightList) { %>
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
                                        <%
                                            List<Request> requestsList = (List<Request>) request.getAttribute("requestsList");
                                            if (requestsList != null) {
                                                int indexReq = 1;
                                                for (Request reqL : requestsList) {
                                        %>

                                        <form action="createFlight" method="post">
                                            <tr>
                                                <input type="hidden" name="idRequest" value="<%=reqL.getId()%>">
                                                <input type="hidden" name="token" value="<%=token%>">
                                                <th rowspan="2" scope="row"><%=indexReq%>
                                                </th>
                                                <td><input type="hidden" name="idDriverInReq"
                                                           value="<%=reqL.getDriver().getId()%>"><%=reqL.getDriver().getLastName()%>
                                                </td>
                                                <td style="padding: 10px"><%=reqL.getCarClass().getClassValue()%>
                                                </td>
                                                <td style="padding: 10px"><%=reqL.getLoadCapacity()%>
                                                </td>
                                                <td style="padding: 10px"><%=reqL.getSeats()%>
                                                </td>
                                                <%
                                                    String luggageCompartment = null;
                                                    if (reqL.getLuggageCompartment()) {
                                                        luggageCompartment = "checked";
                                                    }
                                                %>
                                                <td style="padding: 10px"><input type="checkbox" <%=luggageCompartment%>
                                                                                 disabled></td>
                                                <%
                                                    String airConditioning = null;
                                                    if (reqL.getAirConditioning()) {
                                                        airConditioning = "checked";
                                                    }
                                                %>
                                                <td style="padding: 10px"><input type="checkbox" <%=airConditioning%>
                                                                                 disabled></td>
                                                <%
                                                    String navigator = null;
                                                    if (reqL.getNavigator()) {
                                                        navigator = "checked";
                                                    }
                                                %>
                                                <td style="padding: 10px"><input type="checkbox" <%=navigator%>
                                                                                 disabled></td>
                                                <td style="padding: 10px">
                                                    <select name="carValueId" required>
                                                        <option selected disabled="disabled">Choose car</option>
                                                        <%
                                                            List<Car> carList = (List<Car>) request.getAttribute("carsListForRequest");
                                                            if (carList != null) {
                                                                for (Car car : carList) {
                                                        %>
                                                        <option value="<%=car.getId()%>"
                                                                title="<%=car.toString()%>"><%=car.getCarNumber()%>
                                                        </option>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </select>
                                                </td>
                                                <td style="padding: 10px" rowspan="2">
                                                    <button class="btn btn-success">APPROVE</button>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 10px" colspan="2"><input type="text"
                                                                                             name="numbreFlightInReq"
                                                                                             placeholder="Flight Number"
                                                                                             required>
                                                </td>
                                                <td style="padding: 10px" colspan="2"><input type="date"
                                                                                             name="startDateInReq"
                                                                                             placeholder="Start date"
                                                                                             required>
                                                </td>
                                                <td style="padding: 10px"><input type="time" name="startTimeInReq"
                                                                                 placeholder="Start time" required></td>
                                                <td style="padding: 10px" colspan="2"><input type="date"
                                                                                             name="endDateInReq"
                                                                                             placeholder="End date"
                                                                                             required>
                                                </td>
                                                <td style="padding: 10px"><input type="time" name="endTimeInReq"
                                                                                 placeholder="End time" required></td>

                                            </tr>
                                        </form>
                                        <%
                                                    indexReq++;
                                                }
                                            }
                                        %>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--------------------------------END BLOCK FLIGHTS IN ADMIN PAGE------------------------------------->
                <!-------------------------------START BLOCK DISPATCHER IN ADMIN PAGE------------------------------------->
                <div class="tab-pane fade" id="v-pills-dispatchers" role="tabpanel"
                     aria-labelledby="v-pills-dispatchers-tab">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">FIRST NAME</th>
                            <th scope="col">LAST NAME</th>
                            <th scope="col">PHONE NUMBER</th>
                            <th scope="col">ACTION</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<User> dispatcherList = (List<User>) request.getAttribute("dispatcherList");
                            if (dispatcherList != null) {
                                int index = 1;
                                for (User driver : dispatcherList) {
                        %>
                        <tr>
                            <th scope="row"><%=index%>
                            </th>
                            <td><%=driver.getFirstName()%>
                            </td>
                            <td><%=driver.getLastName()%>
                            </td>
                            <td><%=driver.getPhoneNumber()%>
                            </td>
                            <td>
                                <form method="post" action="deleteDriver">
                                    <input type="hidden" name="token" value="<%=token%>">
                                    <input type="hidden" name="idDriver" value="<%=driver.getId()%>">
                                    <input type="submit" class="btn btn-danger" value="DELETE">
                                </form>
                            </td>
                        </tr>
                        <%
                                    index++;
                                }
                            }
                        %>
                        </tbody>
                    </table>
                    <form method="post" action="createDispatcher">
                        <input type="hidden" name="token" value="<%=token%>">
                        <label for="firstNameDispatcher">First name</label>
                        <input id="firstNameDispatcher" name="firstNameDispatcher">
                        <label for="lastNameDispatcher">Last name</label>
                        <input id="lastNameDispatcher" name="lastNameDispatcher">
                        <label for="phoneNumberDispatcher">Phone number</label>
                        <input id="phoneNumberDispatcher" name="phoneNumberDispatcher">
                        <label for="passwordDispatcher">Password</label>
                        <input id="passwordDispatcher" name="passwordDispatcher">
                        <input type="submit" class="btn btn-success">
                    </form>


                </div>
                <!-------------------------------END BLOCK DISPATCHER IN ADMIN PAGE------------------------------------->
                <!-------------------------------START BLOCK DRIVERS IN ADMIN PAGE------------------------------------->
                <div class="tab-pane fade" id="v-pills-drivers" role="tabpanel" aria-labelledby="v-pills-drivers-tab">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">FIRST NAME</th>
                            <th scope="col">LAST NAME</th>
                            <th scope="col">PHONE NUMBER</th>
                            <th scope="col">ACTION</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<User> driverList = (List<User>) request.getAttribute("driversList");
                            if (driverList != null) {
                                int index = 1;
                                for (User driver : driverList) {
                        %>
                        <tr>
                            <th scope="row"><%=index%>
                            </th>
                            <td><%=driver.getFirstName()%>
                            </td>
                            <td><%=driver.getLastName()%>
                            </td>
                            <td><%=driver.getPhoneNumber()%>
                            </td>
                            <td>
                                <form method="post" action="deleteDriver">
                                    <input type="hidden" name="token" value="<%=token%>">
                                    <input type="hidden" name="idDriver" value="<%=driver.getId()%>">
                                    <input type="submit" class="btn btn-danger" value="DELETE">
                                </form>
                            </td>
                        </tr>
                        <%
                                    index++;
                                }
                            }
                        %>
                        </tbody>
                    </table>
                    <form method="post" action="createDriver">
                        <input type="hidden" name="token" value="<%=token%>">
                        <label for="firstNameDriver">First name</label>
                        <input id="firstNameDriver" name="firstNameDriver">
                        <label for="lastNameDriver">Last name</label>
                        <input id="lastNameDriver" name="lastNameDriver">
                        <label for="phoneNumberDriver">Phone number</label>
                        <input id="phoneNumberDriver" name="phoneNumberDriver">
                        <label for="passwordDriver">Password</label>
                        <input id="passwordDriver" name="passwordDriver">
                        <input type="submit" class="btn btn-success">
                    </form>
                </div>
                <!-------------------------------END BLOCK DRIVERS IN ADMIN PAGE------------------------------------->
                <!-------------------------------START CARS DRIVERS IN ADMIN PAGE------------------------------------->
                <div class="tab-pane fade" id="v-pills-cars" role="tabpanel" aria-labelledby="v-pills-cars-tab">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th scope="col">#</th>
                            <th scope="col">Car Brand</th>
                            <th scope="col">Car Class</th>
                            <th scope="col">Car Number</th>
                            <th scope="col">Load Capacity</th>
                            <th scope="col">Seats</th>
                            <th scope="col">Luggage Compartment</th>
                            <th scope="col">Air Conditioning</th>
                            <th scope="col">Navigator</th>
                            <th scope="col">Car Technical Status</th>
                            <th scope="col">Car Status</th>
                        </tr>
                        </thead>
                        <tbody>
                        <%
                            List<Car> carsList = (List<Car>) request.getAttribute("carsList");
                            if (carsList != null) {
                                int index = 1;
                                for (Car car : carsList) {
                        %>
                        <tr>
                            <th scope="row"><%=index%>
                            </th>
                            <td><%=car.getCarBrand().getBrandName()%>
                            </td>
                            <td><%=car.getCarClass().getClassValue()%>
                            </td>
                            <td><%=car.getCarNumber()%>
                            </td>
                            <td><%=car.getLoadCapacity()%>
                            </td>
                            <td><%=car.getSeats()%>
                            </td>
                            <%
                                String luggageCompartment = null;
                                if (car.getLuggageCompartment()) {
                                    luggageCompartment = "checked";
                                }
                            %>
                            <td><input type="checkbox" <%=luggageCompartment%> disabled></td>
                            <%
                                String airConditioning = null;
                                if (car.getAirConditioning()) {
                                    airConditioning = "checked";
                                }
                            %>
                            <td><input type="checkbox" <%=airConditioning%> disabled></td>
                            <%
                                String navigator = null;
                                if (car.getNavigator()) {
                                    navigator = "checked";
                                }
                            %>
                            <td><input type="checkbox" <%=navigator%> disabled></td>
                            <td><%=car.getCarTechnicalStatus().getCarTechnicalStatusValue()%>
                            </td>
                            <td><%=car.getCarStatus().getCarStatusValue()%>
                            </td>
                        </tr>
                        <%
                                    index++;
                                }
                            }
                        %>
                        </tbody>
                    </table>
                    <%----%>
                    <hr class="hrBeetwen">
                    <%----%>
                    <div class="accordion" id="accordionExample">
                        <div class="card">
                            <div class="card-header" id="headingTwo">
                                <h2 class="mb-0">
                                    <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                                            data-target="#collapseTwo" aria-expanded="false"
                                            aria-controls="collapseTwo">
                                        Add new car
                                    </button>
                                </h2>
                            </div>
                            <div id="collapseTwo" class="collapse" aria-labelledby="headingTwo"
                                 data-parent="#accordionExample">
                                <div class="card-body">
                                    <table class="table table-bordered">
                                        <thead>
                                        <tr>
                                            <th scope="col">#</th>
                                            <th scope="col">Car Brand</th>
                                            <th scope="col">Car Class</th>
                                            <th scope="col">Car Number</th>
                                            <th scope="col">Load Capacity</th>
                                            <th scope="col">Seats</th>
                                            <th scope="col">Luggage Compartment</th>
                                            <th scope="col">Air Conditioning</th>
                                            <th scope="col">Navigator</th>
                                            <th scope="col">Car Technical Status</th>
                                            <th scope="col">Action</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <form>
                                            <tr>
                                                <th scope="row">
                                                </th>
                                                <td></td>
                                                <td>
                                                </td>
                                                <td>
                                                </td>
                                                <td>
                                                </td>
                                                <td>
                                                </td>
                                            </tr>
                                        </form>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!-------------------------------START CARS DRIVERS IN ADMIN PAGE------------------------------------->
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12 foot">

        </div>
    </div>
</div>
<script src="webresources/jquery/jquery.js"></script>
<script src="webresources/bootstrap/js/bootstrap.js"></script>
<script src="webresources/bootstrap/js/bootstrap.bundle.js"></script>
<script src="webresources/js/script.js"></script>
</body>
</html>