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
								<li><span class="bread-blod">List Projects</span></li>
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
						<h4>Projects List</h4>
						<div class="add-product">
							<a href="add_project">Add Project</a>
						</div>
						<div class="admin-pro-accordion-wrap">
							<div class="panel-group adminpro-custon-design" id="accordion2">
								<div class="panel panel-default">
									<div class="panel-heading accordion-head">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse4" title="Click To Expand">
											<h4 class="panel-title">OnBoarded Projects List</h4>
										</a>
									</div>
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
																				<th data-field="projectName">Project Name</th>
																				<th data-field="projectId">Project ID</th>
																				<th data-field="contact">Contact Person</th>
																				<th data-field="mobileNumber">Mobile Number</th>
																				<th data-field="emailId">Email ID</th>
																				<th data-field="projectType">Project Type</th>
																				<th>Action</th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:forEach var="project" items="${projects}">
																				<tr>
																					<td>${project.pId}</td>
																					<td>${project.projectName}</td>
																					<td>${project.projectID}</td>
																					<td>${project.contactPerson}</td>
																					<td>${project.mobileNumber}</td>
																					<td>${project.emailId}</td>
																					<td>${project.projectType}</td>
																					<td>
																						<form action="goto_edit_project" method="post">
																							<input type="hidden" name="pid"
																								value="${project.pId}">
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
								<div class="panel panel-default">
									<div class="panel-heading accordion-head">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse5" title="Click To Expand">
											<h4 class="panel-title">Potential Corporate Projects
												List</h4>
										</a>
									</div>
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
																				<th data-field="contactName">Contact Person</th>
																				<th data-field="mobileNumber">Mobile Number</th>
																				<th data-field="emailId">Email ID</th>
																				<th data-field="companyName">Company Name</th>
																				<th data-field="city">City</th>
																				<th data-field="approvedStatus">Approved Status</th>
																				<th data-field="action">Action</th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:forEach var="project" items="${cprojects}">
																				<tr>
																					<td>${project.pcId}</td>
																					<td>${project.contactPerson}</td>
																					<td>${project.mobileNumber}</td>
																					<td>${project.emailId}</td>
																					<td>${project.companyName}</td>
																					<td>${project.address.city}</td>
																					<td>${project.isApproved}</td>
																					<td><c:if test="${project.isApproved == true}">
																							<form action="goto_add_project" method="post">
																								<input type="hidden" name="pcid"
																									value="${project.pcId}">
																								<button type="submit" data-toggle="tooltip"
																									title="Edit" class="pd-setting-ed" disabled>
																									<i class="fa fa-pencil-square-o"
																										aria-hidden="true"></i>
																								</button>
																							</form>
																						</c:if> <c:if test="${project.isApproved == false}">
																							<form action="goto_add_project" method="post">
																								<input type="hidden" name="pcid"
																									value="${project.pcId}">
																								<button type="submit" data-toggle="tooltip"
																									title="Edit" class="pd-setting-ed">
																									<i class="fa fa-pencil-square-o"
																										aria-hidden="true"></i>
																								</button>
																							</form>
																						</c:if></td>
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
								<div class="panel panel-default">
									<div class="panel-heading accordion-head">
										<a data-toggle="collapse" data-parent="#accordion2"
											href="#collapse6" title="Click To Expand">
											<h4 class="panel-title">Corporate Projects Accounts</h4>
										</a>
									</div>
									<div id="collapse6" class="panel-collapse panel-ic collapse">
										<div class="data-table-area mg-tb-15">
											<div class="container-fluid">
												<div class="row">
													<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
														<div class="sparkline13-list">
															<div class="sparkline13-graph">
																<div
																	class="datatable-dashv1-list custom-datatable-overright">
																	<table id="table2" data-toggle="table"
																		data-pagination="true" data-search="true"
																		data-show-columns="true"
																		data-show-pagination-switch="true"
																		data-key-events="true" data-resizable="true"
																		data-cookie="true" data-cookie-id-table="saveId"
																		data-click-to-select="true">
																		<thead>
																			<tr>
																				<th>#</th>
																				<th data-field="contactName">Project ID</th>
																				<th data-field="mobileNumber">Project Name</th>
																				<th data-field="emailId">Bonus Points</th>
																				<th data-field="companyName">Bonus Amount</th>
																				<th data-field="city">Last Updated At</th>
																			</tr>
																		</thead>
																		<tbody>
																			<c:set var="count" value="1" scope="page" />
																			<c:forEach var="caccount" items="${caccounts}">
																				<tr>
																					<td>${count}</td>
																					<td>${caccount.projectID}</td>
																					<td>${caccount.projectName}</td>
																					<td>${caccount.corporateBonusPoint}</td>
																					<td>${caccount.corporateBonusAmount}</td>
																					<td>${caccount.updatedAt}</td>
																					<c:set var="count" value="${count + 1}"
																						scope="page" />
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
<%@ include file="footer.jsp"%>
