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
								<li><span class="bread-blod">Merchant Limit List</span></li>
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
							<h1>Merchant Limit List</h1>
						</div>
					</div>
					<br>
					<div class="sparkline12-graph">
						<div class="basic-login-form-ad">
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
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
																	data-show-refresh="true" data-key-events="true"
																	data-resizable="true" data-cookie="true"
																	data-cookie-id-table="saveId"
																	data-click-to-select="true">
																	<thead>
																		<tr>
																			<th>#</th>
																			<th>Merchant ID</th>
																			<th>Merchant Limit</th>
																			<th>Due Date</th>
																			<th>Created At</th>
																			<th>Updated At</th>
																		</tr>
																	</thead>
																	<tbody>
																		<c:set var="count" value="1" scope="page" />
																		<c:forEach var="limit" items="${limits}">
																			<tr>
																				<td>${count}</td>
																				<td>${limit.merchantID}</td>
																				<td>${limit.merchantLimit}</td>
																				<td>${limit.dueDate}</td>
																				<td>${limit.createdAt}</td>
																				<td>${limit.updatedAt}</td>
																			</tr>
																			<c:set var="count" value="${count + 1}" scope="page" />
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
<!-- Basic Form End -->
<%@ include file="footer.jsp"%>