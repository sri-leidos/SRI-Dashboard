<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
		<meta http-equiv="cache-control" content="max-age=0" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
		<meta http-equiv="pragma" content="no-cache" />
    <meta property="author" content="Leidos" />
    <meta property="description" content="The Smart Roadside Initiative truck screening application for weight stations." /> 
    <!-- Open Graph tags -->
    <meta property="og:title" content="Smart Roadside Initiative Dashboard" />
    <meta property="og:determiner" content="the" />
    <meta property="og:site_name" content="Smart Roadside Initiative" />
    <meta property="og:description" content="The Smart Roadside Initiative truck screening application for weight stations." />
    <meta property="og:type" content="website" />
    <meta property="og:image" content="images/open_graph_logo.png" />
    <!-- // -->
		<title>Smart Roadside Initiative Dashboard</title>
		<!-- OPEN SANS FONT ( 300, 400 | Italic, 600 ) | NOTE: Google Font -->
		<link href="http://fonts.googleapis.com/css?family=Open+Sans:400,300,400italic,600" rel="stylesheet" type="text/css">
		<!-- This is our customized bootstrap framework -->
		<link rel="stylesheet" href="/DashCon/vendor/bootstrap/custom/css/bootstrap.css" />
		<!-- NOTE: main.css contains all styles compiled using less, 1 http call = faster rendering. -->
		<link rel="stylesheet" href="build/styles/main.css" />
		<!-- Animation effects library used mainly by WowJS -->
		<link rel="stylesheet" href="../vendor/animate/animate.css" />
		<!-- FontAwesome icons library -->
		<link rel="stylesheet" href="../vendor/font-awesome/css/font-awesome.css" />
    <!-- FAVICON -->
    <link rel="icon" type="image/png" href="/DashCon/images/favicon.png" />
	</head>
	
	<body ng-app="SRI.DASHBOARD">
	<!-- BEGIN MAIN WORK AREA -->
		<div id="wrapper" class="container-fluid sticky-footer-wrap">
		  <!-- MainCtrl acts as a parent controller for our sidebar and main content controllers -->
			<div id="main" class="row sticky-footer-main" ng-controller="MainCTRL as main">
				<!-- TOP NAVIGATION -->
				<jsp:include page="includes/top-navigation.jsp"></jsp:include>
				<!-- // -->
				<div id="sideBar" ng-controller="TruckFeedCTRL as truckfeed" class="col-lg-3 col-md-5 col-sm-12 col-xs-12 wow fadeInDown">
					<!-- include our truckfeed controls -->
					<jsp:include page="includes/truck-entry-controls.jsp"></jsp:include>
					<!-- These are our truck feed representing tucks entering WIMs -->
					<div id="vehicleEntries" class="entry-wrapper">
						<div ng-include="'templates/TruckFeedTMPL.html'"></div>
					</div>
				</div>
				<!-- These holds all our modules and everything with it -->
				<div id="mainContent" class="col-lg-9 col-md-7">
					<div id="mainWrapper">
					  <!-- This our loader image while bootstrapping and kickstarting the application -->
						<div id="mainLoader" class="">
							<div class="loader-wrapper">
								<img src="../images/circular-loader.gif" class="loader-image" alt="Loading" />
							</div>
						</div>
						<!-- This is the MiCJIN button that opens our frame for the external site
						Note: This only shows for Michigan weigh stations and members only -->
						<div class="row" style="padding: 10px 10px 0 20px;" ng-controller="RightMenuCTRL as rightMenu">
						  <ul class="nav nav-pills" id="mainMenu" ng-show="rightMenu.user.siteId == '1'" ng-cloak>
					      <li class="pull-right">
					        <a href="#" ng-click="triggerFrame()">
					          <span class="fa fa-external-link-square"></span> MiCJIN
					        </a>
					      </li>
					    </ul>
						</div>
            <!-- This is MiCJIN external website within a frame for Michigan weigh station members only -->
            <div id="frameContainer"class="col-xs-12 col-sm-4 hidden">
              <button id="frameCloser" class="btn btn-sm btn-danger">
                <span class="glyphicon glyphicon-remove-sign"></span>
              </button>
              <iframe src="http://micjin.state.mi.us"></iframe>
            </div>
						<!-- These 3 columns are our module containers which takes up 3 equal width on larger screens and
						collapses into 1 on smaller resolution or screen devices -->
						<div id="left_panel" class="column col-xs-12 col-md-12 col-lg-4 wow fadeInUp" ng-controller="LeftPanelCTRL"
						  style="margin-top: 5px;">
						</div>
						<div id="middle_panel" class="column col-xs-12 col-md-12 col-lg-4 wow fadeInUp"
              style="margin-top: 5px;">
						</div>
						<div id="right_panel" class="column col-xs-12 col-md-12 col-lg-4 wow fadeInUp"
              style="margin-top: 5px;">
						</div>
						<!-- // -->
					</div>
				
				</div>
				
			</div>
		</div>
		<!-- END MAIN WORK AREA -->
		<!-- // -->
		
		<!-- STICKY FOOTER | NOTE: I made changes from the original sticky footer code, 
			 so it ain't so sticky anymore. -->
		<div id="footer" class="sticky-footer hidden-xs">
			<p class="pull-right"><a href="http://www.leidos.com" title="Leidos">leidos™</a></p>
			<p><a href="/DashCon" title="SRI Home">Smart Roadside Initiative</a></p>
		</div>
		<!-- // -->
		<!-- These are available on mobile devices to switch views from the sidebar
		to our main content for better screen maximization -->
		<div id="mobileTrigger" class="visible-xs visible-sm">
			<button id="phoneSideBar" class="btn btn-block btn-primary">
				<span class="fa fa-toggle-off"></span>
				Show Modules
			</button>
			<button id="phoneModules" class="btn btn-block btn-primary" style="display:none; margin-top: 0px">
				<span class="fa fa-toggle-on"></span>
				Show Truck Feed
			</button>
		</div>
    <!-- JS DEPENDENCIES -->
    <!-- Start jQuery first because Angular because life is too short to just taste vanilla -->
    <script src="../vendor/jquery/jquery-1.11.0.js"></script>
    <!-- Use a CDN for Angular and load locally when it fails -->
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.27/angular.js" type="text/javascript"></script>
    <script type="text/javascript">
    //<![CDATA[
    (window.angular)||document.write('<script type="text/javascript" src="/DashCon/vendor/angular/angular.js"><\/script>');//]]>
    </script>
    <!-- // -->
		<!-- OTHER DEPENDECIES -->
		<!-- WOWJS is our reveal animation library, make sure animate.css is a dependency -->
		<script type="text/javascript" src="../vendor/wowjs/wow.js"></script>
		<!-- MomentJS for a better date and time library -->
		<script type="text/javascript" src="../vendor/moment/moment.js"></script>
		<!-- Tadaaa Bootstrap -->
		<script type="text/javascript" src="../vendor/bootstrap/custom/js/bootstrap.js"></script>
    <!-- Start our Angular application using the built file, look into Gulp -->
    <script type="text/javascript" src="build/scripts/scripts.min.js"></script>
    <!-- Note: Refer to includes/scripts.jsp for list of files or to include it for development work -->
    <!-- // -->
	</body>
</html>