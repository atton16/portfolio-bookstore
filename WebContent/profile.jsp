<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
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
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Edit Profile</h3>
		</div>
	</div>
	<!-- Error Message -->
	<jstl:if test="${error_msg != null || need_verify}"></jstl:if>
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<jstl:if test="${error_msg != null}">
				<jstl:if test="${error}">
					<div class="alert alert-danger">
						${error_msg}
					</div>
				</jstl:if>
				<jstl:if test="${!error}">
					<div class="alert alert-success">
						${error_msg}
					</div>
				</jstl:if>
			</jstl:if>
			<jstl:if test="${need_verify}">
				<div class="alert alert-info">
					Your email is not yet verify. <a href="${contextPath}/user/profile/verify" class="underline-text">Click here to resend verification Email</a>
				</div>
			</jstl:if>
		</div>
	</div>
	<!-- Registration: Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- Registration Form -->
					<form action="${contextPath}/user/profile" method="post">
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Username</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="${user.getUsername()}" disabled/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Nick Name</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="${user.getNickname()}" name="nickname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>First Name</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="${user.getFirstname()}" name="firstname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Last Name</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="${user.getLastname()}" name="lastname" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Email</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="${user.getEmail()}" name="email" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Year of Birth</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="${user.getBirthyear()}" name="yob" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Full Address</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="${user.getAddr()}" name="address" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Credit Card Number</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="${user.getCardno()}" name="ccn" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>New Password</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="password" class="form-control" name="npassword" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Current Password <font color="red">*</font></p>
							</div>
							<div class="col-md-10 col-lg-space">
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