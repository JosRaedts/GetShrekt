<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><spring:message code="orderdetail" text="%Orderdetailpagina" /></h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-12">

            <!-- /.panel -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-comments fa-fw"></i><spring:message code="productendetail" text="%productendetail" />
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="row" >
                        <div class="col-lg-12">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer" id="schooltable">
                                    <tr>
                                        <td> <spring:message code="productexample" text="%productexample" /> </td>
                                        <td> <spring:message code="orderproductname" text="%productname" /> </td>
                                        <td> <spring:message code="orderproductamount" text="%productamount" /> </td>
                                        <td> <spring:message code="orderproductprice" text="%orderproductprice" /> </td>
                                        <td class="totalprice"> <spring:message code="orderpriceperproduct" text="%orderpriceperproduct" /> </td>
                                        <td> <spring:message code="orderstatus" text="%orderstatus" /> </td>
                                    </tr>
                                    <c:set var="total" value="0" scope="page"/>
                                    <c:forEach var="orderrow" items="${productlist}">
                                            <tr>
                                                <td><img src="${baseurl}/orders/view/thumb/${orderrow.getId()}"/></td>
                                                <td>${orderrow.getProduct().getName()}</td>
                                                <td>${orderrow.getAantal()}</td>
                                                <td>&#128; <fmt:formatNumber value="${orderrow.getProductprice()}" minFractionDigits="2" maxFractionDigits="2"/></td>
                                                <td>&#128; <fmt:formatNumber value="${orderrow.aantal * orderrow.productprice}" minFractionDigits="2" maxFractionDigits="2"/></td>
                                                <td>${order.getStatus()}</td>
                                            </tr>
                                            <c:set var="total" value="${total + (orderrow.aantal * orderrow.productprice)}" scope="page"/>
                                    </c:forEach>
                                </table>
                                    <hr>
                                    <p align='right' style='margin-right: 10px; font-weight: bold; font-size:18px;' ><spring:message code="ordertotalprice" text="%ordertotalprice" /> &#128; <fmt:formatNumber value="${total}" minFractionDigits="2" maxFractionDigits="2"/></p>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.col-lg-4 (nested) -->
                        <!-- /.col-lg-8 (nested) -->
                    </div>
                    <!-- /.row -->
                </div>
                <!-- /.panel-body -->
            </div>
            <!-- /.panel -->
        </div>
        <!-- /.col-lg-8 -->
        <div class="col-lg-12">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-comments fa-fw"></i> <spring:message code="orderstudentinfo" text="%orderstudents" />
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <div class="col-lg-6">
                            <table class="table table-striped table-bordered table-hover dataTable no-footer" id="schooltable">
                                <tr>
                                    <td> <spring:message code="orderinvoiceaddress" text="%orderinvoiceaddress" /> </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="invoicestudentname" text="%invoicestudentname" /> <br />
                                        <spring:message code="invoicestudentaddress" text="%invoicestudentaddress" /> <br />
                                        <spring:message code="invoicestudentzipcode" text="%invoicestudentzipcode" /> <br />
                                        <spring:message code="invoicestudentcity" text="%invoicestudentcity" /> <br />
                                        <spring:message code="invoicestudentnumber" text="%invoicestudentnumber" />
                                    </td>
                                    <td>
                                        ${student.name} <br />
                                        ${student.address} <br />
                                        ${student.zipcode} <br />
                                        ${student.city} <br />
                                        ${student.studentnr} <br />
                                    </td>
                                </tr>
                            </table> 
                        </div>
                        <div class="col-lg-6">
                            <table class="table table-striped table-bordered table-hover dataTable no-footer" id="schooltable">
                                <tr>
                                    <td> <spring:message code="ordershippingaddress" text="%ordershippingaddress" /> </td>
                                    <td></td>
                                </tr>
                                <tr>
                                    <td>
                                        <spring:message code="shippingstudentname" text="%shippingstudentname" /> <br />
                                        <spring:message code="shippingstudentadres" text="%shippingstudentadres" /> <br />
                                        <spring:message code="shippingstudentzipcode" text="%shippingstudentzipcode" /> <br />
                                        <spring:message code="shippingstudentcity" text="%shippingstudentcity" />
                                    </td>
                                    <td>
                                        ${student.name} <br />
                                        ${student.address} <br />
                                        ${student.zipcode} <br />
                                        ${student.city}
                                    </td>
                                </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->