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
	
	<!-- User Details Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>User Details</h3>
		</div>
	</div>
	<!-- User Details Content -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2"><label>Nick Name</label></div>
						<div class="col-md-10">${user.getNickname()}</div>
					</div>
					<div class="row">
						<div class="col-md-2"><label>First Name</label></div>
						<div class="col-md-10">${user.getFirstname()}</div>
					</div>
					<div class="row">
						<div class="col-md-2"><label>Last Name</label></div>
						<div class="col-md-10">${user.getLastname()}</div>
					</div>
					<div class="row">
						<div class="col-md-2"><label>Email</label></div>
						<div class="col-md-10">${user.getEmail()}</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Buy History Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Buy History</h3>
		</div>
	</div>
	<!-- Buy History Content -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<jstl:forEach var="buy" items="${buys}">
			<!-- Item # -->
			<div class="panel panel-default">
				<div class="panel-body">
					<table class="fill-width">
						<tr>
							<td align="center" class="cell-180px padding-right-12">
								<img src="${contextPath}${buy.getPicture()}" class="square-180-by-180">
							</td>
							<td class="border-left padding-left-12"><table>
								<tr><td valign="top">
									<h3 class="no-margin"><a href="${contextPath}/pubinfo?id=${buy.getPubID()}" class="link-as-text">${buy.getTitle()}</a></h3>
									<h5><i>
									<jstl:forEach var="writer" items="${buy.getWriters()}" varStatus="stat">
										${writer}${stat.last ? '' : ', '}
									</jstl:forEach>
									</i></h5>
								<td></tr>
								<tr><td valign="bottom">
									<h4><b>A$${buy.getPrice()}.00</b></h4>
									<p><i>Seller: ${buy.getSellerNickname()}</i></p>
									<p><i>Buy Date: ${buy.getBuyDateString()}</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
			</jstl:forEach>
		</div>
	</div>
	
	<!-- Removed From Cart Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Removed From Cart</h3>
		</div>
	</div>
	<!-- Removed From Car Content -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<jstl:forEach var="remove" items="${removes}">
			<!-- Item 1 -->
			<div class="panel panel-default">
				<div class="panel-body">
					<table class="fill-width">
						<tr>
							<td align="center" class="cell-180px padding-right-12">
								<img src="${contextPath}${remove.getPicture()}" class="square-180-by-180">
							</td>
							<td class="border-left padding-left-12"><table>
								<tr><td valign="top">
									<h3 class="no-margin"><a href="${contextPath}/pubinfo?id=${remove.getPubID()}" class="link-as-text">${remove.getTitle()}</a></h3>
									<h5><i>
									<jstl:forEach var="writer" items="${remove.getWriters()}" varStatus="stat">
										${writer}${stat.last ? '' : ', '}
									</jstl:forEach>
									</i></h5>
								<td></tr>
								<tr><td valign="bottom">
									<h4><b>A$${remove.getPrice()}.00</b></h4>
									<p><i>Seller: ${buy.getSellerNickname()}</i></p>
									<p><i>Remove Date: ${buy.getRemoveFromCartDateString()}</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
			</jstl:forEach>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>