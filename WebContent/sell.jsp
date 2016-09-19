<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jstl:choose>
	<jstl:when test="${error == false && error_msg != NULL}">
		<jsp:include page="sell.success.jsp"></jsp:include>
	</jstl:when>
	<jstl:otherwise>
		<jsp:include page="sell.form.jsp"></jsp:include>
	</jstl:otherwise>
</jstl:choose>