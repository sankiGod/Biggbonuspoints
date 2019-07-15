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
								<li><span class="bread-blod">Merchant List</span></li>
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
					<div class="product-status-wrap">
						<h4>Merchants List</h4>
						<div class="add-product">
							<a href="add_merchant">Add Merchant</a>
						</div>
						<div class="admin-pro-accordion-wrap">
							<div class="panel-group adminpro-custon-design" id="accordion2">
								<div class="panel panel-default">
									<div class="panel-heading accordion-head">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse4" title="Click To Expand">
											<h4 class="panel-title">OnBoarded Merchants List</h4>
										</a>
										<div id="collapse4" class="panel-collapse panel-ic collapse">
											<div class="data-table-area mg-tb-15">
												<div class="container-fluid">
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="sparkline13-list">
																<div class="sparkline13-graph">
																	<div
																		class="datatable-dashv1-list custom-datatable-overright">
																		<table id="table" data-toggle="table"
																			data-pagination="true" data-search="true"
																			data-show-columns="true"
																			data-show-pagination-switch="true"
																			data-key-events="true" data-resizable="true"
																			data-cookie="true" data-cookie-id-table="saveId"
																			data-click-to-select="true">
																			<thead>
																				<tr>
																					<th>#</th>
																					<th>Project ID</th>
																					<th>Merchant ID</th>
																					<th>Contact Person</th>
																					<th>Mobile Number</th>
																					<th>Shop Name</th>
																					<th>Area Name</th>
																					<th>City</th>
																					<th>Status</th>
																					<th>Action</th>
																				</tr>
																			</thead>
																			<tbody>
																				<c:forEach var="merchant" items="${merchants}">
																					<tr>
																						<td>${merchant.mId}</td>
																						<td>${merchant.projectID}</td>
																						<td>${merchant.merchantID}</td>
																						<td>${merchant.contactPerson}</td>
																						<td>${merchant.mobileNumber}</td>
																						<td>${merchant.shopName}</td>
																						<td>${merchant.address.areaName}</td>
																						<td>${merchant.address.city}</td>
																						<td>${merchant.status}</td>
																						<td>
																							<form action="goto_view_merchant" method="post">
																								<input type="hidden" name="mid"
																									value="${merchant.mId}">
																								<button type="submit" data-toggle="tooltip"
																									title="View" class="pd-setting-ed">
																									<i class="fa fa-external-link"
																										aria-hidden="true"></i>
																								</button>
																							</form>
																							<form action="goto_edit_merchant" method="post">
																								<input type="hidden" name="mid"
																									value="${merchant.mId}">
																								<button type="submit" data-toggle="tooltip"
																									title="Edit" class="pd-setting-ed">
																									<i class="fa fa-pencil-square-o"
																										aria-hidden="true"></i>
																								</button>
																							</form>
																						</td>
																					</tr>
																				</c:forEach>
																			</tbody>
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
								<div class="panel panel-default">
									<div class="panel-heading accordion-head">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse5" title="Click To Expand">
											<h4 class="panel-title">Potential Merchants List</h4>
										</a>
										<div id="collapse5" class="panel-collapse panel-ic collapse">
											<div class="data-table-area mg-tb-15">
												<div class="container-fluid">
													<div class="row">
														<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
															<div class="sparkline13-list">
																<div class="sparkline13-graph">
																	<div
																		class="datatable-dashv1-list custom-datatable-overright">
																		<table id="table1" data-toggle="table"
																			data-pagination="true" data-search="true"
																			data-show-columns="true"
																			data-show-pagination-switch="true"
																			data-key-events="true" data-resizable="true"
																			data-cookie="true" data-cookie-id-table="saveId"
																			data-click-to-select="true">
																			<thead>
																				<tr>
																					<th>#</th>
																					<th>Merchant Type</th>
																					<th>Contact Person</th>
																					<th>Mobile Number</th>
																					<th>Shop Name</th>
																					<th>Area Name</th>
																					<th>City</th>
																					<th>Approved Status</th>
																					<th>Action</th>
																				</tr>
																			</thead>
																			<tbody>
																				<c:forEach var="pmerchant" items="${pmerchants}">
																					<tr>
																						<td>${pmerchant.pmId}</td>
																						<td>${pmerchant.merchantType}</td>
																						<td>${pmerchant.contactPerson}</td>
																						<td>${pmerchant.mobileNumber}</td>
																						<td>${pmerchant.shopName}</td>
																						<td>${pmerchant.address.areaName}</td>
																						<td>${pmerchant.address.city}</td>
																						<td>${pmerchant.isApproved}</td>
																						<td>
																							<form action="goto_add_merchant" method="post">
																								<input type="hidden" name="pmId"
																									value="${pmerchant.pmId}">
																								<button type="submit" data-toggle="tooltip"
																									title="Edit" class="pd-setting-ed">
																									<i class="fa fa-pencil-square-o"
																										aria-hidden="true"></i>
																								</button>
																							</form>
																						</td>
																					</tr>
																				</c:forEach>
																			</tbody>
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
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- Basic Form End-->
<%@ include file="footer.jsp"%>