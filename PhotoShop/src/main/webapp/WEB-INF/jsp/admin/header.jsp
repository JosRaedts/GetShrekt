<%-- 
    Document   : header
    Created on : Mar 17, 2015, 10:11:11 AM
    Author     : Casper
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="baseurl" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="icon" href="${baseurl}/resources/img/favicon.ico" type="image/x-icon"/>
    <link rel="shortcut icon" href="${baseurl}/resources/img/favicon.ico" type="image/x-icon"/>


    <title>Photoshop|Admin</title>

    <!-- Bootstrap Core CSS -->
    <link href="${baseurl}/resources/admin/css/bootstrap.min.css" rel="stylesheet">

    <!-- MetisMenu CSS -->
    <link href="${baseurl}/resources/admin/css/metisMenu.min.css" rel="stylesheet">

    <!-- Timeline CSS -->
    <link href="${baseurl}/resources/admin/css/timeline.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${baseurl}/resources/admin/css/sb-admin-2.css" rel="stylesheet">

    <!-- Custom Fonts -->
    <link href="${baseurl}/resources/admin/css/font-awesome.min.css" rel="stylesheet" type="text/css">

    <!-- DataTables Responsive CSS -->
    <link href="${baseurl}/resources/admin/css/dataTables.bootstrap.css" rel="stylesheet">
    <link href="${baseurl}/resources/admin/css/dataTables.responsive.css" rel="stylesheet">
    <link href="${baseurl}/resources/admin/css/dataTables.fontAwesome.css" rel="stylesheet">

    <link href="${baseurl}/resources/admin/css/jquery.multiselect2side.css" rel="stylesheet">

    <link href="${baseurl}/resources/admin/css/style.css" rel="stylesheet">


    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
        <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
        <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->


    <!-- jQuery -->
    <script src="//ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
    <script src="//cdnjs.cloudflare.com/ajax/libs/jquery-migrate/1.2.1/jquery-migrate.min.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${baseurl}/resources/admin/js/bootstrap.min.js"></script>

    <!-- Metis Menu Plugin JavaScript -->
    <script src="${baseurl}/resources/admin/js/metisMenu.min.js"></script>

    <!-- Custom Theme JavaScript -->
    <script src="${baseurl}/resources/admin/js/sb-admin-2.js"></script>

    <script src="${baseurl}/resources/admin/js/jquery.dataTables.min.js"></script>
    <script src="${baseurl}/resources/admin/js/dataTables.bootstrap.min.js"></script>
    <script src="${baseurl}/resources/admin/js/multiselect.min.js"></script>
</head>

