<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">

    <form method="post"  action="${baseurl}/photographer/edit">
        <input type="hidden" id="hiddenIdfield" name="id" value="${photographer.id}">
        <spring:message code="name" text="%name" /><br> 
        <input type="text" name="name" value="${photographer.name}"><br><br>
        <spring:message code="password" text="%password" /><br>
        <input type="text" name="password" value="${photographer.password}"><br><br>
        <spring:message code="username" text="%username" /><br>
        <input type="text" name="username" value="${photographer.username}">
        <br><br>
        <input type="submit" name="save" value="<spring:message code="save" text="%save" />">
    </form>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />
