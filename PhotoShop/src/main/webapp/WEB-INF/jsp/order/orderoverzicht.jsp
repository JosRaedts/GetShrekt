<%-- 
    Document   : home
    Created on : Mar 17, 2015, 10:14:29 AM
    Author     : Casper
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    test
    <c:forEach var="order" items="${orders}">
        <tr>
            ${order.getStatus()}
        </tr>
    </c:forEach>
</div>
<!-- /#wrapper -->
</html>
