<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import ="java.util.List" %>
<%@ page import="Entity.User" %>
<%@ page import="java.util.Iterator" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
<h3>Введите имя пользователя:</h3>
<form action = "/offerView" method="POST">
    <input type ="text" name = "name">
    <input type ="submit" value="Показать">
</form>
</body>
</html>
