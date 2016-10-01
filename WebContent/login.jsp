<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Login</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Login Title -->
	<div class="row">
		<div class="col-md-4 col-centered col-lg-space">
			<div class="center-text">
				<h3>Login</h3>
			</div>
		</div>
	</div>
	<!-- Error Message -->
	<jstl:if test="${error_msg != NULL}">
		<div class="row">
			<div class="col-md-4 col-centered col-lg-space">
				<div class="alert alert-danger">
					${error_msg}
				</div>
			</div>
		</div>
	</jstl:if>

	<!-- Login: Contents -->
	<div class="row">
		<div class="col-md-4 col-centered col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- Login Form -->
					<form action="${contextPath}/login" method="post">
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="text" class="form-control" placeholder="Username" name="username" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="password" class="form-control" placeholder="Password" name="password" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="submit" class="btn btn-primary" value="Sign in" /> or <a href="${contextPath}/signup">Sign up</a>
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>