<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Registration</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Registration Title -->
	<div class="row">
		<div class="col-md-4 col-centered col-lg-space">
			<center><h3>Registration</h3></center>
		</div>
	</div>
	<!-- Error Message -->
	<div class="row">
		<div class="col-md-4 col-centered col-lg-space">
			<div class="alert alert-danger">
				Error message
			</div>
		</div>
	</div>
	<!-- Registration: Contents -->
	<div class="row">
		<div class="col-md-4 col-centered col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- Registration Form -->
					<form action="${contextPath}/signup" method="post">
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
								<input type="text" class="form-control" placeholder="Nick name" name="nickname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="text" class="form-control" placeholder="First name" name="firstname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="text" class="form-control" placeholder="Last name" name="lastname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="text" class="form-control" placeholder="Email" name="email" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="text" class="form-control" placeholder="Year of Birth" name="yob" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="text" class="form-control" placeholder="Full address" name="address" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="text" class="form-control" placeholder="Credit Card Number" name="ccn" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="submit" class="btn btn-primary" value="Sign up" />
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