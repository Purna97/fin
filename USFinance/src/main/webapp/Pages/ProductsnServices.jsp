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

<body>
	<div id="right-panel" class="right-panel">
		<div class="content">
			<!-- Animated -->
			<div class="animated fadeIn">
<!-- 				<div class="row">
					<div class="col">
						<div class="card">
							<h5 class="card-header">Create Products and Services</h5>
							<br>
							<div class="col-md-11 col-sm-12">
								

								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 control-label"
										style="margin-top: .5%;"><Span style="color: red;">*</Span>Product Name</label>
									<div class="col-sm-4">
										<input id="purchaseamt" type="text"
											title="only alphabets are allowed" minlength="2"
											maxlength="15" autocomplete="off" placeholder="Enter Product Name"
											name="purchaseamt" class="form-control" required>
									</div>

								</div>
								<div class="form-group row">
									<label for="inputPassword" class="col-sm-3 control-label"
										style="margin-top: .5%;"><Span style="color: red;">*</Span>Product
										Type</label>
									<div class="col-sm-4">
										<select name="paytype" class="form-control text-left"
											id="paytype" required>
											<option value="">Select Product Type</option>
											<option value="Inventory">Inventory</option>
											<option value="Service">Service</option>
										</select>
									</div>
								</div>
								
								<div class="form-group row">
									<div class="col-sm-2">
										<button type="button" style="margin-left: 460px;"
											id="createpurchasebtn" class="btn btn-info btn-sm">Purchase</button>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div> -->
				<center>
					<img id="loadid" src="resources/images/loader.gif">
				</center>
				<div id="expensediv">
					<div class="col">
						<div class="card">
							<h5 class="card-header">All Products</h5>
							<br>
							<table id="expensetable"
								class="table table-hover table-bordered"
								style="text-align: center;">
								<thead style="background-color: lavender;">
									<tr>
										<th>Product ID</th>
										<th>Product Name</th>
										<th>Type</th>
										<th>UnitPrice</th>
										<th>Category</th>
										<th>Description</th>
										
									</tr>
								</thead>
								<tbody>

								</tbody>
							</table>
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
	<script>

		 var tabledocse1 = $('#expensetable').DataTable({
				"order" : [ [ 0, "asc" ] ],
				lengthMenu: [[10, 20, 50, -1], [10, 20, 50, "All"]],
				bLengthChange : true,
				paging : true
			});
		 $('#loadid').hide();
		 $('#expensediv').hide();
 		  window.onload=function(){
 			 $('#loadid').show();
			 $.ajax({
					type : "GET",
					asyn : false,
					contentType : "application/text",
					url : "getproducts",
					success : function(response) {
						 $('#loadid').hide();
						  $('#expensediv').show();
						  const obj = JSON.parse(response);
							$('#expensetable').dataTable().fnClearTable();
				  			if (tabledocse1)tabledocse1.clear();
				  			
					 	  $.each(obj.IntuitResponse.QueryResponse.Item, function (index, value) { 
					 		  
					 		  var category=" ";
							    if(value.hasOwnProperty('ParentRef')){
							    	category=value.ParentRef.name;
					 		    }else{
					 		    	category="NA";
					 		    }
							    
							    var desc=" ";
							    if(value.hasOwnProperty('Description')){
							    	desc=value.Description;
					 		    }else{
					 		    	desc="NA";
					 		    }
					
					 		  if(value!=undefined){
					 				tabledocse1.row.add([
							  			'' + value.Id	+ '',
							  			'' + value.Name	+ '',
							  			'' + value.Type	+ '',
							  			'' + value.UnitPrice	+ '',
							  		    '' + category+ '',   
							  			'' + desc+ ''
							              ]).draw();
					 		  }
						  }); 
					}
			 });	
		 }  

	 $("#createpurchasebtn").click(function(event) {
	
		 var producttype = $('#paytype').val();
		 var productname = $('#purchaseamt').val();
		
		
		 $.ajax({
				type : "POST",
				asyn : false,
				url : "createproduct",
				data : {
					"productname" : productname,
					"producttype" : producttype,
				
				},
				success : function(response) {
					 Swal.fire({
						  position: 'top',
						  icon: 'success',
						  width:'500px',
						  height:'900px', 
						  title: 'Product created',
						  showConfirmButton: false,
						  timer: 1250
						});
				}
		 });
		 });

	</script>
</body>
</html>
