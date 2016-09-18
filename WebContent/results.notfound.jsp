<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Search Results</title>
</head>
<body>
<div class="container">
	<center>
		<h1><a href="${contextPath}/" class="h1-color no-decoration">${title}</a></h1>
		<h3 class="no-margin">Sorry, no matching datasets found!</h3>
		<br/>
		<jstl:forEach var="searchTerm" items="${searchTerms}">
			<span class="label label-default">${searchTerm}</span>
		</jstl:forEach>
		<br/>
		<br/>
		<a href="${contextPath}/search" class="btn btn-primary">Back to Search</a>
	</center>
	<div class="row">
		<div class="col col-lg-space">
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>