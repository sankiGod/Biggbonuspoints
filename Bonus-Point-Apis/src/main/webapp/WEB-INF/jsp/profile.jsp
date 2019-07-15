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
								<li><span class="bread-blod">Profile</span></li>
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
							<h1>Employee Profile</h1>
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
																		id="employee_name"
																		value="${sessionScope.user.employeeName}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Email ID</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-envelope"></i></span> <input type="text"
																		class="form-control" name="email_id" id="email_id"
																		value="${sessionScope.user.emailId}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Aadhaar Number</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-book"></i></span> <input type="text"
																		class="form-control" name="adhaar_number"
																		id="adhaar_number"
																		value="${sessionScope.user.aadharNumber}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>House No</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" name="house_no" id="house_no"
																		value="${sessionScope.user.address.houseNo}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Lane Name</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" name="lane_name" id="lane_name"
																		value="${sessionScope.user.address.laneName}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Area Name</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" name="area_name" id="area_name"
																		value="${sessionScope.user.address.areaName}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>State</label> <select name="state"
																	class="form-control" disabled>
																	<option value="${sessionScope.user.address.state}"
																		selected>${sessionScope.user.address.state}</option>

																</select>
															</div>
															<div class="form-group data-custon-pick">
																<label>Country</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" name="country" value="India"
																		required disabled>
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
																		id="employee_id"
																		value="${sessionScope.user.employeeID}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>User Name</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-user"></i></span> <input type="text"
																		class="form-control" name="username" id="username"
																		value="${sessionScope.user.username}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Mobile Number</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-mobile"></i></span> <input type="text"
																		class="form-control" name="mobile_number"
																		id="mobile_number"
																		value="${sessionScope.user.mobileNumber}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Pan Number</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-book"></i></span> <input type="text"
																		class="form-control" name="pan_number" id="pan_number"
																		value="${sessionScope.user.panNumber}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>Lane Number</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" name="lane_no" id="lane_no"
																		value="${sessionScope.user.address.laneNo}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>LandMark</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" name="landmark" id="landmark"
																		value="${sessionScope.user.address.landmark}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>PinCode</label>
																<div class="input-group date">
																	<span class="input-group-addon"><i
																		class="fa fa-home"></i></span> <input type="text"
																		class="form-control" name="pincode" id="pincode"
																		value="${sessionScope.user.emailId}" disabled>
																</div>
															</div>
															<div class="form-group data-custon-pick">
																<label>City</label> <select id="state1" name="city"
																	class="form-control" required disabled>
																	<option value="${sessionScope.user.address.city}"
																		selected>${sessionScope.user.address.city}</option>
																</select>
															</div>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-1"></div>
											<div class="col-lg-11">
												<div class="login-horizental cancel-wp pull-left">
													<a href="dashboard"><button
															class="btn btn-sm btn-danger" type="button">Cancel</button></a>
													<button class="btn btn-sm btn-info login-submit-cs"
														type="button" onclick="getForm()">Change Password</button>
												</div>
											</div>
										</div>
									</div>
									<br>
									<form action="change_password" method="POST"
										id="change_password" style="display: none;">
										<div class="all-form-element-inner">
											<div class="row">
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="sparkline16-graph">
														<div class="form-group data-custon-pick">
															<label>Old Password</label>
															<div class="input-group date">
																<span class="input-group-addon"><i
																	class="fa fa-key"></i></span> <input type="text"
																	class="form-control" name="old_password"
																	id="old_password"> <input type="hidden"
																	name="employee_id"
																	value="${sessionScope.user.employeeID}">
															</div>
														</div>
														<div class="form-group data-custon-pick">
															<label>New Password</label>
															<div class="input-group date">
																<span class="input-group-addon"><i
																	class="fa fa-key"></i></span> <input type="text"
																	class="form-control" name="new_password"
																	id="new_password">
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-1"></div>
												<div class="col-lg-11">
													<div class="login-horizental cancel-wp pull-left">
														<button class="btn btn-sm btn-danger" type="button"
															onclick="hideForm()">Cancel</button>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit">Change</button>
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
	function getForm() {
		var x = document.getElementById("change_password");
		x.style.display = "block";
	}

	function hideForm() {
		var x = document.getElementById("change_password");
		x.style.display = "none";
	}
</script>