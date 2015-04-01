<%-- 
    Document   : home
    Created on : Mar 17, 2015, 10:14:29 AM
    Author     : Casper
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
                                        <li><a href="#">Another action</a>
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
                                                <th></th>
                                                <th><spring:message code="preview" text="%preview" /></th>
                                                <th><spring:message code="productName" text="%productName" /></th>
                                                <th><spring:message code="width" text="%width"/></th>
                                                <th><spring:message code="height" text="%height"/></th>
                                                <th><spring:message code="active" text="%active"/></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="product" items="${products}">
                                            <input type="hidden" id="hiddenIdfield" name="id" value="${product.id}">
                                            <tr>
                                                <td><a href="${baseurl}/product/edit?id=${product.id}"><i class="fa fa-pencil" title="<spring:message code="edit" text="%edit" />"></i></a></td>
                                                <td>img</td>
                                                <td>${product.name}</td>
                                                <c:choose>
                                                    <c:when test="${product.width > 0}">
                                                        <td>${product.width} cm</td>
                                                    </c:when> 
                                                    <c:otherwise>
                                                        <td><spring:message code="N/A" text="%N/A"/></td>
                                                    </c:otherwise>   
                                                </c:choose>
                                                <c:choose>
                                                    <c:when test="${product.height > 0}">
                                                        <td>${product.height} cm</td>
                                                    </c:when> 
                                                    <c:otherwise>
                                                        <td><spring:message code="N/A" text="%N/A"/></td>
                                                    </c:otherwise>   
                                                </c:choose>
                                                        <c:choose>
                                                    <c:when test="${product.active}">
                                                        <td><spring:message code="yes" text="%yes"/></td>
                                                    </c:when>
                                                    <c:when test="${!product.active}">
                                                        <td><spring:message code="no" text="%no"/></td>
                                                    </c:when> 
                                                    <c:otherwise>
                                                        <td><spring:message code="N/A" text="%N/A"/></td>
                                                    </c:otherwise>   
                                                </c:choose>
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
