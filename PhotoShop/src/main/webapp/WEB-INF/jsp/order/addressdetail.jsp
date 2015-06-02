<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/header.jsp" />
<div class="container">
        <form class="form-accountinfo" method="post" action="${baseurl}/order/address">
            <br>
            <br>
            <div class="row">
                <div class="col-sm-6">
                    <table border="0">
                        <tr>
                            <td width="200px"><spring:message code="name" text="%name" />:</td>
                            <td><input required type="text" name="invoice_name" value="${student.name}" /></td>
                        </tr>
                        <tr>
                            <td width="200px"><spring:message code="address" text="%address" />:</td>
                            <td><input required type="text" name="invoice_address" value="${student.address}" /></td>
                        </tr>
                        <tr>
                            <td width="200px"><spring:message code="city" text="%city" />:</td>
                            <td width="200px"><input required type="text" name="invoice_city" value="${student.city}" /></td>
                        </tr>
                        <tr>
                            <td width="200px"><spring:message code="zipcode" text="%zipcode" />:</td>
                            <td width="200px"><input required type="text" name="invoice_zipcode" value="${student.zipcode}"/></td>
                        </tr>
                        <tr>
                            <td width="200px"><spring:message code="phone" text="%phone" />:</td>
                            <td width="200px"><input required type="text" name="invoice_phone" value="" /></td>
                        </tr>
                        <tr>
                            <td colspan="2"><input type="checkbox" id="sameaddress" name="sameaddress" checked value="1"> <spring:message code="sameaddress" text="%sameaddress"></spring:message> </td>
                        </tr>
                    </table>
                </div>
                <div class="col-sm-6">
                    <table border="0" id="shipping" style="float:left; ">
                        <tr>
                        <td width="200px"><spring:message code="name" text="%name" />:</td>
                        <td><input required type="text" name="shipping_name" value="${student.name}" /></td>
                        </tr>
                        <tr>
                        <td width="200px"><spring:message code="address" text="%address" />:</td>
                        <td><input required type="text" name="shipping_address" value="${student.address}" /></td>
                        </tr>
                        <tr>
                        <td width="200px"><spring:message code="city" text="%city" />:</td>
                        <td width="200px"><input required type="text" name="shipping_city" value="${student.city}" /></td>
                        </tr>
                        <tr>
                        <td width="200px"><spring:message code="zipcode" text="%zipcode" />:</td>
                        <td width="200px"><input required type="text" name="shipping_zipcode" value="${student.zipcode}"/></td>
                        </tr>
                        <tr>
                        <td width="200px"><spring:message code="phone" text="%phone" />:</td>
                        <td width="200px"><input required type="text" name="shipping_phone" value="" /></td>
                        </tr>
                    </table>
                </div>
                <script type="text/javascript">

                    function hideShipping(checkbox)
                    {
                        alert("tes2t")
                        if(!checkbox.is(":checked"))
                        {
                            alert("checked")
                            $("#shipping :input").attr("disabled", false);
                            $("#shipping").show();
                        }
                        else
                        {
                            alert("notchecked")
                            $("#shipping :input").attr("disabled", true);
                            $("#shipping").hide();

                        }
                    }

                    hideShipping($("#sameaddress"));
                    $("#sameaddress").change(function()
                    {
                        hideShipping($(this));
                        alert("test")
                    });
                </script>
            </div>
            <br>
            <br>
            <div class="row"><button style="width: 100px" class="btn btn-primary" type="submit"><spring:message code="topay" text="%topay" /></button></div>
    </form>
</div>
<jsp:include page="/WEB-INF/jsp/footer.jsp" />