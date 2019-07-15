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
								<li><span class="bread-blod">Received Offers</span></li>
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
							<h1>Received Offers</h1>
						</div>
						<div class="col-lg-3"></div>
						<div class="col-lg-9">
							<h4 style="color: green">${successMessage}</h4>
							<h4 style="color: red">${errorMessage}</h4>
						</div>
					</div>
					<br> <br>
					<div class="admin-pro-accordion-wrap">
						<div class="panel-group adminpro-custon-design" id="accordion2">
							<div class="panel panel-default">
								<div class="panel-heading accordion-head">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse4" title="Click To Expand"> Pending Offers</a>
									</h4>
									<div id="collapse4" class="panel-collapse panel-ic collapse">
										<br>
										<div class="table-responsive">
											<table class="table table-striped table-hover">
												<tr>
													<th>#</th>
													<th>Offer ID</th>
													<th>Offer Code</th>
													<th>Merchant ID</th>
													<th>Offer Title</th>
													<th>Action</th>
												</tr>
												<c:set var="count" value="1" scope="page" />
												<c:forEach var="offer" items="${poffers}">
													<tr>
														<td>${count}</td>
														<td>${offer.offerId}</td>
														<td>${offer.offerCode}</td>
														<td>${offer.merchantID}</td>
														<td>${offer.offerTitle}</td>
														<td>
															<form action="goto_offer_action" method="post">
																<input type="hidden" name="offer_id"
																	value="${offer.offerId}">
																<button type="submit" data-toggle="tooltip"
																	title="Action" class="pd-setting-ed">
																	<i class="fa fa-pencil-square-o" aria-hidden="true"></i>
																</button>
															</form>
														</td>
													</tr>
													<c:set var="count" value="${count + 1}" scope="page" />
												</c:forEach>
											</table>
										</div>
									</div>
								</div>
							</div>
							<br>
							<div class="panel panel-default">
								<div class="panel-heading accordion-head">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse5" title="Click To Expand"> Approved
											Offers </a>
									</h4>
									<div id="collapse5" class="panel-collapse panel-ic collapse ">
										<br>
										<div class="table-responsive">
											<table class="table table-striped table-hover">
												<tr>
													<th>#</th>
													<th>Offer ID</th>
													<th>Offer Code</th>
													<th>Merchant ID</th>
													<th>Offer Title</th>
													<th>Action</th>
												</tr>
												<c:set var="count" value="1" scope="page" />
												<c:forEach var="offer" items="${aoffers}">
													<tr>
														<td>${count}</td>
														<td>${offer.offerId}</td>
														<td>${offer.offerCode}</td>
														<td>${offer.merchantID}</td>
														<td>${offer.offerTitle}</td>
														<td>
															<form action="goto_view_offer" method="post">
																<input type="hidden" name="offer_id"
																	value="${offer.offerId}">
																<button type="submit" data-toggle="tooltip" title="View"
																	class="pd-setting-ed">
																	<i class="fa fa-external-link" aria-hidden="true"></i>
																</button>
															</form>
														</td>
													</tr>
													<c:set var="count" value="${count + 1}" scope="page" />
												</c:forEach>
											</table>
										</div>
									</div>
								</div>
							</div>
							<br>
							<div class="panel panel-default">
								<div class="panel-heading accordion-head">
									<h4 class="panel-title">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse6" title="Click To Expand"> Rejected
											Offers </a>
									</h4>
									<div id="collapse6" class="panel-collapse panel-ic collapse ">
										<br>
										<div class="table-responsive">
											<table class="table table-striped table-hover">
												<tr>
													<th>#</th>
													<th>Offer ID</th>
													<th>Offer Code</th>
													<th>Merchant ID</th>
													<th>Offer Title</th>
													<th>Action</th>
												</tr>
												<c:set var="count" value="1" scope="page" />
												<c:forEach var="offer" items="${roffers}">
													<tr>
														<td>${count}</td>
														<td>${offer.offerId}</td>
														<td>${offer.offerCode}</td>
														<td>${offer.merchantID}</td>
														<td>${offer.offerTitle}</td>
														<td>
															<form action="goto_view_offer" method="post">
																<input type="hidden" name="offer_id"
																	value="${offer.offerId}">
																<button type="submit" data-toggle="tooltip" title="View"
																	class="pd-setting-ed">
																	<i class="fa fa-external-link" aria-hidden="true"></i>
																</button>
															</form>
														</td>
													</tr>
													<c:set var="count" value="${count + 1}" scope="page" />
												</c:forEach>
											</table>
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
<%@ include file="footer.jsp"%>