<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
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
                                            <c:forEach var="product" items="${products}">
                                            <tr>
                                                <td><img src="${baseurl}/photo/view/thumb/${product.id}"/></td>
                                                <td>${product.content}</td>
                                                <td>${product.price}</td>
                                                <td>c</td>
                                                <td>prijs*aantal</td> 
                                                <td>delete</td>
                                            </tr>
                                        </c:forEach>

                                        </tbody>
                                    </table>
    </div> <!-- /container -->
<jsp:include page="/WEB-INF/jsp/footer.jsp" />


