<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@page import="java.util.List" %>
<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="ru.specifications.EmptySpecification" %>
<%@ page import="ru.repository.OfferRepository" %>
<%@ page import="org.springframework.context.ApplicationContext" %>
<%@ page import="org.springframework.context.support.ClassPathXmlApplicationContext" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Offers</title>
</head>
<body>
<%
    List<Offer> offerList = (List<Offer>) session.getAttribute("list");
%>
<div class="row">
    <div class="col-sm-6">
        <nav class="navbar navbar-default">
            <p class="navbar-text">Offer List of <%=session.getAttribute("userName")%>:</p>
            <div class="container-fluid">
                <ul class="nav navbar-nav navbar-right">
                    <li><a href="/logOut"><span class="glyphicon glyphicon-log-out"></span> Logout</a></li>
                </ul>
            </div>
        </nav>
    </div>
</div>
<div class="row">
    <div class="col-sm-6">
        <table class="table table-hover">

            <%
                for (Iterator<Offer> j = offerList.iterator(); j.hasNext(); ) {
                    Offer userOffer = j.next();
            %>
            <form action="/offerRem" method="POST">
                <tr>
                    <td><h4><%=userOffer.getName()%></h4></td>
                    <input type="hidden" name="offerId" value="<%=userOffer.getOfferId()%>">
                    <div class="btn-group">
                        <td align="right"><input class="btn btn-success" type="submit" value="Remove it!">
                            <button class="btn btn-info" formaction="/offerInfo">Info</button>
                        </td>
                    </div>
                </tr>
            </form>

            <%
                }
            %>
        </table>
    </div>
</div>
<form action="/offerBuy" method="GET">
<button class="btn btn-primary">Buy offer</button>
</form>
</body>
</html>
