<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jstl:choose>
	<jstl:when test="${error == false && email != NULL}">
		<jsp:include page="profile_confirm.valid.jsp"></jsp:include>
	</jstl:when>
	<jstl:otherwise>
		<jsp:include page="profile_confirm.invalid.jsp"></jsp:include>
	</jstl:otherwise>
</jstl:choose>