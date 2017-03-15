<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Users</title>
</head>
<body>
<% if (session.getAttribute("enterError") != null) {
%>
<div class="row">
    <div class="col-sm-4">
        <div class="alert alert-danger">
            <%=session.getAttribute("enterError")%>
        </div>
    </div>
</div>
<%
        session.setAttribute("enterError", null);
    }
%>
<div class="row">
    <div class="col-sm-4">
        <div class="panel panel-default">
            <div class="panel-heading"><h4>Welcome page</h4></div>
        </div>
    </div>
</div>
<div class="row">
    <div class="col-sm-4">
        <form class="form-horizontal" action="/offerView" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-2" for="name">Login:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="name" id="name" placeholder="Enter login">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" for="pwd">Password:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="pwd" name="password" placeholder="Enter password">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <input type="submit" class="btn btn-info" value="Вход">
                </div>
            </div>
        </form>
    </div>
</div>
</body>
</html>
