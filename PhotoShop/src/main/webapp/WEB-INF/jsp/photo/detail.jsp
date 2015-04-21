<%-- 
    Document   : photodetail
    Created on : 14-apr-2015, 13:06:30
    Author     : Jos
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />

<div class="container">
    <div class="Titelphotoview">
        <h1>Photo detail pagina </h1>
    </div>
    <div class="col-md-6">
        <img src="${baseurl}/photo/view/low/${photo.id}" width="100%" height="100%"/>
    </div>
    <div class="col-md-6">
        <div id="boxes">
        <c:forEach var="products" items="${products}">
            <div class="col-md-4 col-sm-6 col-xs-12">
                <div class="box">
                    <img src="${baseurl}/product/view/${products.id}" height="100%" width="90%" /> 
                    <input type="text" class="quantity" name="amount" style=" width: 50px; margin-right: 10px;">
                    <label class="price" name="productprice">${products.price}</label>
                </div>
            </div>
        </c:forEach>
        </div>
        <div class="col-md-12">
            <label name="totalAmount" class="total"> ${amount}</label><br />
            <input type="submit" name="submit" value="Toevoegen aan winkelwagen" style="margin-bottom:15px;"> 
            <script>
                $(document).ready(function(){

                    update_amounts();
                    $('.quantity').change(function() {
                        update_amounts();
                    });
                });


                function update_amounts()
                {
                    var sum = 0.0;
                    $('.box').each(function() {
                        var qty = $(this).find('.quantity').val();
                        var price = $(this).find('.price').val();
                        var amount = (qty*price);
                        sum+=amount;
                    });
                    //just update the total to sum  
                    $('.total').text("Totaal bedrag:" + sum);
                }
            </script>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />