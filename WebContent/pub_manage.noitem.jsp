<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Manage Publications</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<center>
		<br/>
		<h3 class="no-margin">Oops!</h3>
		<h4>It looks like you have no publication listing!</h4>
		<br/>
		<a href="${contextPath}/user/sell" class="btn btn-primary">Start selling</a>
	</center>
	<div class="row">
		<div class="col col-lg-space">
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>