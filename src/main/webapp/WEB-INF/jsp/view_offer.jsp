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
								<li><span class="bread-blod">View Offer</span></li>
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
							<h1>Offer Details</h1>
						</div>
						<div class="add-product">
							<a href="received_offers">Back To List</a>
						</div>
					</div>
					<div class="sparkline12-graph">
						<div class="basic-login-form-ad">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="all-form-element-inner">
										<div class="row">
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
												<div class="sparkline16-graph">
													<div class="form-group data-custon-pick">
														<label>Offer Code</label>
														<div class="input-group date">
															<span class="input-group-addon"><i
																class="fa fa-hashtag"></i></span> <input type="text"
																class="form-control" name="project_id"
																value="${offer.offerCode}" required disabled>
														</div>
													</div>
												</div>
											</div>
											<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
												<div class="sparkline16-graph">
													<div class="form-group data-custon-pick">
														<label>Merchant ID</label>
														<div class="input-group date">
															<span class="input-group-addon"><i
																class="fa fa-diamond"></i></span> <input type="text"
																class="form-control" name="merchant_id"
																value="${offer.merchantID}" required disabled>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
												<div class="sparkline16-graph">
													<div class="form-group data-custon-pick">
														<label>Title</label>
														<div class="input-group date">
															<span class="input-group-addon"><i
																class="fa fa-mail-forward"></i></span> <input type="text"
																class="form-control" value="${offer.offerTitle}"
																disabled>
														</div>
													</div>
												</div>
											</div>
										</div>
										<div class="row">
											<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
												<div class="sparkline16-graph">
													<div class="form-group data-custon-pick">
														<label>Description</label>
														<div class="input-group date">
															<span class="input-group-addon"><i
																class="fa fa-info"></i></span>
															<textarea rows="5" cols="120" disabled>${offer.offerDescription}</textarea>
														</div>
													</div>
												</div>
											</div>
										</div>
										<c:if test="${!empty offer.rejectReason}">
											<div class="row">
											<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
												<div class="sparkline16-graph">
													<div class="form-group data-custon-pick">
														<label>Reject Reason</label>
														<div class="input-group date">
															<span class="input-group-addon"><i
																class="fa fa-info"></i></span>
															<textarea rows="4" cols="80" disabled>${offer.rejectReason}</textarea>
														</div>
													</div>
												</div>
											</div>
										</div>
										</c:if>
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