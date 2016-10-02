<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="app" class="com.sixppl.main.Application"/>
<jstl:set var="contextPath" value="${app.getSharedInstance().getContextPath()}"/>
<jstl:set var="title" value="${app.getSharedInstance().getTitle()}"/>
<!-- Hidden Forms -->

<!-- Main Contents -->
<!-- Header Containder: Fill width -->
<div class="container-fluid header-container">
	<!--  Header Sub Container: Set max width-->
	<div class="container">
		<!-- Main Row -->
		<div class="row">
			<!-- Title Column -->
			<div class="col-md-1">
				<div class="row">
					<h1><a href="${contextPath}/" class="header-title no-decoration">${title}</a></h1>
				</div>
			</div>
			
			<!-- Search Bar Column -->
			<div class="col-md-6">
				<!-- Top Space -->
				<div class="margin-top-24"></div>
				<div class="row padding-left-16">
					<!-- Search Bar -->
					<form action="${contextPath}/results" method="get">
						<div class="input-group">
							<!-- Search Icon -->
							<span class="input-group-addon"> <span class="glyphicon glyphicon-search"></span></span>
							<!-- Search Input -->
							<input type="text" class="form-control" placeholder="Search..." name="keyword" />
							<!-- Search Type Dropdown -->
							<div class="input-group-btn">
								<!-- Button and dropdown menu -->
								<div class="btn-group">
									<button id="dropdown-search-type" type="button"
										class="btn btn-default dropdown-toggle" data-toggle="dropdown">
										Title <span class="caret"></span>
									</button>
									<ul id="dropdown-search-type" class="dropdown-menu">
										<li><a href="#">Title</a></li>
										<li><a href="#">Author / Editor</a></li>
										<li><a href="#">Publication Type</a></li>
										<li><a href="#">Year</a></li>
										<li><a href="#">Venue</a></li>
									</ul>
								</div>
								<input type="hidden" id="dropdown-search-type" name="type" value="Title"/>
								<input type="submit" class="btn btn-danger" value="Search" />
							</div>
						</div>
					</form>
				</div>
			</div>
			
			<!-- Utilities Column -->
			<div class="col-md-5">
				<!-- Top Space -->
				<div class="margin-top-30"></div>
				<div class="row pull-right">
					<!-- Graph button -->
					<a href="${contextPath}/graph" class="white-color" id="graph">
						<span class="glyphicon glyphicon-signal"></span> Graph
					</a>
					<span class="white-color margin-left-8">|</span>
					<!-- Shopping cart button -->
					<a href="${contextPath}/cart" class="margin-left-8 white-color" id="shopping-cart">
						<span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart
 						<jstl:if test="${sessionScope.cart != null}">
							<jstl:if test="${sessionScope.cart.size() > 0}"> (${sessionScope.cart.size()})</jstl:if>
						</jstl:if>
					</a>
					<span class="white-color margin-left-8">|</span>
					<!-- Sell button -->
					<a href="${contextPath}/user/sell" class="margin-left-8 white-color" id="sell">
						<span class="glyphicon glyphicon-bullhorn"></span> Sell
					</a>
					<!-- Login link -->
					<jstl:if test="${sessionScope.user == null}">
						<span class="white-color margin-left-8">|</span>
						<a href="${contextPath}/login" class="margin-left-8 white-color" id="login">Login</a>
					</jstl:if>
					<!-- User action dropdown -->
					<jstl:if test="${sessionScope.user != null}">
						<span class="white-color margin-left-8">|</span>
						<div class="btn-group">
							<a href="#" class="margin-left-8 white-color dropdown-toggle white-color" data-toggle="dropdown" id="login">${sessionScope.user.getNickname()} <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<jstl:if test="${sessionScope.user.isAdmin()}">
									<li><a href="${contextPath}/admin/login">Admin Login</a></li>
								</jstl:if>
								<li><a href="${contextPath}/user/profile">Edit Profile</a></li>
								<li><a href="${contextPath}/user/pub/manage">Manage Publications</a></li>
								<li><a href="${contextPath}/logout">Logout</a></li>
							</ul>
						</div>
					</jstl:if>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-md-9 col-md-offset-1 col-lg-space">
				<div class="row padding-left-16">
					<a href="${contextPath}/search" class="white-color">Advanced Search</a>
				</div>
			</div>
		</div>
	</div>
</div>
