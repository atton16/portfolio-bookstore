<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<style type="text/css">
/* Always display node caption and edge */
.alchemy text {
	display: block !important;
}
</style>
<title>${title}: Graph</title>
</head>
<body>
<jsp:include page="inc.body.header.jsp" />
<div class="container">
	<!-- Graph Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<center><h3>Graph</h3></center>
		</div>
	</div>
	<!-- Graph Content -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- Search Bar -->
					<form action="${contextPath}/graph" method="get">
						<div class="input-group">
							<!-- Search Icon -->
							<span class="input-group-addon"> <span class="glyphicon glyphicon-search"></span></span>
							<!-- Search Input -->
							<input type="text" class="form-control" placeholder="Graph Search" name="keyword" />
							<!-- Search Type Dropdown -->
							<div class="input-group-btn">
								<!-- Button and dropdown menu -->
								<div class="btn-group">
									<button id="dropdown-search-type-graph" type="button"
										class="btn btn-default dropdown-toggle" data-toggle="dropdown">
										Author <span class="caret"></span>
									</button>
									<ul id="dropdown-search-type-graph" class="dropdown-menu">
										<li><a href="#">Author</a></li>
										<li><a href="#">Publication</a></li>
										<li><a href="#">Venue</a></li>
									</ul>
								</div>
								<input type="hidden" id="dropdown-search-type-graph" name="type" value="Author"/>
								<input type="submit" class="btn btn-primary" value="Search" />
							</div>
						</div>
					</form>
					
					<!-- Search Term -->
					<div class="margin-top-12"></div>
					<div>
						<jstl:forEach var="searchTerm" items="${searchTerms}">
							<span class="label label-default">${searchTerm}</span>
						</jstl:forEach>
					</div>
					
					<!-- Data Contontainer -->
					<input id="data-src" type="hidden" value="${fn:escapeXml(data)}"/>
					
					<!-- Graph Display -->
					<div class="margin-top-12"></div>
					<center><div class="alchemy" id="alchemy"></div></center>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
<script type="text/javascript">
	var data = JSON.parse($('input#data-src').val());
    // Configure Alchemy
    var config = {
        dataSource: data,
        graphHeight: function() {
            return 600
        },
        graphWidth: function() {
            return 914
        },
        linkDistancefn: function() {
            return 40
        },
        nodeTypes: {
            'label': ['Venue','Publication','Person','School', 'Author']
        },
        nodeCaption: function(node){
               return node.caption
        },
        "nodeStyle": {
            "Publication": {
                color: "#FFD86E",
                borderColor: "#EDBA39",
                //text-color-internal: "#604A0E",
                caption: '{Title}',
                radius: 32
            },
            "Person":{
                color: "#6DCE9E",
                borderColor: "#60B58B",
                //text-color-internal: "#FFFFFF",
                caption: '{Name}',
                radius: 25
            },
            "Venue": {
                color: "#68BDF6",
                borderColor: "#5CA8DB",
                //text-color-internal: "#FFFFFF",
                caption: '{Journal}',
                radius: 40
            },
            "School": {
                color: "#FF756E",
                borderColor: "#E06760",
                //text-color-internal: "#FFFFFF",
                caption: '{Name}',
                radius: 40
            },
        },
        "edgeStyle": {
            "affiliated in": {
                color: "#6DCE9E",
                borderColor: "#60B58B",
                //text-color-internal: "#FFFFFF",
                //width: 5
            },
            "published in": {
                color: "#68BDF6",
                borderColor: "#5CA8DB",
                //text-color-internal: "#FFFFFF",
                //width: 5
            },
            "authored by": {
                color: "#A5ABB6",
                borderColor: "#9AA1AC",
                //text-color-internal: "#FFFFFF",
                //width: 1
            }
        }
    };
    // Render it
    alchemy.begin(config)
</script>
</body>
</html>