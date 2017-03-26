<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <title>Offer Info</title>
</head>
<body>
<%
    String offerName = (String) session.getAttribute("offerName");
    List<String> attrNames = (List<String>) session.getAttribute("attrNames");
    List<String> attrValues = (List<String>) session.getAttribute("attrValues");
%>

<t:logout>
    <jsp:attribute name =  "title">
        Info about offer "<%=offerName%>":
    </jsp:attribute>
</t:logout>
<div class="row">
    <div class="col-sm-6">

        <table class="table table-striped">
            <thead>
            <tr>
                <th>Attribute</th>
                <th>Value</th>
            </tr>
            </thead>
            <%
                for (int i = 0; i < attrNames.size(); i++) {
            %>
            <tr>
                <td><%=attrNames.get(i)%>
                </td>
                <td><%=attrValues.get(i)%>
                </td>
            </tr>
            <%
                }
            %>
        </table>
    </div>
</div>
</body>
</html>
