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
								<li><span class="bread-blod">Merchant Uploads</span></li>
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
							<h1>Merchant Uploads</h1>
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
										<form action="merchant_pp_upload" method="POST"
											enctype="multipart/form-data">
											<div class="row">
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
													<div class="sparkline16-list responsive-mg-b-30">
														<div class="sparkline16-graph">
															<div class="date-picker-inner">
																<div class="form-group data-custon-pick">
																	<label>Privacy Policy</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-file-excel-o"></i></span> <input type="file"
																			class="form-control" name="privacy_policy"
																			id="privacy_policy" required>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-2"></div>
												<div class="col-lg-10">
													<div class="login-horizental cancel-wp pull-left">
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit" id="submit">Upload</button>
													</div>
													<c:if test="${!empty uploads.privacyPolicyPath}">
														<div class="login-horizental cancel-wp pull-left">
															<a href="${uploads.privacyPolicyPath}"><button
																	type="button" class="btn btn-sm btn-warning">Download
																	Exiting Policy</button></a>
														</div>
													</c:if>
												</div>
											</div>
										</form>
										<br>
										<form action="merchant_tc_uploads" method="POST"
											enctype="multipart/form-data">
											<div class="row">
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
													<div class="sparkline16-list responsive-mg-b-30">
														<div class="sparkline16-graph">
															<div class="date-picker-inner">
																<div class="form-group data-custon-pick">
																	<label>Terms And Conditions</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-file-excel-o"></i></span> <input type="file"
																			class="form-control" name="terms_and_conditions"
																			id="terms_and_conditions" required>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-2"></div>
												<div class="col-lg-10">
													<div class="login-horizental cancel-wp pull-left">
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit" id="submit">Upload</button>
													</div>
													<c:if test="${!empty uploads.termsAndConditionsPath}">
														<div class="login-horizental cancel-wp pull-left">
															<a href="${uploads.termsAndConditionsPath}"><button
																	type="button" class="btn btn-sm btn-warning">Download
																	Exiting Terms And Conditions</button></a>
														</div>
													</c:if>
												</div>
											</div>
										</form>
										<br>
										<form action="merchant_ug_uploads" method="POST"
											enctype="multipart/form-data">
											<div class="row">
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
													<div class="sparkline16-list responsive-mg-b-30">
														<div class="sparkline16-graph">
															<div class="date-picker-inner">
																<div class="form-group data-custon-pick">
																	<label>User Guide</label>
																	<div class="input-group date">
																		<span class="input-group-addon"><i
																			class="fa fa-file-excel-o"></i></span> <input type="file"
																			class="form-control" name="user_guide"
																			id="user_guide" required>
																	</div>
																</div>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-2"></div>
												<div class="col-lg-10">
													<div class="login-horizental cancel-wp pull-left">
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit" id="submit">Upload</button>
													</div>
													<c:if test="${!empty uploads.userGuidePath}">
														<div class="login-horizental cancel-wp pull-left">
															<a href="${uploads.userGuidePath}"><button
																	type="button" class="btn btn-sm btn-warning">Download
																	Exiting User Guide</button></a>
														</div>
													</c:if>
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