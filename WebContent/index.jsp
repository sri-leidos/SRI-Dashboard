<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" />
		<meta name="author" content="Leidos" />
		<meta name="description" content="The Smart Roadside Initiative main web application for weigh station management and organization." />
		<!-- Open Graph tags -->
    <meta property="og:title" content="Smart Roadside Initiative Main Page" />
    <meta property="og:determiner" content="the" />
    <meta property="og:site_name" content="Smart Roadside Initiative" />
    <meta property="og:description" content="The Smart Roadside Initiative main web application for weigh station management and organization." />
    <meta property="og:type" content="website" />
    <meta property="og:image" content="images/open_graph_logo.png" />
    <!-- // -->
		<title>Smart Roadside Initiative - Home</title>
		<!-- Styles -->
		<link rel="stylesheet" href="/DashCon/styles/main.css" />
		<!-- Google Fonts -->
		<link href='http://fonts.googleapis.com/css?family=Istok+Web:400,700' rel='stylesheet' type='text/css' />
		<link href='http://fonts.googleapis.com/css?family=Raleway' rel='stylesheet' type='text/css' />
		<!-- Animation effects -->
		<link rel="stylesheet" href="/DashCon/vendor/animate/animate.css" />
		<!-- Favicon -->
		<link rel="icon" type="image/png" href="/DashCon/images/favicon.png" />
    <!-- preload our reveal animation library -->
    <script src="/DashCon/vendor/wowjs/wow.js" type="text/javascript"></script>
	</head>
	<body>
		<!-- SRI Preloader Animation -->
		<div class="loader-mask hidden">
			<img src="images/sri-logo.png" class="loader-logo transform">
		</div>
		<!-- Page wrapper -->
		<div class="stage container-fluid full-height">
			<!-- Top Navigation -->
			<jsp:include page="/includes/splash/navigation-menu.jsp">
				<jsp:param value="custom" name="class"/>
			</jsp:include>
			<!-- Main Content -->
			<div id="mainContent" class="container">
				<div class="container jumbotron">
					<h1 class="wow fadeInUp main-text">SMART ROADSIDE INITIATIVE</h1>
					<!-- Main buttons for Dashbaord and Administration -->
  					<div class="clearfix" style="margin-top: 130px; padding: 15px;">
  						<a href="/DashCon/dashboard"
  						   class="btn btn-opaque btn-lg btn-boxed sri-main-btn hidden-xs wow fadeInDown"
  						   data-wow-delay=".5s">Dashboard</a>
  						<a href="/DashCon/admin"
  						   class="btn btn-transparent btn-lg btn-boxed sri-main-btn hidden-xs wow fadeInDown"
  						   data-wow-delay=".8s"">Administration</a>
  						<!-- Mobile devices' buttons -->
  						<a href="/DashCon/dashboard"
  						   class="btn btn-opaque btn-lg btn-boxed btn-block visible-xs wow fadeInDown"
  						   data-wow-delay=".5s"
  						   style="margin-bottom: 20px;">Dashboard</a>
  						<a href="/DashCon/admin"
  						   class="btn btn-transparent btn-lg btn-block btn-boxed visible-xs wow fadeInDown"
  						   data-wow-delay=".5s">Administration</a>
  					</div>
  					
				</div>
			</div>
			<!-- Video Background -->
			<video autoplay loop poster="./images/highway-speed.png" id="bgvid">
        <source src="./video/highway-video.mp4" type="video/mp4">
        <source src="./video/highway-video.ogg" type="video/ogg">
        <source src="./images/highway-video.webm" type="video/webm">
			</video>
		</div>
	
		<script src="/DashCon/js/jquery-1.11.0.js"></script>
		<script src="/DashCon/vendor/respondjs/respond.src.js"></script>
		<!-- Google Material effect -->
		<script src="/DashCon/js/material-effect.js"></script>
		<script src="/DashCon/vendor/underscore/underscore.js"></script>
		<script src="/DashCon/vendor/bootstrap/custom/js/bootstrap.js"></script>
		<script src="/DashCon/js/sri.main.js"></script>
		
	</body>
</html>