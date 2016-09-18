<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Manage Publications</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Manage Publications Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Manage Publications</h3>
			<p><i>You have X publications listing in total.</i></p>
		</div>
	</div>
	<!-- Manage Publications Contents: One item per row -->
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
									<p><i>Sold: 99</i></p>
									<p><i>Listed: DD/MM/YY</i></p>
									<p><input type="checkbox" class="submit-hidden-ajax" name="id" value="pubid1" id="pubid1" /> Pause</p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
			<!-- Pause AJAX Form: Item 1 -->
			<form action="${contextPath}/rest/user/pub/list" method="post" id="pubid1-unchecked"></form>
			<form action="${contextPath}/rest/user/pub/unlist" method="post" id="pubid1-checked"></form>
			
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
									<p><i>Sold: 102</i></p>
									<p><i>Listed: DD/MM/YY</i></p>
									<p><input type="checkbox" class="submit-hidden-ajax" name="id" value="pubid2" id="pubid2"/> Pause</p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
			<!-- Pause AJAX Form: Item 2 -->
			<form action="${contextPath}/rest/user/pub/list" method="post" id="pubid2-unchecked"></form>
			<form action="${contextPath}/rest/user/pub/unlist" method="post" id="pubid2-checked"></form>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>