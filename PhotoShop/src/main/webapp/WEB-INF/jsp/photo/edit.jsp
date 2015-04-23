<%-- 
    Document   : home
    Created on : Mar 17, 2015, 10:14:29 AM
    Author     : Me
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><spring:message code="overview" text="%overview" /></h1>
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
                                    <script type="text/javascript">
                                        $().ready(function() {
                                            $("#students").multiselect2side({
                                                search: "Find: "
                                            });
                                            $("#schoolclasses").multiselect2side({
                                                search: "Find: "
                                            });
                                            $("#schools").multiselect2side({
                                                search: "Find: "
                                            });
                                        });
                                    </script>
                                    <select name="students" id="students" multiple>
                                        <c:forEach var="student" items="${students}">
                                            <c:set var="sel_student" value="false" />
                                            <c:forEach var="my_student" items="${my_students}">
                                                <c:if test="${student.id == my_student.id}">
                                                    <c:set var="sel_student" value="true" />
                                                </c:if>
                                            </c:forEach>
                                            <option value="${student.id}" <c:if test="${sel_student}">SELECTED="" </c:if>>${student.name}</option>
                                        </c:forEach>
                                    </select><br><br>
                                    <select name="schoolclasses" id="schoolclasses" multiple>
                                        <c:forEach var="schoolclass" items="${schoolclasses}">
                                            <c:set var="sel_schoolclass" value="false" />
                                            <c:forEach var="my_schoolclass" items="${my_schoolclasses}">
                                                <c:if test="${schoolclass.id == my_schoolclass.id}">
                                                    <c:set var="sel_schoolclass" value="true" />
                                                </c:if>
                                            </c:forEach>
                                            <option value="${schoolclass.id}" <c:if test="${sel_schoolclass}">SELECTED="" </c:if>>${schoolclass.name}</option>
                                        </c:forEach>
                                    </select><br><br>
                                    <select name="schools" id="schools" multiple>
                                        <c:forEach var="school" items="${schools}">
                                            <c:set var="sel_school" value="false" />
                                            <c:forEach var="my_school" items="${my_schools}">
                                                <c:if test="${school.id == my_school.id}">
                                                    <c:set var="sel_school" value="true" />
                                                </c:if>
                                            </c:forEach>
                                            <option value="${school.id}" <c:if test="${sel_school}">SELECTED="" </c:if>>${school.name}</option>
                                        </c:forEach>
                                    </select>
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
