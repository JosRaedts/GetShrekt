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
        <div class="col-md-6">
            <img src="${testphoto}" width="500px" height="400px"/>
        </div>
        <div class="col-md-6">
            <div id="boxes">
            <c:forEach var="products" items="${products}">
                <div class="col-md-4 col-sm-6 col-xs-12 borders">
                    <div class="box">
                        <input type="checkbox" name="sex" value="male"><img src="${baseurl}/product/view/${products.id}" height="100%" width="100%" /> 
                    </div>
                </div>
            </c:forEach>
            </div>
            <div class="col-md-12">
                <label name="total Amount">Totaal bedrag: ${amount}</label> 
                <input type="submit" name="submit" value="Toevoegen aan winkelwagen"> 
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />