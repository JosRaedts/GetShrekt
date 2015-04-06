<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <div class="page-header">
        <h1 class="MainTitle"><spring:message code="404Title" text="%404title" /></h1>
    </div>
    <div>
        <p><spring:message code="404Text" text="%404title" /></p>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />