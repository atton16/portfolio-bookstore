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
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Registration</h3>
		</div>
	</div>
	<!-- Error Message -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="alert alert-danger">
				Error message
			</div>
		</div>
	</div>
	<!-- Registration: Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- Registration Form -->
					<form action="${contextPath}/signup" method="post">
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Username</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Username" name="username" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Password</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="password" class="form-control" placeholder="Password" name="password" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Nick Name</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Nick Name" name="nickname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>First Name</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="First Name" name="firstname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Last Name</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Last Name" name="lastname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Email</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Email" name="email" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Year of Birth</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Year of Birth" name="yob" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Full Address</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Full Address" name="address" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Credit Card Number</p>
							</div>
							<div class="col-md-10 col-lg-space">
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