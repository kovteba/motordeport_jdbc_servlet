<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>




    <p>TTTEEESSSTTTT</p>


    <div class="container">
        <nav class="navbar navbar-expand-sm navbar-light bg-light">
            <a class="navbar-brand" href="#">WebSiteName</a>
            <ul class="navbar-nav">
                <li class="nav-item active"><a class="nav-link" href="#">Home</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Portfolio</a></li>
                <li class="nav-item"><a class="nav-link" href="#">About</a></li>
                <li class="nav-item"><a class="nav-link" href="#">Contact</a></li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#">
                        Dropdown
                    </a>
                    <ul class="dropdown-menu">
                        <li>
                            <form action="admin">
                                <input type="hidden" value="OPEN">
                                <input class="dropdown-item" type="submit" value="OPEN">
                            </form>
                        </li>
                        <li><a class="dropdown-item" href="#">SubPage 2</a></li>
                        <li><a class="dropdown-item" href="#">SubPage 3</a></li>
                    </ul>
                </li>
            </ul>
            <form class="form-inline">
                <input type="text" class="form-control mr-sm-2" placeholder="Search">
                <button type="submit" class="btn btn-success">Submit</button>
            </form>
        </nav>
    </div>


    <nav class="navbar">
        <a class="nav-link dropdown-toggle" data-toggle="dropdown" href="#"></a>
        <ul class="dropdown-menu">
            <li>
                <form action="admin">
                    <input type="hidden" value="OPEN">
                    <input class="dropdown-item" type="submit" value="OPEN">
                </form>
            </li>
        </ul>
    </nav>




    <p>
    <table>
        <tr>
            <td><font size="<%=request.getAttribute("size")%>">посещений </font></td>
            <td><font size="<%=request.getAttribute("size")%>"><%=request.getAttribute("current_count")%>
            </font></td>
        </tr>
        <tr>
            <td><font size="<%=request.getAttribute("size")%>">Дата</font></td>
            <td><font size="<%=request.getAttribute("size")%>"><%=request.getAttribute("date")%>
            </font></td>
        </tr>
    </table>
    </p>
</div>
</body>
</html>