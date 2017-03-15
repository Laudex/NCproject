<%@ page import="java.util.List" %>
<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  User: ааааааааааеееееееее
  Date: 15.02.2017
  Time: 14:18
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Admin Panel</title>
</head>
<body>
<form action="/logOut" method="POST">
    <input type="submit" value="Log out">
</form>
<%
    Map<Offer,Boolean> offerList = (Map<Offer,Boolean>)session.getAttribute("offers");
%>
List of Offers
<br>
<% if (session.getAttribute("error")!=null){
%><p><%=session.getAttribute("error")%></p>
<%
        session.setAttribute("error",null);
    }
%>
<table border="2px" cellpadding="10px">
    <%for (Map.Entry entry : offerList.entrySet()) {
        Offer userOffer = (Offer)entry.getKey();
    %>
    <form action = "/adminRemove" method="POST">
        <tr>
            <%  boolean flag = (Boolean)entry.getValue();
                if (flag == true){%>
            <td><%=userOffer.getName()%></td><input type="hidden" name = "offerId" value="<%=userOffer.getOfferId()%>">
            <td><input type="submit" value="Delete"></td>
            <td><button formaction="/offerInfo">Info</button></td>
            <% }else { %>
            <td><%=userOffer.getName()%> </p></td><input type="hidden" name="offerId" value="<%=userOffer.getOfferId()%>">
            <td><input type="submit" value="Delete" disabled>(Bought)</td>
            <td><button formaction="/offerInfo">Info</button></td>
            <% } %>
        </tr>
    </form>

    <%
        }
    %>
</table>
<br>
<a href = "/addOffer">Add new Offer</a>
</body>
</html>
