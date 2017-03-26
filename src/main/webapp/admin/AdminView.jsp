<%@ page import="java.util.List" %>
<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Admin Panel</title>
</head>
<body>
<%
    Map<Offer, Boolean> offerList = (Map<Offer, Boolean>) session.getAttribute("offers");
%>
<% if (session.getAttribute("error") != null) {
%><div class="row">
    <div class="col-sm-6">
        <div class="alert alert-danger">
            <%=session.getAttribute("error")%>
        </div>
    </div>
</div>
<%
        session.setAttribute("error", null);
    }
%>
<% if (session.getAttribute("success") != null) {
%>
<div class="row">
    <div class="col-sm-6">
        <div class="alert alert-success">
            <%=session.getAttribute("success")%>
        </div>
    </div>
</div>
<%

        session.setAttribute("success", null);
    }
%>
<t:logout>
    <jsp:attribute name =  "title">
        List of offers:
    </jsp:attribute>
</t:logout>
<div class="row">
    <div class="col-sm-6">
        <table class="table table-striped">
            <%
                for (Map.Entry entry : offerList.entrySet()) {
                    Offer userOffer = (Offer) entry.getKey();
            %>
            <form action="/adminRemove" method="POST">
                <tr>
                    <% boolean flag = (Boolean) entry.getValue();
                        if (flag == true) {%>
                    <td><h4><%=userOffer.getName()%>
                    </h4>
                    </td>
                    <input type="hidden" name="offerId" value="<%=userOffer.getOfferId()%>">
                    <div class="btn-group">
                        <td align="right"><input class="btn btn-success" type="submit" value="Delete">
                            <button class="btn btn-info" formaction="/offerInfo">Info</button>
                        </td>
                    </div>
                    <% } else { %>
                    <td><h4><%=userOffer.getName()%>
                    </h4></td>
                    <input type="hidden" name="offerId" value="<%=userOffer.getOfferId()%>">
                    <div class="btn-group">
                        <td align="right"><input class="btn btn-danger" type="submit" value="Delete" disabled>
                            <button class="btn btn-info" formaction="/offerInfo">Info</button>
                        </td>
                    </div>
                    <% } %>
                </tr>
            </form>

            <%
                }
            %>
        </table>
    </div>
</div>
<br>
<form action="/addOfferForm" method="GET">
    <button class="btn btn-primary">Add new Offer</button>
</form>
<form action="/addOffer" method="GET">
    <button class="btn btn-primary">Add new Offer using XML</button>
</form>
</body>
</html>
