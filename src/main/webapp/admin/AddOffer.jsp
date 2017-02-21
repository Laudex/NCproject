<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add Offer</title>
</head>
<body>
<h2>Choose xml file</h2>
<table border="2px" cellpadding="10px">
<form action="/addOffer" enctype="multipart/form-data" method="POST">
    <tr><td><input type="file" name="offer"></td></tr>
    <tr><td><input type="submit" value="Add"></td></tr>
</form>
</table>
</body>
</html>
