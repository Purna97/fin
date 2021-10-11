<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@taglib prefix="c" uri="http://java.sun.com/jstl/core_rt"%>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="shortcut icon" href="resources/images/favicon.ico">
<link rel="stylesheet" href="resources/css/css/normalize.min.css">
<link rel="stylesheet" href="resources/css/css/font-awesome.min.css">
<link rel="stylesheet" href="resources/css/css/themify-icons.css">
<link rel="stylesheet" href="resources/css/css/pe-icon-7-stroke.min.css">
<link rel="stylesheet" href="resources/css/css/flag-icon.min.css">
<link rel="stylesheet" href="resources/css/css/bootstrap.min.css">
<link rel="stylesheet" href="resources/css/style-skin.css">
<link rel="stylesheet" href="resources/css/style.css">
<link rel="stylesheet" href="resources/css/all.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<title>US Finance</title>
<style type="text/css">
.navbar .navbar-nav li>a:hover, .navbar .navbar-nav li>a:hover .menu-icon
	{
	color: #fff;
}

li {
	transition: transform .4s;
	margin: 0 auto;
}

li:hover {
	transform: scale(1.3);
}

.zoom {
	transition: transform .4s;
}

.zoom:hover {
	transform: scale(1.3);
}

.Counter {
	padding: 2px 10px;
	font-size: 14px;
	f font-weight: 600;
	line-height: 1;
	color: #586069;
	background-color: rgba(27, 31, 35, 0.08);
	border-radius: 20px;
}

.stat-heading .badge {
	position: absolute;
	top: -10px;
	right: -10px;
	padding: 10px 15px;
	border-radius: 50%;
	background-color: red;
	color: white;
}
</style>
</head>

<body class="sidebar-mini fixed">
	<div id="right-panel" class="right-panel">
		<!-- Navbar-->
		<header id="header" class="header">
		<div class="top-left">
			<div class="navbar-header">
				<a class="navbar-brand" href="dashboard"><img
					src="resources/images/logo-web.png" alt="Logo"></a> <a
					id="menuToggle" class="menutoggle"><i class="fa fa-bars"></i></a>
			</div>
		</div>
		<div class="top-right">
			<div class="header-menu">
				<div class="header-left"></div>
				<div class="user-area dropdown float-right">
					<a href="#" class="dropdown-toggle active" data-toggle="dropdown"
						aria-haspopup="true" aria-expanded="false"> <span
						class='user-name hidden-phone'
						style="font-weight: bolder; color: aliceblue"> <c:out
								value="${username}"></c:out></span> &nbsp;&nbsp; <input type="hidden"
						name="username" value="${username}" id="username"> <img
						class="user-avatar" src="resources/images/unknown_user.png"
						alt="User Avatar">
					</a>
					<div class="user-menu dropdown-menu">
						<a class="nav-link" href="ChangePassword"><i
							class="fa fa -cog"></i>Change Password</a> <a class="nav-link"
							href="loginpage"><i class="fa fa-power -off"></i>Logout</a>
					</div>
				</div>

			</div>
		</div>
		</header>
		<!-- Side-Nav-->
		<aside id="left-panel" class="left-panel"> <nav
			class="navbar navbar-expand-sm navbar-default">
		<div id="main-menu" class="main-menu collapse navbar-collapse">
			<ul class="nav navbar-nav">
				<li style="background-color: #008ab9;"><a href="dashboard"><i
						style="font-size: 25px;" class="menu-icon fa fa-dashboard"></i>Dashboard</a></li>

				<li><a href="invoicepage"><i style="font-size: 30px;"
						class="fas fa-file-invoice-dollar"></i>&nbsp;&nbsp;Invoice</a></li>

				<li><a href="expensepage"><i style="font-size: 30px;"
						class='fas fa-hand-holding-usd'></i>&nbsp;&nbsp;Expenses</a></li>

				<li><a href="addemployee"><i style="font-size: 30px;"
						class='fas fa-user'></i>&nbsp;&nbsp;All Employees</a></li>

				<li><a href="addcustomer"><i style="font-size: 30px;"
						class='fas fa-user'></i>&nbsp;&nbsp;All Customers</a></li>

				<li><a href="products"><i style="font-size: 30px;"
						class='fas fa-user'></i>&nbsp;&nbsp;Products and Services</a></li>
						
				<li><a href="reports"><i style="font-size: 30px;"
						class='fas fa-line-chart'></i>&nbsp;&nbsp;Reports</a></li>


			</ul>

		</div>
		</nav> </aside>
		<div class="content">
			<!-- Animated -->
			<div class="animated fadeIn">
				<div class="row">
					<div class="col">
						<div class="card">
							<h5 class="card-header">US Finance Dashboard</h5>
							<div class="card-body">
								<div class="row">


									<div class="col-lg-3 col-md-6">
										<a href="invoicepage">
											<div class="card">
												<div class="card-body">
													<div class="stat-widget-five zoom">
														<div class="stat-icon dib flat-color-1">
															<i class="fas fa-file-invoice-dollar"> </i>
														</div>
														<div class="stat-content">
															<div class="text-left dib">
																<div class="stat-heading"
																	style="font-size: 20px; color: black;">Invoice</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</a>
									</div>

									&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;

									<div class="col-lg-3 col-md-6">
										<a href="expensepage">
											<div class="card">
												<div class="card-body">
													<div class="stat-widget-five zoom">
														<div class="stat-icon dib flat-color-3">
															<i class="fas fa-hand-holding-usd"> </i>
														</div>
														<div class="stat-content">
															<div class="text-left dib">
																<div class="stat-heading"
																	style="font-size: 20px; color: black;">Expenses</div>
															</div>
														</div>
													</div>
												</div>
											</div>
										</a>
									</div>

									&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;



								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>

	<!-- Javascripts-->
	<script src="resources/js/js/jquery.min.js"></script>
	<script src="resources/js/js/popper.min.js"></script>
	<script src="resources/js/js/bootstrap.min.js"></script>
	<script src="resources/js/js/jquery.matchHeight.min.js"></script>
	<script src="resources/js/js/main.js"></script>
	<script src="resources/js/js/jquery-2.1.4.min.js"></script>
	<script
		src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"
		type="text/javascript"></script>
	<script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
	<script>
	
	  window.onload=function(){
		 $.ajax({
			    type : "GET",
				asyn : false,
				contentType : "application/text",
				url : "checktokens",
				success : function(response) {
			 	 if(response=='Token expired'){
					  Swal.fire({
								  position: 'top',
								  icon: 'info',
								  width:'620px',
								  height:'900px', 
								  title: 'Connecting to QuickBooks..Please wait',
								  showConfirmButton: false,
								  timer: 1800,
								}).then((result) => {
									window.location.href="connectToQuickbooks";
								});
				} 
				}
		 });
	 } 

	</script>


</body>
</html>