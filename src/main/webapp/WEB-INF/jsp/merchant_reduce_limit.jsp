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
								<li><span class="bread-blod">Reduce Limit</span></li>
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
							<h1>Merchant Reduce Limit</h1>
						</div>
						<div class="add-product">
							<a href="merchant_limit_list">Go To Merchant Limit List </a>
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
										<form action="merchant_limit_reduce" method="POST">
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
															onchange="setCurrentLimit()">
															<option value="">Select Merchant</option>
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
															class="fa fa-money"></i></span> <input type="text"
															class="form-control" placeholder="Current Limit"
															title="Current Limit" required id="current_limit"
															name="current_limit" disabled> 
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
																	<label>Reduce Amount</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-money"></i></span> <input type="text"
																			class="form-control" required name="reduce_amount"
																			id="reduce_amount">
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
																	<label>Transaction ID </label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" required id="transaction_id"
																			name="transaction_id">
																	</div>
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
						document.getElementById("current_limit").value = "";

					},
					error : function(response) {
						var merchant = document.getElementById("merchant");
						merchant.options.length = 0;
						var option = document.createElement('option');
						option.text = "Please Select Project First";
						option.value = "";
						merchant.add(option, 0);
						document.getElementById("current_limit").value = "";
						
					}
				});
	}
	function setCurrentLimit(){
		var merchant = document.getElementById("merchant");
		var merchantID = merchant.options[merchant.selectedIndex].value;
		if (merchantID != "") {
			$
					.ajax({
						url : "${pageContext.request.contextPath}/admin/limit/getLimitByMerchantId/"
								+ merchantID,
						type : "get", //send it through get method
						success : function(response) {
							console.log(response);
							var limit = response.merchantLimit;
							console.log(name);
							setTimeout(
									function() {
										document
												.getElementById("current_limit").value = limit;
									}, 30);
						},
						error : function(response) {
							console.log(response);
							document.getElementById("current_limit").value = "Limit Not Found";
						}
					});
		} else {
			document.getElementById("current_limit").value = "";
		}
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