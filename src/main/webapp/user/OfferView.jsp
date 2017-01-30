<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import ="java.util.List" %>
<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.Iterator" %>
<html>
<head>
    <title>Offers</title>
</head>
<body>
<% if (request.getAttribute("error")!="nope"){
   %><h2><%=request.getAttribute("error")%></h2>
<%
} else {%>
Offer List of <%=request.getAttribute("name")%>
<ul>
<%List<Offer> offers= (List<Offer>)request.getAttribute("list");
    for (Iterator<Offer> i = offers.iterator(); i.hasNext(); ){
        Offer offer = i.next();
        %>
        <li><%=offer.getName() %></li>
        <br>
            <% }
}%>
</ul>

</body>
</html>
