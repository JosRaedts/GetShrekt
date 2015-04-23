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
                        <i class="fa fa-picture fa-fw"></i> Foto aanpassen
                        <div class="pull-right">
                            <div class="btn-group">
                                <button type="button" class="btn btn-default btn-xs dropdown-toggle" data-toggle="dropdown">
                                    Actions
                                    <span class="caret"></span>
                                </button>
                            </div>
                        </div>
                    </div>
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="dataTable_wrapper">
                                    <script type="text/javascript">
                                        jQuery(document).ready(function($) {
                                            $('#students').multiselect({
                                                keepRenderingSort: true
                                            });
                                            $('#schoolclasses').multiselect({
                                                keepRenderingSort: true
                                            });
                                            $('#schools').multiselect({
                                                keepRenderingSort: true
                                            });
                                        });
                                    </script>
                                    <div class="row" style="margin-bottom: 30px;">
                                        <div class="col-sm-3">
                                            <img src="${baseurl}/photo/view/low/${photo.id}" width="100%">
                                        </div>
                                        <div class="col-sm-9">
                                            <b><spring:message code="uploaddate" text="%uploaddate%"/></b><br>
                                            <fmt:formatDate value="${photo.date}" type="both" pattern="dd-MM-yyyy" />
                                        </div>
                                    </div>
                                    <h3><spring:message code="pictures" text="%photosof%" /></h3>
                                    <hr>
                                    <form method="POST" action="${baseurl}/photo/edit/${photo.id}">
                                        <h4><spring:message code="students" text="%students%" /></h4>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <select name="from" id="students" class="form-control" size="8" multiple="multiple">
                                                    <c:forEach var="student" items="${students}">
                                                        <c:set var="sel_student" value="false" />
                                                        <c:forEach var="my_student" items="${my_students}">
                                                            <c:if test="${student.id == my_student.id}">
                                                                <c:set var="sel_student" value="true" />
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${!sel_student}">
                                                            <option value="${student.id}" >${student.name}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="col-sm-2">
                                                <button type="button" id="students_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
                                                <button type="button" id="students_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
                                                <button type="button" id="students_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
                                                <button type="button" id="students_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
                                            </div>

                                            <div class="col-sm-5">
                                                <select name="students" id="students_to" class="form-control" size="8" multiple="multiple">
                                                    <c:forEach var="student" items="${my_students}">
                                                            <option value="${student.id}" SELECTED="">${student.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <hr>

                                        <h4><spring:message code="schoolClasses" text="%schoolClasses%" /></h4>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <select name="from" id="schoolclasses" class="form-control" size="8" multiple="multiple">
                                                    <c:forEach var="schoolclass" items="${schoolclasses}">
                                                        <c:set var="sel_schoolclass" value="false" />
                                                        <c:forEach var="my_schoolclass" items="${my_schoolclasses}">
                                                            <c:if test="${schoolclass.id == my_schoolclass.id}">
                                                                <c:set var="sel_schoolclass" value="true" />
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${!sel_schoolclass}">
                                                            <option value="${schoolclass.id}">${schoolclass.name}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="col-sm-2">
                                                <button type="button" id="schoolclasses_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
                                                <button type="button" id="schoolclasses_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
                                                <button type="button" id="schoolclasses_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
                                                <button type="button" id="schoolclasses_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
                                            </div>

                                            <div class="col-sm-5">
                                                <select name="schoolclasses" id="schoolclasses_to" class="form-control" size="8" multiple="multiple">
                                                    <c:forEach var="my_schoolclass" items="${my_schoolclasses}">
                                                        <option value="${my_schoolclass.id}" SELECTED="">${my_schoolclass.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <hr>

                                        <h4><spring:message code="schools" text="%schools%" /></h4>
                                        <div class="row">
                                            <div class="col-sm-5">
                                                <select name="from" id="schools" class="form-control" size="8" multiple="multiple">
                                                    <c:forEach var="school" items="${schools}">
                                                        <c:set var="sel_school" value="false" />
                                                        <c:forEach var="my_school" items="${my_schools}">
                                                            <c:if test="${school.id == my_school.id}">
                                                                <c:set var="sel_school" value="true" />
                                                            </c:if>
                                                        </c:forEach>
                                                        <c:if test="${!sel_school}">
                                                            <option value="${school.id}">${school.name}</option>
                                                        </c:if>
                                                    </c:forEach>
                                                </select>
                                            </div>

                                            <div class="col-sm-2">
                                                <button type="button" id="schools_rightAll" class="btn btn-block"><i class="glyphicon glyphicon-forward"></i></button>
                                                <button type="button" id="schools_rightSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-right"></i></button>
                                                <button type="button" id="schools_leftSelected" class="btn btn-block"><i class="glyphicon glyphicon-chevron-left"></i></button>
                                                <button type="button" id="schools_leftAll" class="btn btn-block"><i class="glyphicon glyphicon-backward"></i></button>
                                            </div>

                                            <div class="col-sm-5">
                                                <select name="schools" id="schools_to" class="form-control" size="8" multiple="multiple">
                                                    <c:forEach var="my_school" items="${my_schools}">
                                                        <option value="${my_school.id}" SELECTED="">${my_school.name}</option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>

                                        <button style="width: 100px" class="btn btn-lg btn-primary btn-block" type="submit"><spring:message code="save" text="%save" /></button>
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
