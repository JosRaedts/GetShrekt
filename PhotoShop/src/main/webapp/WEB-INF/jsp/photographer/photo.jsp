<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<div id="page-wrapper">
    <div class="row">
        <div class="col-lg-12">
            <h1 class="page-header"><spring:message code="photographers" text="%photographers" /></h1>
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
                                    <script>
                                        $(document).ready(function() {
                                            $('#phototable').DataTable({
                                                responsive: true
                                            });
                                        });
                                    </script>
                                    <table class="table table-striped table-bordered table-hover dataTable no-footer" id="phototable">
                                        <thead>
                                            <tr>
                                                <th><spring:message code="Photoid" text="%Photoid" /></th>
                                                <th><spring:message code="LowResURL" text="%LowResURL" /></th> 
                                                <th><spring:message code="Height" text="%Height" /></th>
                                                <th><spring:message code="Width" text="%Width" /></th>    
                                                <th><spring:message code="Date" text="%Date" /></th>
                                                <th><spring:message code="active" text="%Active" /></th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="Photo" items="${Photos}">
                                                <tr> 
                                                    <td>${Photo.id}</td>
                                                    <td><img SRC="${baseurl}/photo/view/thumb/${Photo.id}"></td>
                                                    <td>${Photo.height}</td>
                                                    <td>${Photo.width}</td>
                                                    <td><fmt:formatDate value="${Photo.date}" type="both" pattern="dd-MM-yyyy" /></td> 
                                                    <c:if test="${Photo.active}">
                                                        <td><i class="fa fa-check fa-2x" style="color: green;"></i>
                                                    </c:if>
                                                    <c:if test="${!Photo.active}">
                                                        <td><i class="fa fa-times fa-2x" style="color: red;"></i>
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
