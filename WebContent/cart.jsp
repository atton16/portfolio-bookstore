<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jstl:choose>
	<jstl:when test="${items.isEmpty()}">
		<jsp:include page="cart.noitem.jsp"></jsp:include>
	</jstl:when>
	<jstl:otherwise>
		<jsp:include page="cart.haveitems.jsp"></jsp:include>
	</jstl:otherwise>
</jstl:choose>