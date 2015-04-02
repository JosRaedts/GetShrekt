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
                    <h1 class="page-header">Dashboard</h1>
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
                                        <li><a href="#">Action</a>
                                        </li>
                                        <li><a href="#">Another action</a>
                                        </li>
                                        <li><a href="#">Something else here</a>
                                        </li>
                                        <li class="divider"></li>
                                        <li><a href="#">Separated link</a>
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
                                        <div class="col-lg-6">
                                            <form method="post"  action="${baseurl}/product/add">
                                                <table border="0">
                                                    <tr>
                                                        <td width="200px"><spring:message code="active" text="%active" />:</td>
                                                        <td><input type="radio" name="active" value="1" checked>Ja<br>
                                                            <input type="radio" name="active" value="0">Nee</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200px"><spring:message code="preview" text="%preview" />:</td>
                                                        <td><input type="text" name="preview" class="form-control formphotograaf"></td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200px"><spring:message code="productName" text="%productName" />:</td>
                                                        <td><input type="text" name="name" class="form-control formphotograaf"></td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200px"><spring:message code="width" text="%width" />:</td>
                                                        <td><input type="text" name="width" class="form-control formphotograaf"></td>
                                                    </tr>
                                                    <tr>
                                                        <td width="200px"><spring:message code="height" text="%height" />:</td>
                                                        <td><input type="text" name="height" class="form-control formphotograaf"></td>
                                                    </tr>
                                                    <tr>
                                                        <td></td>
                                                        <td>
                                                            <input type="submit" class="btn btn-lg btn-primary btn-block photographerBtn" name="save" value="<spring:message code="save" text="%save" />">
                                                        </td>
                                                    </tr>
                                                </table>
                                            </form>
                                        </div>
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
