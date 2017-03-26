<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ page import="ru.entity.Offer" %>
<%@ page import="ru.specifications.EmptySpecification" %>
<%@ page import="ru.repository.OfferRepository" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <meta charset="utf-8">
    <title>Buy Offers</title>
</head>
<body>
<%
    ApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
    EmptySpecification spec = new EmptySpecification();
    OfferRepository rep = (OfferRepository) context.getBean("offerRepository");
    List<Offer> list = rep.query(spec);
    List<Offer> offerList = (List<Offer>) session.getAttribute("list");
%>
<t:logout>
    <jsp:attribute name =  "title">
        List of available offers:
    </jsp:attribute>
</t:logout>
<div class="row">
    <div class="col-sm-6">
        <table class="table table-striped">

            <%
                for (Iterator<Offer> i = list.iterator(); i.hasNext(); ) {
                    Offer offer = i.next();
                    int k = 0;
                    for (Iterator<Offer> j = offerList.iterator(); j.hasNext(); ) {
                        Offer userOffer = j.next();
                        if (offer.getOfferId() == userOffer.getOfferId()) {
                            k++;
                        }
                    }
                    if (k == 0) {

            %>
            <form action="/offerGet" method="POST">
                <tr>
                    <td><h4><%=offer.getName()%></h4>
                    </td>
                    <input type="hidden" name="offerId" value="<%=offer.getOfferId()%>">
                    <div class="btn-group">
                        <td align="right"><input  class="btn btn-success" type="submit" value="Get it!">
                            <button class="btn btn-info" formaction="/offerInfo">Info</button>
                        </td>
                    </div>
                </tr>
            </form>

            <%
                    }
                }
            %>
        </table>
    </div>
</div>
<form action="/offerView" method="GET">
    <button class="btn btn-primary">Back</button>
</form>
</body>
</html>
