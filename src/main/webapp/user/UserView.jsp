<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
<% if (session.getAttribute("enterError")!=null){
    %><p color ="red"><%=session.getAttribute("enterError")%></p>
<%
    session.setAttribute("enterError",null);
}
%>
<h3>Введите имя пользователя:</h3>
<form action = "/offerView" method="POST">
    <input type ="text" name = "name">
    <input type ="submit" value="Показать">
</form>
</body>
</html>