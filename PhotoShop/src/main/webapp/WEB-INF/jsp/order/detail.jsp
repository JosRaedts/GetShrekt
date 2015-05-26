<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header">Order detail pagina</h1>
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
                    <i class="fa fa-comments fa-fw"></i> Order
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="dataTable_wrapper">
                                <table>
                                    <c:forEach var="orderrow" items="${productlist}">
                                            <tr>
                                                <td><img src="${baseurl}/product/view/${orderrow.getProduct().getId()}" height="150px" width="150px"/></td>
                                                <td>${orderrow.getProduct().getId()}</td>
                                                <td>${orderrow.getProduct().getName()}</td>
                                            </tr>
                                    </c:forEach>
                                </table>
                            </div>
                            <!-- /.table-responsive -->
                        </div>
                        <!-- /.col-lg-4 (nested) -->
                        <div class="col-lg-8">
                            <div id="morris-bar-chart">
                                <table>
                                    <tr>
                                        <td>${student.name}</td>
                                    </tr>
                                    <tr>
                                        <th>${student.studentnr}</th>
                                    </tr>
                                    <tr>
                                        <th>${student.address}</th>
                                    </tr>
                                    <tr>
                                        <th>${student.zipcode} ${student.city}</th>
                                    </tr>
                                    <tr>
                                        <th>${student.schoolclass_id}</th>
                                    </tr>
                                </table>
                            </div>
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