<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Sell</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Sell Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3>Sell</h3>
		</div>
	</div>
	<!-- Error Message -->
	<jstl:if test="${error}">
		<div class="row">
			<div class="col-md-10 col-md-offset-1 col-lg-space">
				<div class="alert alert-danger">
					${error_msg}
				</div>
			</div>
		</div>
	</jstl:if>
	<!-- Sell: Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- Sell Form -->
					<form action="${contextPath}/user/sell" method="post" enctype="multipart/form-data">
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Publication Title</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Publication Title" name="title" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Authors</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Author 1, Author 2, Author 3 (Optional)" name="authors" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Editors</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Editor 1, Editor 2, Editor 3 (Optional)" name="editors" />
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
										Publication Type <span class="caret"></span>
									</button>
									<ul id="dropdown-publication-type" class="dropdown-menu disable-input-on-publication-type-select">
										<li><a href="#">Publication Type</a></li>
										<li><a href="#">Article</a></li>
										<li><a href="#">Conference</a></li>
										<li><a href="#">Book</a></li>
										<li><a href="#">Journal</a></li>
										<li><a href="#">Master Thesis</a></li>
										<li><a href="#">Ph.D.Thesis</a></li>
									</ul>
								</div>
								<input type="hidden" id="dropdown-publication-type" class="disable-input-on-publication-type-select" name="pubtype" value="Publication Type" disabled/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Year Published</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Year Published" name="year" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Venue</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Venue" name="venue" id="enable-on-conference-selected" disabled/>
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Image</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="file" accept="image/jpg, image/jpeg" class="form-control" name="pic" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-2 col-lg-space">
								<p>Price</p>
							</div>
							<div class="col-md-10 col-lg-space">
								<input type="text" class="form-control" placeholder="Price" name="price" />
							</div>
						</div>
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<input type="submit" class="btn btn-primary" value="Post" />
							</div>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>