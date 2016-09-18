<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Receipt</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Receipt Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Receipt</h3>
		</div>
	</div>
	<!-- Receipt: One item per row -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<!-- Item 1 -->
			<div class="panel panel-default">
				<div class="panel-body">
					<table class="fill-width">
						<tr>
							<td align="center" class="cell-180px padding-right-12">
								<img src="${contextPath}/uploads/1984.jpeg" class="square-180-by-180">
							</td>
							<td class="border-left padding-left-12"><table>
								<tr><td valign="top">
									<h3 class="no-margin"><a href="${contextPath}/pubinfo?id=pubid1" class="link-as-text">1984</a></h3>
									<h5><i>George Orwell</i></h5>
								<td></tr>
								<tr><td valign="bottom">
									<h4><b>A$40.00</b></h4>
									<p><i>Seller: Nickname</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
			
			<!-- Item 2 -->
			<div class="panel panel-default">
				<div class="panel-body">
					<table class="fill-width">
						<tr>
							<td align="center" class="cell-180px padding-right-12">
								<img src="${contextPath}/uploads/aba-04.png" class="square-180-by-180">
							</td>
							<td class="border-left padding-left-12"><table>
								<tr><td valign="top">
									<h3 class="no-margin"><a href="${contextPath}/pubinfo?id=pubid2" class="link-as-text">Responsive Web Design</a></h3>
									<h5><i>Ethan Marcotte</i></h5>
								<td></tr>
								<tr><td valign="bottom">
									<h4><b>A$40.00</b></h4>
									<p><i>Seller: Nickname</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
		<h4><b>Total: A$80.00</b></h4>
		</div>
	</div>
	<!-- Billing Details Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Billing Details</h3>
		</div>
	</div>
	<!-- Billing Details: Content -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<p><b>Ettus Research</b></p>
					<p>1/11 Kings st<br/>
					Kingsford<br/>
					NSW<br/>
					1111</p>
				</div>
			</div>
		</div>
	</div>
	<!-- Download Links Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Download Links</h3>
		</div>
	</div>
	<!-- Download Links: Content -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<p>1. <a href="#">1984</a></p>
					<p>2. <a href="#">Responsive Web Design</a></p>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>