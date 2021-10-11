<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%-- <%@ taglib uri="http://java.sun.com/js/p/js/tl/core" prefix="c" %> --%>
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<%-- <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> --%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@page import="java.net.URLDecoder"%>
<html>
<head>
<!-- <meta http-equiv="Content-Type" content="text/html;charset=UTF-8" /> -->
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
	type="text/javascript"></script>
<link rel="stylesheet" href="resources/css/css/select2.min.css"
	rel="stylesheet" />
<link rel="stylesheet" href="resources/css/css/datepicker3.min.css" />
</head>

<style type="text/css">

.btns{
background-color:#21a4dd;
border-radius:25px;
margin-left:40px;
width:300px;
font-size:18px;
transition: transform .9s;

}
.btns:hover {
background-color:#21a4dd;
	transform: scale(1.1);
}
</style>

<body>
	<div id="right-panel" class="right-panel">
		<div class="content">
			<!-- Animated -->
			<div class="animated fadeIn">
				<center>
					<img id="loadid" src="resources/images/loader.gif">
				</center>
				<div id="empdiv">
					<div class="row">
						<div class="col">
							<div class="card">
								<center><h5 style="font-size:18px;" class="card-header"><b>Generate Reports</b></h5></center>
								<br>
							<button type="button"  class="btn btn-primary btn-sm btns"><b>Profit and Loss Report</b></button><br>
							<button type="button"  class="btn btn-primary btn-sm btns"><b>Sales Report</b></button><br>
							<button type="button"  class="btn btn-primary btn-sm btns"><b>Ledgers Report</b></button><br>
							<button type="button"  class="btn btn-primary btn-sm btns"><b>Profit and Loss Report</b></button><br>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<script src="resources/js/js/jquery-2.1.4.min.js"></script>
	<script src="resources/js/newjs/bootstrap.min.js"></script>
	<script src="resources/js/newjs/formValidation.min.js"></script>
	<script src="resources/js/newjs/frameworkbootstrap.min.js"></script>
	<script src="resources/js/newdt/jquery.dataTables.min.js"></script>
	<script src="resources/js/newdt/dataTables.bootstrap4.min.js"></script>
	<script src="resources/js/newdt/datatables-init.js"></script>
	<script type="text/javascript" src="resources/js/js/select2.min.js"></script>
	<script src="resources/preDefined/goback.js"></script>

</body>
</html>
