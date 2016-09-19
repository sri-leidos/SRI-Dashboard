<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
    
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=Edge">
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
			
    <c:set var="url">${pageContext.request.requestURL}</c:set>
    <base href="${fn:substring(url, 0, fn:length(url) - fn:length(pageContext.request.requestURI))}${pageContext.request.contextPath}/" />
		
		<meta property="author" content="Leidos" />
		<meta property="description" content="The Smart Roadside Initiative administration page for managing users." /> 
		<!-- Open Graph tags -->
    <meta property="og:title" content="Smart Roadside Initiative Administration Page" />
    <meta property="og:determiner" content="the" />
    <meta property="og:site_name" content="Smart Roadside Initiative" />
    <meta property="og:description" content="The Smart Roadside Initiative administration page for managing users." />
    <meta property="og:type" content="website" />
    <meta property="og:image" content="images/open_graph_logo.png" />
    <!-- // -->
		<title>Smart Roadside Initiative Administration</title>

		<link rel="stylesheet" href="vendor/animate/animate.css">
		<link rel="stylesheet" href="vendor/font-awesome/css/font-awesome.css">
		<link rel="stylesheet" href="styles/admin/main.css">
		<!-- Favicon -->
		<link rel="icon" type="image/png" href="images/favicon.png" />
		<!-- LOAD ANGULAR EARLY, NOT ADVISED BUT THIS AVOIDS EXPOSURE OF EXPRESSIONS -->
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.27/angular.js" type="text/javascript"></script>
		<script type="text/javascript">
		//<![CDATA[
		(window.angular)||document.write('<script type="text/javascript" src="vendor/angular/angular.js"><\/script>');//]]>
		</script>
		<!-- // -->
	</head>
	
	<body ng-app="SRI.ADMIN">
		<!-- BEGIN TOP NAVIGATION -->
		<jsp:include page="includes/top-navigation.jsp">
			<jsp:param name="usersActive" value="active"></jsp:param>
		</jsp:include>
		<!-- // -->
		<div class="stage">
			<div class="container">
				<!-- THIS WILL BE THE CONTAINER FOR OUR VIEWS -->
				<div id="mainContainer" class="clearfix" style="margin-bottom: 70px;" ui-view>						
					<!-- THIS OUR LOADER IMAGE -->
					<div>
						<img src="images/bar-loader.gif" id="" class="loading-bar" alt="loader" />
					</div>
					<!-- // -->
				</div>
				<!-- // -->
			</div>
		</div>
		<!-- // -->
		<!-- FOOTER -->
		<div ng-include="'admin/includes/admin-footer.html'"></div>
		<!-- // -->
		<!-- JS DEPENDENCIES -->
		<script src="js/jquery-1.11.0.js"></script>
		<script src="vendor/angular-ui-router/angular-ui-router.js"></script>
		<script src="vendor/bootstrap/custom/js/bootstrap.js"></script>
		<!-- // -->
    <!-- CONFIGURATIONS & ROUTES -->
    <script>
	    console.info('SRI: Administration Initiated');  
	    angular.module('SRI.ADMIN', ['ui.router']);
    </script>
    <script src="admin/scripts/configs/UserRouteConfig.js"></script>
    <!-- // -->
		<!-- CONTROLLERS -->
		<script src="admin/scripts/controllers/user/ListUsersCTRL.js"></script>
		<script src="admin/scripts/controllers/user/EditUserCTRL.js"></script>
		<script src="admin/scripts/controllers/user/CreateUserCTRL.js"></script>
		<script src="admin/scripts/controllers/navigation/NavigationCTRL.js"></script>
		<!-- // -->
		<!-- SERVICES -->
		<script src="admin/scripts/services/UserFactory.js"></script>
		<script src="admin/scripts/services/SiteFactory.js"></script>
		<script src="admin/scripts/directives/Notifyr.js"></script>
		<!-- // -->
	</body>
	
</html>