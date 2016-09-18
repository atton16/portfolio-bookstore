<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="app" class="com.sixppl.main.Application"/>
<jstl:set var="contextPath" value="${app.getSharedInstance().getContextPath()}"/>
<jstl:set var="title" value="${app.getSharedInstance().getTitle()}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: 5xx</title>
</head>
<body>
	<div class="container">
		<center>
			<h1><a href="${contextPath}/" class="h1-color no-decoration">${title}</a></h1>
			<h3>Server Error</h3>
			<h3>Please contact system support.</h3>
		</center>
	</div>

	<jsp:include page="inc.body.footer.jsp" />
</body>
</html>