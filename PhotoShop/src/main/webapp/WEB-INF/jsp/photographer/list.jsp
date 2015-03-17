<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp"/>
<div class="container">

    <table>
        <thead>
            <tr>
                <th></th>
                <th>photographer</th>
                <th><spring:message code="name" text="%name" /></th>
                <th><spring:message code="username" text="%username" /></th>
                <th><spring:message code="password" text="%password" /></th>
            </tr>
        </thead>
        <tbody>
            <c:forEach var="photographer" items="${photographer}">
            <tr>
                <td><a href="${baseurl}/photographer/edit?id=${photographer.id}"><i class="fa fa-pencil" title="<spring:message code="edit" text="%edit" />"></i></a></td>
                <td>${photographer.name}</td>
                <td>${photographer.username}</td>
                <td>${photographer.password}</td>
            </tr>
            </c:forEach>            
        </tbody>
    </table>

    </div>

<jsp:include page="/WEB-INF/jsp/footer.jsp" />