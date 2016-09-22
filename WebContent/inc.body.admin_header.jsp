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
		</div>
	</div>
</div>
