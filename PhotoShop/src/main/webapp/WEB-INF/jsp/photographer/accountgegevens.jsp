<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
     <c:if test="${Type == 'photographer'}">
        <label><spring:message code="accountTypeText" text="%accountTypeText" /></label> <label><spring:message code="photographer" text="%photographer" /></label>
        <br>    
        <label><spring:message code="username" text="%username" /></label> <label>${UserName}</label>
        <br>
        <label><spring:message code="name" text="%name" /></label> <label>${Name}</label>
        <br>
     </c:if>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />