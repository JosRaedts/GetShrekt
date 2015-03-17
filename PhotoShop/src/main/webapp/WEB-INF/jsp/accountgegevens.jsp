<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <c:if test="${Type == 'student'}">
        <label>${Type}</label>
        <br>    
        <label class="">${UserName}</label>
        <br>
        <label class="">${Name}</label>
        <br>
     </c:if>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />