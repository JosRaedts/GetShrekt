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
<script src="${baseurl}/resources/admin/js/jquery.fileupload-validate.js"></script>
<script src="${baseurl}/resources/admin/js/jquery.fileupload-ui.js"></script>

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
                                    <form id="fileupload" action="${baseurl}/upload/do_upload" method="POST" enctype="multipart/form-data">
                                    <!-- The fileupload-buttonbar contains buttons to add/delete files and start/cancel the upload -->
                                    <div class="row fileupload-buttonbar" style="margin:20px;">
                                        <div class="col-lg-7">
                                            <!-- The fileinput-button span is used to style the file input field as button -->
                                            <span class="btn btn-success fileinput-button">
                                                <i class="glyphicon glyphicon-plus"></i>
                                                <span>Add files...</span>
                                                <input type="file" name="userfile" multiple>
                                            </span>
                                            <button type="submit" class="btn btn-primary start">
                                                <i class="glyphicon glyphicon-upload"></i>
                                                <span>Start upload</span>
                                            </button>
                                            <button type="reset" class="btn btn-warning cancel">
                                                <i class="glyphicon glyphicon-ban-circle"></i>
                                                <span>Cancel upload</span>
                                            </button>
                                            <button type="button" class="btn btn-danger delete">
                                                <i class="glyphicon glyphicon-trash"></i>
                                                <span>Delete</span>
                                            </button>
                                            <input type="checkbox" class="toggle">
                                            <!-- The global file processing state -->
                                            <span class="fileupload-process"></span>
                                        </div>
                                        <!-- The global progress state -->
                                        <div class="col-lg-5 fileupload-progress fade">
                                            <!-- The global progress bar -->
                                            <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100">
                                                <div class="progress-bar progress-bar-success" style="width:0%;"></div>
                                            </div>
                                            <!-- The extended global progress state -->
                                            <div class="progress-extended">&nbsp;</div>
                                        </div>
                                    </div>
                                    <!-- The table listing the files available for upload/download -->
                                    <table role="presentation" class="table table-striped"><tbody class="files"></tbody></table>
                                    </form>
                                    <!-- The template to display files available for upload -->
                                    <script id="template-upload" type="text/x-tmpl">
                                        {% for (var i=0, file; file=o.files[i]; i++) { %}
                                            <tr class="template-upload fade">
                                                <td>
                                                    <span class="preview"></span>
                                                </td>
                                                <td>
                                                    <p class="name">{%=file.name%}</p>
                                                    <strong class="error text-danger"></strong>
                                                </td>
                                                <td>
                                                    <p class="size">Processing...</p>
                                                    <div class="progress progress-striped active" role="progressbar" aria-valuemin="0" aria-valuemax="100" aria-valuenow="0"><div class="progress-bar progress-bar-success" style="width:0%;"></div></div>
                                                </td>
                                                <td>
                                                    {% if (!i && !o.options.autoUpload) { %}
                                                        <button class="btn btn-primary start" disabled>
                                                            <i class="glyphicon glyphicon-upload"></i>
                                                            <span>Start</span>
                                                        </button>
                                                    {% } %}
                                                    {% if (!i) { %}
                                                        <button class="btn btn-warning cancel">
                                                            <i class="glyphicon glyphicon-ban-circle"></i>
                                                            <span>Cancel</span>
                                                        </button>
                                                    {% } %}
                                                </td>
                                            </tr>
                                        {% } %}
                                    </script>
                                    <!-- The template to display files available for download -->
                                    <script id="template-download" type="text/x-tmpl">
                                        {% for (var i=0, file; file=o.files[i]; i++) { %}
                                            <tr class="template-download fade">
                                                <td>
                                                    <span class="preview">
                                                        {% if (file.thumbnailUrl) { %}
                                                            <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" data-gallery><img src="{%=file.thumbnailUrl%}"></a>
                                                        {% } %}
                                                    </span>
                                                </td>
                                                <td>
                                                    <p class="name">
                                                        {% if (file.url) { %}
                                                            <a href="{%=file.url%}" title="{%=file.name%}" download="{%=file.name%}" {%=file.thumbnailUrl?'data-gallery':''%}>{%=file.name%}</a>
                                                        {% } else { %}
                                                            <span>{%=file.name%}</span>
                                                        {% } %}
                                                    </p>
                                                    {% if (file.error) { %}
                                                        <div><span class="label label-danger">Error</span> {%=file.error%}</div>
                                                    {% } %}
                                                </td>
                                                <td>
                                                    <span class="size">{%=o.formatFileSize(file.size)%}</span>
                                                </td>
                                                <td>
                                                    {% if (file.deleteUrl) { %}
                                                        <button class="btn btn-danger delete" data-type="{%=file.deleteType%}" data-url="{%=file.deleteUrl%}"{% if (file.deleteWithCredentials) { %} data-xhr-fields='{"withCredentials":true}'{% } %}>
                                                            <i class="glyphicon glyphicon-trash"></i>
                                                            <span>Delete</span>
                                                        </button>
                                                        <input type="checkbox" name="delete" value="1" class="toggle">
                                                    {% } else { %}
                                                        <button class="btn btn-warning cancel">
                                                            <i class="glyphicon glyphicon-ban-circle"></i>
                                                            <span>Cancel</span>
                                                        </button>
                                                    {% } %}
                                                </td>
                                            </tr>
                                        {% } %}
                                    </script>
                                    <script type="text/javascript">
                                        $(function () {
                                            'use strict';

                                            // Initialize the jQuery File Upload widget:
                                            $('#fileupload').fileupload({
                                                // Uncomment the following to send cross-domain cookies:
                                                //xhrFields: {withCredentials: true},
                                                url: '${baseurl}/photo/upload/do_upload'
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
                                                        url: '//jquery-file-upload.appspot.com/',
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
