<%-- 
    Document   : header
    Created on : Feb 24, 2015, 1:53:43 PM
    Author     : Casper
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<c:set var="baseurl" value="${pageContext.request.servletContext.contextPath}" scope="request" />
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${baseurl}/resources/css/bootstrap.min.css" />
        <link rel="stylesheet" type="text/css" href="${baseurl}/resources/admin/css/font-awesome.css" />
        <link rel="stylesheet" type="text/css" href="${baseurl}/resources/css/style.css" />
        <link rel="icon" href="${baseurl}/resources/img/favicon.ico" type="image/x-icon"/>
        <link rel="shortcut icon" href="${baseurl}/resources/img/favicon.ico" type="image/x-icon"/>
        <link rel="stylesheet" type="text/css" href="${baseurl}/resources/css/cropper.css" />
        <link rel="stylesheet" type="text/css" href="${baseurl}/resources/css/crop.css" />
        <script src="${baseurl}/resources/js/jquery-1.11.2.min.js"></script>
        <script src="${baseurl}/resources/js/bootstrap.min.js"></script>
        <script src="${baseurl}/resources/js/masonry.pkgd.js"></script>
        <script src="${baseurl}/resources/js/imagesloaded.pkgd.min.js"></script>
        <script src="${baseurl}/resources/js/jquery.imageMask.min.js"></script>
        <script src="${baseurl}/resources/admin/js/jquery.fileupload.js"></script>
        <script src="${baseurl}/resources/admin/js/jquery.fileupload-image.js"></script>
        <title>PhotoShop</title>
        <script>
            $( window ).load(function() { 
                var $container = $('#boxes').masonry();
                // layout Masonry again after all images have loaded
                $container.imagesLoaded(function () {
                    $container.masonry();
                });
            });
        </script>
    </head>
    <body>
        <nav class="navbar navbar-default navbar-fixed-top">
            <div class="container">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="${baseurl}/"><img src="${baseurl}/resources/img/Photoshop_white.png"/></a>
                </div>
                <div id="navbar" class="collapse navbar-collapse">
                    <ul class="nav navbar-nav">
                        <li><a href="${baseurl}"><spring:message code="home" text="%home" /></a></li>
                            <c:if test="${sessionScope.UserType == 'STUDENT' && sessionScope.UserID != null}">
                            <li><a href="${baseurl}/photo/mypictures"><spring:message code="mypictures" text="%mypictures" /></a></li>
                            <li><a href="${baseurl}/photo/myschoolclasspictures"><spring:message code="myschoolklasspictures" text="%myschoolklasspictures" /></a></li>
                            <li><a href="${baseurl}/photo/myschoolpictures"><spring:message code="myschoolpictures" text="%myschoolpictures" /></a></li>
                            </c:if>
                        <li><a href="${baseurl}/contact"><spring:message code="contact" text="%contact" /></a></li>
                    </ul>
                    <div class="menuright">
                        <div class="lang-container">
                            <a href="<c:url value='?lang=en'/>"><img class="lang-img" src="${baseurl}/resources/img/en.png"/></a>
                            <a href="<c:url value='?lang=nl'/>"><img class="lang-img" src="${baseurl}/resources/img/nl.png"/></a>
                        </div>
                        <ul class="nav navbar-nav">
                            <li class="dropdown">
                                <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false"><i class="fa fa-user fa-fw"></i><span class="caret"></span></a>
                                <ul class="dropdown-menu" role="menu">
                                    <c:if test="${sessionScope.UserID == null}">
                                        <li><a href="${baseurl}/student/login"><spring:message code="login" text="%login" /></a></li>
                                        </c:if>
                                        <c:if test="${sessionScope.UserType == 'STUDENT' && sessionScope.UserID != null}">
                                        <li><a href="${baseurl}/student/accountgegevens"><spring:message code="information" text="%information" /></a></li>
                                        <li><a href="${baseurl}/order/history"><spring:message code="Orderhistory" text="%Orderhistory" /></a></li>
                                        <li class="divider"></li>
                                        <li><a href="${baseurl}/logout.html"><spring:message code="logout" text="%logout" /></a></li>
                                        </c:if>
                                </ul>
                            </li>
                            <li>
                                <a href="${baseurl}/shoppingcart/list"><i class="fa fa-shopping-cart"></i></a>

                            </li>
                        </ul>
                    </div>
                </div><!--/.nav-collapse -->
            </div>
        </nav>
