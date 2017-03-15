<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
<div class="row">
    <div class="col-sm-6">
        <nav class="navbar navbar-default">
            <p class="navbar-text">Choose xml file</p>
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
            <form action="/addOffer" enctype="multipart/form-data" method="POST">
                <tr>
                    <td><input type="file" name="offer"></td>
                </tr>
                <tr>
                    <td><input class="btn btn-success" type="submit" value="Add"></td>
                </tr>
            </form>
        </table>
    </div>
</div>
</body>
</html>
