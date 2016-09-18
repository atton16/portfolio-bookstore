<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Sell</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Sell Title -->
	<div class="row">
		<div class="col-md-5 col-centered col-lg-space">
			<center><h3>Congratulations!</h3></center>
		</div>
	</div>
	<!-- Sell: Contents -->
	<div class="row">
		<div class="col-md-5 col-centered col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-12 col-lg-space">
							<center>
								Your publication is now listing on our website.<br/><br/>
								<a href="${contextPath}/user/pub/manage" class="btn btn-primary">Manage</a>
								<a href="${contextPath}/user/sell" class="btn btn-info">Sell more</a>
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