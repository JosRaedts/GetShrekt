<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <table class="table table-striped table-bordered table-hover dataTable no-footer" id="producttable">
        <thead>
            <tr>
                <th><spring:message code="product" text="%product" /></th>
                <th><spring:message code="omschrijving" text="%omschrijving" /></th>
                <th><spring:message code="prijs" text="%prijs" /></th>
                <th><spring:message code="aantal" text="%aantal" /></th>
                <th><spring:message code="totaal" text="%totaal" /></th>
                <th></th>
            </tr>
        </thead>
        <tbody>
            <c:set var="total" value="0" scope="page"/>
            <c:forEach var="cartproduct" items="${cartproducts}">

                <tr>

                    <td>plaatje ofzo hier</td>
                    <td>${cartproduct.content}</td>
                    <td>&#128; ${cartproduct.price}</td>
                    <td><form method="post" action="${baseurl}/shoppingcart/list">
                            <input type="hidden" id="hiddenIdfield" name="id" value="${cartproduct.id}">

                            <select onchange="this.form.submit()" name="amount">
                                <c:forEach var="i" begin="1" end="10">

                                    <option value="${i}" <c:if test="${cartproduct.amount == i}">selected="selected"</c:if>>${i}</option>
                                </c:forEach>
                            </select>
                        </form></td>  

                    <td>&#128; <fmt:formatNumber value="${cartproduct.amount * cartproduct.price}" minFractionDigits="2" maxFractionDigits="2"/></td> 
                    <td><a href="${baseurl}/shoppingcart/delete?id=${cartproduct.id}"><i class="fa fa-trash-o" title="Delete"></i></a>
                    </td>
                </tr>
                <c:set var="total" value="${total + (cartproduct.amount * cartproduct.price)}" scope="page"/>
            </c:forEach>
            <tr>
                <td></td>
                <td></td>
                <td></td>
                <td><b><spring:message code="totaal" text="%totaal" />:</b></td>                
                <td>&#128; <fmt:formatNumber value="${total}" minFractionDigits="2" maxFractionDigits="2"/></td>
                <td></td>
            </tr>
        </tbody>
    </table>
    <form method="post" action="${baseurl}/shoppingcart/order">
        <input type="submit" name="order" value="Bestel" style="float: right" >
    </form>
</div> <!-- /container -->
<jsp:include page="/WEB-INF/jsp/footer.jsp" />


