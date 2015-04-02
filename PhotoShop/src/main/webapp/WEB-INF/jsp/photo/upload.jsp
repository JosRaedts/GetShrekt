<%-- 
    Document   : home
    Created on : Mar 17, 2015, 10:14:29 AM
    Author     : Casper
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<jsp:include page="/WEB-INF/jsp/admin/header.jsp" />
<link rel="stylesheet" href="${baseurl}/resources/admin/css/jquery.fileupload.css">
<script src="${baseurl}/resources/admin/js/jquery.ui.widget.js"></script>
<script src="//blueimp.github.io/JavaScript-Templates/js/tmpl.min.js"></script>
<script src="//blueimp.github.io/JavaScript-Load-Image/js/load-image.all.min.js"></script>
<script src="//blueimp.github.io/JavaScript-Canvas-to-Blob/js/canvas-to-blob.min.js"></script>
<script src="//blueimp.github.io/Gallery/js/jquery.blueimp-gallery.min.js"></script>
<script src="${baseurl}/resources/admin/js/jquery.iframe-transport.js"></script>
<script src="${baseurl}/resources/admin/js/jquery.fileupload.js"></script>
<script src="${baseurl}/resources/admin/js/jquery.fileupload-process.js"></script>
<script src="${baseurl}/resources/admin/js/jquery.fileupload-image.js"></script>    
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
                                        <form id="fileupload">
                                            <input id="upload_photos" type="file" name="files[]" multiple>
                                        </form>
                                        
                                        <script type="text/javascript">
                                            $(function () {
                                                'use strict';

                                                // Initialize the jQuery File Upload widget:
                                                $('#fileupload').fileupload({
                                                    // Uncomment the following to send cross-domain cookies:
                                                    //xhrFields: {withCredentials: true},
                                                    url: 'upload/do_upload'
                                                });

                                                // Enable iframe cross-domain access via redirect option:
                                                $('#fileupload').fileupload(
                                                    'option',
                                                    'redirect',
                                                    window.location.href.replace(
                                                        /\/[^\/]*$/,
                                                        '/cors/result.html?%s'
                                                    )
                                                );

                                                if (window.location.hostname === 'blueimp.github.io') {
                                                    // Demo settings:
                                                    $('#fileupload').fileupload('option', {
                                                        url: '//jquery-file-upload.appspot.com/',
                                                        // Enable image resizing, except for Android and Opera,
                                                        // which actually support image resizing, but fail to
                                                        // send Blob objects via XHR requests:
                                                        disableImageResize: /Android(?!.*Chrome)|Opera/
                                                            .test(window.navigator.userAgent),
                                                        maxFileSize: 5000000,
                                                        acceptFileTypes: /(\.|\/)(gif|jpe?g|png)$/i
                                                    });
                                                    // Upload server status check for browsers with CORS support:
                                                    if ($.support.cors) {
                                                        $.ajax({
                                                            url: 'upload/do_upload',
                                                            type: 'HEAD'
                                                        }).fail(function () {
                                                            $('<div class="alert alert-danger"/>')
                                                                .text('Upload server currently unavailable - ' +
                                                                        new Date())
                                                                .appendTo('#fileupload');
                                                        });
                                                    }
                                                } else {
                                                    // Load existing files:
                                                    $('#fileupload').addClass('fileupload-processing');
                                                    $.ajax({
                                                        // Uncomment the following to send cross-domain cookies:
                                                        //xhrFields: {withCredentials: true},
                                                        url: $('#fileupload').fileupload('option', 'url'),
                                                        dataType: 'json',
                                                        context: $('#fileupload')[0]
                                                    }).always(function () {
                                                        $(this).removeClass('fileupload-processing');
                                                    }).done(function (result) {
                                                        $(this).fileupload('option', 'done')
                                                            .call(this, $.Event('done'), {result: result});
                                                    });
                                                }

                                            });
                                        </script>
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
