<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			
		<title>SRI Admin - Login</title>

		<link rel="stylesheet" href="styles/admin/main.css">
		<link rel="stylesheet" href="styles/login.specific.css" />
		<link rel="stylesheet" href="/DashCon/vendor/animate/animate.css" />
		
		<link rel="icon" type="image/png" href="images/favicon.png" />
		<!-- // -->
	</head>
	<body ng-app>
	
		<div id="stage" class="container">
			
			<div class="row">
        <a href="/DashCon" class="sri-home-link" title="SRI Home">
				  <img src="images/sri-alt-logo2.png" class="col-xs-4 col-xs-offset-4 col-md-2 col-md-offset-5 login-logo">
				</a>
			</div>
			
			<div class="row">
				<div id="loginBlock" class="col-md-4 col-md-offset-4" style="margin-top: 25px;">
					<div id="" class="panel panel-default">
						<div class="panel-heading hidden">
							<span class="glyphicon glyphicon-lock"></span>
							SRI Secure Login
						</div>
						<div class="panel-body" style="border: none;">
							<form role="form" action="j_security_check" method="POST">
								<div class="form-group">
									<label for="userId">Username:</label>
									<input type="text" id="j_username" name="j_username" class="login-text form-control" placeholder="Enter username" />
								</div>
								<div class="form-group" style="margin-bottom: 35px;">
									<label for="userPassword">Password:</label>
									<input type="password" id="j_password" name="j_password" class="login-text form-control" placeholder="Enter password" />
								</div>
                
                <button type="submit" class="btn btn-wide btn-primary hidden-xs" id="login">
                  <span class="glyphicon glyphicon-log-in"></span>
                  Sign In
                </button>
                
                <a href="/DashCon" class="btn btn-wide btn-default hidden-xs" id="login">
                  <span class="glyphicon glyphicon-home"></span>
                  Back to Home
                </a>
                
                <!-- These are visible in small devices like smartphones -->
                <button type="submit" class="btn btn-block btn-primary visible-xs" id="login">
                  <span class="glyphicon glyphicon-log-in"></span>
                  Sign In
                </button>
                
                <a href="/DashCon" class="btn btn-block btn-default visible-xs" id="login">
                  <span class="glyphicon glyphicon-home"></span>
                  Back to Home
                </a>
								
								<p class="bottom-link">
									<a href="/DashCon/access">Having trouble logging in?</a>
								</p>
								
							</form>
						</div>
					</div>
				</div>
			</div>
			
		</div>
		
		<!-- FOOTER -->
		<footer class="container-fluid login-footer navbar navbar-fixed-bottom navbar-default hidden-xs hidden-sm">
		  <div class="container">
		    
		    <ul class="nav nav-pills pull-left" style="font-size: 12px">
		      <li><a href="/DashCon">Smart Roadside Initiative</a></li>
		    </ul>
		    
		    <ul class="nav nav-pills pull-right hidden-sm hidden-xs" style="font-size: 12px">
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
				// set auto focus to our username input box
				$(document).ready( function() {
					$('#j_username').focus();
				});
			})(window, document, jQuery)
		</script>
		<!--<script src="vendor/angular-ui-router/angular-ui-router.js"></script>
		<script src="vendor/bootstrap/custom/js/bootstrap.js"></script>-->
		<!-- // -->
	</body>
</html>