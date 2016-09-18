<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
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
			<p><i>There are X items in your shopping cart.</i></p>
			<jstl:forEach var="searchTerm" items="${searchTerms}">
				<span class="label label-default">${searchTerm}</span>
			</jstl:forEach>
		</div>
	</div>
	<!-- Shopping Cart Form -->
	<form action="${contextPath}/cart/remove" method="post">
	
	<!-- Shopping Cart Contents: One item per row -->
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
									<h3 class="no-margin"><a href="#" class="link-as-text">1984</a></h3>
									<h5><i>George Orwell</i></h5>
								<td></tr>
								<tr><td valign="bottom">
									<h4><b>A$40.00</b></h4>
									<p><i>Seller: Nickname</i></p>
									<p><input type="checkbox" class="checked-count" name="id" value="pubid1" /> Remove</p>
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
									<h3 class="no-margin"><a href="#" class="link-as-text">Responsive Web Design</a></h3>
									<h5><i>Ethan Marcotte</i></h5>
								<td></tr>
								<tr><td valign="bottom">
									<h4><b>A$40.00</b></h4>
									<p><i>Seller: Nickname</i></p>
									<p><input type="checkbox" class="checked-count" name="id" value="pubid2" /> Remove</p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	
	<!-- Shopping Cart Buttons -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<input type="submit" id="checked-count-update" class="btn btn-danger" value="Remove from Cart (0)" disabled/>
			<a href="${contextPath}/search" class="btn btn-primary">Back to Search</a>
		</div>
	</div>
	
	</form>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>