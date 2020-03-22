<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>
<div>
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