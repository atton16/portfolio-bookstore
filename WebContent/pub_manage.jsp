<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jstl:choose>
<%--   	<jstl:when test="${items == NULL}">
		<jsp:include page="pub_manage.noitem.jsp"></jsp:include>
	</jstl:when> --%>
	<jstl:when test="${items.isEmpty()}">
		<jsp:include page="pub_manage.noitem.jsp"></jsp:include>
	</jstl:when>
	<jstl:otherwise>
		<jsp:include page="pub_manage.haveitems.jsp"></jsp:include>
	</jstl:otherwise>
</jstl:choose>