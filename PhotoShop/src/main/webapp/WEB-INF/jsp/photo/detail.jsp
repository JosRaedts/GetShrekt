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
                <div class="col-md-4 col-sm-6 col-xs-12">
                    <div class="box">
                        <input type="checkbox" name="products" value="products"><img src="${baseurl}/product/view/${products.id}" height="100%" width="90%" /> 
                        <div class="col-md-6">
                            <input type="text" name="amount" style="width: 100%;">
                        </div>
                        <div class="col-md-6">
                            <label name="productprice">&euro; ${products.price}</label>
                        </div>
                    </div>
                </div>
            </c:forEach>
            </div>
            <div class="col-md-12">
                <label name="total Amount">Totaal bedrag: ${amount}</label><br />
                <input type="submit" name="submit" value="Toevoegen aan winkelwagen"> 
                <script>
                    $(document).ready(function(){

                        update_amounts();
                        $('.qty').change(function() {
                            update_amounts();
                        });
                    });


                    function update_amounts()
                    {
                        var sum = 0.0;
                        $('#myTable > tbody  > tr').each(function() {
                            var qty = $(this).find('option:selected').val();
                            var price = $(this).find('.price').val();
                            var amount = (qty*price)
                            sum+=amount;
                            $(this).find('.amount').text(''+amount);
                        });
                        //just update the total to sum  
                        $('.total').text(sum);
                    }
                </script>
            </div>
        </div>
    </div>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />