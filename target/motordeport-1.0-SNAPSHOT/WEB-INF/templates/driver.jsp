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
  <input type="hidden" name="pageName" value="driver">
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
    <div class="col-md-4"><img src="webresources/img/logo.png" class="logo" alt=""></div>
    <div class="col-md-4 time navBox"><span id="time" style="color: white">00:00:00</span></div>
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
    <div class="col-md-2">
      <ul class="list-group">
        <li class="list-group-item">${requestScope.user.role.roleValue}</li>
        <li class="list-group-item">${requestScope.user.firstName} ${requestScope.user.lastName}</li>
        <li class="list-group-item">${requestScope.user.phoneNumber}</li>
      </ul>
    </div>
    <div class="col-md-10">
      <ul class="nav nav-tabs" id="myTab" role="tablist">
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
        <li class="nav-item">
          <a class="nav-link" id="myFlight-tab" data-toggle="tab"
             href="#myFlight" role="tab" aria-controls="myFlight" aria-selected="false">
            <fmt:message key="label.flights"/>
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
              <th scope="col"><fmt:message key="label.carClass"/></th>
              <th scope="col"><fmt:message key="label.loadCopacity"/></th>
              <th scope="col"><fmt:message key="label.seats"/></th>
              <th scope="col"><fmt:message key="label.luggage"/></th>
              <th scope="col"><fmt:message key="label.air"/></th>
              <th scope="col"><fmt:message key="label.navigatorInCar"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${requestScope.requestsListOpen}" var="requestsListOpen" varStatus="counter">
                <tr>
                  <th>${counter.count}</th>
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
                <input name="freeDriversId" type="hidden" value="${requestScope.user.id}">

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

        <div class="tab-pane fade" id="myFlight" role="tabpanel"
             aria-labelledby="myFlight-tab">


        </div>

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