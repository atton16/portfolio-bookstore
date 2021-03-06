<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
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
			<jstl:set var="total_price" value="0" />
			<jstl:forEach var="item" items="${items}">
			<jstl:set var="total_price" value="${total_price + item.getPrice()}" />
			<!-- Item # -->
			<div class="panel panel-default">
				<div class="panel-body">
					<table class="fill-width">
						<tr>
							<td align="center" class="cell-180px padding-right-12">
								<img src="${contextPath}${item.getPicture()}" class="square-180-by-180">
							</td>
							<td class="border-left padding-left-12"><table>
								<tr><td valign="top">
									<h3 class="no-margin"><a href="${contextPath}/pubinfo?id=${item.getPubID()}" class="link-as-text">${item.getTitle()}</a></h3>
									<h5><i>
									<jstl:forEach var="writer" items="${item.getWriters()}" varStatus="stat">
										${writer}${stat.last ? '' : ', '}
									</jstl:forEach>
									</i></h5>
								<td></tr>
								<tr><td valign="bottom">
									<h4><b>A$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.getPrice()}" /></b></h4>
									<p><i>Seller: ${item.getSellerNickname()}</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
			</jstl:forEach>
		</div>
	</div>
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
		<h4><b>Total: A$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${total_price}" /></b></h4>
		</div>
	</div>
	<!-- Credit Card Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Credit Card Charged</h3>
		</div>
	</div>
	<!-- Credit Card: Content -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<p><b>${user.getCardno()}</b></p>
				</div>
			</div>
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
					
					<p><b>Name: ${user.getFirstname()} ${user.getLastname()}</b></p>
					<p>Address: ${user.getAddr()}</p>
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
					<jstl:set var="count" value="0" />
					<jstl:forEach var="item" items="${items}">
					<jstl:set var="count" value="${count + 1}" />
					<p>${count}. <a href="${contextPath}${item.getPicture()}" target="_blank">${item.getTitle()}</a></p>
					</jstl:forEach>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>