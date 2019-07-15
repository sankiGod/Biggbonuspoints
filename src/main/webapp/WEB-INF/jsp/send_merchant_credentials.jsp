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
								<li><span class="bread-blod">Send Merchant
										Credentials</span></li>
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
				<div class="sparkline12-list" style="padding: 40px;">
					<div class="sparkline12-hd">
						<div class="main-sparkline12-hd">
							<h1>Send Merchant Credentials</h1>
						</div>
						<div class="col-lg-3"></div>
						<div class="col-lg-9">
							<h4 style="color: green">${successMessage}</h4>
							<h4 style="color: red">${errorMessage}</h4>
						</div>
					</div>
					<div class="sparkline12-graph">
						<div class="basic-login-form-ad">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="all-form-element-inner">
										<form action="sendMerchantCredentials" method="POST">
											<div class="row">
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<label class="login2 pull-right pull-right-pro">Select
														Project</label>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="form-select-list">
														<select class="select2_single form-control custom-select-value"
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
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<label class="login2 pull-right pull-right-pro">Select
														Merchant ID</label>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="form-select-list">
														<select class="select2_single form-control custom-select-value"
															name="merchant_id" required id="merchant"
															onchange="getTerminals()">
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
															name="merchant_name" disabled>
													</div>
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<label class="login2 pull-right pull-right-pro">Select
														Terminal ID</label>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="form-select-list">
														<select class="select2_single form-control custom-select-value"
															name="terminal_id" required id="terminal">
															<option value="">Select Terminal ID</option>
														</select>
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
<!-- Basic Form End -->
<script type="text/javascript">
	function getMerchants() {

		var project = document.getElementById("project");
		var projectID = project.options[project.selectedIndex].value;
		$.ajax({
			url : "${pageContext.request.contextPath}/merchants/getByProjectId/" + projectID,
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
				var terminal = document.getElementById("terminal");
				terminal.options.length = 0;
				var option1 = document.createElement('option');
				option1.text = "Select Terminal ID";
				option1.value = null;
				terminal.add(option1, 0);
			},
			error : function(response) {
				var merchant = document.getElementById("merchant");
				merchant.options.length = 0;
				var option = document.createElement('option');
				option.text = "Please Select Project First";
				option.value = "";
				merchant.add(option, 0);
			}
		});
	}
	function getTerminals() {

		var merchant = document.getElementById("merchant");
		var merchantID = merchant.options[merchant.selectedIndex].value;
		if(merchantID != ""){
			$.ajax({
				url : "${pageContext.request.contextPath}/merchants/getByMerchantId/"
						+ merchantID,
				type : "get", //send it through get method
				success : function(response) {
					console.log(response);
					var name = response.contactPerson;
					console.log(name);
					setTimeout(function() {
						document.getElementById("merchant_name").value = name;
						}, 20);
				},
				error : function(response) {
					console.log(response);
					document.getElementById("merchant_name").value = "";
				}
			});
		$.ajax({
			url : "${pageContext.request.contextPath}/terminals/getByMerchantId/" + merchantID,
			type : "get", //send it through get method
			success : function(response) {
				console.log(response);
				var terminal = document.getElementById("terminal");
				terminal.options.length = 0;
				var option = document.createElement('option');
				option.text = "Select Terminal ID";
				terminal.add(option, 0);
				for (var i = 0; i < response.length; i++) {
					var option = document.createElement('option');
					option.text = response[i].terminalID;
					option.value = response[i].terminalID;
					terminal.add(option, i + 1);
				}
			},
			error : function(response) {
				var terminal = document.getElementById("terminal");
				terminal.options.length = 0;
				var option = document.createElement('option');
				option.text = "Please Select Merchant First";
				terminal.add(option, 0);
			}
		});
		} else {
			document.getElementById("merchant_name").value = "";
			var terminal = document.getElementById("terminal");
			terminal.options.length = 0;
			var option1 = document.createElement('option');
			option1.text = "Select Terminal ID";
			option1.value = null;
			terminal.add(option1, 0);
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