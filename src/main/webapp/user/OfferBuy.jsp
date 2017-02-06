<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import ="ru.entity.Offer"%>
<%@ page import="ru.specifications.EmptySpecification" %>
<%@ page import="ru.repository.OfferRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<html>
<head>
    <meta charset="utf-8">
    <title>Buy Offers</title>
</head>
<body>
<%
    EmptySpecification spec = new EmptySpecification();
    OfferRepository rep = new OfferRepository();
    List<Offer> list = rep.query(spec);
    List<Offer> offerList = (List<Offer>) session.getAttribute("list");
%>

<table>

    <%for (Iterator<Offer> i = list.iterator(); i.hasNext(); ){
        Offer offer = i.next();
        int k = 0;
    for (Iterator<Offer> j = offerList.iterator(); j.hasNext(); ) {
        Offer userOffer = j.next();
        if (offer.getOfferId() == userOffer.getOfferId()) {
            k++;
        }
    }
    if (k == 0){

        %><form action = "/offerGet" method="POST">
        <tr>
        <td><%=offer.getName()%></td><input type="hidden" name="offerId" value = "<%=offer.getOfferId()%>">
    <td><input type="submit" value="Get it!"></td>
    </tr>
</form>

    <%}
    }
    %>
</table>
</body>
</html>
