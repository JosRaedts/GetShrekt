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
    <div class="BarcodeHoeken" style="margin-bottom: 40px; margin-top: 50px">
        <p class="barcode">*class-${ClassForBarcode.getSchool_id()}*</p>
    </div>
    <br>
    <br>
    <spring:message code="StudentBarcode" text="%StudentBarcode" />:
    <br>
    <br>
    <div >
    <c:forEach var="student" items="${ClassForBarcode.getStudents()}">
        <div class="BarcodeHoeken FloatLeft">
            <p style="margin-left: 20px;">${student.getName()}, ${student.getStudentnr()}<p>
            <p class="barcode">*student-${student.getId()}*</p>
        </div>
    </c:forEach>
    </div>
</div>
<!-- /#wrapper -->

</body>

</html>
