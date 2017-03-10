<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: ааааааааааеееееееее
  Date: 09.03.2017
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Offer Info</title>
</head>
<body>
<%
    String offerName = (String) session.getAttribute("offerName");
    List<String> attrNames = (List<String>) session.getAttribute("attrNames");
    List<String> attrValues = (List<String>) session.getAttribute("attrValues");
%>
Info about offer <%=offerName%>:
<br>
<table border="2px" cellpadding="10px">
<%
    for (int i = 0 ; i < attrNames.size(); i++){
        %>
    <tr>
        <td><%=attrNames.get(i)%></td>
        <td><%=attrValues.get(i)%></td>
    </tr>
    <%
    }
%>
</table>
</body>
</html>
