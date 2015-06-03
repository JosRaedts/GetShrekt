<%-- 
    Document   : home
    Created on : Mar 17, 2015, 10:14:29 AM
    Author     : Casper
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<head>
    <script type="text/javascript" src="${baseurl}/resources/admin/js/piechart.js"></script>
    <script type="text/javascript">
        google.load("visualization", "1", {packages: ["corechart"]});
        google.setOnLoadCallback(drawChart);
        function drawChart() {

            var values = new Array();

        <c:forEach var="photographer" items="${photographers}">
            values.push("${photographer}");
        </c:forEach>

            var data = google.visualization.arrayToDataTable([
                ['Task', 'Aantal fotos'],
        <c:forEach begin="0" end="${size}" var="i" varStatus="loop">
                [values[${i}], '11'],
        </c:forEach>
            ]);

            var options = {
                title: 'Aantal gemaakte fotos'
            };

            var chart = new google.visualization.PieChart(document.getElementById('piechart'));

            chart.draw(data, options);
        }
    </script>
</head>
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Dashboard</h1>
        </div>
        <!-- /.col-lg-12 -->
    </div>
    <!-- /.row -->
    <div class="row">
        <div class="col-lg-3">
            <div class="panel panel-primary">
                <div class="panel-heading" style="height: 120px">
                    <div class="row">
                        <div class="col-xs-3">
                            <i class="fa fa-shopping-cart fa-5x"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                            <div class="huge">${size}</div>
                            <div><spring:message code="TotalOrders" text="%TotalOrders" /></div>
                        </div>
                    </div>
                </div>
                <c:if test="${sessionScope.UserType == 'ADMIN' && sessionScope.UserID != null}">
                    <a href="${baseurl}/order/overzicht/">
                        <div class="panel-footer">
                            <span class="pull-left"><spring:message code="ViewDetails" text="%ViewDetails" /></span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </c:if>            
            </div>
        </div>
        <div class="col-lg-3">
            <div class="panel panel-green">
                <div class="panel-heading" style="height: 120px">
                    <div class="row">
                        <div class="col-xs-3">
                            <i class="fa fa-picture-o fa-5x"></i>
                        </div>
                        <div class="col-xs-9 text-right">
                            <div class="huge">${Photocount}</div>
                            <div>Photo's</div>
                        </div>
                    </div>
                </div>
                <c:if test="${sessionScope.UserType == 'PHOTOGRAPHER' && sessionScope.UserID != null}">
                    <a href="${baseurl}/photographer/photo">
                        <div class="panel-footer">
                            <span class="pull-left"><spring:message code="photograafpictures" text="%photograafpictures" /></span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </c:if>     
                <c:if test="${sessionScope.UserType == 'ADMIN' && sessionScope.UserID != null}">
                    <a href="${baseurl}/photo/list">
                        <div class="panel-footer">
                            <span class="pull-left"><spring:message code="photograafpictures" text="%photograafpictures" /></span>
                            <span class="pull-right"><i class="fa fa-arrow-circle-right"></i></span>
                            <div class="clearfix"></div>
                        </div>
                    </a>
                </c:if>             
            </div>
        </div>

        <!-- /.row -->
        <div class="row">
            <div class="col-lg-8">
                <!-- /.panel -->
                <c:if test="${sessionScope.UserType == 'ADMIN' && sessionScope.UserID != null}">
                    <div class="panel panel-default">
                        <div class="panel-heading">
                            <i class="fa fa-comments fa-fw"></i> <spring:message code="Sales" text="%Sales" />
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="dataTable_wrapper">
                                    </div>
                                    <!-- /.table-responsive -->
                                    <!--<div id="piechart" class="col-lg-6" style="height: 500px;"></div>-->
                                    <table class="table table-striped table-bordered table-hover dataTable no-footer" id="sales">
                                        <c:forEach var="info" items="${information}">
                                            <tr>
                                                <td>${info[0]}</td>
                                                <td>${info[1]}</td>
                                            </tr>
                                        </c:forEach>    
                                    </table>

                                </div>
                                <!-- /.col-lg-4 (nested) -->
                                <div class="col-lg-8">
                                    <div id="morris-bar-chart"></div>
                                </div>
                                <!-- /.col-lg-8 (nested) -->
                            </div>
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="dataTable_wrapper">
                                    </div>
                                    <!-- /.table-responsive -->
                                    <!--<div id="piechart" class="col-lg-6" style="height: 500px;"></div>-->
                                </div>
                                <!-- /.col-lg-4 (nested) -->
                                <div class="col-lg-8">
                                    <div id="morris-bar-chart"></div>
                                </div>
                                <!-- /.col-lg-8 (nested) -->
                            </div>
                            <!-- /.row -->
                        </div>
                        <!-- /.panel-body -->
                    </div>
                    <!-- /.panel -->
                </c:if>
            </div>
            <!-- /.col-lg-8 -->
            <div class="col-lg-4">
                <div class="panel panel-default">
                    <div class="panel-body">
                        <table class="table table-striped table-bordered table-hover dataTable no-footer" id="sales">
                            <tr>
                                <spring:message code="BestSales" text="%BestSales" />
                            </tr>
                            <c:if test="${aantal1 != null}">
                            <tr>
                                <td><img width="100px" height="100px" src="${baseurl}/product/view/${product1.getId()}"/></td> 
                                <td>${aantal1}x</td>
                            </tr>
                            </c:if>
                            <c:if test="${aantal2 != null}">
                            <tr>
                                <td><img width="100px" height="100px" src="${baseurl}/product/view/${product2.getId()}"/></td> 
                                <td>${aantal2}x</td>
                            </tr>
                            </c:if>
                            <c:if test="${aantal3  != null}">
                            <tr>
                                <td><img width="100px" height="100px" src="${baseurl}/product/view/${product3.getId()}"/></td> 
                                <td>${aantal3}x</td>
                            </tr>
                            </c:if>
                            <c:if test="${aantal4  != null}">
                            <tr>
                                <td><img width="100px" height="100px" src="${baseurl}/product/view/${product4.getId()}"/></td> 
                                <td>${aantal4}x</td>
                            </tr> 
                            </c:if>
                            <c:if test="${aantal5  != null}">
                            <tr>
                                <td><img width="100px" height="100px" src="${baseurl}/product/view/${product5.getId()}"/></td> 
                                <td>${aantal5}x</td>
                            </tr> 
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
            <!-- /.col-lg-4 -->
        </div>
        <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->

</div>
<!-- /#wrapper -->

</body>

</html>
