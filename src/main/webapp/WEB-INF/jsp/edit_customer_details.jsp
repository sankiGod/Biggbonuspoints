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
								<li><span class="bread-blod">Customer Details</span></li>
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
							<h1>Customer Details</h1>
						</div>
						<div class="add-product">
							<a href="modify_customer_details">Back To Find Customer</a>
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
										<form action="edit_customer" method="POST">
											<div class="row">
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
													<div class="sparkline16-list responsive-mg-b-30">
														<div class="sparkline16-graph">
															<div class="date-picker-inner">
																<div class="form-group data-custon-pick">
																	<label>Customer ID</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-user"></i></span> <input type="text"
																			class="form-control" name="customerid"
																			id="customerid" value="${customer.customerID}"
																			required disabled> <input type="hidden"
																			class="form-control" name="customer_id"
																			id="customer_id" value="${customer.customerID}"
																			required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Email ID</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-archive"></i></span> <input type="text"
																			class="form-control" name="email_id" id="email_id"
																			value="${customer.emailId}" required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Aadhaar Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-archive"></i></span> <input type="text"
																			class="form-control" name="aadhar_number"
																			id="aadhar_number" value="${customer.aadharNumber}"
																			required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Password</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="password" id="password"
																			value="${customer.password}" required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Question</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa fa-tags"></i></span> <input type="text"
																			class="form-control" name="question" id="question"
																			value="${customer.question}" required disabled>
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
																	<label>Customer Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-user"></i></span> <input type="text"
																			class="form-control" name="customer_name"
																			id="customer_name" value="${customer.customerName}"
																			required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Mobile Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-archive"></i></span> <input type="text"
																			class="form-control" name="mobile_number"
																			id="mobile_number" value="${customer.mobileNumber}"
																			disabled required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Pan Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-archive"></i></span> <input type="text"
																			class="form-control" name="pan_number"
																			id="pan_number" value="${customer.panNumber}"
																			required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Pin</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="pin" id="pin"
																			value="${customer.pin}" required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Answer</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa fa-tags"></i></span> <input type="text"
																			class="form-control" name="answer" id="answer"
																			value="${customer.answer}" required disabled>
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
														<a href="modify_customer_details"><button
																class="btn btn-sm btn-danger" type="button">Cancel</button></a>
														<button class="btn btn-sm btn-info login-submit-cs"
															type="button" onclick="changeSave()">Edit</button>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit" id="submit" disabled>Save</button>
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
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	function changeSave() {
		var cId = document.getElementById("customer_id").value;
		document.getElementById("submit").disabled = false;
		document.getElementById("email_id").disabled = false;
		document.getElementById("aadhar_number").disabled = false;
		document.getElementById("customer_name").disabled = false;
		document.getElementById("mobile_number").disabled = false;
		document.getElementById("pan_number").disabled = false;
	}
</script>