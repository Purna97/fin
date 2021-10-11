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
				<center>
					<img id="loadid" src="resources/images/loader.gif">
				</center>
				<div id="empdiv">
					<div class="row">
						<div class="col">
							<div class="card">
								<h5 class="card-header">All Employees</h5>
								<br>
								<table id="emptable" class="table table-hover table-bordered"
									style="text-align: center;">
									<thead style="background-color: lavender;">
										<tr>
											<th>Employee ID</th>
											<th>Employee Name</th>
											<th>Phone Number</th>
											<th>Hired Date</th>
											<th>Action</th>
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
	</div>
	<div class="modal fade" id="repomodal1" tabindex="-1" role="dialog"
		aria-labelledby="exampleModalLabel" aria-hidden="true"
		style="top: 60px;">
		<div class="modal-dialog modal-lg" style="max-width: 60%;"
			role="document">
			<div class="modal-content" style="height: 100%;">
				<div class="modal-header" style="padding: .5rem;">
					<button class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
					</button>

					<center>
						<h5 class="modal-title" id="exampleModalLabel"
							style="font-size: 15px">
							<b>Create Products and Services</b>
						</h5>
					</center>
				</div>
				<div class="modal-body">
					<div>
						<div class="content">
							<div class="row">
								<div class="col">
									<div class="card">
										<div class="col-md-12 col-sm-6">
											<div class="form-group row">
												<label for="inputPassword" class="col-sm-4 control-label"
													style="margin-top: .5%;"><Span style="color: red;">*</Span>Product
													Name</label>
												<div class="col-sm-4">
													<input id="productName" type="text"
														title="only alphabets are allowed" minlength="2"
														maxlength="30" autocomplete="off"
														placeholder="Enter Product Name" name="productName"
														class="form-control" required>
												</div>

											</div>

											<div class="form-group row">
												<label for="inputPassword" class="col-sm-4 control-label"
													style="margin-top: .5%;"><Span style="color: red;">*</Span>Hired
													Date</label>
												<div class="col-sm-4">
													<input id="hiredate" type="date"
														title="only alphabets are allowed" minlength="2"
														maxlength="30" autocomplete="off"
														placeholder="Enter Product Name" name="hiredate"
														class="form-control" required>
												</div>

											</div>


											<div class="form-group row">
												<label for="inputPassword" class="col-sm-4 control-label"
													style="margin-top: .5%;"><Span style="color: red;">*</Span>Client</label>
												<div class="col-sm-4">
													<input id="client" type="text"
														title="only alphabets are allowed" minlength="2"
														maxlength="15" autocomplete="off"
														placeholder="Enter Client" name="client"
														class="form-control" required>
												</div>

											</div>
											<div class="form-group row">
												<label for="inputPassword" class="col-sm-4 control-label"
													style="margin-top: .5%;"><Span style="color: red;">*</Span>Unit
													Price($)</label>
												<div class="col-sm-4">
													<input id="unitprice" type="text"
														title="only alphabets are allowed" minlength="2"
														pattern="[0-9]{5}" maxlength="5" autocomplete="off"
														placeholder="Enter unitprice " name="unitprice"
														class="form-control" required>
												</div>
											</div>

											<div class="form-group row">
												<label for="inputPassword" class="col-sm-4 control-label"
													style="margin-top: .5%;"><Span style="color: red;">*</Span>Description</label>
												<div class="col-sm-4">
													<input id="description" type="text"
														title="only alphabets are allowed" minlength="2"
														maxlength="50" autocomplete="off"
														placeholder="Enter Description" name="description"
														class="form-control" required>
												</div>

											</div>


											<div class="form-group row">
												<div class="col-sm-3">
													<button type="button" style="margin-left: 395px;"
														id="createproduct" class="btn btn-info btn-sm">SUBMIT</button>
												</div>
											</div>
										</div>
									</div>
								</div>
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
	<script>

		 var tabledocse1 = $('#emptable').DataTable({
				"order" : [ [ 0, "asc" ] ],
				lengthMenu: [[10, 20, 50, -1], [10, 20, 50, "All"]],
				bLengthChange : true,
				paging : true
			});
		 
		 $('#loadid').hide();
		 $('#empdiv').hide();
		  window.onload=function(){
			  $('#loadid').show();
			 $.ajax({
					type : "GET",
					asyn : false,
					contentType : "application/text",
					url : "getallemployees",
					success : function(response) {
						 $('#loadid').hide();
						 $('#empdiv').show();
						  const obj = JSON.parse(response);
							$('#emptable').dataTable().fnClearTable();
				  			if (tabledocse1)tabledocse1.clear();
				  			
					 	  $.each(obj.IntuitResponse.QueryResponse.Employee, function (index, value) {  
			           
					 		 var phone=" ";
							    if(value.hasOwnProperty('PrimaryPhone')){
							    	phone=value.PrimaryPhone.FreeFormNumber;
					 		    }else{
					 		    	phone="NA";
					 		    }
							    
							    var hiredate=" ";
							    if(value.hasOwnProperty('HiredDate')){
							    	hiredate=value.HiredDate;
					 		    }else{
					 		    	hiredate="NA";
					 		    }
							    
							    var ssn=" ";
							    if(value.hasOwnProperty('SSN')){
							    	ssn=value.SSN;
					 		    }else{
					 		    	ssn="NA";
					 		    }
							    
							tabledocse1.row.add([
				  			'' + value.Id	+ '',
				  			'' + value.DisplayName	+ '',
				  			'' + phone	+ '',
				  			'' + hiredate	+ '',
				  			'<a class="btn btn-primary btn-sm" style="color:white;"  data-toggle="modal" data-target="#repomodal1" onclick="createprod(\''+value.DisplayName+'\',\''+hiredate+'\')" >Create Product</a> '  ]).draw();
				
						  }); 
					}
			 });
		 } 
		  
		  function createprod(name,hiredate){
	
			  $('#productName').val("");
			  $('#hiredate').val("");
			  
			  $('#productName').val(name);
			  $('#hiredate').val(hiredate);
			  
			  
		  }
		  
			  $("#createproduct").click(function(event) {
				  
				 var productName = $('#productName').val();
				 var hiredate = $('#hiredate').val();
				 var client = $('#client').val();
				 var unitprice = $('#unitprice').val();
				 var description = $('#description').val();
				 
			 	$.ajax({
					type : "POST",
					asyn : false,
					url : "createproduct",
					data : {
						productName : productName,
						hiredate : hiredate,
						client : client,
						unitprice : unitprice,
						description : description,
					},
					success : function(response) {
				 		if(response!=""){
							 Swal.fire({
								  position: 'top',
								  icon: 'success',
								  width:'500px',
								  height:'900px', 
								  title: 'Product Created Succesfully',
								  showConfirmButton: false,
								  timer: 1250
								}).then((result) => {
									location.reload();
								});
			
						}else{
							 Swal.fire({
								  position: 'top',
								  icon: 'error',
								  width:'500px',
								  height:'900px', 
								  title: 'Product Already Exists',
								  showConfirmButton: false,
								  timer: 1600
								}).then((result) => {
									location.reload();
								});
						}
					}
				}); 
					 });
	</script>
</body>
</html>
