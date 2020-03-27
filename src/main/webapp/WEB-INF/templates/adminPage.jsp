<%@ page import="java.util.List" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.*" %>
<%@ page import="ua.nure.kovteba.finaltask.enumlist.CarClass" %>
<%@ page import="ua.nure.kovteba.finaltask.enumlist.CarTechnicalStatus" %>
<%@ page import="ua.nure.kovteba.finaltask.enumlist.CarStatus" %>
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
    <input type="hidden" name="token" id="token" value="<%=token%>">
    <%--CHOOSE ACTIVE TAB--%>
    <%String flightShow = (String) request.getAttribute("flightShow");%>
    <%String dispatcherShow = (String) request.getAttribute("dispatcherShow");%>
    <%String driversShow = (String) request.getAttribute("driversShow");%>
    <%String carsShow = (String) request.getAttribute("carsShow");%>
    <%--CHOOSE ACTIVE BUTTON--%>
    <%String flightBtn = (String) request.getAttribute("flightBtn");%>
    <%String dispatcherBtn = (String) request.getAttribute("dispatcherBtn");%>
    <%String driverBtn = (String) request.getAttribute("driverBtn");%>
    <%String carBtn = (String) request.getAttribute("carBtn");%>
    <%--GET ALL FLIGHTS--%>
    <%List<Flight> flightList = (List<Flight>) request.getAttribute("flightsList");%>

    <%--GET ALL REQUESTS OPEN--%>
    <%List<Request> requestsListOpen = (List<Request>) request.getAttribute("requestsListOpen");%>
    <%--GET ALL REQUESTS ALL--%>
    <%List<Request> requestsListAll = (List<Request>) request.getAttribute("requestsListAll");%>
    <%--GET ALL REQUESTS CLOSED--%>
    <%List<Request> requestsListClosed = (List<Request>) request.getAttribute("requestsListClosed");%>

    <%--GET DISPATCHER LIST--%>
    <%List<User> dispatcherList = (List<User>) request.getAttribute("dispatcherList");%>
    <%--GET DRIVER LIST--%>
    <%List<User> driverList = (List<User>) request.getAttribute("driversList");%>
    <%--GET DRIVER LIST--%>
    <%List<User> freeDrivers = (List<User>) request.getAttribute("freeDrivers");%>
    <%--GET CAR LIST FOR REQUEST GOOD TETCHNICAL STATUS AND FREE--%>
    <%List<Car> carsListForRequest = (List<Car>) request.getAttribute("carsListForRequest");%>
    <%--GET ALL CARS LIST--%>
    <%List<Car> carsList = (List<Car>) request.getAttribute("carsList");%>
    <%--GET CAR TECHNICAL STATUS LIST--%>
    <%List<CarTechnicalStatus> carTechnicalStatusList = (List<CarTechnicalStatus>) request.getAttribute("carTechnicalStatusList");%>
    <%--GET CAR STATUS LIST--%>
    <%List<CarStatus> carStatusList = (List<CarStatus>) request.getAttribute("carStatus");%>
    <%--GET CAR BRAND LIST--%>
    <%List<CarBrand> carBrandList = (List<CarBrand>) request.getAttribute("carBrandList");%>
    <%--GET CAR CLASS LIST--%>
    <%List<CarClass> carClassList = (List<CarClass>) request.getAttribute("carClassList");%>
    <div class="row header">
        <div class="col-md-4"><img src="webresources/img/logo.png" class="logo"></div>
        <div class="col-md-4 time">TIME</div>
        <div class="col-md-4 logIn" id="logIn">LOG IN</div>
        <div class="col-md-4 logIn" id="logOut">LOG OUT</div>
    </div>
    <div class="row base">
        <!--    left link bar    -->
        <div class="col-md-2">
            <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
                <a class="nav-link <%=flightBtn%>> btn-light" id="v-pills-flights-tab" data-toggle="pill"
                   href="#v-pills-flights"
                   role="tab" aria-controls="v-pills-flights" aria-selected="true">Flights</a>
                <a class="nav-link <%=dispatcherBtn%> btn-light" id="v-pills-dispatchers-tab" data-toggle="pill"
                   href="#v-pills-dispatchers" role="tab" aria-controls="v-pills-dispatchers" aria-selected="false">Dispatchers</a>
                <a class="nav-link <%=driverBtn%> btn-light" id="v-pills-drivers-tab" data-toggle="pill"
                   href="#v-pills-drivers"
                   role="tab" aria-controls="v-pills-drivers" aria-selected="false">Drivers</a>
                <a class="nav-link <%=carBtn%> btn-light" id="v-pills-cars-tab" data-toggle="pill" href="#v-pills-cars"
                   role="tab"
                   aria-controls="v-pills-cars" aria-selected="false">Cars</a>
            </div>
        </div>
        <!--    main container    -->
        <div class="col-md-10">
            <div class="tab-content" id="v-pills-tabContent">
                <!----------------------------START BLOCK FLIGHTS IN ADMIN PAGE------------------------------------->
                <div class="tab-pane fade <%=flightShow%>" id="v-pills-flights" role="tabpanel"
                     aria-labelledby="v-pills-flights-tab">
                    <%------------------START SHOW ALL FLIGHT-----------------%>
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
                    <%------------------END SHOW ALL FLIGHT-----------------%>
                    <hr class="hrBeetwen">
                    <%----%>
                    <div class="accordion" id="accordionExampleWorkWithRequest">
                        <div class="card">
                            <div class="card-header" id="headingWorkWithRequest">
                                <h5 class="mb-0">
                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseWorkWithRequest"
                                            aria-expanded="true" aria-controls="collapseWorkWithRequest">Requests
                                    </button>
                                </h5>
                            </div>
                            <div id="collapseWorkWithRequest" class="collapse" aria-labelledby="headingWorkWithRequest" data-parent="#accordionExampleWorkWithRequest">
                                <div class="card-body">
                                    <ul class="nav nav-tabs" id="myTabRequest" role="tablist">
                                        <li class="nav-item">
                                            <a class="nav-link active" id="allRequests-tab" data-toggle="tab" href="#allRequests" role="tab" aria-controls="allRequests" aria-selected="true">All Request</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="openRequests-tab" data-toggle="tab" href="#openRequests" role="tab" aria-controls="openRequests" aria-selected="false">Open Request</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="closedRequests-tab" data-toggle="tab" href="#closedRequests" role="tab" aria-controls="closedRequests" aria-selected="false">Closed Request</a>
                                        </li>
                                        <li class="nav-item">
                                            <a class="nav-link" id="createRequests-tab" data-toggle="tab" href="#createRequests" role="tab" aria-controls="createRequests" aria-selected="false">Create Request</a>
                                        </li>
                                    </ul>
                                    <div class="tab-content" id="myTabContent">
                                        <div class="tab-pane fade show active" id="allRequests" role="tabpanel" aria-labelledby="allRequests-tab">
                                            <%-----------------START SHOW ALL REQUEST------------------------%>
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
                                                    <th scope="col">Status</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <%
                                                    if (requestsListAll != null) {
                                                        int indexReqAll = 1;
                                                        for (Request reqAll : requestsListAll) {
                                                %>
                                                <tr>
                                                    <th scope="row"><%=indexReqAll%></th>
                                                    <td><%=reqAll.getDriver().getLastName()%></td>
                                                    <td style="padding: 10px"><%=reqAll.getCarClass().getClassValue()%></td>
                                                    <td style="padding: 10px"><%=reqAll.getLoadCapacity()%></td>
                                                    <td style="padding: 10px"><%=reqAll.getSeats()%></td>
                                                    <%
                                                        String luggageCompartment = null;
                                                        if (reqAll.getLuggageCompartment()) {
                                                            luggageCompartment = "checked";
                                                        }
                                                    %>
                                                    <td style="padding: 10px"><input type="checkbox" <%=luggageCompartment%> disabled></td>
                                                    <%
                                                        String airConditioning = null;
                                                        if (reqAll.getAirConditioning()) {
                                                            airConditioning = "checked";
                                                        }
                                                    %>
                                                    <td style="padding: 10px"><input type="checkbox" <%=airConditioning%> disabled></td>
                                                    <%
                                                        String navigator = null;
                                                        if (reqAll.getNavigator()) {
                                                            navigator = "checked";
                                                        }
                                                    %>
                                                    <td style="padding: 10px"><input type="checkbox" <%=navigator%> disabled></td>
                                                    <td style="padding: 10px"><%=reqAll.getRequestStatus().getRequestSatus()%></td>
                                                </tr>
                                                <%
                                                            indexReqAll++;
                                                        }
                                                    }
                                                %>
                                                </tbody>
                                            </table>
                                            <%--------------------END SHOW ALL REQUEST-----------------------%>
                                        </div>
                                        <div class="tab-pane fade" id="openRequests" role="tabpanel" aria-labelledby="openRequests-tab">
                                            <%-----------------START SHOW ALL REQUEST OPEN------------------------%>
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
                                                    if (requestsListOpen != null) {
                                                        int indexReqOpen = 1;
                                                        for (Request reqOpen : requestsListOpen) {
                                                %>
                                                <form action="createFlight" method="post">
                                                    <tr>
                                                        <input type="hidden" name="idRequest" value="<%=reqOpen.getId()%>">
                                                        <input type="hidden" name="token" value="<%=token%>">
                                                        <input type="hidden" name="idDriverInReq" value="<%=reqOpen.getDriver().getId()%>">
                                                        <th rowspan="2"><%=indexReqOpen%></th>
                                                        <td><%=reqOpen.getDriver().getLastName()%></td>
                                                        <td style="padding: 10px"><%=reqOpen.getCarClass().getClassValue()%></td>
                                                        <td style="padding: 10px"><%=reqOpen.getLoadCapacity()%></td>
                                                        <td style="padding: 10px"><%=reqOpen.getSeats()%></td>
                                                        <%
                                                            String luggageCompartment = null;
                                                            if (reqOpen.getLuggageCompartment()) {
                                                                luggageCompartment = "checked";
                                                            }
                                                        %>
                                                        <td style="padding: 10px"><input type="checkbox" <%=luggageCompartment%> disabled></td>
                                                        <%
                                                            String airConditioning = null;
                                                            if (reqOpen.getAirConditioning()) {
                                                                airConditioning = "checked";
                                                            }
                                                        %>
                                                        <td style="padding: 10px"><input type="checkbox" <%=airConditioning%> disabled></td>
                                                        <%
                                                            String navigator = null;
                                                            if (reqOpen.getNavigator()) {
                                                                navigator = "checked";
                                                            }
                                                        %>
                                                        <td style="padding: 10px"><input type="checkbox" <%=navigator%> disabled></td>
                                                        <td style="padding: 10px">
                                                            <select name="carValueId" required>
                                                                <option selected disabled="disabled">Choose car</option>
                                                                <%
                                                                    if (carsListForRequest != null) {
                                                                        for (Car car : carsListForRequest) {
                                                                %>
                                                                <option value="<%=car.getId()%>" title="<%=car.toString()%>">
                                                                    <%=car.getCarNumber()%>
                                                                </option>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                        </td>
                                                        <td style="padding: 10px" rowspan="2"><button class="btn btn-success">APPROVE</button></td>
                                                    </tr>
                                                    <tr>
                                                        <td  colspan="2">
                                                            <label for="flightNumberInReq">Flight number</label>
                                                            <input type="text" id="flightNumberInReq" name="numbreFlightInReq" placeholder="Flight Number" required>
                                                        </td>
                                                        <td style="padding: 10px" colspan="2">
                                                            <label for="startDateInReq">Start date</label>
                                                            <input type="date" id="startDateInReq" name="startDateInReq" placeholder="Start date" required>
                                                        </td>
                                                        <td style="padding: 10px">
                                                            <label for="startTimeInReq">Start Time</label>
                                                            <input type="time" id="startTimeInReq" name="startTimeInReq" placeholder="Start time" required>
                                                        </td>
                                                        <td style="padding: 10px" colspan="2">
                                                            <label for="endDateInReq">End date</label>
                                                            <input type="date" id="endDateInReq" name="endDateInReq" placeholder="End date" required>
                                                        </td>
                                                        <td style="padding: 10px">
                                                            <label for="endTimeInReq">End Time</label>
                                                            <input type="time" id="endTimeInReq" name="endTimeInReq" placeholder="End time" required>
                                                        </td>
                                                    </tr>
                                                </form>
                                                <%
                                                            indexReqOpen++;
                                                        }
                                                    }
                                                %>
                                                </tbody>
                                            </table>
                                            <%--------------------END SHOW ALL REQUEST OPEN-----------------------%>
                                        </div>
                                        <div class="tab-pane fade" id="closedRequests" role="tabpanel" aria-labelledby="closedRequests-tab">
                                            <%-----------------START SHOW ALL REQUEST CLOSED------------------------%>
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
                                                    <th scope="col">Status</th>
                                                </tr>
                                                </thead>
                                                <tbody>
                                                <%
                                                    if (requestsListClosed != null) {
                                                        int indexReqClosed = 1;
                                                        for (Request reqClosed : requestsListAll) {
                                                %>
                                                <tr>
                                                    <th scope="row"><%=indexReqClosed%></th>
                                                    <td><%=reqClosed.getDriver().getLastName()%></td>
                                                    <td style="padding: 10px"><%=reqClosed.getCarClass().getClassValue()%></td>
                                                    <td style="padding: 10px"><%=reqClosed.getLoadCapacity()%></td>
                                                    <td style="padding: 10px"><%=reqClosed.getSeats()%></td>
                                                    <%
                                                        String luggageCompartment = null;
                                                        if (reqClosed.getLuggageCompartment()) {
                                                            luggageCompartment = "checked";
                                                        }
                                                    %>
                                                    <td style="padding: 10px"><input type="checkbox" <%=luggageCompartment%> disabled></td>
                                                    <%
                                                        String airConditioning = null;
                                                        if (reqClosed.getAirConditioning()) {
                                                            airConditioning = "checked";
                                                        }
                                                    %>
                                                    <td style="padding: 10px"><input type="checkbox" <%=airConditioning%> disabled></td>
                                                    <%
                                                        String navigator = null;
                                                        if (reqClosed.getNavigator()) {
                                                            navigator = "checked";
                                                        }
                                                    %>
                                                    <td style="padding: 10px"><input type="checkbox" <%=navigator%> disabled></td>
                                                    <td style="padding: 10px"><%=reqClosed.getRequestStatus().getRequestSatus()%></td>
                                                </tr>
                                                <%
                                                            indexReqClosed++;
                                                        }
                                                    }
                                                %>
                                                </tbody>
                                            </table>
                                            <%--------------------END SHOW ALL REQUEST CLOSED-----------------------%>
                                        </div>
                                        <div class="tab-pane fade" id="createRequests" role="tabpanel" aria-labelledby="createRequests-tab">
                                            <%------CREATE REQUEST---------%>
                                            <table class="table">
                                                <thead>
                                                <tr>
                                                    <th scope="col">Driver</th>
                                                    <th scope="col">Car Class</th>
                                                    <th scope="col">Load Copacity</th>
                                                    <th scope="col">Seats</th>
                                                    <th scope="col">Luggage</th>
                                                    <th scope="col">Air</th>
                                                    <th scope="col">Navigator</th>
                                                </tr>
                                                </thead>
                                                <tbody>

                                                <form action="createRequest" method="post">
                                                    <tr>
                                                        <input type="hidden" name="token" value="<%=token%>">
                                                        <td style="padding: 10px">
                                                            <select name="freeDriversId" required>
                                                                <option selected disabled="disabled">Choose driver</option>
                                                                <%
                                                                    if (freeDrivers != null) {
                                                                        for (User driver : freeDrivers) {
                                                                %>
                                                                <option value="<%=driver.getId()%>" title="<%=driver.toString()%>">
                                                                    <%=driver.getFirstName()%> <%=driver.getLastName()%>
                                                                </option>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                        </td>
                                                        <td style="padding: 10px">
                                                            <select name="carClassForRequest" required>
                                                                <option selected disabled="disabled">Choose carClass</option>
                                                                <%
                                                                    if (carClassList != null) {
                                                                        for (CarClass carClass : carClassList) {
                                                                %>
                                                                <option value="<%=carClass.getClassValue()%>">
                                                                    <%=carClass.getClassValue()%>
                                                                </option>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                        </td>
                                                        <td style="padding: 10px"><input type="text" placeholder="LoadCapacity" name="loadCapacityForRequest" required></td>
                                                        <td style="padding: 10px"><input type="text" placeholder="Seats" name="seatsForRequest" required></td>
                                                        <td style="padding: 10px"><input type="checkbox" name="luggageCompartmentForRequest"></td>
                                                        <td style="padding: 10px"><input type="checkbox" name="airConditioningForRequest"></td>
                                                        <td style="padding: 10px"><input type="checkbox" name="navigatorForRequest"></td>
                                                        <td style="padding: 10px" rowspan="2"><button class="btn btn-success">Add New request</button></td>
                                                    </tr>
                                                </form>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>



                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <!--------------------------------END BLOCK FLIGHTS IN ADMIN PAGE------------------------------------->
                <!-------------------------------START BLOCK DISPATCHER IN ADMIN PAGE------------------------------------->
                <div class="tab-pane fade <%=dispatcherShow%>" id="v-pills-dispatchers" role="tabpanel" aria-labelledby="v-pills-dispatchers-tab">
                    <%-----------------------START SHOW ALL DISPATCHER TABLE----------------------%>
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
                            if (dispatcherList != null) {
                                int index = 1;
                                for (User driver : dispatcherList) {
                        %>
                        <tr>
                            <th scope="row"><%=index%></th>
                            <td><%=driver.getFirstName()%></td>
                            <td><%=driver.getLastName()%></td>
                            <td><%=driver.getPhoneNumber()%></td>
                            <td>
                                <form method="post" action="deleteUser">
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
                    <%-------------------------END SHOW ALL DISPATCHER TABLE-----------------------%>
                    <hr class="hrBeetwen">
                    <%------------------------START BLOCK ADD NEW DISPATCHER--------------------------%>
                    <div class="accordion" id="accordionExampleCreateNewDispatcher">
                        <div class="card">
                            <div class="card-header" id="headingCreateNewDispatcher">
                                <h2 class="mb-0">
                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseCreateNewDispatcher" aria-expanded="true"
                                            aria-controls="collapseCreateNewDispatcher">Add dispatcher
                                    </button>
                                </h2>
                            </div>
                            <div id="collapseCreateNewDispatcher" class="collapse" aria-labelledby="headingCreateNewDispatcher"
                                 data-parent="#accordionExampleCreateNewDispatcher">
                                <div class="card-body">
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
                            </div>
                        </div>
                    </div>
                    <%-----------------------END BLOCK ADD NEW DISPATCHER-------------------------------%>
                </div>
                <!-------------------------------END BLOCK DISPATCHER IN ADMIN PAGE------------------------------------->
                <!-------------------------------START BLOCK DRIVERS IN ADMIN PAGE------------------------------------->
                <div class="tab-pane fade <%=driversShow%>" id="v-pills-drivers" role="tabpanel" aria-labelledby="v-pills-drivers-tab">
                    <%---------------START TABLE SHOW ALL DRIVERS--------------------%>
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
                            if (driverList != null) {
                                int index = 1;
                                for (User driver : driverList) {
                        %>
                        <tr>
                            <th scope="row"><%=index%></th>
                            <td><%=driver.getFirstName()%></td>
                            <td><%=driver.getLastName()%></td>
                            <td><%=driver.getPhoneNumber()%></td>
                            <td>
                                <form method="post" action="deleteUser">
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
                    <%---------------END TABLE SHOW ALL DRIVERS--------------------%>
                    <hr class="hrBeetwen">
                    <%------------START CREATE NEW USER---------------------%>
                    <div class="accordion" id="accordionExampleCreateNewUser">
                        <div class="card">
                            <div class="card-header" id="headingCreateNewUser">
                                <h2 class="mb-0">
                                    <button class="btn btn-link" type="button" data-toggle="collapse" data-target="#collapseCreateNewUser" aria-expanded="true"
                                            aria-controls="collapseCreateNewUser">add driver
                                    </button>
                                </h2>
                            </div>
                            <div id="collapseCreateNewUser" class="collapse" aria-labelledby="headingCreateNewUser"
                                 data-parent="#accordionExampleCreateNewUser">
                                <div class="card-body">
                                    <table class="table table-bordered">
                                        <form method="post" action="createDriver">
                                            <input type="hidden" name="token" value="<%=token%>">
                                            <tbody>
                                            <tr>
                                                <td style="padding: 10px">
                                                    <label for="firstNameDriver">First name</label>
                                                    <input id="firstNameDriver" name="firstNameDriver">
                                                </td>
                                                <td style="padding: 10px">
                                                    <label for="lastNameDriver">Last name</label>
                                                    <input id="lastNameDriver" name="lastNameDriver">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td style="padding: 10px">
                                                    <label for="phoneNumberDriver">Phone number</label>
                                                    <input id="phoneNumberDriver" name="phoneNumberDriver">
                                                </td>
                                                <td style="padding: 10px">
                                                    <label for="passwordDriver">Password</label>
                                                    <input id="passwordDriver" name="passwordDriver">
                                                </td>
                                            </tr>
                                            <tr>
                                                <td colspan="2" style="padding: 10px">
                                                    <input type="submit" class="btn btn-success" value="Add">
                                                </td>
                                            </tr>
                                            </tbody>
                                        </form>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%------------END CREATE NEW USER---------------------%>
                </div>
                <!-------------------------------END BLOCK DRIVERS IN ADMIN PAGE------------------------------------->
                <!-------------------------------START CARS IN ADMIN PAGE------------------------------------->
                <div class="tab-pane fade <%=carsShow%>" id="v-pills-cars" role="tabpanel" aria-labelledby="v-pills-cars-tab">
                    <table class="table table-bordered">
                        <%---------------------START TABLE SHOW ALL CARS-----------------%>
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
                            <td>
                                <div class="navbar">
                                    <div data-toggle="dropdown" class="navBox">
                                        <div><%=car.getCarTechnicalStatus().getCarTechnicalStatusValue()%></div>
                                    </div>
                                    <ul class="dropdown-menu">
                                        <%
                                            if (carTechnicalStatusList != null) {
                                                for (CarTechnicalStatus carTechnicalStatus : carTechnicalStatusList) {
                                        %>
                                        <li>
                                            <form action="changeTechnicalStatus" method="post" class="navBox">
                                                <input type="hidden" name="token" value="<%=token%>">
                                                <input type="hidden" value="<%=car.getId()%>" name="carId">
                                                <input class="dropdown-item" type="submit"value="<%=carTechnicalStatus.getCarTechnicalStatusValue()%>" name="technicalValue">
                                            </form>
                                        </li>
                                        <%
                                                }
                                            }
                                        %>
                                    </ul>
                                </div>
                            </td>
                            <td>
                                <div class="navbar">
                                    <div data-toggle="dropdown"  class="navBox">
                                        <div><%=car.getCarStatus().getCarStatusValue()%></div>
                                    </div>
                                    <ul class="dropdown-menu">
                                        <%
                                            if (carStatusList != null) {
                                                for (CarStatus carStatus : carStatusList) {
                                        %>
                                        <li>
                                            <form action="changeStatus" method="post" class="navBox">
                                                <input type="hidden" name="token" value="<%=token%>">
                                                <input type="hidden" value="<%=car.getId()%>" name="carId">
                                                <input class="dropdown-item" type="submit"value="<%=carStatus.getCarStatusValue()%>" name="carStatusValue">
                                            </form>
                                        </li>
                                        <%
                                                }
                                            }
                                        %>

                                    </ul>
                                </div>
                            </td>
                        </tr>
                        <%
                                    index++;
                                }
                            }
                        %>
                        </tbody>
                    </table>
                    <%---------------------END TABLE SHOW ALL CARS-----------------%>
                    <hr class="hrBeetwen">
                    <%-------------------START BLOCK FOR CREATE NEW CAR AND CREATE CAR BRAND----------------------%>
                    <div class="accordion" id="accordionExampleCarAndBrand">
                        <div class="card">
                            <div class="card-header" id="headingCarAndBrand">
                                <h2 class="mb-0">
                                    <button class="btn btn-link collapsed" type="button" data-toggle="collapse" data-target="#collapseCarAndBrand" aria-expanded="false"
                                            aria-controls="collapseCarAndBrand">Add car
                                    </button>
                                </h2>
                            </div>
                            <div id="collapseCarAndBrand" class="collapse" aria-labelledby="headingCarAndBrand" data-parent="#accordionExampleCarAndBrand">
                                <div class="card-body">
                                    <ul class="nav nav-tabs" id="myTabCar" role="tablist">
                                        <li class="nav-item"><a class="nav-link active" id="addNewCar-tab" data-toggle="tab" href="#addNewCar" role="tab" aria-controls="addNewCar" aria-selected="true">Add car</a></li>
                                        <li class="nav-item"><a class="nav-link" id="addNewCarBrand-tab" data-toggle="tab" href="#addNewCarBrand" role="tab" aria-controls="addNewCarBrand" aria-selected="false">Add car Brand</a></li>
                                    </ul>
                                    <div class="tab-content" id="myTabContentCar">
                                        <div class="tab-pane fade show active" id="addNewCar" role="tabpanel" aria-labelledby="home-tab">
                                            <%---------------START BLOCK CREATE NEW CAR--------------------%>
                                            <table class="table table-bordered">
                                                <tbody>
                                                <form action="createCar" method="post">
                                                    <tr>
                                                        <td style="padding: 10px">
                                                            <select name="carBrandId" required>
                                                                <option selected disabled="disabled">Choose brand</option>
                                                                <%
                                                                    if (carBrandList != null) {
                                                                        for (CarBrand carBrand : carBrandList) {
                                                                %>
                                                                <option value="<%=carBrand.getId()%>"><%=carBrand.getBrandName()%></option>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                        </td>
                                                        <td style="padding: 10px">
                                                            <select name="carClassValue" required>
                                                                <option selected disabled="disabled">Choose Class</option>
                                                                <%

                                                                    if (carClassList != null) {
                                                                        for (CarClass carClass : carClassList) {
                                                                %>
                                                                <option value="<%=carClass.getClassValue()%>"><%=carClass.getClassValue()%></option>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                        </td>
                                                        <td style="padding: 10px">
                                                            <select name="carTechnicalStatusValue" required>
                                                                <option selected disabled="disabled">Choose Techn Status</option>
                                                                <%
                                                                    if (carTechnicalStatusList != null) {
                                                                        for (CarTechnicalStatus carTechnicalStatus : carTechnicalStatusList) {
                                                                %>
                                                                <option value="<%=carTechnicalStatus.getCarTechnicalStatusValue()%>"><%=carTechnicalStatus.getCarTechnicalStatusValue()%></option>
                                                                <%
                                                                        }
                                                                    }
                                                                %>
                                                            </select>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td style="padding: 10px"><input type="text" placeholder="Car Number" name="carNumber" required></td>
                                                        <td style="padding: 10px"><input type="text" placeholder="Load Capacity" name="loadCapacity" required></td>
                                                        <td style="padding: 10px"><input type="text" placeholder="Seats" name="seats" required></td>
                                                    </tr>
                                                    <tr>
                                                        <td style="padding: 10px">
                                                            <label for="luggageCompartment">Luggage Compartment
                                                                <input type="checkbox" placeholder="Car Number" id="luggageCompartment" name="luggageCompartment">
                                                            </label>
                                                        </td>
                                                        <td style="padding: 10px">
                                                            <label for="airConditioning">Air Conditioning
                                                                <input type="checkbox" placeholder="Load Capacity" id="airConditioning" name="airConditioning">
                                                            </label>
                                                        </td>
                                                        <td style="padding: 10px">
                                                            <label for="navigator">Navigator
                                                                <input type="checkbox" placeholder="Seats" id="navigator" name="navigator">
                                                            </label>
                                                        </td>
                                                    </tr>
                                                    <tr>
                                                        <td colspan="3" style="padding: 10px"><input class="btn btn-success" type="submit" value="ADD"></td>
                                                    </tr>
                                                </form>
                                                </tbody>
                                            </table>
                                            <%---------------END BLOCK CREATE NEW CAR--------------------%>
                                        </div>
                                        <div class="tab-pane fade" id="addNewCarBrand" role="tabpanel"
                                             aria-labelledby="profile-tab">
                                            <div class="row">
                                                <div class="col-md-6">
                                                    <%--------------START BLOCK SHOW ALL CAR BRAND--------------%>
                                                    <table class="table table-bordered">
                                                        <%
                                                            if (carBrandList != null) {
                                                                for (CarBrand carBrand : carBrandList) {
                                                        %>
                                                        <tr>
                                                            <td><%=carBrand.getBrandName()%></td>
                                                            <td>
                                                                <form method="post" action="deleteCarBrand">
                                                                    <input type="hidden" name="token" value="<%=token%>">
                                                                    <input type="hidden" value="<%=carBrand.getId()%>" name="carBrandId">
                                                                    <input type="submit" value="delete" class="btn btn-danger">
                                                                </form>
                                                            </td>
                                                        </tr>
                                                        <%
                                                                }
                                                            }
                                                        %>
                                                    </table>
                                                        <%--------------END BLOCK SHOW ALL CAR BRAND--------------%>
                                                </div>

                                                <div class="col-md-6">
                                                    <%--------------START BLOCK ADD NEW CAR BRAND--------------%>
                                                    <table class="table table-bordered">
                                                        <form method="post" action="createCarBrand">
                                                            <input type="hidden" name="token" value="<%=token%>">
                                                            <tr>
                                                                <td style="padding: 10px">
                                                                    <label for="carBrandName">Car Brand Name</label>
                                                                    <input type="text" id="carBrandName" name="carBrandName">
                                                                </td>
                                                            </tr>
                                                            <tr>
                                                                <td style="padding: 10px">
                                                                    <input class="btn btn-success" type="submit" value="AddNewBrand">
                                                                </td>
                                                            </tr>
                                                        </form>
                                                    </table>
                                                    <%--------------ENDRT BLOCK ADD NEW CAR BRAND--------------%>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%-------------------START BLOCK FOR CREATE NEW CAR AND CREATE CAR BRAND----------------------%>
                </div>
                <!-------------------------------END CARS IN ADMIN PAGE------------------------------------->
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