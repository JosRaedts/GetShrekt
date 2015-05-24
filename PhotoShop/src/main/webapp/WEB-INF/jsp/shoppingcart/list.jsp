<%-- 
    Document   : list
    Created on : 19-mei-2015, 13:20:06
    Author     : pc
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
            <div class="row">
                <div class="col-lg-12">
                    <h1 class="page-header"><spring:message code="products" text="%products" /></h1>
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
                            <i class="fa fa-comments fa-fw"></i> General information
                            <div class="pull-right">
                                <div class="btn-group">
                                    <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                        Actions
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu pull-right" role="menu">
                                        <li><a href="${baseurl}/product/list"><spring:message code="products" text="%products" /></a>
                                        </li>
                                        <li><a href="${baseurl}/product/add"><spring:message code="addProduct" text="%addProduct" /></a>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <!-- /.panel-heading -->
                        <div class="panel-body">
                            <div class="row">
                                <div class="col-lg-12">
                                    <div class="dataTable_wrapper">
                                        <script>
                                        $(document).ready(function () {
                                            $('#producttable').DataTable({
                                                responsive: true
                                            });
                                        });
                                    </script>
                                    <br>
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
                                            <input type="hidden" id="hiddenIdfield" name="id" value="${school.id}">
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
                                    </div>
                                    <!-- /.table-responsive -->
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
                </div>
                <!-- /.col-lg-8 -->
            </div>
            <!-- /.row -->
        </div>
        <!-- /#page-wrapper -->

    </div>
    <!-- /#wrapper -->

</body>

</html>
