<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">

    <table>
        <thead>
            <tr>
                <th></th>
                <th></th><!-- View classes / edit -->
                <th><spring:message code="name" text="%name" /></th>
                <th><spring:message code="address" text="%address" /></th>
                <th><spring:message code="city" text="%city" /></th>  
                <th><spring:message code="zipcode" text="%zipcode" /></th>
                <th>Code</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="school" items="${schools}">
            <input type="hidden" id="hiddenIdfield" name="id" value="${school.id}">
            <tr>
                <td><a href="${baseurl}/student/edit?id=${school.id}"><i class="fa fa-pencil" title="<spring:message code="edit" text="%edit" />"></i></a></td>
                <td><a href="${baseurl}/schoolclass/list?id=${school.id}">View classes</a></td>
                <td>${school.name}</td>
                <td>${school.address}</td>
                <td>${school.city}</td>
                <td>${school.zipcode}</td>
                <td>${school.code}</td>
            </tr>
            </c:forEach>
            
        </tbody>
    </table>

    </div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />