<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Shopping Cart</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Shopping Cart Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Shopping Cart</h3>
			<p><i>There are ${items.size()} items in your shopping cart.</i></p>
			<jstl:forEach var="searchTerm" items="${searchTerms}">
				<span class="label label-default">${searchTerm}</span>
			</jstl:forEach>
		</div>
	</div>
	<!-- Shopping Cart Form -->
	<form class="datasource-cart-remove">
	<!-- Shopping Cart Contents: One item per row -->
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
									<p><input type="checkbox" class="checked-count" name="id" value="${item.getPubID()}" /> Remove</p>
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
	</form>
	<form class="datasource-checkout">
	<jstl:forEach var="item" items="${items}">
		<input type="hidden" name="id" value="${item.getPubID()}" />
	</jstl:forEach>
	</form>
	<!-- Hidden Forms -->
	<form action="${contextPath}/cart/remove" method="post" class="form-cart-remove"></form>
	<form action="${contextPath}/receipt" method="post" class="form-checkout"></form>
	<!-- Shopping Cart Buttons -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<input type="submit" id="checked-count-update" class="btn btn-danger submit-cart-remove" value="Remove from Cart (0)" disabled />
			<input type="submit" class="btn btn-primary submit-checkout" value="Checkout!" />
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>