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
								<li><span class="bread-blod">Send Notification</span></li>
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
							<h1>Send Notification</h1>
						</div>
						<div class="add-product">
							<a href="send_notifications_customer">Refresh</a>
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
											href="#collapse3" title="Click To Expand"> To All
											Customers</a>
									</h4>
									<div id="collapse3" class="panel-collapse panel-ic collapse">
										<br>
										<div class="sparkline12-graph">
											<div class="basic-login-form-ad">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
														<div class="all-form-element-inner">
															<form action="send_notification_to_all" method="POST">
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Notification
																				Type</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<input type="text" class="form-control"
																				placeholder="Enter Notification Type" required
																				name="notification_type" />
																		</div>
																	</div>
																</div>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Title</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<input type="text" class="form-control"
																				placeholder="Enter Title" required name="title" />
																		</div>
																	</div>
																</div>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Description</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<textarea rows="6" cols="90"
																				placeholder=" Enter Description Here . . ." required
																				name="description"></textarea>
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
																			<button
																				class="btn btn-sm btn-primary login-submit-cs"
																				type="submit">Send</button>
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
							<br>
							<div class="panel panel-default">
								<div class="panel-heading accordion-head">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse4" title="Click To Expand"> To Single
											Customer</a>
									</h4>
									<div id="collapse4" class="panel-collapse panel-ic collapse">
										<br>
										<div class="sparkline12-graph">
											<div class="basic-login-form-ad">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
														<div class="all-form-element-inner">
															<form action="send_notification_single_customer"
																method="POST">
																<div class="row">
																	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																		<label class="login2 pull-right pull-right-pro">Select
																			Customer</label>
																	</div>
																	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																		<div class="form-select-list">
																			<select
																				class="select2_single form-control custom-select-value"
																				name="customer_id" required id="customer"
																				style="width: 325px;">
																				<option value="">Select One Customer</option>
																				<c:forEach var="customer" items="${customers}">
																					<option value="${customer.customerID}">${customer.customerID}
																						- ${customer.customerName}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																</div>
																<br>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Notification
																				Type</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<input type="text" class="form-control"
																				placeholder="Enter Notification Type" required
																				name="notification_type" />
																		</div>
																	</div>
																</div>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Title</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<input type="text" class="form-control"
																				placeholder="Enter Title" required name="title" />
																		</div>
																	</div>
																</div>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Description</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<textarea rows="6" cols="90"
																				placeholder=" Enter Description Here . . ." required
																				name="description"></textarea>
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
																			<button
																				class="btn btn-sm btn-primary login-submit-cs"
																				type="submit">Send</button>
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
							<br>
							<div class="panel panel-default">
								<div class="panel-heading accordion-head">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse5" title="Click To Expand"> To Corporate
											Customers </a>
									</h4>
									<div id="collapse5" class="panel-collapse panel-ic collapse ">
										<br>
										<div class="sparkline12-graph">
											<div class="basic-login-form-ad">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
														<div class="all-form-element-inner">
															<form action="send_notification_corporate_project"
																method="POST">
																<div class="row">
																	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																		<label class="login2 pull-right pull-right-pro">Select
																			Project</label>
																	</div>
																	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																		<div class="form-select-list">
																			<select
																				class="select2_single form-control custom-select-value"
																				name="cproject_id" required id="cproject"
																				style="width: 325px;">
																				<option value="">Select One Project</option>
																				<c:forEach var="project" items="${cprojects}">
																					<option value="${project.projectID}">${project.projectName}</option>
																				</c:forEach>
																			</select>
																		</div>
																	</div>
																</div>
																<br>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Notification
																				Type</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<input type="text" class="form-control"
																				placeholder="Enter Notification Type" required
																				name="notification_type" />
																		</div>
																	</div>
																</div>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Title</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<input type="text" class="form-control"
																				placeholder="Enter Title" required name="title" />
																		</div>
																	</div>
																</div>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Description</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<textarea rows="6" cols="90"
																				placeholder=" Enter Description Here . . ." required
																				name="description"></textarea>
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
																			<button
																				class="btn btn-sm btn-primary login-submit-cs"
																				type="submit">Send</button>
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
							<br>
							<div class="panel panel-default">
								<div class="panel-heading accordion-head">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse7" title="Click To Expand"> Merchant Wise</a>
									</h4>
									<div id="collapse7" class="panel-collapse panel-ic collapse">
										<br>
										<div class="sparkline12-graph">
											<div class="basic-login-form-ad">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
														<div class="all-form-element-inner">
															<form action="send_notification_merchant_wise"
																method="POST">
																<div class="row">
																	<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																		<label class="login2 pull-right pull-right-pro">Select
																			Project</label>
																	</div>
																	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																		<div class="form-select-list">
																			<select
																				class="select2_single form-control custom-select-value"
																				name="project_id" required id="ncproject"
																				onchange="getMerchants()" style="width: 325px;">
																				<option value="">Select One Project</option>
																				<c:forEach var="project" items="${ncprojects}">
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
																			Merchant ID </label>
																	</div>
																	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																		<div class="form-select-list">
																			<select
																				class="select2_single form-control custom-select-value"
																				name="merchant_id" required id="merchant"
																				onchange="getMerchantName()" style="width: 325px;">
																				<option value="">Select Merchant ID</option>
																			</select>
																			<input type="hidden" name="all_merchants" id="all_merchants">
																		</div>
																	</div>
																	<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																		<div class="input-group date">
																			<input type="text" class="form-control"
																				placeholder="Merchant Name" title="Merchant Name"
																				required id="merchant_name" name="merchant_name"
																				disabled>
																		</div>
																	</div>
																</div>
																<br>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Notification
																				Type</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<input type="text" class="form-control"
																				placeholder="Enter Notification Type" required
																				name="notification_type" />
																		</div>
																	</div>
																</div>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Title</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<input type="text" class="form-control"
																				placeholder="Enter Title" required name="title" />
																		</div>
																	</div>
																</div>
																<div class="form-group-inner">
																	<div class="row">
																		<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																			<label class="login2 pull-right pull-right-pro">Description</label>
																		</div>
																		<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
																			<textarea rows="6" cols="90"
																				placeholder=" Enter Description Here . . ." required
																				name="description"></textarea>
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
																			<button
																				class="btn btn-sm btn-primary login-submit-cs"
																				type="submit">Send</button>
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

				</div>
			</div>
		</div>
	</div>
