<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setBundle basename="${sessionScope.i18n}"/>
<!DOCTYPE html>
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
          <a style="color: white; cursor: pointer" data-toggle="modal" data-target="#exampleModal" data-whatever="@mdo">
            <fmt:message key="label.registration"/>
          </a>
          <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel"
               aria-hidden="true">
            <div class="modal-dialog" role="document">
              <div class="modal-content">
                <form method="post" action="">
                  <div class="modal-header">
                    <div><fmt:message key="label.registration"/></div>
                  </div>
                  <div class="modal-body">
                    <div class="form-group">
                      <label for="emailAdmin" class="col-form-label">
                        <fmt:message key="label.email.forCreate"/>
                      </label>
                      <input id="emailAdmin" name="emailAdmin" class="form-control">
                    </div>
                    <div class="form-group">
                      <label for="firstNameAdmin" class="col-form-label">
                        <fmt:message key="label.firstName.forCreate"/>
                      </label>
                      <input id="firstNameAdmin" name="firstNameAdmin" class="form-control">
                    </div>
                    <div class="form-group">
                      <label for="lastNameAdmin" class="col-form-label">
                        <fmt:message key="label.lastName.forCerate"/>
                      </label>
                      <input id="lastNameAdmin" name="lastNameAdmin" class="form-control">
                    </div>
                    <div class="form-group">
                      <label for="phoneNumberAdmin" class="col-form-label">
                        <fmt:message key="label.phoneNumber.forCreate"/>
                      </label>
                      <input id="phoneNumberAdmin" name="phoneNumberAdmin" class="form-control">
                    </div>
                    <div class="form-group">
                      <label for="passwordAdmin" class="col-form-label">
                        <fmt:message key="label.password.forCreate"/>
                      </label>
                      <input type="password" id="passwordAdmin" name="passwordAdmin" class="form-control">
                    </div>
                    <div class="form-group">
                      <label for="secretCode" class="col-form-label" class="form-control">
                        <fmt:message key="label.secretCode"/>
                      </label>
                      <input type="password" id="secretCode" name="secretCode" class="form-control">
                    </div>
                  </div>
                  <div class="modal-footer">
                    <button type="button" class="btn btn-secondary" data-dismiss="modal">
                      <fmt:message key="label.close"/>
                    </button>
                    <input id="registration" type="submit" class="btn btn-success registration" value="<fmt:message key="label.submitUser"/>">
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  <div class="container-fluid base">
    <div class="row">
      <div class="col-md-12">
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