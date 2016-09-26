<%@ taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<jsp:include page="inc.head.jsp" />
<title>${title}: Admin - Manage Publications</title>

<script>
	function idSearch(){
			var PubID=$("#id").val();
			alert(PubID);
			
		$.ajax({
	        url : "${contextPath}/admin/pub/find",
	        async: false,
	        data: {cmd:"AdminGetPub", id:PubID},
	       // method: "GET",dataType: 'json'
	        success : function(result) {
	        	//alert(result);
	        	var d=$.parseJSON(result);
	        	/*for(var i=0; i<d.length;i++){
	        		alert(d[i].Title);
	        	}*/
	        	$("#Results").html('<table class="fill-width">')
	        	  for(var i =0;i<d.length;i++){
	        			
	        			 $("#Results").append('<tr><td>Title</td><td>'+d[i].Title+'</td></tr>');
	        	 
	        	  }
	        	
	        	  $("#Results").append('</table>');
	        	  $("#Results").show(500);
	      	  return true;
	        }  ,
	        error:function(result) {
	        	alert("failed ajax call");
	        }
	        });	
	};
	
	
</script>

</head>
<body>
<jsp:include page="inc.body.admin_header.jsp" />
<div class="container">
	<!-- Admin - Manage Publications Title -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<h3><a href="${contextPath}/admin">Admin</a> / Manage Publications</h3>
		</div>
	</div>
	<!-- Admin - Manage Publications Contents -->
	<div class="row">
		<div class="col-md-10 col-md-offset-1 col-lg-space">
			<div class="panel panel-default">
				<div class="panel-body">
					<!-- Search Bar -->
					<form action="${contextPath}/admin/pub/find" method="get" class="submit-ajax">
						<div class="input-group">
							<!-- Search Icon -->
							<span class="input-group-addon"> <span class="glyphicon glyphicon-search"></span></span>
							<!-- Search Input -->
							<input type="text" class="form-control" placeholder="Publication ID" id="id" name="id" />
							<div class="input-group-btn">
								<input type="submit" class="btn btn-primary" onClick="javascript:idSearch();" value="Lookup" />
							</div>
						</div>
					</form>
					<!-- Search Results -->
					<div id="Results" style="display:none"> <!-- class="submit-ajax-result"> -->
						<div class="margin-top-24"></div>
						
						
					</div>
					<!-- Remove AJAX Form -->
					<form action="${contextPath}/admin/pub/remove" method="post" id="" class="submit-ajax-remove">
						<input type="hidden" name="id" value="pubid1"/>
					</form>
					<!-- Empty Result -->
					<div class="submit-ajax-empty-result">
						<div class="margin-top-24"></div>
						<h3>No publication found.</h3>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<jsp:include page="inc.body.footer.jsp" />
</body>
</html>