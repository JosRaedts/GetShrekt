<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="header.jsp" />
<div class="container">

    <table>
        <thead>
            <tr>
                <th>Name</th>
                <th>Address</th>
                <th>City</th>  
            </tr>
        </thead>
        <tbody>
            <c:forEach var="student" items="${PhotoShop.AccountInfoController.studenten}">
            <tr>
                <td>${student.name}</td>
                <td>${student.address}</td>
                <td>${student.city}</td>
            </tr>
            </c:forEach>
            
        </tbody>
    </table>

    </div>

<jsp:include page="footer.jsp" />