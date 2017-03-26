<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Add Offer</title>
</head>
<body>
<% if (session.getAttribute("error") != null) {
%>
<div class="row">
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
<t:logout>
    <jsp:attribute name =  "title">
        Adding new Offer:
    </jsp:attribute>
</t:logout>

<div class="row">
    <div class="col-sm-6">
        <table class="table table-striped">
            <form action="/addOfferForm" method="POST">
                <tr>
                    <td><h4>Name:</h4></td>
                    <td><input type="text" class="form-control" name="offerName"></td>
                </tr>
                <tr>
                    <td><h4>Type:</h4></td>
                    <td><input type="text" class="form-control" name="offerType"></td>
                </tr>
                <tr>
                    <td><h4>Traffic:</h4></td>
                    <td><input type="text" class="form-control" name="offerTraffic"></td>
                </tr>
                <tr>
                    <td><h4>Price:</h4></td>
                    <td><input type="text" class="form-control" name="offerPrice"></td>
                </tr>
                <tr>
                    <td><h4>Period:</h4></td>
                    <td><input type="text" class="form-control" name="offerPeriod"></td>
                </tr>
                <tr>
                    <td><input class="btn btn-success" type="submit" value="Add"></td>
                </tr>
            </form>
        </table>
    </div>
</div>
<form action="/adminPanel" method="GET">
    <button class="btn btn-primary">Back</button>
</form>
</body>
</html>
