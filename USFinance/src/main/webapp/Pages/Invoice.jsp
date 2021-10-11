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
.field-error .control-label, .field-error .help-block, .field-error .form-control-feedback
	{a
	color: red;
}

.field-success .control-label, .field-success .help-block,
	.field-success .form-control-feedback {
	color: #3c763d;
}

#pendingdocs {
	width: 105% !important;
}

.col-lg-2 {
	padding-right: 5px;
}

#iconImgId {
	max-width: 30%;
}

.form-control {
	font-size: 12px;
}
</style>
<body>
	<div id="right-panel" class="right-panel">
		<div class="content">
			<!-- Animated -->
			<div class="animated fadeIn">



				<!-- 		<button type="button" id="btnh1bAppl"
							style="margin-left: 0px; margin-right: auto;margin-left: 3%; display: block; margin-top: 1%; margin-bottom: 1%;border-radius: 25px;background-color:steelblue;color:white;font-size:20px;width:200px;"
							class="btn btn-primary btn-sm collapsibleInvoice">All Invoices</button> -->

				<center>
					<img id="loadid" src="resources/images/loader.gif">
				</center>
				<div id="allinvoicediv">
					<div class="col">
						<div class="card">
							<h5 class="card-header">All Invoices</h5>
							<br>
							<table id="allinvoicetable"
								class="table table-hover table-bordered"
								style="text-align: center;">
								<thead style="background-color: lavender;">
									<tr>
										<th>Invoice ID</th>
										<th>Customer Name</th>
										<th>Product Name</th>
										<th>Document Number</th>
										<th>Due Amount</th>
										<th>Due Date</th>
										<th>Download</th>
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

		 var tabledocse1 = $('#allinvoicetable').DataTable({
				"order" : [ [ 0, "asc" ] ],
				lengthMenu: [[10, 20, 50, -1], [10, 20, 50, "All"]],
				bLengthChange : true,
				paging : true
			});
		 $('#loadid').hide();
		 $('#allinvoicediv').hide();
		  window.onload=function(){
			  $('#loadid').show();
			 $.ajax({
					type : "GET",
					asyn : false,
					contentType : "application/text",
					url : "getallInvoiceIds",
					success : function(response) {
						 $('#loadid').hide();
						 $('#allinvoicediv').show();
						  const obj = JSON.parse(response);
							$('#allinvoicetable').dataTable().fnClearTable();
				  			if (tabledocse1)tabledocse1.clear();
				  			
						  $.each(obj.IntuitResponse.QueryResponse.Invoice, function (index, value) { 
							  
							  
							  var product=" ";
							    if(value.hasOwnProperty('SalesItemLineDetail')){
							    	product=value.SalesItemLineDetail.ItemRef.name;
					 		    }else{
					 		    	product="NA";
					 		    }
							    
							tabledocse1.row.add([
				  			'' + value.Id	+ '',
				  			'' + value.CustomerRef.name	+ '',
				  			'' + value.Line[0].SalesItemLineDetail.ItemRef.name+ '',
				  			'' + value.DocNumber	+ '',
				  			'' + value.Line[0].Amount	+ '',
				  			'' + value.DueDate	+ '',
				            '<a class="btn btn-primary btn-sm" style="color:white;" onclick="downloadinvoice('+value.Id+')"  ><i class="fa fa-download"></i></a> '  ]).draw();
						  });
					}
			 });
		 } 
		 
		  function downloadinvoice(invoiceid){

			 	$.ajax({
					type : "GET",
					asyn : false,
					contentType : "application/pdf", // NOT dataType!
					url : "getinvoicepdf",
					data : {
						invoiceid : invoiceid
					},
					success : function(response) {
						if(response!=""){
							window.location.href="downloadinvoices?filename=InvoiceId_"+invoiceid+".pdf";
							 Swal.fire({
								  position: 'top',
								  icon: 'success',
								  width:'500px',
								  height:'900px', 
								  title: 'Invoice downloaded',
								  showConfirmButton: false,
								  timer: 1250
								});
			
						}
					}
				}); 
					 }

/* 	 $("#invoicebtn").click(function(event) {
		 var invoiceid = $('#invoice_id').val();
		 $.ajax({
				type : "GET",
				asyn : false,
				contentType : "application/json",
				url : "generateinvoice",
				data : {
					"invoiceid" : invoiceid,
				},
				success : function(response) {
				 	if(response==""){
						alert("Data not Available")
					}else{
			    const obj = JSON.parse(response);
	
			    var invoiceid=obj.IntuitResponse.Invoice.Id;
			    var DocNumber=obj.IntuitResponse.Invoice.DocNumber;
			    var DueDate=obj.IntuitResponse.Invoice.DueDate;
			    var customername=obj.IntuitResponse.Invoice.CustomerRef.name;
			    var amt=obj.IntuitResponse.Invoice.Line[0].Amount;
			    $('#historyview').empty();
			    var tr="<tr><td>"+invoiceid+"</td>"
           			tr=  tr+"<td>"+customername+"</td>"
           			tr=  tr+"<td>"+DocNumber+"</td>"
       	   			tr=  tr+"<td>"+amt+"</td>"
       	   		    tr=  tr+"<td>"+DueDate+"</td></tr>";
       				$('#historyview').append(tr); 
       			    $('#invoicediv').show();
					}
				}
		 });
		 }); */
	 
/* 	    var coll = document.getElementsByClassName("collapsibleInvoice");
		var i;
		for (i = 0; i < coll.length; i++) {
			coll[i].addEventListener("click", function() {
				//alert("collapsibleInvoice");
				this.classList.toggle("active");
				var content = this.nextElementSibling;
				if (content.style.display === "block") {
					content.style.display = "none";
				} else {
					content.style.display = "block";
				}
			});
		} */

	</script>
</body>
</html>
