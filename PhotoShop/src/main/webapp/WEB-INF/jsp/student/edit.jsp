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
            <h1 class="page-header"><spring:message code="editstudent" text="%editstudent" /></h1>
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
                                    <form method="post"  action="${baseurl}/student/edit">
                                        <input type="hidden" id="hiddenIdfield" name="id" value="${student.id}">
                                        <spring:message code="name" text="%name" /><br> 
                                        <input type="text" name="name" value="${student.name}"><br><br>
                                        <spring:message code="email" text="%email" /><br> 
                                        <input type="text" name="email" value="${student.email}"><br><br>
                                        <spring:message code="address" text="%address" /><br>
                                        <input type="text" name="address" value="${student.address}"><br><br>
                                        <spring:message code="city" text="%city" /><br>
                                        <input type="text"  name="city" value="${student.city}"><br><br>
                                        <spring:message code="zipcode" text="%zipcode" /><br>
                                        <input type="text" name="zipcode" value="${student.zipcode}"><br><br>
                                        <spring:message code="username" text="%username" /><br>
                                        <input type="text" name="username" value="${student.username}">
                                        <br><br>
                                        <input type="submit" name="save" value="<spring:message code="save" text="%save" />">
                                    </form>
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
