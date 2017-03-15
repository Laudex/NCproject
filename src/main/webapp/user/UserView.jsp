<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Users</title>
</head>
<body>
<% if (session.getAttribute("enterError")!=null){
    %><p><%=session.getAttribute("enterError")%></p>
<%
    session.setAttribute("enterError",null);
}
%>
<h3>Log in</h3>
<form action = "/offerView" method="POST">
    Login:<br/>
    <input type ="text" name = "name">
    <br/>
    Password:
    <br/>
    <input type="password" name="password">
    <br/>
    <input type ="submit" value="Показать">
</form>
</body>
</html>
