<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Search Results</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Search Results Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Search Results</h3>
			<p><i>Displaying X-Y of N results</i></p>
			<jstl:forEach var="searchTerm" items="${searchTerms}">
				<span class="label label-default">${searchTerm}</span>
			</jstl:forEach>
		</div>
	</div>
	<!-- Page Changers: Top -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="as-table">
				<div class="as-cell">
					<%-- <jstl:if test="${prevParams != null}"> --%>
					<a href="${contextPath}/results?${prevParams}" class="no-decoration"> <span
						class="glyphicon glyphicon-menu-left"></span> <span>
							Previous</span>
					</a>
					<%-- </jstl:if> --%>
				</div>
				<div class="as-cell right-text">
					<%-- <jstl:if test="${nextParams != null}"> --%>
					<a href="${contextPath}/results?${nextParams}" class="no-decoration"> <span>Next </span> <span
						class="glyphicon glyphicon-menu-right"></span>
					</a>
					<%-- </jstl:if> --%>
				</div>
			</div>
		</div>
	</div>
	<!-- Search Results Contents: One item per row -->
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
									<h4><a href="#" id="pubid1" class="submit-hidden-ajax">Add to Cart</a></h4>
									<p><i>Seller: Nickname</i></p>
									<p><i>Listed: DD/MM/YY</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
			<!-- Add to Cart AJAX Form: Item 1 -->
			<form action="${contextPath}/rest/cart/add" method="post" id="pubid1">
				<input type="hidden" name="id" value="pubid1"/>
			</form>
			
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
									<h4><a href="#" id="pubid2" class="submit-hidden-ajax">Add to Cart</a></h4>
									<p><i>Seller: Nickname</i></p>
									<p><i>Listed: DD/MM/YY</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
			<!-- Add to Cart AJAX Form: Item 2 -->
			<form action="${contextPath}/rest/cart/add" method="post" id="pubid2">
				<input type="hidden" name="id" value="pubid2"/>
			</form>
		</div>
	</div>
	<!-- Page Changers: Bottom -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="as-table">
				<div class="as-cell">
					<%-- <jstl:if test="${prevParams != null}"> --%>
					<a href="${contextPath}/results?${prevParams}" class="no-decoration"> <span
						class="glyphicon glyphicon-menu-left"></span> <span>
							Previous</span>
					</a>
					<%-- </jstl:if> --%>
				</div>
				<div class="as-cell right-text">
					<%-- <jstl:if test="${nextParams != null}"> --%>
					<a href="${contextPath}/results?${nextParams}" class="no-decoration"> <span>Next </span> <span
						class="glyphicon glyphicon-menu-right"></span>
					</a>
					<%-- </jstl:if> --%>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>