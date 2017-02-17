<%@ page import="java.util.List" %>
<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.Iterator" %><%--
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
<%
    List<Offer> offerList = (List<Offer>)session.getAttribute("offers");
%>
List of Offers
<br>
<% if (session.getAttribute("deleteError")!=null){
%><p color ="red"><%=session.getAttribute("deleteError")%></p>
<%
        session.setAttribute("deleteError",null);
    }
%>
<table border="2px" cellpadding="10px">
    <%for (Iterator<Offer> j = offerList.iterator(); j.hasNext(); ) {
        Offer userOffer = j.next();
    %>
    <form action = "/adminRemove" method="POST">
        <tr>
            <td><%=userOffer.getName()%></td><input type="hidden" name = "offerId" value="<%=userOffer.getOfferId()%>">
            <td><input type="submit" value="Delete"></td>
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
