<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="header.jsp" />
<div class="container">
    <div class="page-header">
        <h1 class="MainTitle"><spring:message code="welcomeText" text="%welcomeText" /></h1>
    </div>  
    <div>
        <p><spring:message code="instructionText" text="%instructionText" /></p>
    </div>    
    <p class="form-signin-heading">${msg}</p>
    <a href="${baseurl}/login"><spring:message code="login" text="%login" /></a>
    <a href="${baseurl}/register"><spring:message code="register" text="%register" /></a>
    <a href="${baseurl}/adminpanel"><spring:message code="adminpanel" text="%adminpanel" /></a>
</div>
<jsp:include page="footer.jsp" />