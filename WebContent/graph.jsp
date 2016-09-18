<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Graph</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<center>
		<div class="margin-top-24"></div>
		<div class="panel panel-default">
			<div class="panel-body">
				<jsp:include page="graph/index.html" />
			</div>
		</div>
	</center>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>