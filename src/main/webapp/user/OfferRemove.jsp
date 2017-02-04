<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Remove Offers</title>
</head>
<body>
<%
    List<Offer> offerList = (List<Offer>) session.getAttribute("list");
%>
<form action="/offerRem" method="POST" >
    <select name = "offerId">
        <%for (Iterator<Offer> j = offerList.iterator(); j.hasNext(); ) {
                Offer userOffer = j.next();
        %><option value = "<%=userOffer.getOfferId()%>"><%=userOffer.getName()%></option>
        <%
        }
        %>
    </select>
    <input type="submit" value="Remove">
</form>
</body>
</html>
