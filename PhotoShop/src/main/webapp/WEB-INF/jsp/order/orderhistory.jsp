<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <table class="table table-striped table-bordered table-hover dataTable no-footer">
        <tr>
            <td><strong><spring:message code="OrderID" text="%OrderID" /></strong></td>
            <td><strong><spring:message code="OrderStudentName" text="%OrderStudentName" /></strong></td>
            <td><strong><spring:message code="OrderDate" text="%OrderDate" /></strong></td>
            <td><strong><spring:message code="OrderOrderStatus" text="%OrderOrderStatus" /></strong></td>
            <td><strong><spring:message code="invoice" text="%invoice" /></strong></td>
            <td><strong><spring:message code="IndexCard" text="%IndexCard" /></strong></td>
        </tr>
        <c:forEach var="order" items="${orders}">
            <tr>            
                <td>${order.getId()}</td>
                <td>${order.getStudent().getName()}</td>
                <td>${order.getDatumAsString()}</td>
                <td>${order.getStatus()}</td>    
                <td><a href="${baseurl}/order/factuur/${order.getId()}">${order.getFactuur()}</a></td>
                <td><a href="${baseurl}/order/indexkaart/${order.getId()}">${order.getIndexkaart()}</a></td>
            </tr>
        </c:forEach>
    </table>
</div> <!-- /container -->
<jsp:include page="/WEB-INF/jsp/footer.jsp" />