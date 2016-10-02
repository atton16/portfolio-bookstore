<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
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
	<!-- Admin - Manage Publications Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3><a href="${contextPath}/admin">Admin</a> / Manage Publications</h3>
		</div>
	</div>
	<!-- Advanced Search Form -->
	<form action="${contextPath}/admin/pub/manage" method="get">
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<div class="row">
						<div class="col-md-2 col-lg-space">
							<p>Title</p>
						</div>
						<div class="col-md-10 col-lg-space">
							<input type="text" class="form-control"
								placeholder="e.g. Winnie the Pooh" name="title" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 col-lg-space">
							<p>Author / Editor</p>
						</div>
						<div class="col-md-10 col-lg-space">
							<input type="text" class="form-control"
								placeholder="e.g. Author 1, Author 2, Editor 1" name="authors-and-editors" />
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 col-lg-space">
							<p>Publication Type</p>
						</div>
						<div class="col-md-10 col-lg-space">
							<!-- Publication Type Selector -->
							<div class="btn-group">
								<button id="dropdown-publication-type" type="button"
									class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									Any <span class="caret"></span>
								</button>
								<ul id="dropdown-publication-type" class="dropdown-menu">
									<li><a href="#">Any</a></li>
									<li><a href="#">Article</a></li>
									<li><a href="#">Conference</a></li>
									<li><a href="#">Book</a></li>
									<li><a href="#">Journal</a></li>
									<li><a href="#">Master Thesis</a></li>
									<li><a href="#">Ph.D.Thesis</a></li>
								</ul>
							</div>
							<input type="hidden" id="dropdown-publication-type" name="pubtype" value="Any"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 col-lg-space">
							<p>Year</p>
						</div>
						<div class="col-md-10 col-lg-space">
							<!-- Year Selector: From -->
							From 
							<div class="btn-group">
								<button id="dropdown-year-from" type="button"
									class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									Dawn of the Ape <span class="caret"></span>
								</button>
								<ul id="dropdown-year-from" class="dropdown-menu">
									<li><a href="#">Dawn of the Ape</a></li>
									<jstl:forEach var="year" items="${years_asc}">
										<li><a href="#">${year}</a></li>
									</jstl:forEach>
								</ul>
							</div>
							<input type="hidden" id="dropdown-year-from" name="year-from" value="Dawn of the Ape"/>
							To 
							<!-- Year Selector: To -->
							<div class="btn-group">
								<button id="dropdown-year-to" type="button"
									class="btn btn-default dropdown-toggle" data-toggle="dropdown">
									End of Humanity <span class="caret"></span>
								</button>
								<ul id="dropdown-year-to" class="dropdown-menu">
									<li><a href="#">End of Humanity</a></li>
									<jstl:forEach var="year" items="${years_desc}">
										<li><a href="#">${year}</a></li>
									</jstl:forEach>
								</ul>
							</div>
							<input type="hidden" id="dropdown-year-to" name="year-to" value="End of Humanity"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2 col-lg-space">
							<p>Conference Venue</p>
						</div>
						<div class="col-md-10 col-lg-space">
							<input type="text" class="form-control"
								placeholder="e.g. BPM" name="venue" id="enable-on-conference-selected"/>
						</div>
					</div>
					<div class="row">
						<div class="col-md-2">
							<input type="submit" class="btn btn-primary" value="Search" />
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</form>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>