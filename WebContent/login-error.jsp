<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html lang="en">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="author" content="Leidos" />
    <meta name="description" content="The Smart Roadside Initiative login page for granting registered access to the SRI system." />
    <!-- Open Graph tags -->
    <meta property="og:title" content="Smart Roadside Initiative Login Page" />
    <meta property="og:determiner" content="the" />
    <meta property="og:site_name" content="Smart Roadside Initiative" />
    <meta property="og:description" content="The Smart Roadside Initiative login page for granting registered access to the SRI system." />
    <meta property="og:type" content="website" />
    <meta property="og:image" content="images/open_graph_logo.png" />
    <!-- // -->
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
		<title>SRI Admin - Login Error</title>

		<link rel="stylesheet" href="styles/admin/main.css">
		<link rel="stylesheet" href="styles/login.specific.css">
		
		<link rel="icon" type="image/png" href="images/favicon.png">
    
	</head>
	<body>
		<div id="stage" class="container">
      
      <div class="row">
        <a href="/DashCon" class="sri-home-link" title="SRI Home">
          <img src="images/sri-alt-logo2.png" class="col-xs-4 col-xs-offset-4 col-md-2 col-md-offset-5 login-logo">
        </a>
      </div>
			
			<div class="row">
				<div id="loginErrorBlock" class="col-md-4 col-md-offset-4">
					<div id="" class="panel panel-default">
						<div class="panel-heading">
							<span class="glyphicon glyphicon-exclamation-sign"></span>
							Login Error
						</div>
						<div class="panel-body">
							<button id="back-button" class="btn btn-block btn-primary" onclick="window.history.back()">
								<span class="glyphicon glyphicon-circle-arrow-left"></span>
								Back to Login
							</button>
							<p class="trouble-link">
								<a href="/DashCon/access" style="color: white;">Having trouble logging in?</a>
							</p>
						</div>
					</div>
				</div>
			</div>
			
		</div>
    
    <!-- FOOTER -->
    <footer class="container-fluid login-footer navbar navbar-fixed-bottom navbar-default hidden-xs hidden-sm">
      <div class="container">
        
        <ul class="nav nav-pills pull-left" style="">
          <li><a href="/DashCon">Smart Roadside Initiative</a></li>
        </ul>
        
        <ul class="nav nav-pills pull-right hidden-sm hidden-xs" style="">
          <li><a href="/DashCon">Home</a></li>
          <li><a href="/DashCon/downloads">Downloads</a></li>
          <li><a href="/DashCon/profile">Login</a></li>
        </ul>
      
      </div>
    </footer>
    <!-- // -->
	
		<!-- JS DEPENDENCIES -->
		<script src="js/jquery-1.11.0.js"></script>
		<script>
			;(function(window, document, $, undefined) {
	      $(function() {
	          $('#back-button').focus();
	        });
			})(window, document, jQuery);
		</script>
		<!-- // -->
	</body>
</html>