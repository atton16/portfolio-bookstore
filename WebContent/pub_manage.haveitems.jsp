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
			<p><i>You have ${items.size()} publications listing in total.</i></p>
		</div>
	</div>
	<!-- Manage Publications Contents: One item per row -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<jstl:forEach var="item" items="${items}">
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
									<h4><b>A$${item.getPrice()}.00</b></h4>
									<p><i>Sold: ${item.getSoldCount()}</i></p>
									<p><i>Listed: ${item.getTimestampString()}</i></p>
									<p><input type="checkbox" class="submit-hidden-ajax" name="id" value="${item.getPubID()}" id="${item.getPubID()}" /> Pause</p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
			<!-- Pause AJAX Form: Item # -->
			<form action="${contextPath}/rest/user/pub/list" method="post" id="${item.getPubID()}-unchecked"></form>
			<form action="${contextPath}/rest/user/pub/unlist" method="post" id="${item.getPubID()}-checked"></form>
			</jstl:forEach>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>