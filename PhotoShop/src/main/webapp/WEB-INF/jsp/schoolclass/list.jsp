<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">

    <table>
        <thead>
            <tr>
                <th></th>
                <th></th><!-- View/edit -->
                <th><spring:message code="name" text="%name" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="schoolclass" items="${schoolclasses}">
            <tr>
                <td><a href="${baseurl}/student/edit?id=${schoolclass.id}"><i class="fa fa-pencil" title="<spring:message code="edit" text="%edit" />"></i></a></td>
                <td><a href="${baseurl}/student/list?id=${schoolclass.id}">view students</a></td>
                <td>${schoolclass.name}</td>

            </tr>
            </c:forEach>
            
        </tbody>
    </table>

    </div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />