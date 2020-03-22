<%@ page import="ua.nure.kovteba.finaltask.entity.User" %>
<html>
<head>
    <title>Student Record</title>
</head>
<body>
<%
    if (request.getAttribute("studentRecord") != null) {
        User user = (User) request.getAttribute("studentRecord");
%>

<h1>Student Record</h1>
<div>ID: <%= user.getId()%></div>
<div>First Name: <%= user.getFirstName()%></div>
<div>Last Name: <%= user.getLastName()%></div>

<%
} else {
%>

<h1>No student record found.</h1>

<% } %>
</body>
</html>