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
								<li><span class="bread-blod">View Merchant</span></li>
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
							<h1>Merchant Details</h1>
						</div>
					</div>
					<br>
					<div class="sparkline12-graph">
						<div class="basic-login-form-ad">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="all-form-element-inner">

										<div class="row">
											<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
												<div class="sparkline16-list responsive-mg-b-30">
													<div class="sparkline16-graph">
														<div class="date-picker-inner">
															<div class="form-group data-custon-pick">
																<label>Project ID</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-bank"></i></span> <input type="text"
																		class="form-control" name="project_id"
																		value="${merchant.projectID}" required disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Project Name</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-bank"></i></span> <input type="text"
																		class="form-control" value="${projectName}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Mobile Number</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-mobile"></i></span> <input type="text"
																		class="form-control" value="${merchant.mobileNumber}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>GST Number</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-book"></i></span> <input type="text"
																		class="form-control" value="${merchant.gstNumber}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>PAN Number</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-book"></i></span> <input type="text"
																		class="form-control" value="${merchant.panNumber}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Legal Name</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-shopping-cart"></i></span> <input type="text"
																		class="form-control" value="${merchant.legalName}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Lane No</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control"
																		value="${merchant.address.laneNo}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Area Name</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control"
																		value="${merchant.address.areaName}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>City</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" value="${merchant.address.city}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Country</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control"
																		value="${merchant.address.country}" disabled>
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
																<label>Merchant ID</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-bank"></i></span> <input type="text"
																		class="form-control" name="merchant_id"
																		value="${merchant.merchantID}" required disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Contact Person</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-user"></i></span> <input type="text"
																		class="form-control" value="${merchant.contactPerson}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Email Id</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-envelope"></i></span> <input type="text"
																		class="form-control" value="${merchant.emailId}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Aadhaar Number</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-book"></i></span> <input type="text"
																		class="form-control" value="${merchant.aadharNumber}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Shop Name</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-shopping-cart"></i></span> <input type="text"
																		class="form-control" value="${merchant.shopName}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Shop No</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control"
																		value="${merchant.address.shopNo}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Lane Name</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control"
																		value="${merchant.address.laneName}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Land Mark</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control"
																		value="${merchant.address.landmark}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>State</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" value="${merchant.address.state}"
																		disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>PinCode</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" value="${merchant.address.pin}"
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
											<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
												<div class="main-sparkline12-hd">
													<h1>Terminals</h1>
												</div>
											</div>
											<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
												<div class="table-responsive">
													<table
														class="table table-striped table-hover table-bordered">
														<tr>
															<th>#</th>
															<th>Terminal ID</th>
															<th>Password</th>
															<th>Pin</th>
														</tr>
														<c:set var="count" value="1" scope="page" />
														<c:forEach var="terminal" items="${merchant.terminals}">
															<tr>
																<td>${count}</td>
																<td>${terminal.terminalID}</td>
																<td>${terminal.password}</td>
																<td>${terminal.pin}</td>
															</tr>
															<c:set var="count" value="${count + 1}" scope="page" />
														</c:forEach>
													</table>
												</div>
											</div>
											<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
												<div class="add-product ">
													<a href="merchant_onboarding">Back To Merchant List</a>
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