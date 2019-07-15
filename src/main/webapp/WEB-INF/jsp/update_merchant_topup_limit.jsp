<%@ include file="header.jsp"%>
<div class="breadcome-area">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="breadcome-list single-page-breadcome">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<ul class="breadcome-menu">
								<li><a href="dashboard">Home</a> <span class="bread-slash">/</span>
								</li>
								<li><span class="bread-blod">Top-Up Limit</span></li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
</div>
<!-- Basic Form Start -->
<div class="basic-form-area mg-tb-15">
	<div class="container-fluid">
		<div class="row">
			<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
				<div class="sparkline12-list">
					<div class="sparkline12-hd">
						<div class="main-sparkline12-hd">
							<h1>Merchant Top-Up Limit</h1>
						</div>
						<div class="add-product">
							<a href="#" data-toggle="modal" data-target="#UploadTopUpLimit">Upload
								Merchant Top-Up</a>
						</div>
						<div class="col-lg-3"></div>
						<div class="col-lg-9">
							<h4 style="color: green">${successMessage}</h4>
							<h4 style="color: red">${errorMessage}</h4>
						</div>
					</div>
					<br>
					<div class="sparkline12-graph">
						<div class="basic-login-form-ad">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="all-form-element-inner">
										<form action="merchant_limit_topup" method="POST">
											<div class="row">
												<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
													<label class="login2 ">Select Project</label>
												</div>
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<div class="form-select-list">
														<select
															class="select2_single form-control custom-select-value"
															name="project_id" required id="project"
															onchange="getMerchants()">
															<option value="">Select One Project</option>
															<c:forEach var="project" items="${projects}">
																<option value="${project.projectID}">${project.projectName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
												<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
													<label class="login2 ">Select Merchant</label>
												</div>
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<div class="form-select-list">
														<select
															class="select2_single form-control custom-select-value"
															name="merchant_id" required id="merchant"
															onchange="setMerchantID()">
															<option value="">Select Merchant</option>
														</select>
													</div>
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
													<div class="sparkline16-list responsive-mg-b-30">
														<div class="sparkline16-graph">
															<div class="date-picker-inner">
																<div class="form-group data-custon-pick">
																	<label>Merchant ID</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-diamond"></i></span> <input type="text"
																			class="form-control" id="merchant_id">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Actual Top-Up Paid </label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-money"></i></span> <input type="text"
																			class="form-control" required id="actual_topup_paid"
																			name="actual_topup_paid">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Transaction ID </label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" required id="transaction_id"
																			name="transaction_id">
																	</div>
																</div>
																<div class="form-group data-custon-pick" id="data_1">
																	<label>Cheque Date</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-calendar"></i></span> <input type="text"
																			class="form-control" placeholder="dd/mm/yyyy"
																			id="cheque_date" name="cheque_date" required>
																	</div>
																</div>
																<div class="input-group mg-b-pro-edt">
																	<span class="input-group-addon"><i
																		class="fa fa-home" aria-hidden="true"></i></span> <select
																		onchange="print_city('state', this.selectedIndex);"
																		id="sts" name="state"
																		class=" select2_single form-control" required>
																	</select>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
													<div class="sparkline16-list responsive-mg-b-30">
														<div class="sparkline16-graph">
															<div class="date-picker-inner">
																<div class="form-group data-custon-pick">
																	<label>Agreed Top-Up Amount </label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-money"></i></span> <input type="text"
																			class="form-control" required
																			id="agreed_topup_amount" name="agreed_topup_amount">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Bank Name </label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-money"></i></span> <input type="text"
																			class="form-control" required id="bank_name"
																			name="bank_name">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Cheque Number </label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" required id="cheque_number"
																			name="cheque_number">
																	</div>
																</div>
																<div class="form-group data-custon-pick" id="data_1">
																	<label>Cheque Clearance Date</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-calendar"></i></span> <input type="text"
																			class="form-control" placeholder="dd/mm/yyyy"
																			id="cheque_clearance_date"
																			name="cheque_clearance_date" required>
																	</div>
																</div>
																<div class="input-group mg-b-pro-edt">
																	<span class="input-group-addon"><i
																		class="fa fa-home" aria-hidden="true"></i></span> <select
																		id="state" name="city"
																		class="select2_single form-control" required>
																		<option value="">Select City</option>
																	</select>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-lg-4"></div>
												<div class="col-lg-8">
													<div class="login-horizental cancel-wp pull-left">
														<a href="dashboard"><button
																class="btn btn-sm btn-danger" type="button">Cancel</button></a>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit">Submit</button>
													</div>
												</div>
											</div>
										</form>
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
<!-- Modal's Start-->
<div id="UploadTopUpLimit"
	class="modal modal-adminpro-general fullwidth-popup-InformationproModal fade"
	role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-close-area modal-close-df">
				<a class="close" data-dismiss="modal" href="#"><i
					class="fa fa-close"></i></a>
			</div>
			<form id="limitupload" action="upload_merchant_topup_limit"
				method="POST" enctype="multipart/form-data">
				<div class="modal-body">
					<i class="fa fa-cloud-upload modal-check-pro"></i>
					<h2>Upload Merchant's Top-Up Limit</h2>
					<div class="row">
						<div class="col-lg-4 col-md-12 col-sm-12 col-xs-12">
							<label class="login2 pull-right pull-right-pro">Select
								File To Upload</label>
						</div>
						<div class="col-lg-8 col-md-12 col-sm-12 col-xs-12">

							<div class="input append-small-btn">
								<div class="file-button">
									<input type="file" name="topup">
								</div>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<a href="${pageContext.request.contextPath}/downloads/xlsx/uploadlimitformat.xlsx"><button type="button" class="btn btn-sm btn-primary">Download Upload Format</button></a>
						</div>
					</div>
				</div>
				<div class="modal-footer">
					<a data-dismiss="modal" href="#">Cancel</a> <a href="#"
						id="submitform">Upload</a>	
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Modal's End -->
<!-- Basic Form End -->
<script type="text/javascript">
	function getMerchants() {

		var project = document.getElementById("project");
		var projectID = project.options[project.selectedIndex].value;
		$
				.ajax({
					url : "${pageContext.request.contextPath}/merchants/getByProjectId/"
							+ projectID,
					type : "get", //send it through get method
					success : function(response) {
						console.log(response);
						var merchant = document.getElementById("merchant");
						merchant.options.length = 0;
						var option = document.createElement('option');
						option.text = "Select Merchant";
						option.value = "";
						merchant.add(option, 0);
						for (var i = 0; i < response.length; i++) {
							var option = document.createElement('option');
							option.text = response[i].merchantID;
							option.value = response[i].merchantID;
							merchant.add(option, i + 1);
						}
						document.getElementById("merchant_id").value = "";
						document.getElementById("merchant_id").disabled = true;

					},
					error : function(response) {
						var merchant = document.getElementById("merchant");
						merchant.options.length = 0;
						var option = document.createElement('option');
						option.text = "Please Select Project First";
						option.value = "";
						merchant.add(option, 0);
						document.getElementById("merchant_id").value = "";
						document.getElementById("merchant_id").disabled = true;
					}
				});
	}
	function setMerchantID() {

		var merchant = document.getElementById("merchant");
		var merchantID = merchant.options[merchant.selectedIndex].value;
		if (merchantID != "") {
			document.getElementById("merchant_id").value = merchantID;
		} else {
			document.getElementById("merchant_id").value = "";
		}
	}
</script>
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	print_state("sts");
	$(document).ready(function() {
		$(".select2_single").select2({
			allowClear : true
		});
	});
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$('#submitform').click(function() {
			$('#limitupload').submit();
		});
	});
</script>