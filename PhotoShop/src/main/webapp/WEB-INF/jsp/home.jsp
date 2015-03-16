<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <div class="page-header">
        <h1 class="MainTitle"><spring:message code="welcomeText" text="%welcomeText" /></h1>
    </div>  
    <div>
        test ${pizza}
        <p><spring:message code="instructionText" text="%instructionText" /></p>
    </div>    
    <p class="form-signin-heading">${msg}</p>
    <a href="${baseurl}/student/login"><spring:message code="Studentlogin" text="%Studentlogin" /></a>
    <a href="${baseurl}/photographer/login"><spring:message code="photographerlogin" text="%photographerlogin" /></a>
    <a href="${baseurl}/photographer/add"><spring:message code="registerPhotographer" text="%registerPhotographer" /></a>
    <a href="${baseurl}/adminpanel"><spring:message code="adminpanel" text="%adminpanel" /></a>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />