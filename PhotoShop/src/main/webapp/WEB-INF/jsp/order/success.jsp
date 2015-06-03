<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <h1><spring:message code="ordersuccess" text="ordersuccess"></spring:message></h1>
    <p><spring:message code="ordersuccessmessage" text="ordersuccessmessage"></spring:message></p>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />