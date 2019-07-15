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
								<li><span class="bread-blod">Map</span></li>
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
							<h1>Bonus Points Mapping</h1>
						</div>
						<div class="add-product">
							<a href="map_and_modify_bonuspoints">Refresh</a>
						</div>
						<div class="col-lg-3"></div>
						<div class="col-lg-9">
							<h4 style="color: green">${successMessage}</h4>
							<h4 style="color: red">${errorMessage}</h4>
						</div>
					</div>
					<br> <br>

					<div class="admin-pro-accordion-wrap">
						<div class="panel-group adminpro-custon-design" id="accordion2">
							<div class="panel panel-default">
								<div class="panel-heading accordion-head">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse4" title="Click To Expand"> Individual
											Mapping</a>
									</h4>
									<div id="collapse4" class="panel-collapse panel-ic collapse">
										<br>
										<div class="row">
											<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
												<div class="all-form-element-inner">
													<form action="bonus_points_map" method="POST">
														<div class="row">
															<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																<label class="login2 pull-right pull-right-pro">Select
																	Project</label>
															</div>
															<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																<div class="form-select-list">
																	<select
																		class="select2_single form-control custom-select-value"
																		name="project_id" required id="project"
																		onchange="getMerchants()" style="width: 325px;">
																		<option value="">Select One Project</option>
																		<c:forEach var="project" items="${projects}">
																			<option value="${project.projectID}">${project.projectName}</option>
																		</c:forEach>
																	</select>
																</div>
															</div>
														</div>
														<br>
														<div class="row">
															<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																<label class="login2 pull-right pull-right-pro">Select
																	Merchant ID</label>
															</div>
															<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																<div class="form-select-list">
																	<select
																		class=" select2_single form-control custom-select-value"
																		name="merchant_id" required id="merchant"
																		onchange="getMapping()" style="width: 325px;">
																		<option value="">Select Merchant ID</option>
																	</select>
																</div>
															</div>
														</div>
														<br>
														<div class="row">
															<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																<label class="login2 pull-right pull-right-pro"></label>
															</div>
															<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-user"></i></span> <input type="text"
																		class="form-control" placeholder="Merchant Name"
																		title="Merchant Name" required id="merchant_name"
																		name="merchant_name" disabled> <input
																		type="hidden" name="user"
																		value="${sessionScope.user.employeeName }">
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
																				<label>Bonus Point Per 100(INR)</label>
																				<div class="input-group date">
																					<span class="input-group-addon"><i
																						class="fa fa-diamond"></i></span> <input type="text"
																						class="form-control" required id="bpPer100"
																						name="bpPer100">
																				</div>
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
																				<label>Amount(INR) Per Bonus Point</label>
																				<div class="input-group date">
																					<span class="input-group-addon"><i
																						class="fa fa-money"></i></span> <input type="text"
																						class="form-control" required id="amtPerBp"
																						name="amtPerBp">
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-4"></div>
															<div class="col-lg-8">
																<div class="login-horizental cancel-wp pull-left">
																	<a href="dashboard"><button
																			class="btn btn-sm btn-danger" type="button">Cancel</button></a>
																	<button class="btn btn-sm btn-primary login-submit-cs"
																		type="submit">Save</button>
																</div>
															</div>
														</div>
													</form>
													<br>
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="table-responsive">
																<table class="table table-striped table-hover"
																	id="individualmapping">
																	<thead class="thead-dark">
																		<tr>
																			<th>#</th>
																			<th>Employee Name</th>
																			<th>Created On</th>
																			<th>New BP/100</th>
																			<th>New Amount/BP</th>
																			<th>Previous BP/100</th>
																			<th>Previous Amount/BP</th>
																			<th>Mapping Type</th>
																		</tr>
																	</thead>
																	<tbody id="individualmappingbody">

																	</tbody>
																</table>
																<div class="col-lg-2"></div>
																<div class="col-lg-10">
																	<h4 id="errorMessage" style="color: red"></h4>
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
							<br>
							<div class="panel panel-default">
								<div class="panel-heading accordion-head">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse5" title="Click To Expand"> Corporate
											Mapping </a>
									</h4>
									<div id="collapse5" class="panel-collapse panel-ic collapse ">
										<br>
										<div class="row">
											<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
												<div class="all-form-element-inner">
													<form action="bonus_points_map_corporate" method="POST">
														<div class="row">
															<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																<label class="login2 pull-right pull-right-pro">Select
																	Corporate Project</label>
															</div>
															<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																<div class="form-select-list">
																	<select
																		class="select2_single form-control custom-select-value"
																		name="cproject_id" required id="cproject"
																		onchange="getTransactions()" style="width: 325px;">
																		<option value="">Select One Project</option>
																		<c:forEach var="cproject" items="${cprojects}">
																			<option value="${cproject.projectID}">${cproject.projectName}</option>
																		</c:forEach>
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
																				<label>Bonus Point Per 100(INR)</label>
																				<div class="input-group date">
																					<span class="input-group-addon"><i
																						class="fa fa-diamond"></i></span> <input type="text"
																						class="form-control" required name="bpPer100"
																						id="cbpPer100"> <input type="hidden"
																						name="user"
																						value="${sessionScope.user.employeeName }">
																				</div>
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
																				<label>Amount(INR) Per Bonus Point</label>
																				<div class="input-group date">
																					<span class="input-group-addon"><i
																						class="fa fa-money"></i></span> <input type="text"
																						class="form-control" required name="amtPerBp"
																						id="camtPerBp">
																				</div>
																			</div>
																		</div>
																	</div>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-4"></div>
															<div class="col-lg-8">
																<div class="login-horizental cancel-wp pull-left">
																	<a href="dashboard"><button
																			class="btn btn-sm btn-danger" type="button">Cancel</button></a>
																	<button class="btn btn-sm btn-primary login-submit-cs"
																		type="submit">Save</button>
																</div>
															</div>
														</div>
													</form>
													<br>
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="table-responsive">
																<table class="table table-striped table-hover"
																	id="corporatemapping">
																	<thead class="thead-dark">
																		<tr>
																			<th>#</th>
																			<th>MerchantID</th>
																			<th>Employee Name</th>
																			<th>Created On</th>
																			<th>New BP/100</th>
																			<th>New Amount/BP</th>
																			<th>Previous BP/100</th>
																			<th>Previous Amount/BP</th>
																			<th>Mapping Type</th>
																		</tr>
																	</thead>
																	<tbody id="corporatemappingbody">

																	</tbody>
																</table>
																<div class="col-lg-2"></div>
																<div class="col-lg-10">
																	<h4 id="errorMessage1" style="color: red"></h4>
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
							<br>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
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
						option.text = "Select Merchant ID";
						option.value = "";
						merchant.add(option, 0);
						for (var i = 0; i < response.length; i++) {
							var option = document.createElement('option');
							option.text = response[i].merchantID;
							option.value = response[i].merchantID;
							merchant.add(option, i + 1);
						}
						document.getElementById("bpPer100").value = "";
						document.getElementById("amtPerBp").value = "";
						document.getElementById("merchant_name").value = "";
						$("#individualmappingbody").empty();
						$('#errorMessage').text("");
						

					},
					error : function(response) {
						var merchant = document.getElementById("merchant");
						merchant.options.length = 0;
						var option = document.createElement('option');
						option.text = "Please Select Project First";
						option.value = "";
						merchant.add(option, 0);
						document.getElementById("bpPer100").value = "";
						document.getElementById("amtPerBp").value = "";
						document.getElementById("merchant_name").value = "";
						$("#individualmappingbody").empty();
						$('#errorMessage').text("");
					}
				});
	}

	function getMapping() {

		var merchant = document.getElementById("merchant");
		var merchantID = merchant.options[merchant.selectedIndex].value;
		if (merchantID != "") {
			$
					.ajax({
						url : "${pageContext.request.contextPath}/merchants/getByMerchantId/"
								+ merchantID,
						type : "get", //send it through get method
						success : function(response) {
							console.log(response);
							var name = response.contactPerson;
							console.log(name);
							setTimeout(
									function() {
										document
												.getElementById("merchant_name").value = name;
									}, 30);
						},
						error : function(response) {
							console.log(response);
							document.getElementById("merchant_name").value = "";
						}
					});

			$
					.ajax({
						url : "${pageContext.request.contextPath}/admin/map/getByMerchantId/"
								+ merchantID,
						type : "get", //send it through get method
						success : function(response) {
							console.log(response);

							document.getElementById("merchant_name").value = response.contactPerson;
							document.getElementById("bpPer100").value = response.bpPer100;
							document.getElementById("amtPerBp").value = response.amountPerBP;

						},
						error : function(response) {
							console.log(response);
							document.getElementById("bpPer100").value = "";
							document.getElementById("amtPerBp").value = "";
						}
					});

			$
					.ajax({
						url : "${pageContext.request.contextPath}/admin/map/getTransByMerchantId/"
								+ merchantID,
						type : "get", //send it through get method
						success : function(response) {
							console.log(response);
							$("#individualmappingbody").empty();
							$('#errorMessage').text("");
							var len = response.length;
							var i = 0;
							for (i = 0; i <= len; i++) {
								var prevAmt =0 ;
								if(response[i].prevAmountPerBP != null){
									prevAmt = response[i].prevAmountPerBP;
								} else {
									prevAmt = 0;
								}
								$('#individualmapping').append(
										'<tr><td>' + (i + 1) + '</td><td>'
												+ response[i].mappedBy
												+ '</td><td>'
												+ response[i].createdAt
												+ '</td><td>'
												+ response[i].newBpPer100
												+ '</td><td>'
												+ response[i].newAmountPerBP
												+ '</td><td>'
												+ response[i].prevBpPer100
												+ '</td><td>'
												+ prevAmt
												+ '</td><td>'
												+ response[i].mappingType
												+ '</td></tr>');
							}

						},
						error : function(response) {
							console.log(response);
							$("#individualmappingbody").empty();
							$('#errorMessage').text("");
						}
					});

		} else {
			document.getElementById("merchant_name").value = "";
			document.getElementById("bpPer100").value = "";
			document.getElementById("amtPerBp").value = "";
			$("#individualmappingbody").empty();
			$('#errorMessage').text("");
		}
	}

	function getTransactions() {

		var cproject = document.getElementById("cproject");
		var cprojectID = cproject.options[cproject.selectedIndex].value;
		$
		.ajax({
			url : "${pageContext.request.contextPath}/admin/map/getByProjectId/"
					+ cprojectID,
			type : "get", //send it through get method
			success : function(response) {
				console.log(response);
				$("#corporatemappingbody").empty();
				$('#errorMessage1').text("");
				document.getElementById("cbpPer100").value = "";
				document.getElementById("camtPerBp").value = "";
				if (response != null) {
					document.getElementById("cbpPer100").value = response[0].corpBpPer100;
					document.getElementById("camtPerBp").value = response[0].corpAmountPerBP;
				}

			},
			error : function(response) {
				document.getElementById("cbpPer100").value = "";
				document.getElementById("camtPerBp").value = "";
				$("#corporatemappingbody").empty();
				$('#errorMessage1').text("");
			}
		});
		
		$
				.ajax({
					url : "${pageContext.request.contextPath}/admin/map/getTransByProjectId/"
							+ cprojectID,
					type : "get", //send it through get method
					success : function(response) {
						console.log(response);
						$("#corporatemappingbody").empty();
						$('#errorMessage1').text("");
						var len = response.length;
						var i = 0;
						for (i = 0; i <= len; i++) {
							var prevAmt = 0 ;
							if(response[i].prevAmountPerBP  != null){
								prevAmt = response[i].prevAmountPerBP;
							} else {
								prevAmt = 0;
							}
							$('#corporatemapping').append(
									'<tr><td>' + (i + 1) + '</td><td>'
											+ response[i].merchantID
											+ '</td><td>'
											+ response[i].mappedBy
											+ '</td><td>'
											+ response[i].createdAt
											+ '</td><td>'
											+ response[i].newBpPer100
											+ '</td><td>'
											+ response[i].newAmountPerBP
											+ '</td><td>'
											+ response[i].prevBpPer100
											+ '</td><td>'
											+ prevAmt
											+ '</td><td>'
											+ response[i].mappingType
											+ '</td></tr>');
						}

					},
					error : function(response) {
					}
				});
	}
</script>
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$(".select2_single").select2({
			allowClear : true
		});
	});
</script>