<%@ page import="java.util.List" %>
<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="java.util.Map" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="row">
    <div class="col-sm-6">
        <nav class="navbar navbar-default">
            <p class="navbar-text">List of offers:</p>
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
<form action="/addOffer" method="GET">
    <button class="btn btn-primary">Add new Offer</button>
</form>
</body>
</html>
