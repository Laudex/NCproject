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
<form action="/offerGet" method="POST" >
<select name = "offerId">
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
        %><option value = "<%=offer.getOfferId()%>"><%=offer.getName()%></option>
    <%}
    }
    %>
</select>
    <input type="submit" value="Purchase">
</form>
</body>
</html>
