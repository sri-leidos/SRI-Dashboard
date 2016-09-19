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
			
		<title>SRI Admin - Sites</title>

<!--    <link rel="stylesheet" href="styles/admin.css"> -->
    <link rel="stylesheet" href="profile/styles/main.css">
		
		<link rel="icon" type="image/png" href="images/favicon.png" />
		
		<!-- LOAD ANGULAR EARLY, NOT ADVISED BUT THIS AVOIDS EXPOSURE OF EXPRESSIONS -->
		<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.27/angular.js" type="text/javascript"></script>
		<script type="text/javascript">
		//<![CDATA[
		(window.angular)||document.write('<script type="text/javascript" src="vendor/angular/angular.js"><\/script>');//]]>
		</script>
		<!-- // -->
		
	</head>
	
	<body ng-app="ADMIN_SITES">
		<!-- BEGIN TOP NAVIGATION -->
		<jsp:include page="includes/top-navigation.jsp">
			<jsp:param name="sitesActive" value="active"></jsp:param>
		</jsp:include>


		<div class="stage">
		
			<div class="container">
				<!-- THIS WILL BE THE CONTAINER FOR OUR VIEWS -->
				<div id="mainContainer" ui-view>						
					<!-- THIS OUR LOADER IMAGE -->
					<div>
						<img src="images/bar-loader.gif" id="" class="loading-bar" alt="loader" />
					</div>
					<!-- /// -->
				</div>
				<!-- /// -->
			</div>
			
		</div>
		
		<!-- FOOTER -->
		<div ng-include="'admin/includes/admin-footer.html'"></div>
		<!-- // -->
		
		
		
		<!-- JS DEPENDENCIES -->
		<script src="js/jquery-1.11.0.js"></script>
		<script src="vendor/angular-ui-router/angular-ui-router.js"></script>
		<script src="vendor/bootstrap/custom/js/bootstrap.js"></script>
		<!-- // -->
		<!-- APP DEVELOPMENT FILES -->
		<script>
			// Initialize our app's namespace
			var sri = sri || {};
			console.log('Smart Roadside Initiative: Admin Initiated');
		</script>
		<!-- // -->
		
		<!-- CONTROLLERS -->
		<script src="admin/scripts/controllers/site/ListSitesCTRL.js"></script>
		<script src="admin/scripts/controllers/site/CreateSiteCTRL.js"></script>
		<script src="admin/scripts/controllers/site/EditSiteCTRL.js"></script>
		<!-- // -->
		
		<!-- SERVICES -->
		<script src="admin/scripts/services/SiteFactory.js"></script>
		<script src="admin/scripts/directives/Notifyr.js"></script>
		<script src="admin/scripts/controllers/navigation/NavigationCTRL.js"></script>
		<!-- // -->
		
		<!-- CONFIGURATIONS & ROUTES -->
		<script src="admin/scripts/configs/SiteRouteConfig.js"></script>
		<script src="admin/scripts/app.js"></script>
		<!-- // -->
		
	</body>
	
</html>