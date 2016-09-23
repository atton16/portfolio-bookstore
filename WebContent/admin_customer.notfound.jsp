<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Admin - Customer Activity</title>
</head>
<body>
<jsp:include page="inc.body.admin_header.jsp" />
<div class="container">
	<!-- Admin - Customer Activity Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>
				<a href="${contextPath}/admin">Admin</a> /
				<a href="${contextPath}/admin/users/manage">Manage Users</a> /
				Customer Activity
			</h3>
		</div>
	</div>
	<!-- Admin - Customer Activity Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<center><h3>Sorry, user not found.</h3></center>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>