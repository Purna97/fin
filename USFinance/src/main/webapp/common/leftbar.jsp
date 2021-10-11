<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="resources/css/style.css"
	rel="stylesheet" />

</head>

<style type="text/css">
.active1{
background-color:#008ab9; 
}
.navbar .navbar-nav li > a:hover{
    color: #fff;   	
    transform: scale(1.3); 
    transition: transform .2s;
 	margin: 0 auto;
}
.navbar .navbar-nav li > a:hover .menu-icon {
    color: #fff;   	
}
</style>

<body class="sidebar-mini fixed">
<aside id="left-panel" class="left-panel" > <nav
		class="navbar navbar-expand-sm navbar-default">
	<div id="main-menu" class="main-menu collapse navbar-collapse" id="sidenav">
		<ul class="nav navbar-nav">
		<c:choose>
		<c:when test="${not empty lgemailid}">
		<li ><a href="bgvFormsUser"><i class="menu-icon fa fa-dashboard"></i>Dashboard
		    </a></li>
		</c:when>
		<c:otherwise>
		<li ><a href="dashboard"><i class="menu-icon fa fa-home"></i>Home
				</a></li>
		</c:otherwise>
		</c:choose>
				
				
			
				
	
		
		
		</ul>
	</div>
	</nav> </aside> 
</body>
<script>

window.onload = function() {
	var path=location.pathname.split("/")[2];
	$('nav a[href^="' +path + '"]').closest('li').addClass('active1');
	};

</script>
</html>