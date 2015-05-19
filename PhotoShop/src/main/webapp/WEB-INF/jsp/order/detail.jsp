<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />

<div id="page-wrapper">
    <div class="col-md-6">
        <!-- producten -->
        <c:forEach var="productlist" items="${productlist}">
            <div class="box">
                <!-- <img src="${baseurl}/product/view/${productlist.id}"/> -->
                <label>${productlist.id} ${student.name}</label>
            </div>
        </c:forEach>
    </div>
    <div class="col-md-6">
        <!-- klantgegevens -->
        
    </div>
</div> <!-- /container -->