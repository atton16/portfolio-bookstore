<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Admin - Publication Details</title>
</head>
<body>
<jsp:include page="inc.body.admin_header.jsp" />
<div class="container">
	<!-- Admin - Publication Details Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3><a href="${contextPath}/admin">Admin</a> / <a href="${contextPath}/admin/pub/manage">Manage Publications</a> / Publication Details</h3>
			<div>
				<div class="margin-top-24"></div>
				<h3>No publication found.</h3>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>