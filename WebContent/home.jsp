<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="app" class="com.sixppl.main.Application"/>
<jstl:set var="contextPath" value="${app.getSharedInstance().getContextPath()}"/>
<jstl:set var="title" value="${app.getSharedInstance().getTitle()}"/>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Random Pick Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Random Picks</h3>
			<p><i>From XXX publications</i></p>
		</div>
	</div>
	<!-- Random Pick Contents: One item per row -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
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
									<h4><a href="#">Add to Cart</a></h4>
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
									<h4><a href="#">Add to Cart</a></h4>
									<p><i>Seller: Nickname</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>