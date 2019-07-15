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
								<li><span class="bread-blod">Employee</span></li>
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
				<div class="product-status-wrap" style="background-color: #e9f6f6;">
					<h4>Employee's</h4>
					<div class="add-product">
						<a href="add_employee">Add Employee</a>
					</div>
					<br>
					<div class="admin-pro-accordion-wrap">
						<div class="panel-group adminpro-custon-design" id="accordion2">
							<div class="panel panel-default">
								<div class="panel-heading accordion-head">
									<a data-toggle="collapse" data-parent="#accordion2"
										href="#collapse4" title="Click To Expand">
										<h4 class="panel-title">Employee's List</h4>
									</a>
									<div id="collapse4" class="panel-collapse panel-ic collapse in">
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
																				<th>Employee ID</th>
																				<th>Employee Name</th>
																				<th>Email ID</th>
																				<th>Mobile Number</th>
																				<th>User Name</th>
																				<th>Password</th>
																				<th>Action</th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:set var="count" value="1" scope="page" />
																			<c:forEach var="employee" items="${employees}">
																				<tr>
																					<td>${count}</td>
																					<td>${employee.employeeID}</td>
																					<td>${employee.employeeName}</td>
																					<td>${employee.emailId}</td>
																					<td>${employee.mobileNumber}</td>
																					<td>${employee.username}</td>
																					<td>${employee.password}</td>
																					<td>
																						<form action="goto_view_employee" method="post">
																							<input type="hidden" name="eid"
																								value="${employee.eId}">
																							<button type="submit" data-toggle="tooltip"
																								title="View" class="pd-setting-ed">
																								<i class="fa fa-external-link"
																									aria-hidden="true"></i>
																							</button>
																						</form>
																					</td>
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
</div>
<!-- Basic Form End -->
<%@ include file="footer.jsp"%>