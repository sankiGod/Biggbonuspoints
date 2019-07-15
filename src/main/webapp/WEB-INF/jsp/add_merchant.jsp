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
								<li><span class="bread-blod">Add Merchant</span></li>
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
							<h1>Add Merchant</h1>
						</div>
						<div class="add-product">
							<a href="merchant_onboarding">Back To Merchant List</a>
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
										<form action="add_merchant" method="POST">
											<div class="row">
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<label class="login2 pull-right pull-right-pro"></label>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="form-select-list">
														<select class="select2_single form-control custom-select-value"
															name="project_id" required>
															<c:if test="${empty potential}">
																<c:if test="${empty merchant}">
																	<option value="">Select One Project</option>
																	<c:forEach var="project" items="${projects}">
																		<option value="${project.projectID}">${project.projectName}</option>
																	</c:forEach>
																</c:if>
																<c:if test="${empty project}">
																	<option value="${merchant.projectID}">${projectName}</option>
																</c:if>
															</c:if>
															<c:if test="${!empty potential}">
																<option value="">Select One Project</option>
																<c:forEach var="project" items="${projects}">
																	<option value="${project.projectID}">${project.projectName}</option>
																</c:forEach>
															</c:if>
														</select>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
													<div class="review-content-section">
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-user" aria-hidden="true"></i></span> <input
																type="text" class="form-control"
																placeholder="Contact Person" title="Contact Person"
																name="contact_person" required
																value="${merchant.contactPerson}">
														</div>
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-envelope" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="Email Id"
																title="Email Id" name="email_id" required
																value="${merchant.emailId}">
														</div>
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-book" aria-hidden="true"></i></span> <input
																type="text" class="form-control"
																placeholder="Aadhaar Number" title="Aadhaar Number"
																name="aadhar_number" value="${merchant.aadharNumber}">
														</div>
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-shopping-cart" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="Shop Name"
																title="Shop Name" name="shop_name" required
																value="${merchant.shopName}">
														</div>
													</div>
												</div>
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
													<div class="review-content-section">
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-mobile" aria-hidden="true"></i></span> <input
																type="text" class="form-control"
																placeholder="Mobile Number" title="Mobile Number"
																name="mobile_number" required
																value="${merchant.mobileNumber}">
														</div>
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-book" aria-hidden="true"></i></span> <input
																type="text" class="form-control"
																placeholder="GST Number" title="GST Number"
																name="gst_number" value="${merchant.gstNumber}">
														</div>
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-book" aria-hidden="true"></i></span> <input
																type="text" class="form-control"
																placeholder="Pan Number" title="Pan Number"
																name="pan_number" value="${merchant.panNumber}">
														</div>
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-shopping-cart" aria-hidden="true"></i></span> <input
																type="text" class="form-control"
																placeholder="Legal Name" title="Legal Name"
																name="legal_name" required value="${merchant.legalName}">
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<div class="review-content-section">
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-home" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="Shop No"
																name="shop_no" title="Shop No" required
																value="${merchant.address.shopNo}">
														</div>
													</div>
												</div>
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<div class="review-content-section">
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-home" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="Lane No"
																name="lane_no" title="Lane No"
																value="${merchant.address.laneNo}">
														</div>
													</div>
												</div>
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<div class="review-content-section">
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-home" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="Lane Name"
																name="lane_name" title="Lane Name"
																value="${merchant.address.laneName}">
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
													<div class="review-content-section">
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-home" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="Area Name"
																name="area_name" title="Area Name" required
																value="${merchant.address.areaName}">
														</div>
														<div class="input-group mg-b-pro-edt">
															<c:if test="${empty merchant.address.state}">
																<span class="input-group-addon"><i
																	class="fa fa-home" aria-hidden="true"></i></span>
																<select
																	onchange="print_city('state', this.selectedIndex);"
																	id="sts" name="state"
																	class=" select2_single form-control" required>
																</select>
															</c:if>
															<c:if test="${!empty merchant.address.state}">
																<span class="input-group-addon"><i
																	class="fa fa-home" aria-hidden="true"></i></span>
																<select name="state" class="form-control" required>
																	<option value="${merchant.address.state}" selected>${merchant.address.state}</option>

																</select>
															</c:if>
														</div>
														<div class="input-group mg-b-pro-edt">
														<c:if test="${empty merchant.address.country}">
															<span class="input-group-addon"><i
																class="fa fa-home" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="Country"
																 title="Country" required disabled
																value="India">
																<input
																type="hidden" 
																name="country" title="Country" required
																value="India">
														</c:if>
														<c:if test="${!empty merchant.address.country}">
															<span class="input-group-addon"><i
																class="fa fa-home" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="Country"
																 title="Country" required disabled
																value="${merchant.address.country}">
																<input
																type="hidden" 
																name="country" title="Country" required
																value="${merchant.address.country}">
														</c:if>
														</div>
													</div>
												</div>
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
													<div class="review-content-section">
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-home" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="Land Mark"
																name="land_mark" title="Land Mark"
																value="${merchant.address.landmark}">
														</div>
														<div class="input-group mg-b-pro-edt">
															<c:if test="${!empty merchant.address.city}">
																<span class="input-group-addon"><i
																	class="fa fa-home" aria-hidden="true"></i></span>
																<select id="state" name="city" class="form-control"
																	required>
																	<option value="${merchant.address.city}" selected>${merchant.address.city}</option>
																</select>

															</c:if>
															<c:if test="${empty merchant.address.city}">
																<span class="input-group-addon"><i
																	class="fa fa-home" aria-hidden="true"></i></span>
																<select id="state" name="city"
																	class="select2_single form-control" required>
																	<option value="">Select City</option>
																</select>

															</c:if>
														</div>
														<div class="input-group mg-b-pro-edt">
															<span class="input-group-addon"><i
																class="fa fa-home	" aria-hidden="true"></i></span> <input
																type="text" class="form-control" placeholder="PinCode"
																name="pincode" title="PinCode" required
																value="${merchant.address.pin}">
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-4"></div>
												<div class="col-lg-8">
													<div class="login-horizental cancel-wp pull-left">
														<a href="merchant_onboarding"><button
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
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	print_state("sts");
	$(document).ready(function() {
		$(".select2_single").select2({
			allowClear : true
		});
	});
</script>