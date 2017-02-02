<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import ="java.util.List" %>
<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.Iterator" %>
<html>
<head>
    <title>Offers</title>
</head>
<body>

Offer List of <%=session.getAttribute("userName")%>
<ul>
<%List<Offer> offers= (List<Offer>)session.getAttribute("list");
    for (Iterator<Offer> i = offers.iterator(); i.hasNext(); ){
        Offer offer = i.next();
        %>
        <li><%=offer.getName() %></li>
        <br>
            <% }
%>
</ul>
</body>
</html>
