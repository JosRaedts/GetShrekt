<%-- 
    Document   : home
    Created on : Mar 17, 2015, 10:14:29 AM
    Author     : Casper
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <form class="" method="post" action="${baseurl}/admin/getBarcodes">
    <select name ="Schoolclassid">
        <c:forEach var="School" items="${Scholen}">
            <c:forEach var="Klas" items="${School.getSchoolClasses()}">
                <option name ="School" value="${Klas.id}">${School.name}: ${Klas.name}</option>
            </c:forEach>
        </c:forEach>
    </select>
    </form>
</div>
<!-- /#wrapper -->

</body>

</html>
