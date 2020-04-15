<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>

<fmt:setBundle basename="${sessionScope.i18n}"/>
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
  <input type="hidden" id="userToken" name="userToken" value="${sessionScope.userToken}">
  <input type="hidden" name="pageName" value="admin">
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

  <div class="row header">
    <div class="col-md-4"><img src="webresources/img/logo.png" class="logo"></div>
    <div class="col-md-4 time navBox">
      <span id="time" style="color: white">00:00:00</span>
    </div>
    <div class="col-md-4">
      <div class="container changeLanguage navBox">
        <div class="box">
          <div>
            <form method="post" action="ru">
              <input style="height: 20px" type="image" src="webresources/img/ru.png" alt="ОК">
            </form>
          </div>
          <div>
            <form method="post" action="ua">
              <input style="height: 20px" type="image" src="webresources/img/ua.png" alt="ОК">
            </form>
          </div>
          <div>
            <form method="post" action="us">
              <input style="height: 20px" type="image" src="webresources/img/usa.png" alt="ОК">
            </form>
          </div>
        </div>
      </div>
      <div id="logOut" class="logOut">
        <form method="post" action="logOut">
          <input type="submit" value="<fmt:message key="label.logOut"/>">
        </form>
      </div>
      <div class="navbar logIn" id="logIn">
        <div data-toggle="dropdown" class="navBox">
          <div><fmt:message key="label.logIn"/></div>
        </div>
        <form method="post" class="dropdown-menu" accept-charset="ISO-8859-1">
          <div class="col-md-12">
            <div class="row phoneNumber">
              <input type="text" class="phoneNumber" id="phoneNumber" name="phoneNumber" placeholder="Phone Number">
            </div>
            <div class="row password">
              <input type="password" class="password" id="password" name="password" placeholder="Password">
            </div>
            <button type="submit" id="logInBtn" class="logInBtn btn btn-success">
              <fmt:message key="label.logIn"/>
            </button>
          </div>
        </form>
      </div>
    </div>

  </div>
  <div class="row base">
    <!--    left link bar    -->
    <div class="col-md-1">
      <div class="nav flex-column nav-pills" id="v-pills-tab" role="tablist" aria-orientation="vertical">
        <a class="nav-link <%=flightBtn%>> btn-light" id="v-pills-flights-tab" data-toggle="pill"
           href="#v-pills-flights" role="tab" aria-controls="v-pills-flights" aria-selected="true">
          <fmt:message key="label.flights"/>
        </a>
        <a class="nav-link <%=dispatcherBtn%> btn-light" id="v-pills-dispatchers-tab" data-toggle="pill"
           href="#v-pills-dispatchers" role="tab" aria-controls="v-pills-dispatchers" aria-selected="false">
          <fmt:message key="label.dispatchers"/>
        </a>
        <a class="nav-link <%=driverBtn%> btn-light" id="v-pills-drivers-tab" data-toggle="pill" href="#v-pills-drivers"
           role="tab" aria-controls="v-pills-drivers" aria-selected="false">
          <fmt:message key="label.drivers"/>
        </a>
        <a class="nav-link <%=carBtn%> btn-light" id="v-pills-cars-tab" data-toggle="pill" href="#v-pills-cars"
           role="tab" aria-controls="v-pills-cars" aria-selected="false">
          <fmt:message key="label.cars"/>
        </a>
      </div>
    </div>
    <!--    main container    -->
    <div class="col-md-11">
      <div class="tab-content" id="v-pills-tabContent">
        <!----------------------------START BLOCK FLIGHTS IN ADMIN PAGE------------------------------------->
        <div class="tab-pane fade <%=flightShow%>" id="v-pills-flights" role="tabpanel"
             aria-labelledby="v-pills-flights-tab">
          <%------------------START SHOW ALL FLIGHT-----------------%>
          <table class="table-hover table-dark tableFlights">
            <thead>
            <tr>
              <th scope="col"><fmt:message key="label.number.flight"/></th>
              <th scope="col"><fmt:message key="label.startDate"/></th>
              <th scope="col"><fmt:message key="label.endDate"/></th>
              <th scope="col">
                <div class="navbar">
                  <div class="tableFlights" data-toggle="dropdown">
                    <fmt:message key="label.status.flight"/>
                  </div>
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
              <th scope="col"><fmt:message key="label.carInfo"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.flightsList}" var="flight">
              <tr>
                <td>${flight.flightNumber}</td>
                <td>${flight.startDate.toLocalDate()}</td>
                <td>${flight.endDate.toLocalDate()}</td>
                <td>${flight.flightStatus}</td>
                <td>${flight.car.carNumber}</td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <%------------------END SHOW ALL FLIGHT-----------------%>
          <hr class="hrBeetwen">
          <%----%>
          <div class="accordion" id="accordionExampleWorkWithRequest">
            <div class="card">
              <div class="card-header" id="headingWorkWithRequest">
                <h5 class="mb-0">
                  <button class="btn btn-link" type="button" data-toggle="collapse" aria-expanded="true"
                          data-target="#collapseWorkWithRequest" aria-controls="collapseWorkWithRequest">
                    <fmt:message key="label.requests"/>
                  </button>
                </h5>
              </div>
              <div id="collapseWorkWithRequest" class="collapse" aria-labelledby="headingWorkWithRequest"
                   data-parent="#accordionExampleWorkWithRequest">
                <div class="card-body">
                  <ul class="nav nav-tabs" id="myTabRequest" role="tablist">
                    <li class="nav-item">
                      <a class="nav-link active" id="allRequests-tab" data-toggle="tab" href="#allRequests" role="tab"
                         aria-controls="allRequests" aria-selected="true">
                        <fmt:message key="label.allRequests"/>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" id="openRequests-tab" data-toggle="tab" href="#openRequests" role="tab"
                         aria-controls="openRequests" aria-selected="false">
                        <fmt:message key="label.openRequests"/>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" id="closedRequests-tab" data-toggle="tab" href="#closedRequests" role="tab"
                         aria-controls="closedRequests" aria-selected="false">
                        <fmt:message key="label.closedRequests"/>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" id="createRequests-tab" data-toggle="tab"
                         href="#createRequests" role="tab" aria-controls="createRequests" aria-selected="false">
                        <fmt:message key="label.createRequest"/>
                      </a>
                    </li>
                  </ul>
                  <div class="tab-content" id="myTabContent">
                    <div class="tab-pane fade show active" id="allRequests" role="tabpanel"
                         aria-labelledby="allRequests-tab">
                      <%-----------------START SHOW ALL REQUEST------------------------%>
                      <table class="table">
                        <thead>
                        <tr>
                          <th scope="col">#</th>
                          <th scope="col"><fmt:message key="label.driver"/></th>
                          <th scope="col"><fmt:message key="label.carClass"/></th>
                          <th scope="col"><fmt:message key="label.loadCopacity"/></th>
                          <th scope="col"><fmt:message key="label.seats"/></th>
                          <th scope="col"><fmt:message key="label.luggage"/></th>
                          <th scope="col"><fmt:message key="label.air"/></th>
                          <th scope="col"><fmt:message key="label.navigatorInCar"/></th>
                          <th scope="col"><fmt:message key="label.status.request"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.requestsListAll}" var="requestsListAll" varStatus="counter">
                          <tr>
                            <th scope="row">${counter.count}</th>
                            <td style="padding: 10px">${requestsListAll.driver.lastName}</td>
                            <td style="padding: 10px">${requestsListAll.carClass.classValue}</td>
                            <td style="padding: 10px">${requestsListAll.loadCapacity}</td>
                            <td style="padding: 10px">${requestsListAll.seats}</td>
                            <c:choose>
                              <c:when test="${requestsListAll.luggageCompartment}">
                                <td style="padding: 10px"><input type="checkbox" checked disabled></td>
                              </c:when>
                              <c:otherwise>
                                <td style="padding: 10px"><input type="checkbox" disabled></td>
                              </c:otherwise>
                            </c:choose>
                            <c:choose>
                              <c:when test="${requestsListAll.airConditioning}">
                                <td style="padding: 10px"><input type="checkbox" checked disabled></td>
                              </c:when>
                              <c:otherwise>
                                <td style="padding: 10px"><input type="checkbox" disabled></td>
                              </c:otherwise>
                            </c:choose>
                            <c:choose>
                              <c:when test="${requestsListAll.navigator}">
                                <td style="padding: 10px"><input type="checkbox" checked disabled></td>
                              </c:when>
                              <c:otherwise>
                                <td style="padding: 10px"><input type="checkbox" disabled></td>
                              </c:otherwise>
                            </c:choose>
                            <td style="padding: 10px">${requestsListAll.requestStatus.requestSatus}</td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>
                      <%--------------------END SHOW ALL REQUEST-----------------------%>
                    </div>
                    <div class="tab-pane fade" id="openRequests" role="tabpanel"
                         aria-labelledby="openRequests-tab">
                      <%-----------------START SHOW ALL REQUEST OPEN------------------------%>
                      <table class="table">
                        <thead>
                        <tr>
                          <th scope="col">#</th>
                          <th scope="col"><fmt:message key="label.driver"/></th>
                          <th scope="col"><fmt:message key="label.carClass"/></th>
                          <th scope="col"><fmt:message key="label.loadCopacity"/></th>
                          <th scope="col"><fmt:message key="label.seats"/></th>
                          <th scope="col"><fmt:message key="label.luggage"/></th>
                          <th scope="col"><fmt:message key="label.air"/></th>
                          <th scope="col"><fmt:message key="label.navigatorInCar"/></th>
                          <th scope="col"><fmt:message key="label.listCar"/></th>
                          <th scope="col"><fmt:message key="label.actionApprove"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.requestsListOpen}" var="requestsListOpen" varStatus="counter">
                          <form action="createFlight" method="post" accept-charset="ISO-8859-1">
                            <tr>
                              <input type="hidden" name="idRequest" value="${requestsListOpen.id}">
                              <input type="hidden" name="idDriverInReq" value="${requestsListOpen.driver.id}">
                              <th rowspan="2">${counter.count}</th>
                              <td>${requestsListOpen.driver.lastName}</td>
                              <td style="padding: 10px">${requestsListOpen.carClass.classValue}</td>
                              <td style="padding: 10px">${requestsListOpen.loadCapacity}</td>
                              <td style="padding: 10px">${requestsListOpen.seats}</td>
                              <c:choose>
                                <c:when test="${requestsListOpen.luggageCompartment}">
                                  <td style="padding: 10px"><input type="checkbox" checked disabled></td>
                                </c:when>
                                <c:otherwise>
                                  <td style="padding: 10px"><input type="checkbox" disabled></td>
                                </c:otherwise>
                              </c:choose>
                              <c:choose>
                                <c:when test="${requestsListOpen.airConditioning}">
                                  <td style="padding: 10px"><input type="checkbox" checked disabled></td>
                                </c:when>
                                <c:otherwise>
                                  <td style="padding: 10px"><input type="checkbox" disabled></td>
                                </c:otherwise>
                              </c:choose>
                              <c:choose>
                                <c:when test="${requestsListOpen.navigator}">
                                  <td style="padding: 10px"><input type="checkbox" checked disabled></td>
                                </c:when>
                                <c:otherwise>
                                  <td style="padding: 10px"><input type="checkbox" disabled></td>
                                </c:otherwise>
                              </c:choose>
                              <td style="padding: 10px">
                                <select name="carValueId" required>
                                  <option selected disabled="disabled"><fmt:message key="label.chooseCar"/></option>
                                  <c:forEach items="${requestScope.carsListForRequest}" var="carsListForRequest">
                                    <option value="${carsListForRequest.id}" title="${carsListForRequest.toString()}">
                                        ${carsListForRequest.carNumber}
                                    </option>
                                  </c:forEach>
                                </select>
                              </td>
                              <td style="padding: 10px" rowspan="2">
                                <button class="btn btn-success"><fmt:message key="label.approve"/></button>
                              </td>
                            </tr>
                            <tr>
                              <td colspan="2">
                                <label for="flightNumberInReq"><fmt:message key="label.flightNumber"/></label>
                                <input type="text" id="flightNumberInReq" name="numbreFlightInReq"
                                       placeholder="<fmt:message key="label.flightNumber"/>" required>
                              </td>
                              <td style="padding: 10px" colspan="2">
                                <label for="startDateInReq"><fmt:message key="label.startDateIn"/></label>
                                <input type="date" id="startDateInReq" name="startDateInReq" required>
                              </td>
                              <td style="padding: 10px">
                                <label for="startTimeInReq"><fmt:message key="label.startTimeIn"/></label>
                                <input type="time" id="startTimeInReq" name="startTimeInReq" required>
                              </td>
                              <td style="padding: 10px" colspan="2">
                                <label for="endDateInReq"><fmt:message key="label.endDateIn"/></label>
                                <input type="date" id="endDateInReq" name="endDateInReq" required>
                              </td>
                              <td style="padding: 10px">
                                <label for="endTimeInReq"><fmt:message key="label.endTimeIn"/></label>
                                <input type="time" id="endTimeInReq" name="endTimeInReq" required>
                              </td>
                            </tr>
                          </form>
                        </c:forEach>
                        </tbody>
                      </table>
                      <%--------------------END SHOW ALL REQUEST OPEN-----------------------%>
                    </div>
                    <div class="tab-pane fade" id="closedRequests" role="tabpanel"
                         aria-labelledby="closedRequests-tab">
                      <%-----------------START SHOW ALL REQUEST CLOSED------------------------%>
                      <table class="table">
                        <thead>
                        <tr>
                          <th scope="col">#</th>
                          <th scope="col"><fmt:message key="label.driver"/></th>
                          <th scope="col"><fmt:message key="label.carClass"/></th>
                          <th scope="col"><fmt:message key="label.loadCopacity"/></th>
                          <th scope="col"><fmt:message key="label.seats"/></th>
                          <th scope="col"><fmt:message key="label.luggage"/></th>
                          <th scope="col"><fmt:message key="label.air"/></th>
                          <th scope="col"><fmt:message key="label.navigatorInCar"/></th>
                          <th scope="col"><fmt:message key="label.status.request"/></th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${requestScope.requestsListClosed}" var="requestsListClosed"
                                   varStatus="counter">
                          <th scope="row">${counter.count}</th>
                          <td>${requestsListClosed.driver.lastName}</td>
                          <td style="padding: 10px">${requestsListClosed.carClass.classValue}</td>
                          <td style="padding: 10px">${requestsListClosed.loadCapacity}</td>
                          <td style="padding: 10px">${requestsListClosed.seats}</td>
                          <c:choose>
                            <c:when test="${requestsListClosed.luggageCompartment}">
                              <td style="padding: 10px"><input type="checkbox" checked disabled></td>
                            </c:when>
                            <c:otherwise>
                              <td style="padding: 10px"><input type="checkbox" disabled></td>
                            </c:otherwise>
                          </c:choose>
                          <c:choose>
                            <c:when test="${requestsListClosed.airConditioning}">
                              <td style="padding: 10px"><input type="checkbox" checked disabled></td>
                            </c:when>
                            <c:otherwise>
                              <td style="padding: 10px"><input type="checkbox" disabled></td>
                            </c:otherwise>
                          </c:choose>
                          <c:choose>
                            <c:when test="${requestsListClosed.navigator}">
                              <td style="padding: 10px"><input type="checkbox" checked disabled></td>
                            </c:when>
                            <c:otherwise>
                              <td style="padding: 10px"><input type="checkbox" disabled></td>
                            </c:otherwise>
                          </c:choose>
                          <td style="padding: 10px">${requestsListClosed.requestStatus.requestSatus}</td>
                          </tr>
                        </c:forEach>
                        </tbody>
                      </table>
                      <%--------------------END SHOW ALL REQUEST CLOSED-----------------------%>
                    </div>
                    <div class="tab-pane fade" id="createRequests" role="tabpanel"
                         aria-labelledby="createRequests-tab">
                      <%------CREATE REQUEST---------%>
                      <table class="table">
                        <thead>
                        <tr>
                          <th scope="col"><fmt:message key="label.driver"/></th>
                          <th scope="col"><fmt:message key="label.carClass"/></th>
                          <th scope="col"><fmt:message key="label.loadCopacity"/></th>
                          <th scope="col"><fmt:message key="label.seats"/></th>
                          <th scope="col"><fmt:message key="label.luggage"/></th>
                          <th scope="col"><fmt:message key="label.air"/></th>
                          <th scope="col"><fmt:message key="label.navigatorInCar"/></th>
                        </tr>
                        </thead>
                        <tbody>

                        <form action="createRequest" method="post" accept-charset="ISO-8859-1">
                          <tr>
                            <td style="padding: 10px">
                              <select name="freeDriversId" required>
                                <option selected disabled="disabled"><fmt:message key="label.chooseDriver"/></option>
                                <c:forEach items="${requestScope.freeDrivers}" var="freeDrivers">
                                  <option value="${freeDrivers.id}" title="${freeDrivers.toString()}">
                                      ${freeDrivers.firstName} ${freeDrivers.lastName}
                                  </option>
                                </c:forEach>
                              </select>
                            </td>
                            <td style="padding: 10px">
                              <select name="carClassForRequest" required>
                                <option selected disabled="disabled">
                                  <fmt:message key="label.chooseCarClass"/>
                                </option>
                                <c:forEach items="${requestScope.carClassList}" var="carClassList">
                                  <option value="${carClassList.classValue}">
                                      ${carClassList.classValue}
                                  </option>
                                </c:forEach>
                              </select>
                            </td>
                            <td style="padding: 10px">
                              <input type="text" placeholder="<fmt:message key="label.loadCopacity"/>"
                                     name="loadCapacityForRequest" required>
                            </td>
                            <td style="padding: 10px">
                              <input type="text" placeholder="<fmt:message key="label.seats"/>"
                                     name="seatsForRequest" required>
                            </td>
                            <td style="padding: 10px"><input type="checkbox" name="luggageCompartmentForRequest"></td>
                            <td style="padding: 10px"><input type="checkbox" name="airConditioningForRequest"></td>
                            <td style="padding: 10px"><input type="checkbox" name="navigatorForRequest"></td>
                            <td style="padding: 10px" rowspan="2">
                              <button class="btn btn-success"><fmt:message key="label.addNewRequest"/></button>
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
          </div>
        </div>
        <!--------------------------------END BLOCK FLIGHTS IN ADMIN PAGE------------------------------------->
        <!-------------------------------START BLOCK DISPATCHER IN ADMIN PAGE------------------------------------->
        <div class="tab-pane fade <%=dispatcherShow%>" id="v-pills-dispatchers" role="tabpanel"
             aria-labelledby="v-pills-dispatchers-tab">
          <%-----------------------START SHOW ALL DISPATCHER TABLE----------------------%>
          <table class="table table-bordered">
            <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col"><fmt:message key="label.firstName.table"/></th>
              <th scope="col"><fmt:message key="label.lastName.table"/></th>
              <th scope="col"><fmt:message key="label.phoneNumber.table"/></th>
              <th scope="col"><fmt:message key="label.actionWithUser"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.dispatcherList}" var="dispatcherList" varStatus="counter">
              <tr>
                <th scope="row">${counter.count}</th>
                <td>${dispatcherList.firstName}</td>
                <td>${dispatcherList.lastName}</td>
                <td>${dispatcherList.phoneNumber}</td>
                <td>
                  <form method="post" action="deleteUser" accept-charset="ISO-8859-1">
                    <input type="hidden" name="idUserForDelete" value="${dispatcherList.id}">
                    <input type="submit" class="btn btn-danger" value="<fmt:message key="label.deleteUser"/>">
                  </form>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <%-------------------------END SHOW ALL DISPATCHER TABLE-----------------------%>
          <hr class="hrBeetwen">
          <%------------------------START BLOCK ADD NEW DISPATCHER--------------------------%>
          <div class="accordion" id="accordionExampleCreateNewDispatcher">
            <div class="card">
              <div class="card-header" id="headingCreateNewDispatcher">
                <h2 class="mb-0">
                  <button class="btn btn-link" type="button" data-toggle="collapse"
                          data-target="#collapseCreateNewDispatcher" aria-expanded="true"
                          aria-controls="collapseCreateNewDispatcher">
                    <fmt:message key="label.addDispatcher"/>
                  </button>
                </h2>
              </div>
              <div id="collapseCreateNewDispatcher" class="collapse"
                   aria-labelledby="headingCreateNewDispatcher"
                   data-parent="#accordionExampleCreateNewDispatcher">
                <div class="card-body">
                  <form method="post" action="createDispatcher" accept-charset="ISO-8859-1">
                    <label for="firstNameDispatcher"><fmt:message key="label.firstName.forCreate"/></label>
                    <input id="firstNameDispatcher" name="firstNameDispatcher">
                    <label for="lastNameDispatcher"><fmt:message key="label.lastName.forCerate"/></label>
                    <input id="lastNameDispatcher" name="lastNameDispatcher">
                    <label for="phoneNumberDispatcher"><fmt:message key="label.phoneNumber.forCreate"/></label>
                    <input id="phoneNumberDispatcher" name="phoneNumberDispatcher">
                    <label for="passwordDispatcher"><fmt:message key="label.password.forCreate"/></label>
                    <input id="passwordDispatcher" name="passwordDispatcher">
                    <input type="submit" class="btn btn-success" value="<fmt:message key="label.submitUser"/>">
                  </form>
                </div>
              </div>
            </div>
          </div>
          <%-----------------------END BLOCK ADD NEW DISPATCHER-------------------------------%>
        </div>
        <!-------------------------------END BLOCK DISPATCHER IN ADMIN PAGE------------------------------------->
        <!-------------------------------START BLOCK DRIVERS IN ADMIN PAGE------------------------------------->
        <div class="tab-pane fade <%=driversShow%>" id="v-pills-drivers" role="tabpanel"
             aria-labelledby="v-pills-drivers-tab">
          <%---------------START TABLE SHOW ALL DRIVERS--------------------%>
          <table class="table table-bordered">
            <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col"><fmt:message key="label.firstName.table"/></th>
              <th scope="col"><fmt:message key="label.lastName.table"/></th>
              <th scope="col"><fmt:message key="label.phoneNumber.table"/></th>
              <th scope="col"><fmt:message key="label.actionWithUser"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.driversList}" var="driverList" varStatus="counter">
              <tr>
                <th scope="row">${counter.count}</th>
                <td>${driverList.firstName}</td>
                <td>${driverList.lastName}</td>
                <td>${driverList.phoneNumber}</td>
                <td>
                  <form method="post" action="deleteUser" accept-charset="ISO-8859-1">
                    <input type="hidden" name="idUserForDelete" value="${driverList.id}">
                    <input type="submit" class="btn btn-danger" value="<fmt:message key="label.deleteUser"/>">
                  </form>
                </td>
              </tr>
            </c:forEach>
            </tbody>
          </table>
          <%---------------END TABLE SHOW ALL DRIVERS--------------------%>
          <hr class="hrBeetwen">
          <%------------START CREATE NEW DRIVER---------------------%>
          <div class="accordion" id="accordionExampleCreateNewUser">
            <div class="card">
              <div class="card-header" id="headingCreateNewUser">
                <h2 class="mb-0">
                  <button class="btn btn-link" type="button" data-toggle="collapse"
                          data-target="#collapseCreateNewUser" aria-expanded="true"
                          aria-controls="collapseCreateNewUser"><fmt:message key="label.addDriver"/>
                  </button>
                </h2>
              </div>
              <div id="collapseCreateNewUser" class="collapse" aria-labelledby="headingCreateNewUser"
                   data-parent="#accordionExampleCreateNewUser">
                <div class="card-body">
                  <table class="table table-bordered">
                    <form method="post" action="createDriver" accept-charset="ISO-8859-1">
                      <tbody>
                      <tr>
                        <td style="padding: 10px">
                          <label for="firstNameDriver"><fmt:message key="label.firstName.forCreate"/></label>
                          <input id="firstNameDriver" name="firstNameDriver">
                        </td>
                        <td style="padding: 10px">
                          <label for="lastNameDriver"><fmt:message key="label.lastName.forCerate"/></label>
                          <input id="lastNameDriver" name="lastNameDriver">
                        </td>
                      </tr>
                      <tr>
                        <td style="padding: 10px">
                          <label for="phoneNumberDriver"><fmt:message key="label.phoneNumber.forCreate"/></label>
                          <input id="phoneNumberDriver" name="phoneNumberDriver">
                        </td>
                        <td style="padding: 10px">
                          <label for="passwordDriver"><fmt:message key="label.password.forCreate"/></label>
                          <input id="passwordDriver" name="passwordDriver">
                        </td>
                      </tr>
                      <tr>
                        <td colspan="2" style="padding: 10px">
                          <input type="submit" class="btn btn-success" value="<fmt:message key="label.submitUser"/>">
                        </td>
                      </tr>
                      </tbody>
                    </form>
                  </table>
                </div>
              </div>
            </div>
          </div>
          <%------------END CREATE NEW DRIVER---------------------%>
        </div>
        <!-------------------------------END BLOCK DRIVERS IN ADMIN PAGE------------------------------------->
        <!-------------------------------START CARS IN ADMIN PAGE------------------------------------->
        <div class="tab-pane fade <%=carsShow%>" id="v-pills-cars" role="tabpanel"
             aria-labelledby="v-pills-cars-tab">
          <table class="table table-bordered" style="border-collapse:collapse;">
            <%---------------------START TABLE SHOW ALL CARS-----------------%>
            <thead>
            <tr>
              <th scope="col">#</th>
              <th scope="col"><fmt:message key="label.carBrand"/></th>
              <th scope="col"><fmt:message key="label.carClass"/></th>
              <th scope="col"><fmt:message key="label.carNumber"/></th>
              <th scope="col"><fmt:message key="label.loadCopacity"/></th>
              <th scope="col"><fmt:message key="label.seats"/></th>
              <th scope="col"><fmt:message key="label.luggageCompartment"/></th>
              <th scope="col"><fmt:message key="label.airConditioning"/></th>
              <th scope="col"><fmt:message key="label.navigatorInCar"/></th>
              <th scope="col"><fmt:message key="label.carTechnicalStatus"/></th>
              <th scope="col"><fmt:message key="label.carStatus"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.carsList}" var="carsList" varStatus="counter">

              <tr data-toggle="collapse" data-target="#demo${carsList.id}" class="accordion-toggle">
                <th scope="row">${counter.count}</th>
                <td>${carsList.carBrand.brandName}</td>
                <td>${carsList.carClass.classValue}</td>
                <td>${carsList.carNumber}</td>
                <td>${carsList.loadCapacity}</td>
                <td>${carsList.seats}</td>
                <td style="padding: 10px">
                  <c:choose>
                    <c:when test="${carsList.luggageCompartment}">
                      <input type="checkbox" checked disabled>
                    </c:when>
                    <c:otherwise>
                      <input type="checkbox" disabled>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td style="padding: 10px">
                  <c:choose>
                    <c:when test="${carsList.airConditioning}">
                      <input type="checkbox" checked disabled>
                    </c:when>
                    <c:otherwise>
                      <input type="checkbox" disabled>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td style="padding: 10px">
                  <c:choose>
                    <c:when test="${carsList.navigator}">
                      <input type="checkbox" checked disabled>
                    </c:when>
                    <c:otherwise>
                      <input type="checkbox" disabled>
                    </c:otherwise>
                  </c:choose>
                </td>
                <td>
                  <div class="navbar">
                    <div data-toggle="dropdown" class="navBox">
                      <div>${carsList.carTechnicalStatus.carTechnicalStatusValue}</div>
                    </div>
                    <ul class="dropdown-menu">
                      <c:forEach items="${requestScope.carTechnicalStatusList}" var="carTechnicalStatusList">
                        <li>
                          <form action="changeTechnicalStatus" method="post" class="navBox" accept-charset="ISO-8859-1">
                            <input type="hidden" value="${carsList.id}" name="carId">
                            <input class="dropdown-item" type="submit" name="technicalValue"
                                   value="${carTechnicalStatusList.carTechnicalStatusValue}">
                          </form>
                        </li>
                      </c:forEach>
                    </ul>
                  </div>
                </td>
                <td>
                  <div class="navbar">
                    <div data-toggle="dropdown" class="navBox">
                      <div>${carsList.carStatus.carStatusValue}</div>
                    </div>
                    <ul class="dropdown-menu">
                      <c:forEach items="${requestScope.carStatus}" var="carStatus">
                        <li>
                          <form action="changeStatus" method="post" class="navBox" accept-charset="ISO-8859-1">
                            <input type="hidden" value="${carsList.id}" name="carId">
                            <input class="dropdown-item" type="submit" name="carStatusValue"
                                   value="${carStatus.carStatusValue}">
                          </form>
                        </li>
                      </c:forEach>
                    </ul>
                  </div>
                </td>
              </tr>
              <%----%>
              <form method="post" action="changeInfoCar" accept-charset="ISO-8859-1">
                <tr>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">${counter.count}</div>
                  </td>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">
                      <select name="carBrandId" required>
                        <option value="${carsList.carBrand.brandName}" selected >${carsList.carBrand.brandName}</option>
                        <c:forEach items="${requestScope.carBrandList}" var="carBrandList">
                          <option value="${carBrandList.brandName}">${carBrandList.brandName}</option>
                        </c:forEach>
                      </select>
                    </div>
                  </td>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">
                      <select name="carClassValue" required>
                        <option value="${carsList.carClass.classValue}" selected >${carsList.carClass.classValue}</option>
                        <c:forEach items="${requestScope.carClassList}" var="carClassList">
                          <option value="${carClassList.classValue}">${carClassList.classValue}</option>
                        </c:forEach>
                      </select>
                    </div>
                  </td>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">
                      <input style="width: 100px" name="carNumber" value="${carsList.carNumber}">
                    </div>
                  </td>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">
                      <input style="width: 100px" name="loadCapacity" value="${carsList.loadCapacity}">
                    </div>
                  </td>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">
                      <input style="width: 100px" name="seats" value="${carsList.seats}">
                    </div>
                  </td>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">
                      <c:choose>
                        <c:when test="${carsList.luggageCompartment}">
                          <input type="checkbox" name="luggageCompartment" checked>
                        </c:when>
                        <c:otherwise>
                          <input type="checkbox" name="luggageCompartment">
                        </c:otherwise>
                      </c:choose>
                    </div>
                  </td>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">
                      <c:choose>
                        <c:when test="${carsList.airConditioning}">
                          <input type="checkbox" name="airConditioning" checked>
                        </c:when>
                        <c:otherwise>
                          <input type="checkbox" name="airConditioning">
                        </c:otherwise>
                      </c:choose>
                    </div>
                  </td>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">
                      <c:choose>
                        <c:when test="${carsList.navigator}">
                          <input type="checkbox" name="navigator" checked>
                        </c:when>
                        <c:otherwise>
                          <input type="checkbox" name="navigator">
                        </c:otherwise>
                      </c:choose>
                    </div>
                  </td>
                  <td class="hiddenRow">
                    <div id="demo${carsList.id}" class="accordian-body collapse">
                      <button type="submit" class="btn btn-success">ok</button>
                    </div>
                  </td>
                <input type="hidden" name="carTechnicalStatusValue" value="${carsList.carTechnicalStatus.carTechnicalStatusValue}">
                <input type="hidden" name="carStatusValue" value="${carsList.carStatus.carStatusValue}">
                <input type="hidden" name="carIdForChange" value="${carsList.id}">
              </form>
              <form method="post" action="deleteCar" accept-charset="ISO-8859-1">
                <td class="hiddenRow">
                  <input type="hidden" name="idCarForDelete" value="${carsList.id}">
                  <div id="demo${carsList.id}" class="accordian-body collapse">
                    <button type="submit" class="btn btn-danger">delete</button>
                  </div>
                </td>
                </tr>
              </form>
            </c:forEach>
            </tbody>
          </table>
          <%---------------------END TABLE SHOW ALL CARS-----------------%>
          <hr class="hrBeetwen">
          <%-------------------START BLOCK FOR CREATE NEW CAR AND CREATE CAR BRAND----------------------%>
          <div class="accordion" id="accordionExampleCarAndBrand">
            <div class="card">
              <div class="card-header" id="headingCarAndBrand">
                <h2 class="mb-0">
                  <button class="btn btn-link collapsed" type="button" data-toggle="collapse"
                          data-target="#collapseCarAndBrand" aria-expanded="false"
                          aria-controls="collapseCarAndBrand"><fmt:message key="label.addCar"/>
                  </button>
                </h2>
              </div>
              <div id="collapseCarAndBrand" class="collapse" aria-labelledby="headingCarAndBrand"
                   data-parent="#accordionExampleCarAndBrand">
                <div class="card-body">
                  <ul class="nav nav-tabs" id="myTabCar" role="tablist">
                    <li class="nav-item">
                      <a class="nav-link active" id="addNewCar-tab" data-toggle="tab" href="#addNewCar" role="tab"
                         aria-controls="addNewCar" aria-selected="true">
                        <fmt:message key="label.addCar"/>
                      </a>
                    </li>
                    <li class="nav-item">
                      <a class="nav-link" id="addNewCarBrand-tab" data-toggle="tab" href="#addNewCarBrand"
                         role="tab" aria-controls="addNewCarBrand" aria-selected="false">
                        <fmt:message key="label.addCarBrand"/>
                      </a>
                    </li>
                  </ul>
                  <div class="tab-content" id="myTabContentCar">
                    <div class="tab-pane fade show active" id="addNewCar" role="tabpanel"
                         aria-labelledby="home-tab">
                      <%---------------START BLOCK CREATE NEW CAR--------------------%>
                      <table class="table table-bordered">
                        <tbody>
                        <form action="createCar" method="post" accept-charset="ISO-8859-1">
                          <tr>
                            <td style="padding: 10px">
                              <select name="carBrandId" required>
                                <option selected disabled="disabled"><fmt:message key="label.chooseBrand"/></option>
                                <c:forEach items="${requestScope.carBrandList}" var="carBrandList">
                                  <option value="${carBrandList.id}">${carBrandList.brandName}</option>
                                </c:forEach>
                              </select>
                            </td>
                            <td style="padding: 10px">
                              <select name="carClassValue" required>
                                <option selected disabled="disabled"><fmt:message key="label.chooseCarClass"/></option>
                                <c:forEach items="${requestScope.carClassList}" var="carClassList">
                                  <option value="${carClassList.classValue}">${carClassList.classValue}</option>
                                </c:forEach>
                              </select>
                            </td>
                            <td style="padding: 10px">
                              <select name="carTechnicalStatusValue" required>
                                <option selected disabled="disabled"><fmt:message
                                 key="label.ChooseTechnStatus"/></option>
                                <c:forEach items="${requestScope.carTechnicalStatusList}" var="carTechnicalStatusList">
                                  <option value="${carTechnicalStatusList.carTechnicalStatusValue}">
                                      ${carTechnicalStatusList.carTechnicalStatusValue}
                                  </option>
                                </c:forEach>
                              </select>
                            </td>
                          </tr>
                          <tr>
                            <td style="padding: 10px">
                              <input type="text" placeholder="<fmt:message key="label.carNumber"/>" name="carNumber"
                                     required>
                            </td>
                            <td style="padding: 10px">
                              <input type="text" placeholder="<fmt:message key="label.loadCopacity"/>"
                                     name="loadCapacity" required>
                            </td>
                            <td style="padding: 10px">
                              <input type="text" placeholder="<fmt:message key="label.seats"/>" name="seats" required>
                            </td>
                          </tr>
                          <tr>
                            <td style="padding: 10px">
                              <label for="luggageCompartment"><fmt:message key="label.luggageCompartment"/>
                                <input type="checkbox" id="luggageCompartment" name="luggageCompartment">
                              </label>
                            </td>
                            <td style="padding: 10px">
                              <label for="airConditioning"><fmt:message key="label.airConditioning"/>
                                <input type="checkbox" id="airConditioning" name="airConditioning">
                              </label>
                            </td>
                            <td style="padding: 10px">
                              <label for="navigator"><fmt:message key="label.navigatorInCar"/>
                                <input type="checkbox" id="navigator" name="navigator">
                              </label>
                            </td>
                          </tr>
                          <tr>
                            <td colspan="3" style="padding: 10px">
                              <input class="btn btn-success" type="submit" value="<fmt:message key="label.addCar"/>">
                            </td>
                          </tr>
                        </form>
                        </tbody>
                      </table>
                      <%---------------END BLOCK CREATE NEW CAR--------------------%>
                    </div>
                    <div class="tab-pane fade" id="addNewCarBrand" role="tabpanel" aria-labelledby="profile-tab">
                      <div class="row">
                        <div class="col-md-6">
                          <%--------------START BLOCK SHOW ALL CAR BRAND--------------%>
                          <table class="table table-bordered">
                            <c:forEach items="${requestScope.carBrandList}" var="carBrandList">
                              <tr>
                                <td>${carBrandList.brandName}</td>
                                <td>
                                  <form method="post" action="deleteCarBrand" accept-charset="ISO-8859-1">
                                    <input type="hidden" value="${carBrandList.id}" name="carBrandId">
                                    <input type="submit" value="<fmt:message key="label.deleteUser"/>"
                                           class="btn btn-danger">
                                  </form>
                                </td>
                              </tr>
                            </c:forEach>
                          </table>
                          <%--------------END BLOCK SHOW ALL CAR BRAND--------------%>
                        </div>
                        <div class="col-md-6">
                          <%--------------START BLOCK ADD NEW CAR BRAND--------------%>
                          <table class="table table-bordered">
                            <form method="post" action="createCarBrand" accept-charset="ISO-8859-1">
                              <tr>
                                <td style="padding: 10px">
                                  <label for="carBrandName"><fmt:message key="label.carBrandName"/></label>
                                  <input type="text" id="carBrandName" name="carBrandName">
                                </td>
                              </tr>
                              <tr>
                                <td style="padding: 10px">
                                  <input class="btn btn-success" type="submit"
                                         value="<fmt:message key="label.addCarBrand"/>">
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