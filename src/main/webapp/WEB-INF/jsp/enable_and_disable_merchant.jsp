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
								<li><span class="bread-blod">Change Merchant Status</span></li>
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
							<h1>Change Merchant Status</h1>
						</div>
						<div class="add-product">
							<a href="merchant_onboarding">Go To Merchant List</a>
						</div>
					</div>
					<br>
					<div class="sparkline12-graph">
						<div class="basic-login-form-ad">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="all-form-element-inner">
										<div class="row">
											<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
												<label class="login2 pull-right pull-right-pro ">Select
													Project</label>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
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
										</div>
										<br>
										<div class="row">
											<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
												<label class="login2 pull-right pull-right-pro ">Select
													Merchant ID</label>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
												<div class="form-select-list">
													<select
														class="select2_single form-control custom-select-value"
														name="merchant_id" required id="merchant"
														onchange="getStatus()">
														<option value="">Select Merchant ID</option>
													</select>
												</div>
											</div>
										</div>
										<br>
										<div class="row">
											<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
												<label class="login2 pull-right pull-right-pro"></label>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
												<div class="input-group date">
													<span class="input-group-addon"><i
														class="fa fa-user"></i></span> <input type="text"
														class="form-control" placeholder="Merchant Name"
														title="Merchant Name" required id="merchant_name"
														name="merchant_name" disabled>
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
													<button class="btn btn-sm btn-info login-submit-cs"
														type="button" id="change_status" onclick="changeStatus()"
														disabled>Status</button>
													<!-- <button class="btn btn-sm btn-warning login-submit-cs"
														type="button" disabled id="disable"
														onclick="disableMerchant()">Disable</button> -->
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

		var projectID = $("#project").val();
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
						option.value = "0";
						$('#change_status').val('Status');
						$('#change_status').text('Status');
						$('#change_status').attr("disabled", true);
						$('#change_status').removeClass("btn-warning")
								.addClass("btn-info");
						merchant.add(option, 0);
						for (var i = 0; i < response.length; i++) {
							var option = document.createElement('option');
							option.text = response[i].merchantID;
							option.value = response[i].merchantID;
							merchant.add(option, i + 1);
						}
						document.getElementById("merchant_name").value = "";

					},
					error : function(response) {
						var merchant = document.getElementById("merchant");
						merchant.options.length = 0;
						var option = document.createElement('option');
						option.text = "Please Select Project First";
						option.value = "0";
						merchant.add(option, 0);
						$('#change_status').val('Status');
						$('#change_status').text('Status');
						$('#change_status').attr("disabled", true);
						$('#change_status').removeClass("btn-warning")
								.addClass("btn-info");
					}
				});
	}

	function getStatus() {

		var merchant = document.getElementById("merchant");
		var merchantID = merchant.options[merchant.selectedIndex].value;
		if (merchantID != "0") {
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
									}, 20);
						},
						error : function(response) {
							console.log(response);
							document.getElementById("merchant_name").value = "";
						}
					});

			$.ajax({
				url : "${pageContext.request.contextPath}/merchants/getStatus/"
						+ merchantID,
				type : "get", //send it through get method
				success : function(response) {
					console.log(response);
					if (response == true) {
						console.log("true");
						$('#change_status').val('Disable');
						$('#change_status').text('Disable');
						$('#change_status').attr("disabled", false);
						$('#change_status').removeClass("btn-info")
								.removeClass("btn-success").addClass(
										"btn-warning");
					} else {
						console.log("fasle");
						$('#change_status').val('Enable');
						$('#change_status').text('Enable');
						$('#change_status').attr("disabled", false);
						$('#change_status').removeClass("btn-info")
								.removeClass("btn-warning").addClass(
										"btn-success");
					}
				},
				error : function(response) {
					console.log(response);
				}
			});
		} else {
			document.getElementById("merchant_name").value = "";
			$('#change_status').val('Status');
			$('#change_status').text('Status');
			$('#change_status').attr("disabled", true);
			$('#change_status').removeClass("btn-warning").addClass("btn-info");
		}
	}

	function changeStatus() {
		var merchant = document.getElementById("merchant");
		var merchantID = merchant.options[merchant.selectedIndex].value;

		var change_status = $('#change_status').val();
		var url;
		if (change_status === "Enable") {

			url = "${pageContext.request.contextPath}/merchants/enableMerchant/";

		} else {

			url = "${pageContext.request.contextPath}/merchants/disableMerchant/";
		}
		$.ajax({
			url : url + merchantID,
			type : "get", //send it through get method
			success : function(response) {
				console.log(response);
				if (change_status === "Enable") {

					$('#change_status').val('Disable');
					$('#change_status').text('Disable');
					$('#change_status').attr("disabled", false);
					$('#change_status').removeClass("btn-success").addClass(
							"btn-warning");
				} else {
					$('#change_status').val('Enable');
					$('#change_status').text('Enable');
					$('#change_status').attr("disabled", false);
					$('#change_status').removeClass("btn-warning").addClass(
							"btn-success");
				}

			},
			error : function(response) {
				console.log(response);
			}
		});

	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".select2_single").select2({
			allowClear : true
		});
	});
</script>