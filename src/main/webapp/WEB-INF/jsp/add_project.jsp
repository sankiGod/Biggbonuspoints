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
								<li><span class="bread-blod">Add Project</span></li>
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
							<h1>Create New Project</h1>
						</div>
						<div class="add-product">
							<a href="project_onboarding">Back To List</a>
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
										<form action="add_project" method="POST">
											<c:if test="${empty project.isCorporate}">
												<div class="form-group-inner">
													<div class="row">
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<label class="login2 pull-right pull-right-pro">Select
																The Type Of Project </label>
														</div>

														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="corporate"
																	name="project_type" id="project_corporate" required>
																	<i></i> Corporate
																</label>
															</div>
														</div>
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="individual"
																	name="project_type" id="project_individual" required>
																	<i></i> Individual
																</label>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group-inner">
													<div class="row">
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<label class="login2 pull-right pull-right-pro">Select
																The Type Of Merchant </label>
														</div>
														<input type="hidden" id="hidden_merchant_type"
															name="merchant_type" value="0">

														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="single"
																	name="m_type" id="merchant_single" required> <i></i>
																	Single
																</label>
															</div>
														</div>
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="multiple"
																	name="m_type" id="merchant_multiple" required>
																	<i></i> Multiple
																</label>
															</div>
														</div>
													</div>
												</div>
											</c:if>
											<c:if test="${project.isCorporate == 'true'}">
												<div class="form-group-inner">
													<div class="row">
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<label class="login2 pull-right pull-right-pro">Select
																The Type Of Project </label>
														</div>
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="corporate"
																	checked disabled id="project_corporate" required>
																	<i></i> Corporate
																</label> <input type="hidden" id="hidden_project_type"
																	name="project_type" value="corporate">
															</div>
														</div>
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="individual"
																	disabled id="project_individual" required> <i></i>
																	Individual
																</label>
															</div>
														</div>
													</div>
												</div>
												<div class="form-group-inner">
													<div class="row">
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<label class="login2 pull-right pull-right-pro">Select
																The Type Of Merchant </label>
														</div>
														<input type="hidden" id="hidden_merchant_type"
															name="merchant_type" value="0">

														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="single"
																	name="m_type" disabled id="merchant_single"
																	required> <i></i> Single
																</label>
															</div>
														</div>
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="multiple"
																	name="m_type" disabled id="merchant_multiple" required>
																	<i></i> Multiple
																</label>
															</div>
														</div>
													</div>
												</div>
											</c:if>
											<c:if test="${project.isCorporate == 'false'}">
												<div class="form-group-inner">
													<div class="row">
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<label class="login2 pull-right pull-right-pro">Select
																The Type Of Project </label>
														</div>
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="corporate"
																	id="project_corporate" disabled required> <i></i>
																	Corporate
																</label>
															</div>
														</div>
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<div>
																<label> <input type="radio" value="individual"
																	checked id="project_individual" disabled required>
																	<i></i> Individual
																</label> <input type="hidden" id="hidden_project_type"
																	name="project_type" value="individual">
															</div>
														</div>
													</div>
												</div>
												<div class="form-group-inner">
													<div class="row">
														<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
															<label class="login2 pull-right pull-right-pro">Select
																The Type Of Merchant </label>
														</div>

														<c:if
															test="${project.isTransferAllowedWithinProject == 'true'}">
															<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																<div>
																	<label> <input type="radio" value="single"
																		name="m_type" id="merchant_single" disabled required>
																		<i></i> Single
																	</label>
																</div>
															</div>
															<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																<div>
																	<label> <input type="radio" value="multiple"
																		name="m_type" checked disabled id="merchant_multiple"
																		required> <i></i> Multiple
																	</label> <input type="hidden" id="hidden_merchant_type"
																		name="merchant_type" value="multiple">
																</div>
															</div>
														</c:if>
														<c:if
															test="${project.isTransferAllowedWithinProject == 'false'}">
															<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																<div>
																	<label> <input type="radio" value="single"
																		name="m_type" checked disabled id="merchant_single"
																		required> <i></i> Single
																	</label> <input type="hidden" id="hidden_merchant_type"
																		name="merchant_type" value="single">
																</div>
															</div>
															<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
																<div>
																	<label> <input type="radio" disabled
																		value="multiple" name="m_type" id="merchant_multiple"
																		required> <i></i> Multiple
																	</label>
																</div>
															</div>
														</c:if>
													</div>
												</div>
											</c:if>

											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<label class="login2 pull-right pull-right-pro">Project
															Name</label>
													</div>
													<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
														<input type="text" class="form-control"
															placeholder="Enter Project Name" required
															name="project_name"
															value='<c:out value="${project.projectName}"></c:out>' />
													</div>
												</div>
											</div>
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<label class="login2 pull-right pull-right-pro">Contact
															Person</label>
													</div>
													<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
														<input type="text" class="form-control"
															placeholder="Enter Contact Person Name" required
															name="contact_person"
															value='<c:out value="${project.contactPerson}"></c:out>' />
													</div>
												</div>
											</div>
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<label class="login2 pull-right pull-right-pro">Mobile
															Number</label>
													</div>
													<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
														<input type="text" class="form-control"
															placeholder="Enter Mobile Number" required
															name="mobile_number"
															value='<c:out value="${project.mobileNumber}"></c:out>' />
													</div>
												</div>
											</div>
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<label class="login2 pull-right pull-right-pro">Email
															Id</label>
													</div>
													<div class="col-lg-5 col-md-5 col-sm-5 col-xs-12">
														<input type="text" class="form-control"
															placeholder="Enter Email Id" required name="email_id"
															value='<c:out value="${project.emailId}"></c:out>' />
													</div>
												</div>
											</div>
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<label class="login2 pull-right pull-right-pro">Legal
															Name</label>
													</div>
													<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
														<input type="text" class="form-control"
															placeholder="Enter Legal Name" required name="legal_name"
															value='<c:out value="${project.legalName}"></c:out>' />
													</div>
												</div>
											</div>
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<label class="login2 pull-right pull-right-pro">Address</label>
													</div>
													<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
														<c:if test="${empty project.address.state}">
															<select
																onchange="print_city('state', this.selectedIndex);"
																id="sts" name="state"
																class=" select2_single form-control" required>
															</select>
														</c:if>
														<c:if test="${!empty project.address.state}">
															<select name="state" class="form-control" required>
																<option value="${project.address.state}" selected>${project.address.state}</option>

															</select>
														</c:if>
													</div>
													<c:if test="${!empty project.address.city}">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
															<select id="state" name="city" class="form-control"
																required>
																<option value="${project.address.city}" selected>${project.address.city}</option>
															</select>
														</div>
													</c:if>
													<c:if test="${empty project.address.city}">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
															<select id="state" name="city"
																class="select2_single form-control" required>
																<option value="">Select City</option>
															</select>
														</div>
													</c:if>
												</div>
											</div>
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
														<label class="login2 pull-right pull-right-pro"></label>
													</div>
													<c:if test="${empty project.address.country}">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
															<input type="text" class="form-control"
																placeholder="Country" required value="India" disabled />
															<input type="hidden" class="form-control" name="country"
																required value="India" />
														</div>
													</c:if>
													<c:if test="${!empty project.address.country}">
														<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
															<input type="text" class="form-control"
																placeholder="Country" name="country" required disabled
																value='<c:out value="${project.address.country}"></c:out>' />
															<input type="hidden" class="form-control" name="country"
																required
																value='<c:out value="${project.address.country}"></c:out>' />
														</div>
													</c:if>
													<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
														<input type="text" class="form-control" placeholder="Pin"
															name="pin" required
															value='<c:out value="${project.address.pin}"></c:out>' />
													</div>
												</div>
											</div>
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-3"></div>
													<div class="col-lg-9">
														<div class="login-horizental cancel-wp pull-left">
															<a href="project_onboarding"><button
																	class="btn btn-sm btn-danger" type="button">Cancel</button></a>
															<a href="add_project"><button
																	class="btn btn-sm btn-warning" type="button">Reset</button></a>
															<button class="btn btn-sm btn-primary login-submit-cs"
																type="Submit">Submit</button>
														</div>
													</div>
												</div>
											</div>
											<div class="form-group-inner">
												<div class="row">
													<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
														<label class="login2 pull-right pull-right-pro">
															*All Fields In The Form Above Are Mandatory! </label>
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

	$('input[type=radio][name=project_type]').change(function() {
		if (this.value == 'corporate') {
			$('input[type=radio][name=m_type]').attr('disabled', true);
			$("#merchant_type").val("-1");
		} else if (this.value == 'individual') {
			$('input[type=radio][name=m_type]').attr('disabled', false);
		}
	});
	$('input[type=radio][name=m_type]').change(function() {
		if (this.value == 'single') {
			$("#hidden_merchant_type").val("single");
			console.log($('#hidden_merchant_type').val());
		} else if (this.value == 'multiple') {
			$("#hidden_merchant_type").val("multiple");
			var bla = $('#hidden_merchant_type').val();
			console.log(bla);
		}
	});
</script>