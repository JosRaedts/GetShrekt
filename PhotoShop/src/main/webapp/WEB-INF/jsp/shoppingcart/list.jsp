<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
    <table class="table table-striped table-bordered table-hover dataTable no-footer" id="producttable">
        <thead>
            <tr>
                <th>Product</th>
                <th>Omschrijving</th>
                <th>Prijs</th>
                <th>Aantal</th>
                <th>Totaal</th>
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

                    <td>&#128; <fmt:formatNumber value="${cartproduct.amount * cartproduct.price}" maxFractionDigits="2"/></td> 
                    <td><a href="${baseurl}/shoppingcart/delete?id=${cartproduct.id}"><i class="fa fa-trash-o" title="Delete"></i></a>
                        </td>
                </tr>
                <c:set var="total" value="${total + (cartproduct.amount * cartproduct.price)}" scope="page"/>
            </c:forEach>
                <tr>${total}</tr>

        </tbody>
    </table>
</div> <!-- /container -->
<jsp:include page="/WEB-INF/jsp/footer.jsp" />


