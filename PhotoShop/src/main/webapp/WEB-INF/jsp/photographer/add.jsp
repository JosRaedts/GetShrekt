<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><spring:message code="photographers" text="%photographers" /></a></h1>
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
                                    <li><a href="${baseurl}/photographer/add"><spring:message code="photographerText" text="%Add photographer" /></a>
                                    </li>
                                    <li><a href="${baseurl}/photographer/edit"><spring:message code="photographers" text="%photographers" /></a>
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
                                        <form class="form-signin" method="post" action="${baseurl}/photographer/add/addphotographer">
                                            <h2><spring:message code="photographerText" text="%photographerText" /></h2>                                        
                                            <table border="0" style="">
                                                <tr>
                                                    <td width="200px"><spring:message code="name" text="%name" />:</td>
                                                    <td><input type="text" id="inputName" name="name" class="form-control formphotograaf" placeholder="<spring:message code="photographerName" text="%photographerName" />" required autofocus></td>
                                                </tr>
                                                <tr>
                                                    <td width="200px"><spring:message code="username" text="%username" />:</td>
                                                    <td><input type="text" id="inputName" name="username" class="form-control formphotograaf" placeholder="<spring:message code="photographerName" text="%photographerName" />" required autofocus></td>
                                                </tr>
                                                <tr>
                                                    <td width="200px"><spring:message code="newPassword" text="%newPassword" />:</td>
                                                    <td><input type="password" name="newpassword" id="inputSchoolcode" class="form-control formphotograaf" placeholder="<spring:message code="password" text="%password" />" required></td>
                                                </tr>
                                                <tr>
                                                    <td width="200px"><spring:message code="confirmPassword" text="%confirmPassword" />:</td>
                                                    <td><input type="password" name="confirmpassword" id="inputSchoolcode" class="form-control formphotograaf" placeholder="<spring:message code="password" text="%password" />" required></td>
                                                </tr>
                                                <tr>    
                                                    <td>
                                                    </td>
                                                    <td>
                                                    <button class="btn btn-lg btn-primary btn-block photographerBtn" type="submit"><spring:message code="createphotographer" text="%createphotographer" /></button>
                                                    </td>
                                                </tr>
                                            </table>
                                            <br>
                                            
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
