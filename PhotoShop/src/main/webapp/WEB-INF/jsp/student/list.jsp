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
                                    <form id="formid" method="post" action="${baseurl}/student/list">
                                        <input type="hidden" id="hiddenIdfield" name="id" value="${schoolclass.id}">
                                             <a href="#" onclick="document.getElementById('formid').submit()"><i class="fa fa-long-arrow-left" title="<spring:message code="back" text="%back" />"></i></a>
                                       
                                    </form>
                                    <table>
                                        <thead>
                                            <tr>
                                                <th></th>
                                                <th>StudentNr</th>
                                                <th><spring:message code="name" text="%name" /></th>
                                                <th><spring:message code="address" text="%address" /></th>
                                                <th><spring:message code="city" text="%city" /></th>  
                                                <th><spring:message code="zipcode" text="%zipcode" /></th>
                                                <th><spring:message code="username" text="%username" /></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="student" items="${students}">
                                                <tr>
                                                    <td><a href="${baseurl}/student/edit?id=${student.id}"><i class="fa fa-pencil" title="<spring:message code="edit" text="%edit" />"></i></a></td>
                                                    <td>${student.studentnr}</td>
                                                    <td>${student.name}</td>
                                                    <td>${student.address}</td>
                                                    <td>${student.city}</td>
                                                    <td>${student.zipcode}</td>
                                                    <td>${student.username}</td>
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
