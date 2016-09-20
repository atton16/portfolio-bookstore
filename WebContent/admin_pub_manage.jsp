<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Admin - Manage Publications</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Admin - Manage Publications Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3><a href="${contextPath}/admin" class="link-as-text">Admin</a> / Manage Publications</h3>
		</div>
	</div>
	<!-- Admin - Manage Publications Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- Search Bar -->
					<form action="${contextPath}/admin/pub/find" method="get" class="submit-ajax">
						<div class="input-group">
							<!-- Search Icon -->
							<span class="input-group-addon"> <span class="glyphicon glyphicon-search"></span></span>
							<!-- Search Input -->
							<input type="text" class="form-control" placeholder="Publication ID" name="id" />
							<div class="input-group-btn">
								<input type="submit" class="btn btn-primary" value="Lookup" />
							</div>
						</div>
					</form>
					<!-- Search Results -->
					<div class="submit-ajax-result">
						<div class="margin-top-24"></div>
						<table class="fill-width">
							<tr>
								<td align="center" class="cell-180px padding-right-12">
									<img class="square-180-by-180" id="submit-ajax-picurl">
								</td>
								<td class="border-left padding-left-12"><table>
									<tr><td valign="top">
										<input type="hidden" id="submit-ajax-context-path" value="${contextPath}"/>
										<h3 class="no-margin"><a href="${contextPath}/pubinfo?id=pubid1" class="link-as-text" id="submit-ajax-title"></a></h3>
										<h5><i id="submit-ajax-authors-and-editors"></i></h5>
									<td></tr>
									<tr><td valign="bottom">
										<h4><b id="submit-ajax-price"></b></h4>
										<h4><a href="#" id="" class="submit-hidden-ajax submit-ajax-remove">Remove</a></h4>
										<p><i id="submit-ajax-seller"></i></p>
										<p><i id="submit-ajax-listed"></i></p>
									<td></tr>
								</table></td>
							</tr>
						</table>
					</div>
					<!-- Remove AJAX Form -->
					<form action="${contextPath}/admin/pub/remove" method="post" id="" class="submit-ajax-remove">
						<input type="hidden" name="id" value="pubid1"/>
					</form>
					<!-- Empty Result -->
					<div class="submit-ajax-empty-result">
						<div class="margin-top-24"></div>
						<h3>No publication found.</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>