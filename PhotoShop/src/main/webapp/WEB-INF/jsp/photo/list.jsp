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
                    <!-- /.panel-heading -->
                    <div class="panel-body">
                        <div class="row">
                            <div class="col-lg-12">
                                <div class="dataTable_wrapper">
                                    <script>
                                        $(document).ready(function () {
                                            $('#schooltable').DataTable({
                                                responsive: true
                                            });
                                        });
                                    </script>
                                    <table class="table table-striped table-bordered table-hover dataTable no-footer" id="schooltable">
                                        <thead>
                                            <tr>
                                                <th><spring:message code="photolistpicture" text="%photolistpicture" /></th>
                                                <th><spring:message code="photolistphotographername" text="%photolistphotographername" /></th>
                                                <th><spring:message code="photolistwidth" text="%photolistwidth" /></th>  
                                                <th><spring:message code="photolistheight" text="%photolistheight" /></th>
                                                <th><spring:message code="photolistdate" text="%photolistdate" /></th>
                                                <th><spring:message code="photolistactive" text="%photolistactive" /></th>
                                              
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="picture" items="${pictures}">
                                            <input type="hidden" id="hiddenIdfield" name="id" value="${school.id}">
                                            <tr>
                                                <td><img src="${baseurl}/photo/view/thumb/${picture.id}"/></td>
                                                <td>${picture.getPhotographer().getName()}</td>
                                                <td>${picture.width}</td>
                                                <td>${picture.height}</td>
                                                <td><fmt:formatDate value="${picture.date}" type="both" pattern="dd-MM-yyyy" /></td> 
                                                <c:if test="${picture.active}">
                                                    <td><a href="${baseurl}/photo/active/${picture.id}"><i class="fa fa-check fa-2x" style="color: green;"></i></a>
                                                </c:if>
                                                <c:if test="${!picture.active}">
                                                    <td><a href="${baseurl}/photo/active/${picture.id}"><i class="fa fa-times fa-2x" style="color: red;"></i></a>
                                                </c:if>
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
