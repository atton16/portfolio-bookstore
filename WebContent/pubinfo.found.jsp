<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Publication Details</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Publication Details Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Publication Details</h3>
		</div>
	</div>
	<!-- Publication Details: Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="panel panel-default">
				<div class="panel-body">
					<table class="fill-width">
						<tr>
							<td align="center" class="cell-180px padding-right-12">
								<img src="${contextPath}/uploads/1984.jpeg" class="square-180-by-180">
							</td>
							<td class="border-left padding-left-12"><table>
								<tr><td valign="top">
									<h3 class="no-margin">1984</h3>
									<h5>Authors: George Orwell</h5>
									<h5>Editors: Dumbledoor</h5>
									<h5>Type: Book</h5>
									<h5>Venue: Platform 9 3/4</h5>
								<td></tr>
								<tr><td valign="bottom">
									<h4><b>A$40.00</b></h4>
									<h4><a href="#" id="pubid1" class="submit-hidden-ajax">Add to Cart</a></h4>
									<p><i>Seller: Nickname</i></p>
									<p><i>Listed: DD/MM/YY</i></p>
									<p><i>ID: 1</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- Add to Cart AJAX Form: Item 1 -->
	<form action="${contextPath}/rest/cart/add" method="post" id="pubid1">
		<input type="hidden" name="id" value="pubid1"/>
	</form>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>