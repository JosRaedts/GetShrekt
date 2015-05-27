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
        <div class="col-lg-6">

            <!-- /.panel -->
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-comments fa-fw"></i> Products
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="row" >
                        <div class="col-lg-12">
                            <div class="dataTable_wrapper">
                                <table class="table table-striped table-bordered table-hover dataTable no-footer" id="schooltable">
                                    <tr>
                                        <td> Product example </td>
                                        <td> Product name </td>
                                        <td> Amount </td>
                                    </tr>
                                    <c:forEach var="price" items="${$pricelist}">
                                    <c:forEach var="orderrow" items="${productlist}">
                                            <tr>
                                                <td><img src="${baseurl}/product/view/${orderrow.getProduct().getId()}" height="150px" width="150px"/></td>
                                                <td>${orderrow.getProduct().getName()}</td>
                                                <td>${orderrow.getAantal()}</td>
                                                <td>${price}</td>
                                            </tr>
                                    </c:forEach>
                                    </c:forEach>
                                </table>
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
        <div class="col-lg-6">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <i class="fa fa-comments fa-fw"></i> Studentgegevens
                </div>
                <!-- /.panel-heading -->
                <div class="panel-body">
                    <div class="dataTable_wrapper">
                        <table class="table table-striped table-bordered table-hover dataTable no-footer" id="schooltable">
                            <tr>
                                <td> Factuuradres </td>
                            </tr>
                            <tr>
                                <td>
                                    Naam <br />
                                    Adres <br />
                                    Postcode <br />
                                    Woonplaats <br />
                                    Studentnummer
                                </td>
                                <td>
                                    ${student.name} <br />
                                    ${student.address} <br />
                                    ${student.zipcode} <br />
                                    ${student.city} <br />
                                    ${student.studentnr} <br />
                                </td>
                            </tr>
                        </table> <br />
                        <table class="table table-striped table-bordered table-hover dataTable no-footer" id="schooltable">
                            <tr>
                                <td> Bezorgadres </td>
                            </tr>
                            <tr>
                                <td>
                                    Naam <br />
                                    Adres <br />
                                    Postcode <br />
                                    Woonplaats
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
    <!-- /.row -->
    </div>
    <!-- /#page-wrapper -->
</div>
<!-- /#wrapper -->