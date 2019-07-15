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
								<li><span class="bread-blod">Login Page Info</span></li>
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
							<h1>Login Page Info</h1>
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
										<form action="update_login_page_info" method="POST">
											<div class="row">
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
													<div class="sparkline16-list responsive-mg-b-30">
														<div class="sparkline16-graph">
															<div class="date-picker-inner">
																<div class="form-group data-custon-pick">
																	<label>Sales Contact Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="sales_contact_name"
																			id="sales_contact_name"
																			placeholder="Click Edit And Enter Sales Contact Name"
																			value="${info.salesContactName}" required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Sales Mobile Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="sales_mobile_number"
																			id="sales_mobile_number"
																			placeholder="Click Edit And Enter Sales Mobile Number"
																			value="${info.salesMobileNumber}" required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Sales Email ID</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="sales_email_id"
																			id="sales_email_id"
																			placeholder="Click Edit And Enter Sales Email ID"
																			value="${info.salesEmailId}" required disabled>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<br>

											<div class="row">
												<div class="col-lg-2"></div>
												<div class="col-lg-10">
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
		document.getElementById("sales_contact_name").disabled = false;
		document.getElementById("sales_mobile_number").disabled = false;
		document.getElementById("sales_email_id").disabled = false;
	}
</script>