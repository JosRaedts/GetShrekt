<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />

<div id="page-wrapper">
    <div class="col-md-6">
        <!-- producten -->
        <table>
            <c:forEach var="productlist" items="${productlist}">
                <div class="box">
                    <tr>
                        <!-- <img src="${baseurl}/product/view/${productlist.id}"/> -->
                        <td>${productlist.id}</td>
                        <td>${student.name}</td>
                    </tr>
                </div>
            </c:forEach>
        </table>
    </div>
    <div class="col-md-6">
        <!-- klantgegevens -->
        <table>
            <tr>
                <td>${student.name}</td>
            </tr>
            <tr>
                <th>${student.studentnr}</th>
            </tr>
            <tr>
                <th>${student.address}</th>
            </tr>
            <tr>
                <th>${student.zipcode} ${student.city}</th>
            </tr>
            <tr>
                <th>${student.schoolclass_id}</th>
            </tr>
        </table>
    </div>
</div> <!-- /container -->