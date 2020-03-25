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
            <div class="row header">
                <div class="col-md-4"><img src="webresources/img/logo.png" class="logo"></div>
                <div class="col-md-4 time">TIME</div>
                <div class="col-md-4 logIn">LOG IN</div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-md-12">
            <div class="logIn">
                <form action="indexServlet" method="post">
                    <input type="text" name="phoneNumber" placeholder="Phone Number">
                    <input type="password" name="password" placeholder="Password">
                    <input type="submit">
                </form>
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