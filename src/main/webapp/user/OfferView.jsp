<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import ="java.util.List" %>
<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="ru.specifications.EmptySpecification" %>
<%@ page import="ru.repository.OfferRepository" %>
<html>
<head>
    <title>Offers</title>
</head>
<body>
<%
    EmptySpecification spec = new EmptySpecification();
    OfferRepository rep = new OfferRepository();
    List<Offer> offerList = (List<Offer>) session.getAttribute("list");
%>
Offer List of <%=session.getAttribute("userName")%>:
<br>
<table border="2px" cellpadding="10px">

    <%for (Iterator<Offer> j = offerList.iterator(); j.hasNext(); ) {
            Offer userOffer = j.next();
    %>
    <form action = "/offerRem" method="POST">
        <tr>
            <td><%=userOffer.getName()%> (Bought)</td><input type="hidden" name = "offerId" value="<%=userOffer.getOfferId()%>">
            <td><input type="submit" value="Remove it!"></td>
        </tr>
    </form>

    <%
    }
    %>
</table>
<a href = "/offerBuy">Buy offer</a>
</body>
</html>
