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
								<li><span class="bread-blod">Edit Merchant</span></li>
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
						<div class="add-product ">
							<a href="merchant_onboarding">Back To Merchant List</a>
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
										<form action="edit_merchant" method="POST">
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
																			class="form-control" value="${merchant.projectID}"
																			required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Project Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-bank"></i></span> <input type="text"
																			class="form-control" name="project_name"
																			value="${projectName}" disabled> <input
																			type="hidden" name="project_id"
																			value="${merchant.projectID}"> <input
																			type="hidden" name="mId" value="${merchant.mId}">
																		<input type="hidden" name="merchant_id"
																			value="${merchant.merchantID}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Mobile Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-mobile"></i></span> <input type="text"
																			class="form-control" name="mobile_number"
																			value="${merchant.mobileNumber}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>GST Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-book"></i></span> <input type="text"
																			class="form-control" name="gst_number"
																			value="${merchant.gstNumber}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>PAN Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-book"></i></span> <input type="text"
																			class="form-control" name="pan_number"
																			value="${merchant.panNumber}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Legal Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-shopping-cart"></i></span> <input type="text"
																			class="form-control" name="legal_name"
																			value="${merchant.legalName}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Lane No</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="lane_no"
																			value="${merchant.address.laneNo}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Area Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="area_name"
																			value="${merchant.address.areaName}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>City</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="city"
																			value="${merchant.address.city}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Country</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="country"
																			value="${merchant.address.country}" required>
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
																			class="form-control" value="${merchant.merchantID}"
																			required disabled>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Contact Person</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-user"></i></span> <input type="text"
																			class="form-control" name="contact_person"
																			value="${merchant.contactPerson}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Email Id</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-envelope"></i></span> <input type="text"
																			class="form-control" name="email_id"
																			value="${merchant.emailId}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Aadhaar Number</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-book"></i></span> <input type="text"
																			class="form-control" name="aadhar_number"
																			value="${merchant.aadharNumber}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Shop Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-shopping-cart"></i></span> <input type="text"
																			class="form-control" name="shop_name"
																			value="${merchant.shopName}" required>
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Shop No</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="shop_no"
																			value="${merchant.address.shopNo}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Lane Name</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="lane_name"
																			value="${merchant.address.laneName}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>Land Mark</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="landmark"
																			value="${merchant.address.landmark}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>State</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="state"
																			value="${merchant.address.state}">
																	</div>
																</div>
																<div class="form-group data-custon-pick">
																	<label>PinCode</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-home"></i></span> <input type="text"
																			class="form-control" name="pincode"
																			value="${merchant.address.pin}" required>
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
														<a href="merchant_onboarding"><button
																class="btn btn-sm btn-danger" type="button">Cancel</button></a>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit">Save</button>
													</div>
												</div>
											</div>
										</form>
										<br>
										<div class="row">
											<div class="col-lg-2 col-md-2 col-sm-2 col-xs-12">
												<div class="main-sparkline12-hd">
													<h1>Terminals</h1>
												</div>
											</div>
											<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
												<div class="table-responsive">
													<table id="terminaltable"
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
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
												<div class="login-horizental cancel-wp pull-left ">
													<button class="btn btn-md btn-warning" type="button" title="Click To Add Terminal"
														onclick="addTerminal('${merchant.projectID}','${merchant.merchantID}')">Add
														Terminal</button>
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
<script type="text/javascript">
	function addTerminal(pid,mid) {
		$.ajax({
			  url: "${pageContext.request.contextPath}/terminals/create",
			  type: "post", //send it through get method
			  data: { 
				  projectID: pid, 
				  merchantID: mid
			  },
			  success: function(response) {
				  var table = document.getElementById("terminaltable");
				  var rows = document.getElementById("terminaltable").getElementsByTagName("tr").length;
				  var row = table.insertRow(rows);
				  var cell1 = row.insertCell(0);
				  var cell2 = row.insertCell(1);
				  var cell3 = row.insertCell(2);
				  var cell4 = row.insertCell(3);
				  cell1.innerHTML = rows;
				  cell2.innerHTML = response.terminalID;
				  cell3.innerHTML = response.password;
				  cell4.innerHTML = response.pin;
				  console.log(response);
			  },
			  error: function(xhr) {
			    console.log(response)
			  }
			});
	}
</script>
<%@ include file="footer.jsp"%>
