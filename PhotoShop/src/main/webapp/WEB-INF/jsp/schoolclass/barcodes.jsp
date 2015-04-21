<%-- 
    Document   : home
    Created on : Mar 17, 2015, 10:14:29 AM
    Author     : Casper
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <br>
    <input class="FloatRight" type="button" onClick="window.print()" value="<spring:message code="printthis" text="%printthis" />"/>
    <spring:message code="ClassBarcode" text="%ClassBarcode" />:
    <div style="margin-bottom: 40px; margin-top: 50px">
        <p class="barcode">*schoolclass-${ClassForBarcode.getSchool_id()}*</p>
    </div>
    <br>
    <br>
    <spring:message code="StudentBarcode" text="%StudentBarcode" />:
    <br>
    <br>
    <c:forEach var="student" items="${ClassForBarcode.getStudents()}">
        <div class="BarcodeHoeken FloatLeft">
            <p style="margin-left: 20px;">${student.getName()}, ${student.getStudentnr()}<p>
            <p class="barcode">*student-${student.getId()}*</p>
        </div>
    </c:forEach>
</div>
<!-- /#wrapper -->

</body>

</html>
