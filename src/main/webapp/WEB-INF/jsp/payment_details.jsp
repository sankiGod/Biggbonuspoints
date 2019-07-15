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
								<li><span class="bread-blod">Payment Details</span></li>
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
							<h1>Payment Details</h1>
						</div>
					</div>
					<br>
					<div class="sparkline12-graph">
						<div class="basic-login-form-ad">
							<div class="row">
								<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
									<div class="all-form-element-inner">
										<div class="row">
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
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
												<div class="form-select-list">
													<select
														class=" select2_single form-control custom-select-value"
														name="merchant_id" required id="merchant"
														onchange="getMapping()">
														<option value="0">Select Merchant ID</option>
													</select>
												</div>
											</div>

											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
												<div class="input-group date">
													<input type="text" class="form-control"
														placeholder="Merchant Name" title="Merchant Name" required
														id="merchant_name" name="merchant_name" disabled>
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<br> <br>
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="table-responsive">
										<table class="table table-striped table-hover" id="payment">
											<thead class="thead-dark">
												<tr>
													<th>#</th>
													<th>Cheque Number</th>
													<th>Cheque Date</th>
													<th>Bank Name</th>
													<th>City</th>
													<th>Cheque Amount</th>
												</tr>
											</thead>
											<tbody id="paymentbody">

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
						var merchant = document.getElementById("merchant");
						merchant.options.length = 0;
						var option = document.createElement('option');
						option.text = "Select Merchant ID";
						option.value = "0";
						merchant.add(option, 0);
						for (var i = 0; i < response.length; i++) {
							var option = document.createElement('option');
							option.text = response[i].merchantID;
							option.value = response[i].merchantID;
							merchant.add(option, i + 1);
						}

						document.getElementById("merchant_name").value = "";
						$("#paymentbody").empty();
						$('#errorMessage').text("");

					},
					error : function(response) {
						var merchant = document.getElementById("merchant");
						merchant.options.length = 0;
						var option = document.createElement('option');
						option.text = "Please Select Project First";
						option.value = "0";
						merchant.add(option, 0);
						document.getElementById("merchant_name").value = "";
						$('#errorMessage').text("");
						$("#paymentbody").empty();
					}
				});
	}

	function getMapping() {

		var merchant = document.getElementById("merchant");
		var merchantID = merchant.options[merchant.selectedIndex].value;
		if (merchantID != "0") {
			$
					.ajax({
						url : "${pageContext.request.contextPath}/merchants/getByMerchantId/"
								+ merchantID,
						type : "get", //send it through get method
						success : function(response) {
							var name = response.contactPerson;
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

			$
					.ajax({
						url : "${pageContext.request.contextPath}/admin/limit/getTransactionByMerchantId/"
								+ merchantID,
						type : "get", //send it through get method
						success : function(response) {
							$("#paymentbody").empty();
							$('#errorMessage').text("");
							var len = response.length;
							var i = 0;
							var cn = "";
							for (i = 0; i <= len; i++) {
								cn = response[i].chequeNumber + "";
								$('#payment').append(
										'<tr><td>' + (i + 1) + '</td><td>' + cn
												+ '</td><td>'
												+ response[i].chequeDate
												+ '</td><td>'
												+ response[i].bankName
												+ '</td><td>'
												+ response[i].city
												+ '</td><td>'
												+ response[i].actualTopupPaid
												+ '</td></tr>');
							}

						},
						error : function(response) {
							console.log(response);
							$("#paymentbody").empty();
							var msg = response.responseJSON.message;
							var mID = msg.split(":");
							$('#errorMessage').text(
									"No Payment Details Found For MID :"
											+ mID[1]);

						}
					});

		} else {
			document.getElementById("merchant_name").value = "";
			$('#errorMessage').text("");
			$("#paymentbody").empty();
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