<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html class="no-js" lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="x-ua-compatible" content="ie=edge">
<title>Bonus Points</title>
<meta name="description" content="">
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- favicon
	    	============================================ -->
<link rel="shortcut icon" type="image/x-icon" href="img/favicon.ico">
<!-- Google Fonts
	    	============================================ -->
<link href="https://fonts.googleapis.com/css?family=Play:400,700"
	rel="stylesheet">
<!-- Bootstrap CSS
	    	============================================ -->
<link rel="stylesheet" href="css/bootstrap.min.css">
<!-- Bootstrap CSS
	    	============================================ -->
<link rel="stylesheet" href="css/font-awesome.min.css">
<!-- owl.carousel CSS
	    	============================================ -->
<link rel="stylesheet" href="css/owl.carousel.css">
<link rel="stylesheet" href="css/owl.theme.css">
<link rel="stylesheet" href="css/owl.transitions.css">
<!-- animate CSS
	    	============================================ -->
<link rel="stylesheet" href="css/animate.css">
<!-- normalize CSS
	    	============================================ -->
<link rel="stylesheet" href="css/normalize.css">
<!-- meanmenu icon CSS
	    	============================================ -->
<link rel="stylesheet" href="css/meanmenu.min.css">
<!-- main CSS
	    	============================================ -->
<link rel="stylesheet" href="css/main.css">
<!-- morrisjs CSS
	    	============================================ -->
<link rel="stylesheet" href="css/morrisjs/morris.css">
<!-- mCustomScrollbar CSS
	    	============================================ -->
<link rel="stylesheet"
	href="css/scrollbar/jquery.mCustomScrollbar.min.css">
<!-- metisMenu CSS
	    	============================================ -->
<link rel="stylesheet" href="css/metisMenu/metisMenu.min.css">
<link rel="stylesheet" href="css/metisMenu/metisMenu-vertical.css">
<!-- calendar CSS
	    	============================================ -->
<link rel="stylesheet" href="css/calendar/fullcalendar.min.css">
<link rel="stylesheet" href="css/calendar/fullcalendar.print.min.css">

<!-- x-editor CSS
		============================================ -->
<link rel="stylesheet" href="css/editor/select2.css">
<link rel="stylesheet" href="css/editor/datetimepicker.css">
<link rel="stylesheet" href="css/editor/bootstrap-editable.css">
<link rel="stylesheet" href="css/editor/x-editor-style.css">

<!-- normalize CSS
		============================================ -->
<link rel="stylesheet" href="css/data-table/bootstrap-table.css">
<link rel="stylesheet" href="css/data-table/bootstrap-editable.css">
<!-- modals CSS
	    	============================================ -->
<link rel="stylesheet" href="css/modals.css">
<!-- forms CSS
	    	============================================ -->
<link rel="stylesheet" href="css/form/all-type-forms.css">
<!-- style CSS
	    	============================================ -->
<link rel="stylesheet" href="style.css">
<!-- responsive CSS
	    	============================================ -->
<link rel="stylesheet" href="css/responsive.css">
<!-- modernizr JS
	    	============================================ -->
<script src="js/vendor/modernizr-2.8.3.min.js"></script>

<!-- datapicker CSS
		============================================ -->
<link rel="stylesheet" href="css/datapicker/datepicker3.css">

<!-- select2 CSS -->
<link rel="stylesheet" href="css/select2.min.css">

<!-- State and City Picker JS -->
<script src="js/cities.js"></script>
<style>
#sidebar {
	max-width: 210px;
}

.basic-login-form-ad {
	padding-bottom: 110px;
}

.sparkline12-list {
	background-color: #e9f6f6;
}
</style>
</head>

