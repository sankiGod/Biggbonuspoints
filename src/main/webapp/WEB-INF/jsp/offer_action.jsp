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
									<form action="approve_offer" method="POST">
										<div class="all-form-element-inner">
											<div class="row">
												<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
													<div class="sparkline16-graph">
														<div class="form-group data-custon-pick">
															<label>Offer Code</label>
															<div class="input-group date">
																<span class="input-group-addon"><i
																	class="fa fa-hashtag"></i></span> <input type="text"
																	class="form-control" name="offer_code"
																	value="${offer.offerCode}" required disabled> <input
																	type="hidden" name="offer_id" value="${offer.offerId}">
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
																	class="form-control" name="offer_title"
																	value="${offer.offerTitle}" disabled>
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
																<textarea rows="5" cols="120" name="offer_description"
																	disabled>${offer.offerDescription}</textarea>
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-4"></div>
												<div class="col-lg-8">
													<div class="login-horizental cancel-wp pull-left">
														<a href="received_offers"><button
																class="btn btn-sm btn-danger" type="button">Cancel</button></a>
														<button class="btn btn-sm btn-warning login-submit-cs"
															type="button" onclick="getForm()">Reject</button>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit">Approve</button>
													</div>
												</div>
											</div>
										</div>
									</form>
									<form action="reject_offer" method="POST" id="reject_offer"
										style="display: none;">
										<div class="all-form-element-inner">
											<div class="row">
												<div class="col-lg-6 col-md-6 col-sm-6 col-xs-12">
													<div class="sparkline16-graph">
														<div class="form-group data-custon-pick">
															<label>Reason</label>
															<div class="input-group date">
																<span class="input-group-addon"><i
																	class="fa fa-info"></i></span>
																<textarea rows="3" cols="80" name="reason"
																	placeholder="Enter Reasson For Rejecting" required></textarea>
																<input type="hidden" name="offer_id"
																	value="${offer.offerId}">
															</div>
														</div>
													</div>
												</div>
											</div>
											<div class="row">
												<div class="col-lg-4"></div>
												<div class="col-lg-8">
													<div class="login-horizental cancel-wp pull-left">
														<button class="btn btn-sm btn-danger" type="button"
															onclick="hideForm()">Cancel</button>
														<button class="btn btn-sm btn-primary login-submit-cs"
															type="submit">Reject</button>
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
<script type="text/javascript">
	function getForm() {
		var x = document.getElementById("reject_offer");
		x.style.display = "block";
	}

	function hideForm() {
		var x = document.getElementById("reject_offer");
		x.style.display = "none";
	}
</script>
<%@ include file="footer.jsp"%>