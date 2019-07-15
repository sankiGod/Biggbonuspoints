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
								<li><span class="bread-blod">Bonus Points Account</span></li>
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
							<h1>Bonus Point Account</h1>
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
										<form action="update_account_info" method="POST">
											<div class="row">
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
													<div class="sparkline16-list responsive-mg-b-30">
														<div class="sparkline16-graph">
															<div class="date-picker-inner">
																<div class="form-group data-custon-pick">
																	<label>Account Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="account_number"
																			placeholder="Click Edit And Enter Account Number"
																			id="account_number" value="${account.accountNumber}"
																			required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Payable To</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="payable_to"
																			placeholder="Click Edit And Enter Payable To"
																			id="payable_to" value="${account.payableTo}" required
																			disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Bank Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="bank_name"
																			placeholder="Click Edit And Enter Bank Name"
																			id="bank_name" value="${account.bankName}" required
																			disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>City</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="city"
																			placeholder="Click Edit And Enter City"
																			id="city" value="${account.city}" required disabled>
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
																	<label>Account Type</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="account_type"
																			placeholder="Click Edit And Enter Account Type"
																			id="account_type" value="${account.accountType}"
																			required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>IFSC Code</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="ifsc_code"
																			placeholder="Click Edit And Enter IFSC Code"
																			id="ifsc_code" value="${account.ifscCode}" disabled
																			required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Branch Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="branch_name"
																			placeholder="Click Edit And Enter Branch Name"
																			id="branch_name" value="${account.branchName}"
																			required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>PinCode</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="pincode"
																			placeholder="Click Edit And Enter PinCode"
																			id="pincode" value="${account.pincode}" required
																			disabled>
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
														<button class="btn btn-sm btn-info login-submit-cs"
															type="button" onclick="changeSave()">Edit</button>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit" id="submit" disabled>Save</button>
													</div>
												</div>
											</div>
										</form>
										<br>
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
		document.getElementById("submit").disabled = false;
		document.getElementById("account_number").disabled = false;
		document.getElementById("payable_to").disabled = false;
		document.getElementById("bank_name").disabled = false;
		document.getElementById("city").disabled = false;
		document.getElementById("account_type").disabled = false;
		document.getElementById("ifsc_code").disabled = false;
		document.getElementById("branch_name").disabled = false;
		document.getElementById("pincode").disabled = false;
	}
</script>