<body>
	<c:if test="${empty sessionScope}">
		<c:redirect url="/logout" />
	</c:if>

	<div class="left-sidebar-pro">
		<nav id="sidebar" class="">
			<div class="sidebar-header">
				<a href="dashboard"><img class="main-logo"
					src="img/logo/logo.png" alt="" /></a> <strong><img
					src="img/logo/logosn.png" alt="" /></strong>
			</div>
			<div class="left-custom-menu-adp-wrap comment-scrollbar">
				<nav class="sidebar-nav left-sidebar-menu-pro">
					<ul class="metismenu" id="menu1">
						<li class="active"><a href="dashboard"> <i
								class="fa big-icon fa-home icon-wrap"></i> <span
								class="mini-click-non">DashBoard</span>
						</a></li>
						<c:if test="${sessionScope.roles.projectOnboarding == true }">
							<li><a href="project_onboarding" aria-expanded="false"><i
									class="fa big-icon fa-book icon-wrap"></i> <span
									class="mini-click-non">Project-OnBoarding</span></a></li>
						</c:if>
						<li><a class="has-arrow" href="dashboard"
							aria-expanded="false"><i
								class="fa big-icon fa fa-user-secret icon-wrap"></i> <span
								class="mini-click-non">Merchant</span> </a>
							<ul class="submenu-angle" aria-expanded="false">
								<c:if test="${sessionScope.roles.merchantOnboarding == true }">
									<li><a href="merchant_onboarding"><i
											class="fa fa-location-arrow sub-icon-mg" aria-hidden="true"></i>
											<span class="mini-sub-pro">Merchant's List</span> </a></li>
								</c:if>
								<c:if test="${sessionScope.roles.merchantOnboarding == true }">
									<li><a href="add_merchant"><i
											class="fa fa-plus sub-icon-mg" aria-hidden="true"></i> <span
											class="mini-sub-pro">Add Merchant</span> </a></li>
								</c:if>
								<c:if
									test="${sessionScope.roles.merchantSendCredentials == true }">
									<li><a href="send_merchant_credentials"><i
											class="fa fa-envelope sub-icon-mg" aria-hidden="true"></i> <span
											class="mini-sub-pro">Send Credentials</span> </a></li>
								</c:if>
								<c:if
									test="${sessionScope.roles.merchantMapBonusPoint == true }">
									<li><a href="map_and_modify_bonuspoints"><i
											class="fa fa-copy sub-icon-mg" aria-hidden="true"></i> <span
											class="mini-sub-pro">Map Bonus Points</span> </a></li>
								</c:if>
								<c:if test="${sessionScope.roles.merchantTopUpLimit == true }">
									<li><a href="merchant_limit_list"><i
											class="fa fa-location-arrow sub-icon-mg" aria-hidden="true"></i>
											<span class="mini-sub-pro">Merchant Limit List</span> </a></li>
								</c:if>
								<c:if test="${sessionScope.roles.merchantTopUpLimit == true }">
									<li><a href="update_merchant_topup_limit"><i
											class="fa fa-edit sub-icon-mg" aria-hidden="true"></i> <span
											class="mini-sub-pro">Top-Up Limit</span> </a></li>
								</c:if>
								<c:if test="${sessionScope.roles.merchantTopUpLimit == true }">
									<li><a href="merchant_reduce_limit"><i
											class="fa fa-edit sub-icon-mg" aria-hidden="true"></i> <span
											class="mini-sub-pro">Reduce Limit</span> </a></li>
								</c:if>
								<c:if test="${sessionScope.roles.merchantStatus == true }">
									<li><a href="enable_and_disable_merchant"><i
											class="fa fa-hand-o-right sub-icon-mg" aria-hidden="true"></i>
											<span class="mini-sub-pro">Merchant Status</span> </a></li>
								</c:if>
								<c:if
									test="${sessionScope.roles.merchantLoginPageInfo == true }">
									<li><a href="merchant_login_page_info"><i
											class="fa fa-user sub-icon-mg" aria-hidden="true"></i> <span
											class="mini-sub-pro">Login Page Info</span> </a></li>
								</c:if>
								<c:if
									test="${sessionScope.roles.merchantSendNotification == true }">
									<li><a href="send_notifications_merchant"><i
											class="fa fa-bell sub-icon-mg" aria-hidden="true"></i> <span
											class="mini-sub-pro">Send Notification</span> </a></li>
								</c:if>
								<c:if
									test="${sessionScope.roles.merchantReceivedOffers == true }">
									<li><a href="received_offers"><i
											class="fa fa-gift sub-icon-mg" aria-hidden="true"></i> <span
											class="mini-sub-pro">Received Offers</span> </a></li>
								</c:if>

								<c:if
									test="${sessionScope.roles.projectOnboarding == true and sessionScope.roles.merchantOnboarding == true and sessionScope.roles.createEmployee == true and sessionScope.roles.assignEmployeeRoles == true }">
									<li><a href="merchant_uploads"><i
											class="fa fa-external-link sub-icon-mg" aria-hidden="true"></i>
											<span class="mini-sub-pro">Uploads</span> </a></li>
								</c:if>

							</ul></li>
						<c:if
							test="${sessionScope.roles.modifyCustomerDetails == true or sessionScope.roles.customerSendNotification == true }">
							<li><a class="has-arrow" href="dashboard"
								aria-expanded="false"><i
									class="fa big-icon fa fa-user icon-wrap"></i> <span
									class="mini-click-non">Customer</span> </a>
								<ul class="submenu-angle" aria-expanded="false">
									<c:if
										test="${sessionScope.roles.modifyCustomerDetails == true }">
										<li><a href="modify_customer_details"><i
												class="fa fa-edit sub-icon-mg" aria-hidden="true"></i> <span
												class="mini-sub-pro">Modify Customer Details</span> </a></li>
									</c:if>
									<c:if
										test="${sessionScope.roles.customerSendNotification == true }">
										<li><a href="send_notifications_customer"><i
												class="fa fa-bell sub-icon-mg" aria-hidden="true"></i> <span
												class="mini-sub-pro">Send Notification</span> </a></li>
									</c:if>
									<c:if
										test="${sessionScope.roles.projectOnboarding == true and sessionScope.roles.merchantOnboarding == true and sessionScope.roles.createEmployee == true and sessionScope.roles.assignEmployeeRoles == true }">
										<li><a href="customer_uploads"><i
												class="fa fa-external-link sub-icon-mg" aria-hidden="true"></i>
												<span class="mini-sub-pro">Uploads</span> </a></li>
									</c:if>
								</ul></li>
						</c:if>
						<c:if
							test="${sessionScope.roles.createEmployee == true or sessionScope.roles.assignEmployeeRoles == true}">
							<li><a class="has-arrow" href="dashboard"
								aria-expanded="false"><i
									class="fa big-icon fa fa-desktop icon-wrap"></i> <span
									class="mini-click-non">Employee</span> </a>
								<ul class="submenu-angle" aria-expanded="false">
									<c:if test="${sessionScope.roles.createEmployee == true }">
										<li><a href="employee_list"><i
												class="fa fa-location-arrow sub-icon-mg" aria-hidden="true"></i>
												<span class="mini-sub-pro">Employees List</span> </a></li>
									</c:if>
									<c:if test="${sessionScope.roles.createEmployee == true }">
										<li><a href="add_employee"><i
												class="fa fa-plus sub-icon-mg" aria-hidden="true"></i> <span
												class="mini-sub-pro">Add Employees</span> </a></li>
									</c:if>
									<c:if test="${sessionScope.roles.assignEmployeeRoles == true }">
										<li><a href="assign_employee_roles"><i
												class="fa fa-edit sub-icon-mg" aria-hidden="true"></i> <span
												class="mini-sub-pro">Assign Roles</span> </a></li>
									</c:if>
								</ul></li>
						</c:if>
						<c:if test="${sessionScope.roles.paymentDetails == true }">
							<li><a href="payment_details" aria-expanded="false"><i
									class="fa fa-money icon-wrap"></i> <span class="mini-click-non">Payment
										Details</span> </a></li>
						</c:if>
						<c:if test="${sessionScope.roles.accountInfo == true }">
							<li><a href="bp_account_info" aria-expanded="false"><i
									class="fa fa-bank icon-wrap sub-icon-mg" aria-hidden="true"></i>
									<span class="mini-click-non">Account Info</span> </a></li>
						</c:if>
						<c:if test="${sessionScope.roles.reports == true }">
							<li><a href="reports" aria-expanded="false"><i
									class="fa fa-line-chart icon-wrap sub-icon-mg"
									aria-hidden="true"></i> <span class="mini-click-non">Reports</span>
							</a></li>
						</c:if>
						<%-- <c:if test="${sessionScope.roles.helpdeskAction == true }">
							<li><a href="helpdesk_action" aria-expanded="false"><i
									class="fa fa-hand-rock-o sub-icon-mg" aria-hidden="true"></i> <span
									class="mini-click-non">HelpDesk Action</span> </a></li>
						</c:if> --%>
						<c:if test="${sessionScope.roles.emailSettings == true }">
							<li><a href="email_settings" aria-expanded="false"><i
									class="fa fa-gear sub-icon-mg" aria-hidden="true"></i> <span
									class="mini-click-non">Email Settings</span> </a></li>
						</c:if>
					</ul>
				</nav>
			</div>
		</nav>
	</div>
	<!-- Start Welcome area -->
	<div class="all-content-wrapper">
		<div class="container-fluid">
			<div class="row">
				<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
					<div class="logo-pro">
						<a href="dashboard"><img class="main-logo"
							src="img/logo/logo.png" alt="" /></a>
					</div>
				</div>
			</div>
		</div>
		<div class="header-advance-area">
			<div class="header-top-area">
				<div class="container-fluid">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="header-top-wraper">
								<div class="row">
									<div class="col-lg-1 col-md-0 col-sm-1 col-xs-12">
										<div class="menu-switcher-pro">
											<button type="button" id="sidebarCollapse"
												class="btn bar-button-pro header-drl-controller-btn btn-info navbar-btn">
												<i class="fa fa-bars"></i>
											</button>
										</div>
									</div>
									<div class="col-lg-6 col-md-7 col-sm-6 col-xs-12">
										<div class="header-top-menu tabl-d-n">
											<ul class="nav navbar-nav mai-top-nav">
												<li class="nav-item"><a href="dashboard"
													class="nav-link">Home</a></li>

												<li class="nav-item"><a href="services"
													class="nav-link">Services</a></li>

												<li class="nav-item"><a
													href="http://www.biggbonuspoints.com/about"
													target="_blank" class="nav-link">About</a></li>
											</ul>
										</div>
									</div>
									<div class="col-lg-5 col-md-5 col-sm-12 col-xs-12">
										<div class="header-right-info">
											<ul class="nav navbar-nav mai-top-nav header-right-menu">
												<li class="nav-item"><a href="#" data-toggle="dropdown"
													role="button" aria-expanded="false"
													class="nav-link dropdown-toggle"> <i
														class="fa fa-user adminpro-user-rounded header-riht-inf"
														aria-hidden="true"></i> <span class="admin-name">${sessionScope.user.employeeName }</span>
														<i
														class="fa fa-angle-down adminpro-icon adminpro-down-arrow"></i>
												</a>
													<ul role="menu"
														class="dropdown-header-top author-log dropdown-menu animated zoomIn">
														<li><a href="profile"><span
																class="fa fa-user author-log-ic"></span>Profile</a></li>
														<li><a href="logout"><span
																class="fa fa-lock author-log-ic"></span>Log Out</a></li>
													</ul></li>
											</ul>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Mobile Menu start -->
			<div class="mobile-menu-area">
				<div class="container">
					<div class="row">
						<div class="col-lg-12 col-md-12 col-sm-12 col-xs-12">
							<div class="mobile-menu">
								<nav id="dropdown">
									<ul class="mobile-menu-nav">
										<li><a data-toggle="collapse" data-target="#Charts"
											href="dashboard">DashBoard <span
												class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
										</li>
										<c:if test="${sessionScope.roles.projectOnboarding == true }">
											<li><a data-toggle="collapse" data-target="#demo"
												href="project_onboarding">Project OnBoarding <span
													class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
											</li>
										</c:if>
										<li><a data-toggle="collapse" data-target="#others"
											href="#">Merchant <span
												class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
											<ul id="others" class="collapse dropdown-header-top">
												<c:if
													test="${sessionScope.roles.merchantOnboarding == true }">
													<li><a href="merchant_onboarding">Merchant's List</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.merchantOnboarding == true }">
													<li><a href="add_merchant">Add Merchant</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.merchantSendCredentials == true }">
													<li><a href="send_merchant_credentials">Send
															Credentials</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.merchantMapBonusPoint == true }">
													<li><a href="map_and_modify_bonuspoints">Map Bonus
															Points</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.merchantTopUpLimit == true }">
													<li><a href="merchant_limit_list">Merchant Limit
															List</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.merchantTopUpLimit == true }">
													<li><a href="update_merchant_topup_limit">Top-Up
															Limit</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.merchantTopUpLimit == true }">
													<li><a href="merchant_reduce_limit">Reduce Limit</a></li>
												</c:if>
												<c:if test="${sessionScope.roles.merchantStatus == true }">
													<li><a href="enable_and_disable_merchant">Merchant
															Status</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.merchantLoginPageInfo == true }">
													<li><a href="merchant_login_page_info">Login Page
															Info</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.merchantSendNotification == true }">
													<li><a href="send_notifications_merchant">Send
															Notification</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.merchantReceivedOffers == true }">
													<li><a href="received_offers">Received Offers</a></li>
												</c:if>
												<c:if
													test="${sessionScope.roles.projectOnboarding == true and sessionScope.roles.merchantOnboarding == true and sessionScope.roles.createEmployee == true and sessionScope.roles.assignEmployeeRoles == true }">
													<li><a href="merchant_uploads">Uploads</a></li>
												</c:if>
											</ul></li>
										<c:if
											test="${sessionScope.roles.modifyCustomerDetails == true or sessionScope.roles.customerSendNotification == true }">
											<li><a data-toggle="collapse"
												data-target="#Miscellaneousmob" href="#">Customer <span
													class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
												<ul id="Miscellaneousmob"
													class="collapse dropdown-header-top">
													<c:if
														test="${sessionScope.roles.modifyCustomerDetails == true }">
														<li><a href="modify_customer_details">Modify
																Customer Details</a></li>
													</c:if>
													<c:if
														test="${sessionScope.roles.customerSendNotification == true }">
														<li><a href="send_notifications_customer">Send
																Notification</a></li>
													</c:if>
													<c:if
														test="${sessionScope.roles.projectOnboarding == true and sessionScope.roles.merchantOnboarding == true and sessionScope.roles.createEmployee == true and sessionScope.roles.assignEmployeeRoles == true }">
														<li><a href="customer_uploads">Uploads</a></li>
													</c:if>
												</ul></li>
										</c:if>
										<c:if
											test="${sessionScope.roles.createEmployee == true or sessionScope.roles.assignEmployeeRoles == true}">
											<li><a data-toggle="collapse" data-target="#Chartsmob"
												href="#">Employee <span
													class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
												<ul id="Chartsmob" class="collapse dropdown-header-top">
													<c:if test="${sessionScope.roles.createEmployee == true }">
														<li><a href="employee_list">Employees List</a></li>
													</c:if>
													<c:if test="${sessionScope.roles.createEmployee == true }">
														<li><a href="add_employee">Add Employees</a></li>
													</c:if>
													<c:if
														test="${sessionScope.roles.assignEmployeeRoles == true }">
														<li><a href="assign_employee_roles">Assign Roles</a>
														</li>
													</c:if>
												</ul></li>
										</c:if>
										<c:if test="${sessionScope.roles.paymentDetails == true }">
											<li><a data-toggle="collapse" data-target="#Tablesmob"
												href="payment_details">Payment Details <span
													class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
											</li>
										</c:if>
										<c:if test="${sessionScope.roles.accountInfo == true }">
											<li><a data-toggle="collapse" data-target="#formsmob"
												href="bp_account_info">Account Info<span
													class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
											</li>
										</c:if>
										<c:if test="${sessionScope.roles.reports == true }">
											<li><a data-toggle="collapse" data-target="#Appviewsmob"
												href="reports">Reports <span
													class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
											</li>
										</c:if>
										<%-- <c:if test="${sessionScope.roles.helpdeskAction == true }">
											<li><a data-toggle="collapse" data-target="#Pagemob"
												href="helpdesk_action">HelpDesk Action <span
													class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
											</li>
										</c:if> --%>
										<c:if test="${sessionScope.roles.emailSettings == true }">
											<li><a data-toggle="collapse" data-target="#Pagemob"
												href="email_settings">Email Settings <span
													class="admin-project-icon adminpro-icon adminpro-down-arrow"></span></a>
											</li>
										</c:if>
									</ul>
								</nav>
							</div>
						</div>
					</div>
				</div>
			</div>
			<!-- Mobile Menu end -->