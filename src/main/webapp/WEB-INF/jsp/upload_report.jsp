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
								<li><span class="bread-blod">Upload Report</span></li>
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
							<h1>Payment Details</h1>
						</div>
						<div class="add-product ">
							<a href="update_merchant_topup_limit">Back</a>
						</div>
					</div>
					<br>
					<div class="sparkline12-graph">
						<div class="basic-login-form-ad">
							<br> <br>
							<div class="row">
								<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
									<div class="table-responsive">
										<table class="table table-hover table-bordered" id="payment">
											<thead class="thead-dark">
												<tr>
													<th>#</th>
													<th>Transaction ID</th>
													<th>Message</th>
												</tr>
											</thead>
											<c:set var="count" value="1" scope="page" />
											<c:forEach var="message" items="${messages}">
												<tr>
													<td>${count}</td>
													<td>${message.key}</td>
													<td>${message.value}</td>
												</tr>
												<c:set var="count" value="${count + 1}" scope="page" />
											</c:forEach>
										</table>
										<div class="col-lg-2"></div>
										<div class="col-lg-10">
											<h4 id="errorMessage" style="color: red"></h4>
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