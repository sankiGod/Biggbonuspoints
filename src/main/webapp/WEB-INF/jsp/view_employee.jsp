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
							<h1>Employee Details</h1>
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
									<form action="edit_employee" method="POST">
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
																			id="employee_name" value="${employee.employeeName}"
																			disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Email ID</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-envelope"></i></span> <input type="text"
																			class="form-control" name="email_id" id="email_id"
																			value="${employee.emailId}" disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Aadhaar Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-book"></i></span> <input type="text"
																			class="form-control" name="adhaar_number"
																			id="adhaar_number" value="${employee.aadharNumber}"
																			disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>House No</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="house_no" id="house_no"
																			value="${employee.address.houseNo}" disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Lane Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="lane_name" id="lane_name"
																			value="${employee.address.laneName}" disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Area Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="area_name" id="area_name"
																			value="${employee.address.areaName}" disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																<label>State</label>
																	<div id="select_state" style="display: none">
																		<select
																			onchange="print_city('state', this.selectedIndex);"
																			id="sts" name="state" style="width: 390px;"
																			class=" select2_single form-control" required>
																		</select>
																	</div>
																	<div id="view_state">
																		<select name="state" class="form-control" disabled>
																			<option value="${employee.address.state}" selected>${employee.address.state}</option>

																		</select>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Country</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="country" value="India"
																			required disabled> <input type="hidden"
																			name="country" title="Country" required value="India">
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
																	<label>Employee ID</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-user"></i></span> <input type="text"
																			class="form-control" name="employee_id"
																			id="employee_id" value="${employee.employeeID}"
																			disabled> <input type="hidden"
																			name="employee_id" title="Country" required
																			value="${employee.employeeID}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>User Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-user"></i></span> <input type="text"
																			class="form-control" name="username" id="username"
																			value="${employee.username}" disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Mobile Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-mobile"></i></span> <input type="text"
																			class="form-control" name="mobile_number"
																			id="mobile_number" value="${employee.mobileNumber}"
																			disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Pan Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-book"></i></span> <input type="text"
																			class="form-control" name="pan_number"
																			id="pan_number" value="${employee.panNumber}"
																			disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Lane Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="lane_no" id="lane_no"
																			value="${employee.address.laneNo}" disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>LandMark</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="landmark" id="landmark"
																			value="${employee.address.landmark}" disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>PinCode</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="pincode" id="pincode"
																			value="${employee.emailId}" disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																<label>City</label>
																	<div id="view_city">
																		<select id="state1" name="city" class="form-control"
																			required disabled >
																			<option value="${employee.address.city}" selected>${employee.address.city}</option>
																		</select>

																	</div>
																	<div id="select_city" style="display: none">
																		<select id="state" name="city"
																			class="select2_single form-control" style="width: 390px;" required>
																			<option value="">Select City</option>
																		</select>
																	</div>
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
														<button class="btn btn-sm btn-danger login-submit-cs"
															type="button" onclick="cancelSave()">Cancel</button>
														<button class="btn btn-sm btn-info login-submit-cs"
															type="button" onclick="changeSave()">Edit</button>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit" id="submit" disabled>Save</button>
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
<script type="text/javascript">
function changeSave() {
	document.getElementById("submit").disabled = false;
	document.getElementById("employee_name").disabled = false;
	document.getElementById("pincode").disabled = false;
	document.getElementById("landmark").disabled = false;
	document.getElementById("lane_no").disabled = false;
	document.getElementById("pan_number").disabled = false;
	document.getElementById("mobile_number").disabled = false;
	document.getElementById("username").disabled = false;
	document.getElementById("lane_name").disabled = false;
	document.getElementById("area_name").disabled = false;
	document.getElementById("house_no").disabled = false;
	document.getElementById("adhaar_number").disabled = false;
	document.getElementById("email_id").disabled = false;
	document.getElementById("select_city").style.display = "block";
	document.getElementById("view_city").style.display = "none";
	document.getElementById("select_state").style.display = "block";
	document.getElementById("view_state").style.display = "none";
}
function cancelSave() {
	document.getElementById("submit").disabled = true;
	document.getElementById("employee_name").disabled = true;
	document.getElementById("pincode").disabled = true;
	document.getElementById("landmark").disabled = true;
	document.getElementById("lane_no").disabled = true;
	document.getElementById("pan_number").disabled = true;
	document.getElementById("mobile_number").disabled = true;
	document.getElementById("username").disabled = true;
	document.getElementById("lane_name").disabled = true;
	document.getElementById("area_name").disabled = true;
	document.getElementById("house_no").disabled = true;
	document.getElementById("adhaar_number").disabled = true;
	document.getElementById("email_id").disabled = true;
	document.getElementById("select_city").style.display = "none";
	document.getElementById("view_city").style.display = "block";
	document.getElementById("select_state").style.display = "none";
	document.getElementById("view_state").style.display = "block";
}
</script>