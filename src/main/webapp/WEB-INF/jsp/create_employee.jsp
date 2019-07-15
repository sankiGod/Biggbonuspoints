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
								<li><span class="bread-blod">Employee</span></li>
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
							<h1>Create Employee</h1>
						</div>
						<div class="add-product">
							<a href="employee_list">Back To Employee List</a>
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
									<form action="add_employee" method="POST">
										<div class="all-form-element-inner">
											<div class="row">
												<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
													<div class="sparkline16-list responsive-mg-b-30">
														<div class="sparkline16-graph">
															<div class="date-picker-inner">
																<div class="form-group data-custon-pick">
																	<label>Employee Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-user"></i></span> <input type="text"
																			class="form-control" name="employee_name"
																			value="${employee.employeeName}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Email ID</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-envelope"></i></span> <input type="text"
																			class="form-control" name="email_id"
																			value="${employee.emailId}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Aadhaar Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-book"></i></span> <input type="text"
																			class="form-control" name="adhaar_number"
																			value="${employee.aadharNumber}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>House No</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="house_no"
																			value="${employee.address.houseNo}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Lane Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="lane_name"
																			value="${employee.address.laneName}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Area Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="area_name"
																			value="${employee.address.areaName}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<c:if test="${empty employee.address.state}">
																		<select
																			onchange="print_city('state', this.selectedIndex);"
																			id="sts" name="state"
																			class=" select2_single form-control" required>
																		</select>
																	</c:if>
																	<c:if test="${!empty employee.address.state}">
																		<select name="state" class="form-control" required>
																			<option value="${employee.address.state}" selected>${employee.address.state}</option>

																		</select>
																	</c:if>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Country</label>
																	<div class="input-group date">
																		<c:if test="${empty employee.address.country}">
																			<span class="input-group-addon"><i
																				class="fa fa-home"></i></span>
																			<input type="text" class="form-control"
																				name="country" value="India" required disabled>
																			<input type="hidden" name="country" title="Country"
																				required value="India">
																		</c:if>
																		<c:if test="${!empty employee.address.country}">
																			<span class="input-group-addon"><i
																				class="fa fa-home"></i></span>
																			<input type="text" class="form-control"
																				name="country" value="${employee.address.country}"
																				required disabled>
																			<input type="hidden" name="country" title="Country"
																				required value="${employee.address.country}">
																		</c:if>
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
															<c:if test="${!empty employee.employeeID}">
																<div class="form-group data-custon-pick">
																	<label>Employee ID</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-user"></i></span> <input type="text"
																			class="form-control" name="employee_id"
																			value="${employee.employeeID}" disabled>
																	</div>
																</div>
															</c:if>
																<div class="form-group data-custon-pick">
																	<label>User Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-user"></i></span> <input type="text"
																			class="form-control" name="username"
																			value="${employee.username}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Mobile Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-mobile"></i></span> <input type="text"
																			class="form-control" name="mobile_number"
																			value="${employee.mobileNumber}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Pan Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-book"></i></span> <input type="text"
																			class="form-control" name="pan_number"
																			value="${employee.panNumber}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Lane Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="lane_no"
																			value="${employee.address.laneNo}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>LandMark</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="landmark"
																			value="${employee.address.landmark}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>PinCode</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="pincode"
																			value="${employee.emailId}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<c:if test="${!empty employee.address.city}">
																		<select id="state" name="city" class="form-control"
																			required>
																			<option value="${employee.address.city}" selected>${employee.address.city}</option>
																		</select>

																	</c:if>
																	<c:if test="${empty employee.address.city}">
																		<select id="state" name="city"
																			class="select2_single form-control" required>
																			<option value="">Select City</option>
																		</select>
																	</c:if>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-5"></div>
												<div class="col-lg-7">
													<div class="login-horizental cancel-wp pull-left">
														<a href="employee_list"><button
																class="btn btn-sm btn-danger" type="button">Cancel</button></a>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit">Submit</button>
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