<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jstl:choose>
  	<jstl:when test="${error == true}">
		<jsp:include page="admin_customer.notfound.jsp"></jsp:include>
	</jstl:when>
	<jstl:otherwise>
		<jsp:include page="admin_customer.found.jsp"></jsp:include>
	</jstl:otherwise>
</jstl:choose>