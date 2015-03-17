<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">

    <form method="post"  action="${baseurl}/student/edit">
        <input type="hidden" id="hiddenIdfield" name="id" value="${student.id}">
        <spring:message code="name" text="%name" /><br> 
        <input type="text" name="name" value="${student.name}"><br><br>
        <spring:message code="address" text="%address" /><br>
        <input type="text" name="address" value="${student.address}"><br><br>
        <spring:message code="city" text="%city" /><br>
        <input type="text"  name="city" value="${student.city}"><br><br>
        <spring:message code="zipcode" text="%zipcode" /><br>
        <input type="text" name="zipcode" value="${student.zipcode}"><br><br>
        <spring:message code="username" text="%username" /><br>
        <input type="text" name="username" value="${student.username}">
        <br><br>
        <input type="submit" name="save" value="<spring:message code="save" text="%save" />">
    </form>
</div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />