<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Edit Profile</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Registration Title -->
	<div class="row">
		<div class="col-md-4 col-centered col-lg-space">
			<center><h3>Edit Profile</h3></center>
		</div>
	</div>
	<!-- Error Message -->
	<div class="row">
		<div class="col-md-4 col-centered col-lg-space">
			<div class="alert alert-danger">
				Error message
			</div>
			<div class="alert alert-success">
				Success message
			</div>
		</div>
	</div>
	<!-- Registration: Contents -->
	<div class="row">
		<div class="col-md-4 col-centered col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- Registration Form -->
					<form action="${contextPath}/user/profile" method="post">
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>Username</label>
								<input type="text" class="form-control" placeholder="foo" disabled/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>Nick Name</label>
								<input type="text" class="form-control" placeholder="Fb" name="nickname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>First Name</label>
								<input type="text" class="form-control" placeholder="Foo" name="firstname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>Last Name</label>
								<input type="text" class="form-control" placeholder="Bar" name="lastname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>Email</label>
								<input type="text" class="form-control" placeholder="foo@bar.com" name="email" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>Year of Birth</label>
								<input type="text" class="form-control" placeholder="1999" name="yob" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>Full Address</label>
								<input type="text" class="form-control" placeholder="1/11 King st, Kingsford, NSW, 1111" name="address" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>Credit Card Number</label>
								<input type="text" class="form-control" placeholder="1234 5678 9012 3456" name="ccn" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>New Password</label>
								<input type="password" class="form-control" name="npassword" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<label>Current Password <font color="red">*</font></label>
								<input type="password" class="form-control" name="cpassword" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="submit" class="btn btn-primary" value="Done" />
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