<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <div class="page-header">
        <h1 class="MainTitle"><spring:message code="welcomeText" text="%welcomeText" /></h1>
    </div>  
    <div>
       
        <p><spring:message code="instructionText" text="%instructionText" /></p>
    </div>    
    <p class="form-signin-heading">${msg}</p>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />