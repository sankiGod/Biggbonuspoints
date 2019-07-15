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
								<li><span class="bread-blod">Employee Roles</span></li>
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
							<h1>Assign Employee Roles</h1>
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
								<div class=" col-lg-12 col-md-12 col-sm-12 col-xs-12 ">
									<div class="all-form-element-inner">
										<form action="assign_employee_roles" method="POST">
											<div class="row">
												<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
													<label class="login2 pull-right pull-right-pro">Select
														Employee</label>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="form-select-list">
														<select
															class="select2_single form-control custom-select-value"
															required id="employee_id" name="employee_id"
															onchange="getRoles()">
															<option value="0">Select One Employee</option>
															<c:forEach var="employee" items="${employees}">
																<option value="${employee.employeeID}">${employee.employeeName}</option>
															</c:forEach>
														</select>
													</div>
												</div>
											</div>
											<br>
											<div class="row">
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="bt-df-checkbox pull-left">
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="project_onboarding" id="project_onboarding"
																		name="roles"> <i></i> Project OnBoarding
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		id="merchant_onboarding" value="merchant_onboarding"
																		name="roles"> <i></i> Merchant OnBoarding
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="merchant_send_credentials"
																		id="merchant_send_credentials" name="roles"> <i></i>
																		Merchant Send Credentials
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="merchant_map_bonus_point"
																		id="merchant_map_bonus_point" name="roles"> <i></i>
																		Merchant Map Bonus Points
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="merchant_top_up_limit"
																		id="merchant_top_up_limit" name="roles"> <i></i>
																		Merchant Limit
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="merchant_status" id="merchant_status"
																		name="roles"> <i></i> Merchant Status
																	</label>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="bt-df-checkbox pull-left">
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="merchant_login_page_info"
																		id="merchant_login_page_info" name="roles"> <i></i>
																		Merchant Login Page-Info
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="merchant_send_notification"
																		id="merchant_send_notification" name="roles">
																		<i></i> Merchant Send Notification
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="merchant_received_offers"
																		id="merchant_received_offers" name="roles"> <i></i>
																		Merchant Received Offers
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="modify_customer_details"
																		id="modify_customer_details" name="roles"> <i></i>
																		Modify Customer Details
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="customer_send_notification"
																		id="customer_send_notification" name="roles">
																		<i></i> Customer Send Notification
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="create_employee" id="create_employee"
																		name="roles"> <i></i> Create Employee
																	</label>
																</div>
															</div>
														</div>
													</div>
												</div>
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="bt-df-checkbox pull-left">
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="assign_employee_roles"
																		id="assign_employee_roles" name="roles"> <i></i>
																		Assign Employee Roles
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="payment_details" id="payment_details"
																		name="roles"> <i></i> Payment Details
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="account_info" id="account_info" name="roles">
																		<i></i> Account-Info
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox" id="reports"
																		value="reports" name="roles"> <i></i> Reports
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="helpdesk_action" id="helpdesk_action"
																		name="roles"> <i></i> HelpDesk Action
																	</label>
																</div>
															</div>
														</div>
														<div class="row">
															<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
																<div>
																	<label> <input type="checkbox"
																		value="email_settings" id="email_settings"
																		name="roles"> <i></i> Email Settings
																	</label>
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
														<a href="dashboard"><button
																class="btn btn-sm btn-danger" type="button">Cancel</button></a>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit">Save</button>
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
	function getRoles() {

		var employee = document.getElementById("employee_id");
		var employeeID = employee.options[employee.selectedIndex].value;

		if (employeeID != "0") {
			$
					.ajax({
						url : "${pageContext.request.contextPath}/employees/getRolesByEmployeeId/"
								+ employeeID,
						type : "get", //send it through get method
						success : function(response) {
							console.log(response);
							if (response.accountInfo == true)
								document.getElementById("account_info").checked = true;
							else
								document.getElementById("account_info").checked = false;
							if (response.assignEmployeeRoles == true)
								document.getElementById("assign_employee_roles").checked = true;
							else
								document.getElementById("assign_employee_roles").checked = false;
							if (response.createEmployee == true)
								document.getElementById("create_employee").checked = true;
							else
								document.getElementById("create_employee").checked = false;
							if (response.customerSendNotification == true)
								document.getElementById("customer_send_notification").checked = true;
							else
								document.getElementById("customer_send_notification").checked = false;
							if (response.emailSettings == true)
								document.getElementById("email_settings").checked = true;
							else
								document.getElementById("email_settings").checked = false;
							if (response.helpdeskAction == true)
								document.getElementById("helpdesk_action").checked = true;
							else
								document.getElementById("helpdesk_action").checked = false;
							if (response.merchantLoginPageInfo == true)
								document.getElementById("merchant_login_page_info").checked = true;
							else
								document.getElementById("merchant_login_page_info").checked = false;
							if (response.merchantMapBonusPoint == true)
								document.getElementById("merchant_map_bonus_point").checked = true;
							else
								document.getElementById("merchant_map_bonus_point").checked = false;
							if (response.merchantOnboarding == true)
								document.getElementById("merchant_onboarding").checked = true;
							else
								document.getElementById("merchant_onboarding").checked = false;
							if (response.merchantReceivedOffers == true)
								document.getElementById("merchant_received_offers").checked = true;
							else
								document.getElementById("merchant_received_offers").checked = false;
							if (response.merchantSendCredentials == true)
								document.getElementById("merchant_send_credentials").checked = true;
							else
								document.getElementById("merchant_send_credentials").checked = false;
							if (response.merchantSendNotification == true)
								document.getElementById("merchant_send_notification").checked = true;
							 else
								document.getElementById("merchant_send_notification").checked = false; 
							if (response.customerSendNotification == true)
								document.getElementById("customer_send_notification").checked = true;
							 else
								document.getElementById("customer_send_notification").checked = false; 
							if (response.merchantStatus == true)
								document.getElementById("merchant_status").checked = true;
							else
								document.getElementById("merchant_status").checked = false;
							if (response.merchantTopUpLimit == true)
								document.getElementById("merchant_top_up_limit").checked = true;
							else
								document.getElementById("merchant_top_up_limit").checked = false;
							if (response.modifyCustomerDetails == true)
								document.getElementById("modify_customer_details").checked = true;
							else
								document.getElementById("modify_customer_details").checked = false;
							if (response.paymentDetails == true)
								document.getElementById("payment_details").checked = true;
							else
								document.getElementById("payment_details").checked = false;
							if (response.projectOnboarding == true)
								document.getElementById("project_onboarding").checked = true;
							else
								document.getElementById("project_onboarding").checked = false;
							if (response.reports == true)
								document.getElementById("reports").checked = true;
							else
								document.getElementById("reports").checked = false;

						},
						error : function(response) {
							console.log(response);
							document.getElementById("account_info").checked = false;
							document.getElementById("assign_employee_roles").checked = false;
							document.getElementById("create_employee").checked = false;
							document.getElementById("customer_send_notification").checked = false;
							document.getElementById("email_settings").checked = false;
							document.getElementById("helpdesk_action").checked = false;
							document.getElementById("merchant_login_page_info").checked = false;
							document.getElementById("merchant_map_bonus_point").checked = false;
							document.getElementById("merchant_onboarding").checked = false;
							document.getElementById("merchant_received_offers").checked = false;
							document.getElementById("merchant_send_credentials").checked = false; 
							document.getElementById("customer_send_notification").checked = false;
							document.getElementById("merchant_status").checked = false;
							document.getElementById("merchant_top_up_limit").checked = false;
							document.getElementById("modify_customer_details").checked = false;
							document.getElementById("payment_details").checked = false;
							document.getElementById("project_onboarding").checked = false;
							document.getElementById("reports").checked = false;
						}
					});
		} else {
			document.getElementById("account_info").checked = false;
			document.getElementById("assign_employee_roles").checked = false;
			document.getElementById("create_employee").checked = false;
			document.getElementById("customer_send_notification").checked = false;
			document.getElementById("email_settings").checked = false;
			document.getElementById("helpdesk_action").checked = false;
			document.getElementById("merchant_login_page_info").checked = false;
			document.getElementById("merchant_map_bonus_point").checked = false;
			document.getElementById("merchant_onboarding").checked = false;
			document.getElementById("merchant_received_offers").checked = false;
			document.getElementById("merchant_send_credentials").checked = false;
			document.getElementById("customer_send_notification").checked = false;
			document.getElementById("merchant_status").checked = false;
			document.getElementById("merchant_top_up_limit").checked = false;
			document.getElementById("modify_customer_details").checked = false;
			document.getElementById("payment_details").checked = false;
			document.getElementById("project_onboarding").checked = false;
			document.getElementById("reports").checked = false;
		}
	}
</script>
<script type="text/javascript">
	$(document).ready(function() {
		$(".select2_single").select2({
			allowClear : true
		});
	});
</script>