</div>
<!-- Basic Form End -->
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	function getMerchants() {

		var project = document.getElementById("ncproject");
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
						var option1 = document.createElement('option');
						option1.text = "Select All Merchants";
						option1.value = projectID;
						merchant.add(option1, 1);
						for (var i = 0; i < response.length; i++) {
							var option = document.createElement('option');
							option.text = response[i].merchantID;
							option.value = response[i].merchantID;
							merchant.add(option, i + 2);
						}

					},
					error : function(response) {
						var merchant = document.getElementById("merchant");
						merchant.options.length = 0;
						var option = document.createElement('option');
						option.text = "Please Select Project First";
						option.value = "";
						merchant.add(option, 0);
						document.getElementById("merchant_name").value = "";
					}
				});
	}

	function getMerchantName() {
		var merchant = document.getElementById("merchant");
		var merchantID = merchant.options[merchant.selectedIndex].value;
		var project = document.getElementById("ncproject");
		var projectID = project.options[project.selectedIndex].value;
		if (merchantID != "" && merchantID != projectID) {
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
										document.getElementById("merchant_name").value = name;
										document.getElementById("all_merchants").value = false;
									}, 20);
						},
						error : function(response) {
							console.log(response);
							document.getElementById("merchant_name").value = "";
						}
					});
		} else {
			if (merchantID == projectID) {
				var projectName = project.options[project.selectedIndex].text;
				document.getElementById("all_merchants").value = true;
				document.getElementById("merchant_name").value = projectName;
			} else {
				document.getElementById("merchant_name").value = "";
			}

		}

	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".select2_single").select2({
			allowClear : true
		});
	});
</script>