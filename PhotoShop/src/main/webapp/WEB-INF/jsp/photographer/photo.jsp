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
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="Photo" items="${Photos}">
                                                <tr>
                                                    <td><a href="${baseurl}/photographer/edit?id=${Photo.id}"><i class="fa fa-pencil" title="<spring:message code="edit" text="%edit" />"></i></a></td>
                                                    <td>${Photo.LowResURL}</td>
                                                    <td>${Photo.Height}</td>
                                                    <td>${Photo.Width}</td>
                                                    <td>${Photo.Date}</td>                                                                                                        
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
