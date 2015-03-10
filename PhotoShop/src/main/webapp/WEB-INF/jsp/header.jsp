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
        <link rel="stylesheet" type="text/css" href="${baseurl}/resources/css/style.css" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
        <script src="${baseurl}/resources/js/js.js"></script>
        <script src="${baseurl}/resources/js/bootstrap.min.js"></script>
        <title>PhotoShop</title>
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
          <a class="navbar-brand" href="${baseurl}/"><spring:message code="pageName" text="%pageName" /></a>
        </div>
        <div id="navbar" class="collapse navbar-collapse">
          <ul class="nav navbar-nav">
            <li class="active"><a href="${baseurl}">Home</a></li>
            <li><a href="#about">About</a></li>
            <li><a href="${baseurl}/contact">Contact</a></li>
            <li class="dropdown">
              <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-expanded="false">Dropdown <span class="caret"></span></a>
              <ul class="dropdown-menu" role="menu">
                <li><a href="#">Action</a></li>
                <li><a href="#">Another action</a></li>
                <li><a href="#">Something else here</a></li>
                <li class="divider"></li>
                <li class="dropdown-header">Nav header</li>
                <li><a href="#">Separated link</a></li>
                <li><a href="#">One more separated link</a></li>
              </ul>
            </li>
            <li><a href="${baseurl}/accountinformatie">My account</a></li>
          </ul>
            <div class="lang-container">
            <a href="<c:url value='?lang=en'/>"><img class="lang-img" src="${baseurl}/resources/img/en.png"/></a>
            <a href="<c:url value='?lang=nl'/>"><img class="lang-img" src="${baseurl}/resources/img/nl.png"/></a>
        </div>
        </div><!--/.nav-collapse -->
      </div>
    </nav>
