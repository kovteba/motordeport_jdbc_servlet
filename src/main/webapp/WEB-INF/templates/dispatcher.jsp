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
<input type="hidden" id="userToken" name="userToken" value="${sessionScope.userToken}">
<input type="hidden" id="pageName" name="pageName" value="">

<div class="container-fluid perent">
  <div class="row">
    <div class="col-md-12">
      <div class="row header">
        <div class="col-md-4"><img src="webresources/img/logo.png" class="logo"></div>
        <div class="col-md-4 time ">
          <span id="time" style="color: white">00:00:00</span>
        </div>
        <div class="col-md-4 link">
          <div class="changeLanguage">
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
            <form method="get" action="">
              <a style="color: white; text-underline: none" href="logOut"><fmt:message key="label.logOut"/></a>
            </form>
          </div>
          <div class="navbar logIn" id="logIn">
            <div data-toggle="dropdown" class="navBox">
              <div><fmt:message key="label.logIn"/></div>
            </div>
            <form method="post" class="dropdown-menu">
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

    </div>
  </div>
  <div class="container-fluid base">
    <div class="row">
      <div class="col-md-2">
        <ul class="list-group">
          <li class="list-group-item">${requestScope.user.role.roleValue}</li>
          <li class="list-group-item">${requestScope.user.firstName} ${requestScope.user.lastName}</li>
          <li class="list-group-item">${requestScope.user.phoneNumber}</li>
        </ul>
      </div>
      <div class="col-md-10">
        <%------------------START SHOW ALL FLIGHT-----------------%>
        <table class="table-hover table-dark tableFlights">
          <thead>
          <tr>
            <th scope="col">
              <div class="navbar">
                <div class="navBox" data-toggle="dropdown">
                  <div><fmt:message key="label.number.flight"/></div>
                </div>
                <ul class="dropdown-menu">
                  <li>
                    <form action="sortFlightByNumberDown" method="post" accept-charset="ISO-8859-1">
                      <input type="hidden" name="flightNumberValue" value="flightNumberDown">
                      <input type="submit" class="dropdown-item" type="submit" value="↑">
                    </form>
                  </li>
                  <li>
                    <form action="sortFlightByNumberUp" method="post" accept-charset="ISO-8859-1">
                      <input type="hidden" name="flightNumberValue" value="flightNumberUp">
                      <input type="submit" class="dropdown-item" type="submit" value="↓">
                    </form>
                  </li>
                </ul>
              </div>
            </th>
            <th scope="col">
              <div class="navbar">
                <div class="navBox" data-toggle="dropdown">
                  <div><fmt:message key="label.startDate"/></div>
                </div>
                <ul class="dropdown-menu">
                  <li>
                    <form action="sortFlightByStartDateDown" method="post" accept-charset="ISO-8859-1">
                      <input type="hidden" name="flightStartDateValue" value="startDateDown">
                      <input type="submit" class="dropdown-item" type="submit" value="↑">
                    </form>
                  </li>
                  <li>
                    <form action="sortFlightByStartDateUp" method="post" accept-charset="ISO-8859-1">
                      <input type="hidden" name="flightStartDateValue" value="startDateUp">
                      <input type="submit" class="dropdown-item" type="submit" value="↓">
                    </form>
                  </li>
                </ul>
              </div>
            </th>
            <th scope="col">
              <div class="navbar">
                <div class="navBox" data-toggle="dropdown">
                  <div><fmt:message key="label.endDate"/></div>
                </div>
                <ul class="dropdown-menu">
                  <li>
                    <form action="sortFlightByEndDateDown" method="post" accept-charset="ISO-8859-1">
                      <input type="hidden" name="flightEndDateValue" value="endDateDown">
                      <input type="submit" class="dropdown-item" type="submit" value="↑">
                    </form>
                  </li>
                  <li>
                    <form action="sortFlightByEndDateUp" method="post" accept-charset="ISO-8859-1">
                      <input type="hidden" name="flightEndDateValue" value="endDateUp">
                      <input type="submit" class="dropdown-item" type="submit" value="↓">
                    </form>
                  </li>
                </ul>
              </div>
            </th>
            <th scope="col">
              <div class="navbar">
                <div class="navBox" data-toggle="dropdown">
                  <div><fmt:message key="label.status.flight"/></div>
                </div>
                <ul class="dropdown-menu">
                  <c:forEach items="${requestScope.flightStatusList}" var="flightStatusList">
                    <li>
                      <form action="sortFlightByStatus" method="post" accept-charset="ISO-8859-1">
                        <input type="hidden" name="flightStatusValue" value="${flightStatusList.statusValue}">
                        <input type="submit" class="dropdown-item" type="submit"
                               value="${flightStatusList.statusValue}">
                      </form>
                    </li>
                  </c:forEach>
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

                        <tr>
                          <form action="createFlight" method="post" accept-charset="ISO-8859-1">
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
                                  <option value="${carsListForRequest.id}"
                                          title="<fmt:message key="label.carClass"/>: ${carsListForRequest.carClass};<fmt:message key="label.loadCopacity"/>: ${carsListForRequest.loadCapacity};<fmt:message key="label.seats"/>: ${carsListForRequest.seats};<fmt:message key="label.luggage"/>: ${carsListForRequest.luggageCompartment};<fmt:message key="label.air"/>: ${carsListForRequest.airConditioning};<fmt:message key="label.navigatorInCar"/>: ${carsListForRequest.navigator};">
                                      ${carsListForRequest.carNumber}
                                  </option>
                                </c:forEach>
                              </select>
                            </td>
                            <td style="padding: 10px">
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
                          </form>
                          <form action="deleteRequest" method="post" accept-charset="ISO-8859-1">
                            <td style="padding: 10px">
                              <input type="hidden" name="idRequestForDelete" value="${requestsListOpen.id}">
                              <button class="btn btn-danger"><fmt:message key="label.deleteUser"/></button>
                            </td>
                          </form>
                        </tr>
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
                </div>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</div>
<div class="row">
  <div class="col-md-12 foot">

  </div>
</div>


<script src="webresources/jquery/jquery.js"></script>
<script src="webresources/bootstrap/js/bootstrap.js"></script>
<script src="webresources/bootstrap/js/bootstrap.bundle.js"></script>
<script src="webresources/js/script.js"></script>
</body>
</html>