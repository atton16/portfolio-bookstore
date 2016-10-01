<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Admin - Analytics</title>
</head>
<body>
<jsp:include page="inc.body.admin_header.jsp" />
<div class="container">
	<!-- Admin - Analytics Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3><a href="${contextPath}/admin">Admin</a> / Analytics</h3>
		</div>
	</div>
	<!-- Admin - Analytics Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<!-- Page Hits -->
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Page Hits</h3>
				</div>
				<div class="panel-body">
					<jstl:forEach var="page_hit" items="${page_hits}">
					<div class="as-table">
						<div class="as-cell as-cell-17"><b>${page_hit.getTitle()}</b></div>
						<div class="as-cell as-cell-83">${page_hit.getHitCount()}</div>
					</div>
					</jstl:forEach>
				</div>
			</div>
			
			<!-- Users -->
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">Users</h3>
				</div>
				<div class="panel-body">
					<div class="as-table">
						<div class="as-cell as-cell-17"><b>Unique IPs</b></div>
						<div class="as-cell as-cell-83">${unique_ips}</div>
					</div>
					<div class="as-table">
						<div class="as-cell as-cell-17"><b>Registered Users</b></div>
						<div class="as-cell as-cell-83">${users}</div>
					</div>
					<div class="as-table">
						<div class="as-cell as-cell-17"><b>Logged In Sessions</b></div>
						<div class="as-cell as-cell-83">${logins}</div>
					</div>
				</div>
			</div>
			
			<!-- 10 Most Viewed -->
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">10 Most Viewed</h3>
				</div>
				<div class="panel-body">
<%-- 					<jstl:set var="entrylist" value="${app.getSharedInstance().getMostItemViewCounts(10) }" scope="page" />
					<jstl:forEach var="entry" items="${entrylist}">
						<jstl:set var="simpleInfo" value="${app.getSharedInstance().getPublicationInfoByKey(entry.getKey())}" scope="page" /> --%>
						<div class="as-table">
							<div class="as-cell as-cell-5"><b>1</b></div>
							<div class="as-cell as-cell-95"><a href="${contextPath}/pubinfo?id=pubid1" target="_blank">1984</a></div>
						</div>
					<%-- </jstl:forEach> --%>
				</div>
			</div>
			
			<!-- 10 Most Added To Cart -->
			<div class="panel panel-primary">
				<div class="panel-heading">
					<h3 class="panel-title">10 Most Added To Cart</h3>
				</div>
				<div class="panel-body">
					<%-- <jstl:set var="entrylist" value="${app.getSharedInstance().getMostItemAddToCartCounts(10) }" scope="page" /> --%>
					<%-- <jstl:forEach var="entry" items="${entrylist}"> --%>
						<%-- <jstl:set var="simpleInfo" value="${app.getSharedInstance().getPublicationInfoByKey(entry.getKey())}" scope="page" /> --%>
						<div class="as-table">
							<div class="as-cell as-cell-5"><b>2</b></div>
							<div class="as-cell as-cell-95"><a href="${contextPath}/pubinfo?id=pubid2" target="_blank">Responsive Web Design</a></div>
						</div>
					<%-- </jstl:forEach> --%>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>