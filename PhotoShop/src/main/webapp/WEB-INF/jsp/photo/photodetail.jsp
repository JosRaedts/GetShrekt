<%-- 
    Document   : photodetail
    Created on : 14-apr-2015, 13:06:30
    Author     : Jos
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div id="page-wrapper">
    <div class="container">
        <div class="titeldetailpagina" style="margin-top: 60px;">
            <h1>Photo detail pagina </h1>
        </div>
        <div class="col-md-6" width="500px">
            <img src="${testphoto}" width="500px" height="400px"/>
        </div>
        <c:forEach var="products" items="${products}">
            <div class="col-md-2 col-sm-4 col-xs-8">
                <input type="radio" name="sex" value="male"><img src="${baseurl}/product/view/${products.id}" height="125px" width="125px" />                       
            </div>
        </c:forEach>
        <input type="submit" name="submit" value="Betalen">
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />