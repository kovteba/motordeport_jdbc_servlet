<%@ page import="ua.nure.kovteba.finaltask.entity.User" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.Flight" %>
<%@ page import="ua.nure.kovteba.finaltask.entity.Request" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="${sessionScope.i18n}"/>


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
<input type="hidden" name="pageName" value="">

<div class="container-fluid perent">
  <div class="row">
    <div class="col-md-12">
      <div class="row header">
        <div class="col-md-4"><img src="webresources/img/logo.png" class="logo"></div>
        <div class="col-md-4 time">
          <span id="time" style="color: white">00:00:00</span>
        </div>
        <div class="col-md-4">
          <form method="post" action="logOut" id="logOut">
            <input type="hidden" name="token" value="<%=token%>">
            <input type="submit" value="log out">
          </form>
          <div class="container changeLanguage navBox">
            <div class="box">
              <div>
                <form method="post" action="ru">
                  <input type="hidden" name="pageName" value="">
                  <input type="hidden" name="token" value="<%=token%>">
                  <input style="height: 20px" type="image" src="webresources/img/ru.png" alt="ОК">
                </form>
              </div>
              <div>
                <form method="post" action="ua">
                  <input type="hidden" name="pageName" value="">
                  <input type="hidden" name="token" value="<%=token%>">
                  <input style="height: 20px" type="image" src="webresources/img/ua.png" alt="ОК">
                </form>
              </div>
              <div>
                <form method="post" action="us">
                  <input type="hidden" name="pageName" value="">
                  <input type="hidden" name="token" value="<%=token%>">
                  <input style="height: 20px" type="image" src="webresources/img/usa.png" alt="ОК">
                </form>
              </div>
            </div>
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
  <div class="container-fluid html base">
    <div class="row">
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