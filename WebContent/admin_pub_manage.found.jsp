<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Admin - Manage Publications</title>
</head>
<body>
<jsp:include page="inc.body.admin_header.jsp" />
<div class="container">
	<!-- Admin - Manage Publications Results Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3><a href="${contextPath}/admin">Admin</a> / <a href="${contextPath}/admin/pub/manage">Manage Publications</a> / Results</h3>
			<p><i>Displaying ${start}-${end} of ${total} results</i></p>
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
					<jstl:if test="${prevParams != null}">
					<a href="${contextPath}/admin/pub/manage?${prevParams}" class="no-decoration"> <span
						class="glyphicon glyphicon-menu-left"></span> <span>
							Previous</span>
					</a>
					</jstl:if>
				</div>
				<div class="as-cell right-text">
					<jstl:if test="${nextParams != null}">
					<a href="${contextPath}/admin/pub/manage?${nextParams}" class="no-decoration"> <span>Next </span> <span
						class="glyphicon glyphicon-menu-right"></span>
					</a>
					</jstl:if>
				</div>
			</div>
		</div>
	</div>
	<!-- Search Results Contents: One item per row -->
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
										<h3 class="no-margin"><a href="${contextPath}/admin/pubinfo?id=${item.getPubID()}" class="link-as-text">${item.getTitle()}</a></h3>
										<h5><i>
										<jstl:forEach var="writer" items="${item.getWriters()}" varStatus="stat">
											${writer}${stat.last ? '' : ', '}
										</jstl:forEach>
										</i></h5>
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
				<!-- Remove AJAX Form -->
				<form action="${contextPath}/admin/pub/remove" method="post" id="${item.getPubID()}" class="submit-ajax-remove">
					<input type="hidden" name="id" value="${item.getPubID()}"/>
				</form>
			</jstl:forEach>
		</div>
	</div>
	<!-- Page Changers: Bottom -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1">
			<div class="as-table">
				<div class="as-cell">
					<jstl:if test="${prevParams != null}">
					<a href="${contextPath}/admin/pub/manage?${prevParams}" class="no-decoration"> <span
						class="glyphicon glyphicon-menu-left"></span> <span>
							Previous</span>
					</a>
					</jstl:if>
				</div>
				<div class="as-cell right-text">
					<jstl:if test="${nextParams != null}">
					<a href="${contextPath}/admin/pub/manage?${nextParams}" class="no-decoration"> <span>Next </span> <span
						class="glyphicon glyphicon-menu-right"></span>
					</a>
					</jstl:if>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>