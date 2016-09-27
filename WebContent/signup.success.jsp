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
		<div class="col-md-5 col-centered col-lg-space">
			<center><h3>Confirm Email</h3></center>
		</div>
	</div>
	<!-- Registration: Contents -->
	<div class="row">
		<div class="col-md-5 col-centered col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-lg-space">
							<center>
								A confirmation email has been sent to ${email}.<br/>
								Please click the link in your email to confirm your registration.<br/>
								<form action="${contextPath}/signup/resend" method="post"  class="form-signup-resend">
									<input type="hidden" name="email" value="${email}" />
								</form>
								<a href="#" class="submit-signup-resend">Resend</a>
							</center>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>