<body>

    <div id="wrapper">

        <!-- Navigation -->
        <nav class="navbar navbar-inverse navbar-static-top" role="navigation" style="margin-bottom: 0">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="admin"><img src="${baseurl}/resources/img/Photoshop_white.png"/></a>
            </div>
            <!-- /.navbar-header -->

            <ul class="nav navbar-top-links navbar-right">
                <li>
                    <a href="<c:url value='?lang=en'/>"><img class="lang-img" height="20px" src="${baseurl}/resources/img/en.png"/></a>
                </li>
                <li>
                    <a href="<c:url value='?lang=nl'/>"><img class="lang-img" height="20px" src="${baseurl}/resources/img/nl.png"/></a>
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-bell fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-alerts">
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-comment fa-fw"></i> New Comment
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-twitter fa-fw"></i> 3 New Followers
                                    <span class="pull-right text-muted small">12 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-envelope fa-fw"></i> Message Sent
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-tasks fa-fw"></i> New Task
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a href="#">
                                <div>
                                    <i class="fa fa-upload fa-fw"></i> Server Rebooted
                                    <span class="pull-right text-muted small">4 minutes ago</span>
                                </div>
                            </a>
                        </li>
                        <li class="divider"></li>
                        <li>
                            <a class="text-center" href="#">
                                <strong>See All Alerts</strong>
                                <i class="fa fa-angle-right"></i>
                            </a>
                        </li>
                    </ul>
                    <!-- /.dropdown-alerts -->
                </li>
                <!-- /.dropdown -->
                <li class="dropdown">
                    <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                        <i class="fa fa-user fa-fw"></i>  <i class="fa fa-caret-down"></i>
                    </a>
                    <ul class="dropdown-menu dropdown-user">
                        <li><a href="${baseurl}/admin/accountgegevens"><i class="fa fa-user fa-fw"></i> User Profile</a>
                        </li>
                        <li><a href="#"><i class="fa fa-gear fa-fw"></i> Settings</a>
                        </li>
                        <li class="divider"></li>
                        <li><a href="${baseurl}/logout.html"><i class="fa fa-sign-out fa-fw"></i> Logout</a>
                        </li>
                    </ul>
                    <!-- /.dropdown-user -->
                </li>
                <!-- /.dropdown -->
            </ul>
            <!-- /.navbar-top-links -->

            <div class="navbar-inverse sidebar" role="navigation">
                <div class="sidebar-nav navbar-collapse">
                    <ul class="nav" id="side-menu">
                        <li class="sidebar-search">
                            <div class="input-group custom-search-form">
                                <input type="text" class="form-control" placeholder="Search...">
                                <span class="input-group-btn">
                                <button class="btn btn-default" type="button">
                                    <i class="fa fa-search"></i>
                                </button>
                            </span>
                            </div>
                            <!-- /input-group -->
                        </li>
                        <li>
                            <a href="${baseurl}/admin"><i class="fa fa-dashboard fa-fw"></i> Dashboard</a>
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-picture-o fa-fw"></i> <spring:message code="photos" text="%photos" /><span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <c:if test="${sessionScope.UserType == 'PHOTOGRAPHER' && sessionScope.UserID != null}">
                                <li>
                                    <a href="${baseurl}/photo/upload"><i class="fa fa-plus fa-fw"></i> <spring:message code="add" text="%add" /></a>
                                </li>
                                <li>
                                    <a href="${baseurl}/photographer/photo"><i class="fa fa-list-ul fa-fw"></i> <spring:message code="overview" text="%overview" /></a>
                                </li>
                                </c:if>
                                <c:if test="${sessionScope.UserType == 'ADMIN' && sessionScope.UserID != null}">
                                <li>
                                    <a href="${baseurl}/photo/list"><i class="fa fa-list-ul fa-fw"></i> <spring:message code="overview" text="%overview" /></a>
                                </li>
                                </c:if>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-beer fa-fw"></i> <spring:message code="products" text="%products" /><span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <c:if test="${sessionScope.UserType == 'PHOTOGRAPHER' && sessionScope.UserID != null}">
                                <li>
                                    <a href="${baseurl}/product/set"><i class="fa fa-eur fa-fw"></i> <spring:message code="setPrice" text="%setPrice" /></a>
                                </li>
                                </c:if>
                                <c:if test="${sessionScope.UserType == 'ADMIN' && sessionScope.UserID != null}">
                                <li>
                                    <a href="${baseurl}/product/add"><i class="fa fa-plus fa-fw"></i> <spring:message code="add" text="%add" /></a>
                                </li>
                                <li>
                                    <a href="${baseurl}/product/list"><i class="fa fa-list-ul fa-fw"></i> <spring:message code="overview" text="%overview" /></a>
                                </li>
                                </c:if>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <c:if test="${sessionScope.UserType == 'ADMIN' && sessionScope.UserID != null}">
                        <li>
                            <a href="#"><i class="fa fa-camera fa-fw"></i> <spring:message code="photographers" text="%photographers" /><span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${baseurl}/photographer/add"><i class="fa fa-plus fa-fw"></i> <spring:message code="add" text="%add" /></a>
                                </li>
                                <li>
                                    <a href="${baseurl}/photographer/list"><i class="fa fa-list-ul fa-fw"></i> <spring:message code="overview" text="%overview" /></a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        </c:if>
                        <li>
                            <a href="#"><i class="fa fa-building fa-fw"></i> <spring:message code="schools" text="%schools" /><span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${baseurl}/school/add"><i class="fa fa-plus fa-fw"></i> <spring:message code="add" text="%add" /></a>
                                </li>
                                <li>
                                    <a href="${baseurl}/school/list"><i class="fa fa-list-ul fa-fw"></i> <spring:message code="overview" text="%overview" /></a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-graduation-cap fa-fw"></i> <spring:message code="schoolClasses" text="%schoolClasses" /><span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${baseurl}/schoolclass/add"><i class="fa fa-plus fa-fw"></i> <spring:message code="add" text="%add" /></a>
                                </li>
                                <li>
                                    <a href="${baseurl}/schoolclass/list"><i class="fa fa-list-ul fa-fw"></i> <spring:message code="overview" text="%overview" /></a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <li>
                            <a href="#"><i class="fa fa-user fa-fw"></i> <spring:message code="students" text="%students" /><span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="${baseurl}/student/add"><i class="fa fa-plus fa-fw"></i> <spring:message code="add" text="%add" /></a>
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        <!--
                        <li>
                            <a href="${baseurl}/admin/barcodes"><i class="fa fa-barcode fa-fw"></i> <spring:message code="barcode" text="%barcode" /></a>
                        </li>
                        -->
                        <li>
                            <a href="#"><i class="fa fa-sitemap fa-fw"></i> Multi-Level Dropdown<span class="fa arrow"></span></a>
                            <ul class="nav nav-second-level">
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Second Level Item</a>
                                </li>
                                <li>
                                    <a href="#">Third Level <span class="fa arrow"></span></a>
                                    <ul class="nav nav-third-level">
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                        <li>
                                            <a href="#">Third Level Item</a>
                                        </li>
                                    </ul>
                                    <!-- /.nav-third-level -->
                                </li>
                            </ul>
                            <!-- /.nav-second-level -->
                        </li>
                        
                    </ul>
                </div>
                <!-- /.sidebar-collapse -->
            </div>
            <!-- /.navbar-static-side -->
        </nav>