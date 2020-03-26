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
</div>


    <div class="row">
        <div class="col-md-16">
            <div class="logIn">
                <form action="indexServlet" method="post">
                    <input type="text" name="phoneNumber" placeholder="Phone Number">
                    <input type="password" name="password" placeholder="Password">
                    <input type="submit">
                </form>
            </div>
        </div>
        <div class="col-md-6">
            <ul class="nav nav-tabs" id="myTab" role="tablist">
                <li class="nav-item">
                    <a class="nav-link active" id="home-tab" data-toggle="tab" href="#home" role="tab"
                       aria-controls="home" aria-selected="true">Home</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" id="profile-tab" data-toggle="tab" href="#profile" role="tab"
                       aria-controls="profile" aria-selected="false">Profile</a>
                </li>
            </ul>
            <div class="tab-content" id="myTabContent">
                <div class="tab-pane fade show active" id="home" role="tabpanel" aria-labelledby="home-tab">
                    ;oj30j-03-20
                </div>
                <div class="tab-pane fade" id="profile" role="tabpanel" aria-labelledby="profile-tab">
                    sldkfnsdlkfksdmf
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