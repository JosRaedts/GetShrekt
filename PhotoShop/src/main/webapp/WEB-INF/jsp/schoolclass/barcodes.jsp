<%-- 
    Document   : home
    Created on : Mar 17, 2015, 10:14:29 AM
    Author     : Casper
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <!--
        Hier de barcode van de klas zelf
    -->
    <br>
    <spring:message code="ClassBarcode" text="%ClassBarcode" />:
    <div class="col-lg-12 barcode" style="margin-bottom: 30px">
        <p>*schoolclass-${ClassForBarcode.getSchool_id()}*</p>
    </div>
    <br>
    <br>
    <spring:message code="StudentBarcode" text="%StudentBarcode" />:
    <br>
    <br>
    <c:forEach var="student" items="${ClassForBarcode.getStudents()}">
        <div class="col-lg-3 BarcodeHoeken">
            <p>${student.getName()}, ${student.getStudentnr()}<p>
            <p class="barcode">*student-${student.getId()}*</p>
        </div>
    </c:forEach>
</div>
<!-- /#wrapper -->

</body>

</html>
