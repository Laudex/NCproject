<%@ page import="ru.entity.Offer" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Remove Offers</title>
</head>
<body>
<%
    List<Offer> offerList = (List<Offer>) session.getAttribute("list");
%>
<table>
        <%for (Iterator<Offer> j = offerList.iterator(); j.hasNext(); ) {
                Offer userOffer = j.next();
        %><form action= "/offerRem" method="POST">
            <tr>
            <td><%=userOffer.getName()%></td><input type="hidden" name="offerId" value = "<%=userOffer.getOfferId()%>">
            <td><input type= "submit" value="Remove it!"></td>
        </tr>
        </form>
        <%
        }
        %>
</table>
</body>
</html>
