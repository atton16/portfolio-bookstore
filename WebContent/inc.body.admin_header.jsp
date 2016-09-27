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
			
			<div class="col-md-6">
			</div>
			
			<!-- Utilities Column -->
			<div class="col-md-5">
				<!-- Top Space -->
				<div class="margin-top-30"></div>
				<div class="row pull-right">
					<!-- Admin action dropdown -->
					<jstl:if test="${sessionScope.admin != null}">
						<div class="btn-group">
							<a href="#" class="margin-left-8 white-color dropdown-toggle white-color" data-toggle="dropdown" id="login">${sessionScope.admin.getNickname()} <span class="caret"></span></a>
							<ul class="dropdown-menu">
								<li><a href="${contextPath}/admin/logout">Logout</a></li>
							</ul>
						</div>
					</jstl:if>
				</div>
			</div>
		</div>
	</div>
</div>
