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
								<li><span class="bread-blod">Reports</span></li>
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
							<h1>Reports</h1>
						</div>
						<div class="col-lg-3"></div>
						<div class="col-lg-9">
							<h4 style="color: green">${successMessage}</h4>
							<h4 style="color: red">${errorMessage}</h4>
						</div>
					</div>
					<br>
					<div class="widget-program-box mg-tb-30">
						<div class="container-fluid">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel responsive-mg-b-30">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Projects</h2>
												<p class="font-bold text-success">List Of Project ID's
													With OnBoarding Data</p>
												<div class="m icon-box">
													<i class="fa fa-pie-chart" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain all
													of the projects details with their on boarding data.</p>
												<a
													href="${pageContext.request.contextPath}/downloads/xlsx/project.xlsx"><button
														class="btn btn-success btn-sm">Download</button></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel responsive-mg-b-30">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Merchants</h2>
												<p class="font-bold text-info">List Of Merchant ID's
													With OnBoarding Data</p>
												<div class="m icon-box">
													<i class="fa fa-pie-chart" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain all
													of the merchants details with their on boarding data.</p>
												<a
													href="${pageContext.request.contextPath}/downloads/xlsx/merchant.xlsx"><button
														class="btn btn-success btn-sm">Download</button></a>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel responsive-mg-b-30">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Merchant Disabled List</h2>
												<p class="font-bold text-warning">List Of Disabled
													Merchants</p>
												<div class="m icon-box">
													<i class="fa fa-pie-chart" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain all
													disabled merchant list</p>
												<button class="btn btn-success btn-sm" data-toggle="modal"
													data-target="#DisableMerchantModal">Download</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div class="widget-program-box mg-tb-30">
						<div class="container-fluid">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Project-Wise</h2>
												<p class="font-bold text-danger">Balance Tables</p>
												<div class="m icon-box">
													<i class="fa fa-line-chart" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain
													project wise balance tables</p>
												<button class="btn btn-danger btn-sm" data-toggle="modal"
													data-target="#ProjectWiseModal">Download</button>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Merchant-Wise</h2>
												<p class="font-bold text-danger">Balance Tables</p>
												<div class="m icon-box">
													<i class="fa fa-line-chart" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain
													merchant wise balance tables</p>
												<button class="btn btn-danger btn-sm" data-toggle="modal"
													data-target="#MerchantWiseModal">Download</button>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Customer-Wise</h2>
												<p class="font-bold text-danger">Balance Tables</p>
												<div class="m icon-box">
													<i class="fa fa-line-chart" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain
													customer wise balance tables</p>
												<button class="btn btn-danger btn-sm" data-toggle="modal"
													data-target="#CustomerWiseModal">Download</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div class="widget-program-box mg-tb-30">
						<div class="container-fluid">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Award Transactions</h2>
												<p class="font-bold text-danger">Project Wise</p>
												<div class="m icon-box">
													<i class="fa fa-star-o" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain all
													award transactions project wise.</p>
												<button class="btn btn-warning btn-sm" data-toggle="modal"
													data-target="#AwardTransactions">Download</button>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Redeem Transactions</h2>
												<p class="font-bold text-danger">Project Wise</p>
												<div class="m icon-box">
													<i class="fa fa-star-o" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain all
													redeem transactions project wise.</p>
												<button class="btn btn-warning btn-sm" data-toggle="modal"
													data-target="#RedeemTransactions">Download</button>
											</div>
										</div>
									</div>
								</div>
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Payment Dues</h2>
												<p class="font-bold text-danger">In Last 7 Days</p>
												<div class="m icon-box">
													<i class="fa fa-star-o" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain
													merchants details whose due date is in last 7 days.</p>
												<a
													href="${pageContext.request.contextPath}/downloads/xlsx/paymentdue.xlsx"><button
														class="btn btn-warning btn-sm">Download</button></a>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
					<br>
					<div class="widget-program-box mg-tb-30">
						<div class="container-fluid">
							<div class="row">
								<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
									<div class="hpanel responsive-mg-b-30">
										<div class="panel-body">
											<div class="text-center content-box">
												<h2 class="m-b-xs">Corporate Merchant ReCon Report</h2>
												<p class="font-bold text-warning">ReCon Report</p>
												<div class="m icon-box">
													<i class="fa fa-pie-chart" aria-hidden="true"></i>
												</div>
												<p class="small mg-t-box">This Report will contain ReCon report for selected corporate project</p>
												<button class="btn btn-success btn-sm" data-toggle="modal"
													data-target="#ReconModal">Download</button>
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
<!-- Modal's Start -->
<div id="DisableMerchantModal"
	class="modal modal-adminpro-general FullColor-popup-DangerModal fade"
	role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header header-color-modal bg-color-4">
				<h4 class="modal-title">List Of Disabled Merchants</h4>
				<div class="modal-close-area modal-close-df">
					<a class="close" data-dismiss="modal" href="#"><i
						class="fa fa-close"></i></a>
				</div>
			</div>
			<form
				action="${pageContext.request.contextPath}/downloads/xlsx/disabledmerchants.xlsx"
				method="get" id="disablemerchant">
				<div class="modal-body">
					<span
						class="adminpro-icon adminpro-danger-error modal-check-pro information-icon-pro"></span>
					<h3>Select Dates</h3>
					<div class="form-group data-custon-pick data-custom-mg" id="data_5">
						<div class="input-daterange input-group" id="datepicker">
							<input type="text" class="form-control" name="startDate"
								value="01/01/2019" /> <span class="input-group-addon">to</span>
							<input type="text" class="form-control" name="endDate"
								value="01/01/2020" />
						</div>
					</div>
				</div>
				<div class="modal-footer footer-modal-admin">
					<a data-dismiss="modal" href="#">Cancel</a> <a href="#"
						id="submitform">Submit</a>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="ProjectWiseModal"
	class="modal modal-adminpro-general FullColor-popup-DangerModal fade"
	role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header header-color-modal bg-color-4">
				<h4 class="modal-title">Project Wise Balance Table</h4>
				<div class="modal-close-area modal-close-df">
					<a class="close" data-dismiss="modal" href="#"><i
						class="fa fa-close"></i></a>
				</div>
			</div>
			<form
				action="${pageContext.request.contextPath}/downloads/xlsx/projectwisebalance.xlsx"
				method="get" id="projectwise">
				<div class="modal-body">
					<span
						class="adminpro-icon adminpro-danger-error modal-check-pro information-icon-pro"></span>
					<h3>Select Dates</h3>
					<div class="form-group data-custon-pick data-custom-mg" id="data_5">
						<div class="input-daterange input-group" id="datepicker">
							<input type="text" class="form-control" name="startDate"
								value="01/01/2019" /> <span class="input-group-addon">to</span>
							<input type="text" class="form-control" name="endDate"
								value="01/01/2020" />
						</div>
					</div>
				</div>
				<div class="modal-footer footer-modal-admin">
					<a data-dismiss="modal" href="#">Cancel</a> <a href="#"
						id="submitform1">Submit</a>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="MerchantWiseModal"
	class="modal modal-adminpro-general FullColor-popup-DangerModal fade"
	role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header header-color-modal bg-color-4">
				<h4 class="modal-title">Merchant Wise Balance Table</h4>
				<div class="modal-close-area modal-close-df">
					<a class="close" data-dismiss="modal" href="#"><i
						class="fa fa-close"></i></a>
				</div>
			</div>
			<form
				action="${pageContext.request.contextPath}/downloads/xlsx/merchantwisebalance.xlsx"
				method="get" id="merchantwise">
				<div class="modal-body">
					<span
						class="adminpro-icon adminpro-danger-error modal-check-pro information-icon-pro"></span>
					<h3>Select Dates</h3>
					<div class="form-group data-custon-pick data-custom-mg" id="data_5">
						<div class="input-daterange input-group" id="datepicker">
							<input type="text" class="form-control" name="startDate"
								value="01/01/2019" /> <span class="input-group-addon">to</span>
							<input type="text" class="form-control" name="endDate"
								value="01/01/2020" />
						</div>
					</div>
				</div>
				<div class="modal-footer footer-modal-admin">
					<a data-dismiss="modal" href="#">Cancel</a> <a href="#"
						id="submitform2">Submit</a>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="CustomerWiseModal"
	class="modal modal-adminpro-general FullColor-popup-DangerModal fade"
	role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header header-color-modal bg-color-4">
				<h4 class="modal-title">Customer Wise Balance Table</h4>
				<div class="modal-close-area modal-close-df">
					<a class="close" data-dismiss="modal" href="#"><i
						class="fa fa-close"></i></a>
				</div>
			</div>
			<form
				action="${pageContext.request.contextPath}/downloads/xlsx/customerwisebalance.xlsx"
				method="get" id="customerwise">
				<div class="modal-body">
					<span
						class="adminpro-icon adminpro-danger-error modal-check-pro information-icon-pro"></span>
					<h3>Select Dates</h3>
					<div class="form-group data-custon-pick data-custom-mg" id="data_5">
						<div class="input-daterange input-group" id="datepicker">
							<input type="text" class="form-control" name="startDate"
								value="01/01/2019" /> <span class="input-group-addon">to</span>
							<input type="text" class="form-control" name="endDate"
								value="01/01/2020" />
						</div>
					</div>
				</div>
				<div class="modal-footer footer-modal-admin">
					<a data-dismiss="modal" href="#">Cancel</a> <a href="#"
						id="submitform3">Submit</a>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="AwardTransactions"
	class="modal modal-adminpro-general FullColor-popup-DangerModal fade"
	role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header header-color-modal bg-color-4">
				<h4 class="modal-title">Project Wise Award Transactions</h4>
				<div class="modal-close-area modal-close-df">
					<a class="close" data-dismiss="modal" href="#"><i
						class="fa fa-close"></i></a>
				</div>
			</div>
			<form
				action="${pageContext.request.contextPath}/downloads/xlsx/awardtransactions.xlsx"
				method="get" id="awardtransaction">
				<div class="modal-body">
					<span
						class="adminpro-icon adminpro-danger-error modal-check-pro information-icon-pro"></span>
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
							<label class="login2 pull-right pull-right-pro ">Select
								Project</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
							<div class="form-select-list">
								<select class="select2_single form-control custom-select-value"
									name="project_id" required id="project" style="width: 240px;">
									<option value="">Select One Project</option>
									<c:forEach var="project" items="${projects}">
										<option value="${project.projectID}">${project.projectName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
							<label class="login2 pull-right pull-right-pro ">Select
								Dates</label>
						</div>
						<div class="form-group data-custon-pick data-custom-mg"
							id="data_5">
							<div class="input-daterange input-group" id="datepicker">
								<input type="text" class="form-control" name="startDate"
									value="01/01/2019" /> <span class="input-group-addon">to</span>
								<input type="text" class="form-control" name="endDate"
									value="01/01/2020" />
							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer footer-modal-admin">
					<a data-dismiss="modal" href="#">Cancel</a> <a href="#"
						id="submitform4">Submit</a>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="RedeemTransactions"
	class="modal modal-adminpro-general FullColor-popup-DangerModal fade"
	role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header header-color-modal bg-color-4">
				<h4 class="modal-title">Project Wise Redeem Transactions</h4>
				<div class="modal-close-area modal-close-df">
					<a class="close" data-dismiss="modal" href="#"><i
						class="fa fa-close"></i></a>
				</div>
			</div>
			<form
				action="${pageContext.request.contextPath}/downloads/xlsx/redeemtransactions.xlsx"
				method="get" id="redeemtransaction">
				<div class="modal-body">
					<span
						class="adminpro-icon adminpro-danger-error modal-check-pro information-icon-pro"></span>
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
							<label class="login2 pull-right pull-right-pro ">Select
								Project</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
							<div class="form-select-list">
								<select class="select2_single form-control custom-select-value"
									name="project_id" required id="project" style="width: 240px;">
									<option value="">Select One Project</option>
									<c:forEach var="project" items="${projects}">
										<option value="${project.projectID}">${project.projectName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<br>
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
							<label class="login2 pull-right pull-right-pro ">Select
								Dates</label>
						</div>
						<div class="form-group data-custon-pick data-custom-mg"
							id="data_5">
							<div class="input-daterange input-group" id="datepicker">
								<input type="text" class="form-control" name="startDate"
									value="01/01/2019" /> <span class="input-group-addon">to</span>
								<input type="text" class="form-control" name="endDate"
									value="01/01/2020" />
							</div>
						</div>
					</div>

				</div>
				<div class="modal-footer footer-modal-admin">
					<a data-dismiss="modal" href="#">Cancel</a> <a href="#"
						id="submitform5">Submit</a>
				</div>
			</form>
		</div>
	</div>
</div>
<div id="ReconModal"
	class="modal modal-adminpro-general FullColor-popup-DangerModal fade"
	role="dialog">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header header-color-modal bg-color-4">
				<h4 class="modal-title">Project Wise Redeem Transactions</h4>
				<div class="modal-close-area modal-close-df">
					<a class="close" data-dismiss="modal" href="#"><i
						class="fa fa-close"></i></a>
				</div>
			</div>
			<form
				action="${pageContext.request.contextPath}/downloads/xlsx/corporaterecon.xlsx"
				method="get" id="corpoarterecon">
				<div class="modal-body">
					<span
						class="adminpro-icon adminpro-danger-error modal-check-pro information-icon-pro"></span>
					<div class="row">
						<div class="col-lg-3 col-md-3 col-sm-3 col-xs-12">
							<label class="login2 pull-right pull-right-pro ">Select
								Project</label>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-4 col-xs-12">
							<div class="form-select-list">
								<select class="select2_single form-control custom-select-value"
									name="project_id" required id="project" style="width: 240px;">
									<option value="">Select One Project</option>
									<c:forEach var="project" items="${cprojects}">
										<option value="${project.projectID}">${project.projectName}</option>
									</c:forEach>
								</select>
							</div>
						</div>
					</div>
					<br>
				</div>
				<div class="modal-footer footer-modal-admin">
					<a data-dismiss="modal" href="#">Cancel</a> <a href="#"
						id="submitform6">Submit</a>
				</div>
			</form>
		</div>
	</div>
</div>
<!-- Modal's End -->
<!-- Basic Form End -->
<%@ include file="footer.jsp"%>
<script type="text/javascript">
	$(document).ready(function() {
		$('#submitform').click(function() {
			$('#disablemerchant').submit();
		});

		$('#submitform1').click(function() {
			$('#projectwise').submit();
		});

		$('#submitform2').click(function() {
			$('#merchantwise').submit();
		});

		$('#submitform3').click(function() {
			$('#customerwise').submit();
		});

		$('#submitform4').click(function() {
			$('#awardtransaction').submit();
		});

		$('#submitform5').click(function() {
			$('#redeemtransaction').submit();
		});
		
		$('#submitform6').click(function() {
			$('#corpoarterecon').submit();
		});

		$(".select2_single").select2({
			allowClear : true
		});
	});
</script>