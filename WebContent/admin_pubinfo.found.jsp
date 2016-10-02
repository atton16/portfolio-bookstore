<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Admin - Publication Details</title>
</head>
<body>
<jsp:include page="inc.body.admin_header.jsp" />
<div class="container">
	<!-- Publication Details Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3><a href="${contextPath}/admin">Admin</a> / Publication Details</h3>
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
								<img src="${contextPath}${item.getPicture()}" class="square-180-by-180">
							</td>
							<td class="border-left padding-left-12"><table>
								<tr><td valign="top">
									<h3 class="no-margin">${item.getTitle()}</h3>
									<h5>Year Published: ${item.getYear()}</h5>
									<jstl:if test="${!item.getAuthors().isEmpty()}">
									<h5>Authors:
										<jstl:forEach var="author" items="${item.getAuthors()}" varStatus="stat">
											${author}${stat.last ? '' : ', '}
										</jstl:forEach>
									</h5>
									</jstl:if>
									<jstl:if test="${!item.getEditors().isEmpty()}">
									<h5>Editors: 
										<jstl:forEach var="editor" items="${item.getEditors()}" varStatus="stat">
											${editor}${stat.last ? '' : ', '}
										</jstl:forEach>
									</h5>
									</jstl:if>
									<h5>Type: ${item.getType()}</h5>
									<jstl:if test="${item.getVenue().length() > 0}">
									<h5>Venue: ${item.getVenue()}</h5>
									</jstl:if>
								<td></tr>
								<tr><td valign="bottom">
									<h4><b>A$<fmt:formatNumber type="number" minFractionDigits="2" maxFractionDigits="2" value="${item.getPrice()}" /></b></h4>
									<h4><a href="#" id="${item.getPubID()}" class="submit-hidden-ajax submit-ajax-remove">Remove</a></h4>
									<p><i>Seller: ${item.getSellerNickname()}</i></p>
									<p><i>Listed: ${item.getTimestampString()}</i></p>
								<td></tr>
							</table></td>
						</tr>
					</table>
				</div>
			</div>
		</div>
	</div>
	<!-- Remove AJAX Form -->
	<form action="${contextPath}/admin/pub/remove" method="post" id="${item.getPubID()}" class="submit-ajax-remove">
		<input type="hidden" name="id" value="${item.getPubID()}"/>
	</form>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>