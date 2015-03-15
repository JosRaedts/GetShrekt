<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">

    <table>
        <thead>
            <tr>
                <th></th>
                <th>StudentNr</th>
                <th><spring:message code="name" text="%name" /></th>
                <th><spring:message code="address" text="%address" /></th>
                <th><spring:message code="city" text="%city" /></th>  
                <th><spring:message code="zipcode" text="%zipcode" /></th>
                <th><spring:message code="username" text="%username" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${students}">
            <tr>
                <td><a href="${baseurl}/student/edit?id=${student.id}"><img src="${baseurl}/resources/img/pencil.png" title="<spring:message code="edit" text="%edit" />"/></a></td>
                <td>${student.studentnr}</td>
                <td>${student.name}</td>
                <td>${student.address}</td>
                <td>${student.city}</td>
                <td>${student.zipcode}</td>
                <td>${student.username}</td>
            </tr>
            </c:forEach>
            
        </tbody>
    </table>

    </div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />