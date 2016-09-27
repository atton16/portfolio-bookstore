<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Admin - Manage Users</title>
</head>
<body>
<jsp:include page="inc.body.admin_header.jsp" />
<div class="container">
	<!-- Admin - Manage Users Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3><a href="${contextPath}/admin">Admin</a> / Manage Users</h3>
		</div>
	</div>
	<!-- Admin - Manage Users Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<!-- Search Bar -->
			<form action="${contextPath}/admin/users/manage" method="get">
				<div class="input-group">
					<!-- Search Icon -->
					<span class="input-group-addon"> <span class="glyphicon glyphicon-search"></span></span>
					<!-- Search Input -->
					<input type="text" class="form-control" placeholder="Search" name="keyword" id="disable-on-all-customers-or-sellers-selected" />
					<!-- Search Type Dropdown -->
					<div class="input-group-btn">
						<!-- Button and dropdown menu -->
						<div class="btn-group">
							<button id="dropdown-search-type-users" type="button"
								class="btn btn-default dropdown-toggle" data-toggle="dropdown">
								Nick Name <span class="caret"></span>
							</button>
							<ul id="dropdown-search-type-users" class="dropdown-menu">
								<li><a href="#">Nick Name</a></li>
								<li><a href="#">First Name</a></li>
								<li><a href="#">Last Name</a></li>
								<li><a href="#">Email</a></li>
								<li><a href="#">All Customers</a></li>
								<li><a href="#">All Sellers</a></li>
							</ul>
						</div>
						<input type="hidden" id="dropdown-search-type-users" name="type" value="Nick Name"/>
						<input type="submit" class="btn btn-primary" value="Search" />
					</div>
				</div>
			</form>
			<!-- Search Results -->
			<div>
				<div class="margin-top-24"></div>
				<!-- No Search -->
				<jstl:if test="${items == null}">
					<center><h4>Please search to begin.</h4></center>
				</jstl:if>
				<jstl:if test="${items != null}">
					<jstl:if test="${items.isEmpty()}">
						<!-- No Results -->
						<center><h4>User not found.</h4></center>
					</jstl:if>
					<jstl:if test="${!items.isEmpty()}">
						<!-- Results -->
						<!-- Search Results Title -->
						<div class="row">
							<div class="col-md-12 col-lg-space">
								<h3>Search Results</h3>
								<p><i>Displaying ${start}-${end} of ${total} results</i></p>
								<jstl:forEach var="searchTerm" items="${searchTerms}">
									<span class="label label-default">${searchTerm}</span>
								</jstl:forEach>
							</div>
						</div>
						<!-- Page Changers: Top -->
						<div class="row">
							<div class="col-md-12">
								<div class="as-table">
									<div class="as-cell">
										<jstl:if test="${prevParams != null}">
										<a href="${contextPath}/admin/users/manage?${prevParams}" class="no-decoration"> <span
											class="glyphicon glyphicon-menu-left"></span> <span>
												Previous</span>
										</a>
										</jstl:if>
									</div>
									<div class="as-cell right-text">
										<jstl:if test="${nextParams != null}">
										<a href="${contextPath}/admin/users/manage?${nextParams}" class="no-decoration"> <span>Next </span> <span
											class="glyphicon glyphicon-menu-right"></span>
										</a>
										</jstl:if>
									</div>
								</div>
							</div>
						</div>
						<!-- Search Results Contents: One item per row -->
						<div class="row">
							<div class="col-md-12">
								<div class="panel panel-default">
									<div class="panel-body">
										<table class="table">
											<tr>
												<th>Nick Name</th>
												<th>First Name</th>
												<th>Last Name</th>
												<th>Email</th>
												<th>Action</th>
												<th>Activity (Customer)</th>
											</tr>
											<jstl:forEach var="user" items="${items}">
												<tr>
													<td>${user.getNickname()}</td>
													<td>${user.getFirstname()}</td>
													<td>${user.getLastname()}</td>
													<td>${user.getEmail()}</td>
													<td>
														<jstl:if test="${user.isAdmin()}">
															-
														</jstl:if>
														<jstl:if test="${!user.isAdmin()}">
															<jstl:if test="${user.isBanned()}">
																<a href="#" id="${user.getUserID()}-unban" class="submit-hidden-ajax ban-unban-toggle">Unban</a>
															</jstl:if>
															<jstl:if test="${!user.isBanned()}">
																<a href="#" id="${user.getUserID()}-ban" class="submit-hidden-ajax ban-unban-toggle">Ban</a>
															</jstl:if>
															<!-- Ban/Unban AJAX Form: Item # -->
															<form action="${contextPath}/rest/admin/users/ban" method="post" id="${user.getUserID()}-ban">
																<input type="hidden" name="id" value="${user.getUserID()}"/>
															</form>
															<form action="${contextPath}/rest/admin/users/unban" method="post" id="${user.getUserID()}-unban">
																<input type="hidden" name="id" value="${user.getUserID()}"/>
															</form>
														</jstl:if>
													</td>
													<td>
														<jstl:if test="${user.isCustomer()}">
															<a href="${contextPath}/admin/users/viewcustomer?id=${user.getUserID()}" target="_blank">View</a>
														</jstl:if>
														<jstl:if test="${!user.isCustomer()}">
															-
														</jstl:if>
													</td>
												</tr>
											</jstl:forEach>
										</table>
									</div>
								</div>
							</div>
						</div>
						<!-- Page Changers: Bottom -->
						<div class="row">
							<div class="col-md-12">
								<div class="as-table">
									<div class="as-cell">
										<jstl:if test="${prevParams != null}">
										<a href="${contextPath}/admin/users/manage?${prevParams}" class="no-decoration"> <span
											class="glyphicon glyphicon-menu-left"></span> <span>
												Previous</span>
										</a>
										</jstl:if>
									</div>
									<div class="as-cell right-text">
										<jstl:if test="${nextParams != null}">
										<a href="${contextPath}/admin/users/manage?${nextParams}" class="no-decoration"> <span>Next </span> <span
											class="glyphicon glyphicon-menu-right"></span>
										</a>
										</jstl:if>
									</div>
								</div>
							</div>
						</div>
					</jstl:if>
				</jstl:if>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>