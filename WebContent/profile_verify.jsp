<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Verify Email</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Verify Email Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Verify Email</h3>
		</div>
	</div>
	<!-- Verify Email Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<center>
				<h3>The verification email has been sent to <b>xxxx@yyy.zz</b>.</h3>
				<h4>Please check your email.</h4>
				<h3>Failed to send verification email to <b>xxxx@yyy.zz</b>.</h3>
				<h3>You are already activated.</h3>
			</center>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>