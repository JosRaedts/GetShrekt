<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><spring:message code="students" text="%students" /></a></h1>
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
                                    <li><a href="${baseurl}/student/add"><spring:message code="addstudent" text="%addstudent" /></a>
                                    </li>
                                    <li><a href="${baseurl}/student/list"><spring:message code="editstudentaction" text="%editstudentaction" /></a>
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
                                    <div class="col-lg-4">
                                        <form class="form-signin" method="post" action="${baseurl}/student/add">
                                            <h2 class="form-signin-heading"><spring:message code="addstudent" text="%addstudent" /></h2>
                                            <input type="text" name="Name" class="form-control" placeholder="<spring:message code="studentname" text="&studentname" />" required autofocus>
                                            <input type="text" name="Address" class="form-control" placeholder="<spring:message code="address" text="&address" />" required autofocus>
                                            <input type="text" name="City" class="form-control" placeholder="<spring:message code="city" text="&city" />" required autofocus>  
                                            <input type="text" name="ZipCode" class="form-control" placeholder="<spring:message code="zipcode" text="&zipcode" />" required autofocus>     
                                            <input type="text" name="Username" class="form-control" placeholder="<spring:message code="username" text="&username" />" required autofocus>
                                            <input type="text" name="Studentcode" class="form-control" placeholder="<spring:message code="studentnr" text="&studentnr" />" required autofocus>
                                            <input type="password" name="Password1" class="form-control" placeholder="<spring:message code="password" text="&password" />" required autofocus>
                                            <input type="password" name="Password2" class="form-control" placeholder="<spring:message code="confirmpassword" text="&confirmpassword" />" required autofocus>
                                            <button class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="createstudent" text="%createstudent" /></button>
                                        </form>
                                    </div>
                                </div> <!-- /container -->
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
