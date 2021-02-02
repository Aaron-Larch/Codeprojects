<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import= "java.util.Arrays"%>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<c:set var="contextPath" value="${pageContext.request.contextPath}"/> 
<head>
    <title>Hello</title>
     <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
  <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
</head>
<body>
    JSP PAGE: Hello ${name}<br>
    This is a new page and not what is being downloaded<br>
    <spring:url value="${contextPath}/download" var="printURL" />
  	<a class="btn btn-primary" href="${printURL }" role="button" >Download a PDF and refresh</a>
	<br>
	<spring:url value="${contextPath}/download1" var="printfile" />
  	<a class="btn btn-primary" href="${printfile }" role="button" >Download a PDF with browser</a>
  	<br>
	<spring:url value="${contextPath}/download2" var="printdynamic" />
  	<a class="btn btn-primary" href="${printdynamic }" role="button" >Download a PDF test</a>
  	<br>
  	These are some of the ways to download a file
</body>
</html>