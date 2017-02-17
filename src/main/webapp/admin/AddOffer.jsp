<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Offer</title>
</head>
<body>
<form action="/addOffer" enctype="multipart/form-data" method="POST">
    <input type="file" name="offer">
    <br>
    <input type="submit" value="Add">
</form>
</body>
</html>
