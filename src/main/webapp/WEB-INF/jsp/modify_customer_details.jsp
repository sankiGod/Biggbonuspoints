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
										<form action="get_customer" method="POST">
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<label class="login2 pull-right pull-right-pro">Select
															The Type Of Input </label>
													</div>

													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<div class="i-checks pull-left">
															<label> <input type="radio" value="email_id"
																name="input_type" required> <i></i> Email ID
															</label>
														</div>
													</div>
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<div class="i-checks pull-left">
															<label> <input type="radio" value="mobile_no"
																name="input_type" required> <i></i> Mobile No
															</label>
														</div>
													</div>
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<div class="i-checks pull-left">
															<label> <input type="radio" value="customer_id"
																name="input_type" required> <i></i> Customer ID
															</label>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<label class="login2 pull-right pull-right-pro"></label>
													</div>
													<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
														<input type="text" class="form-control"
															placeholder="Enter Here" required
															name="customer" />
													</div>
												</div>
											</div>

											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3"></div>
													<div class="col-lg-9">
														<div class="login-horizental cancel-wp pull-left">
															<a href="dashboard"><button
																	class="btn btn-sm btn-danger" type="button">Cancel</button></a>
															<button class="btn btn-sm btn-primary login-submit-cs"
																type="Submit">Submit</button>
														</div>
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