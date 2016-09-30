<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="app" class="com.sixppl.main.Application"/>
<jstl:set var="contextPath" value="${app.getSharedInstance().getContextPath()}"/>
<jstl:set var="title" value="${app.getSharedInstance().getTitle()}"/>
<jstl:set var="total_pub" value="${app.getSharedInstance().getListingCount()}"/>
<jstl:set var="items" value="${app.getSharedInstance().getRandomPubs()}"/>
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
			<p><i>From ${total_pub} publications</i></p>
		</div>
	</div>
	<!-- Random Pick Contents: One item per row -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
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
										<h3 class="no-margin"><a href="${contextPath}/pubinfo?id=${item.getPubID()}" class="link-as-text">${item.getTitle()} (${item.getYear()})</a></h3>
										<h5><i>
										<jstl:forEach var="writer" items="${item.getWriters()}" varStatus="stat">
											${writer}${stat.last ? '' : ', '}
										</jstl:forEach>
										</i></h5>
									<td></tr>
									<tr><td valign="bottom">
										<h4><b>A$${item.getPrice()}.00</b></h4>
										<h4><a href="#" id="${item.getPubID()}" class="submit-hidden-ajax">Add to Cart</a></h4>
										<p><i>Seller: ${item.getSellerNickname()}</i></p>
										<p><i>Listed: ${item.getTimestampString()}</i></p>
									<td></tr>
								</table></td>
							</tr>
						</table>
					</div>
				</div>
				<!-- Add to Cart AJAX Form: Item # -->
				<form action="${contextPath}/rest/cart/add" method="post" id="${item.getPubID()}">
					<input type="hidden" name="id" value="${item.getPubID()}"/>
				</form>
			</jstl:forEach>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>