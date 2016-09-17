<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="app" class="com.sixppl.main.Application"/>
<jstl:set var="contextPath" value="${app.getSharedInstance().getContextPath()}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Digital Bibliographic Library</title>
<link href="${contextPath}/css/bootstrap.min.css" rel="stylesheet">
<link href="${contextPath}/css/asst2.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<center>
			<h1><a href="${contextPath}/" class="h1-color no-decoration">Digital Bibliographic Library</a></h1>
			<br />
			<h3>Sorry</h3>
			<h3>Page not found</h3>
			<h4>(404)</h4>
		</center>
	</div>

	<script src="${contextPath}/js/jquery-3.1.0.min.js"></script>
	<script src="${contextPath}/js/jquery.color-2.1.2.min.js"></script>
	<script src="${contextPath}/js/bootstrap.min.js"></script>
	<script src="${contextPath}/js/asst2.js"></script>
</body>
</html>