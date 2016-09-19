<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=Edge" />
		<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">

		<title>Insert title here</title>
		
<!-- 	Bootstrap UI Framework -->
		<link rel="stylesheet" href="/DashCon/vendor/bootstrap/custom/css/bootstrap.min.css" />
		
		<style>

		</style>
		
<!-- 	jQuery		 -->
		<script src="/DashCon/js/jquery-1.11.0.js"></script>
	</head>
	<body>
	
<!-- 	NAVIGATION MENU -->
		<jsp:include page="/includes/main/navigation-menu.jsp"></jsp:include>
		
<!-- 	BEGIN SITE CONTENT -->

<!-- 	This is the main stage for the site, do not change nor style -->
<!-- 	it is a container -->
		<div id="mainContent" class="container clearfix">
		
<!-- 	Use class row for every module row in the page -->
			<div class="row">
<!-- 		We usually use col-md-6 for tablets and desktop and col-12-sm for mobile for our module columns -->
				<div class="col-md-6 col-sm-12 well">
					<h1>Left Column</h1>
					<p>Paragraph</p>
					<button class="btn btn-primary btn-rounded btn-lg pull-right">
						btn btn-primary pull-right
					</button>
					<p>Paragraph after float</p>
				</div>
				
				<div class="col-md-6 col-sm-12 well">
					<h1>Right Column</h1>
					<p>Paragraph</p>
					<button class="btn btn-info btn-lg pull-left">
						btn btn-info btn-large pull-left
					</button>
					<p>Paragraph after float</p>
				</div>
			
			</div>
			
			<div class="row">
<!-- 			Use for 3 module columns -->
				<div class="col-md-4 col-sm-12 well">
					<h2>Left Column</h2>
					<p>Paragraph</p>
					<button class="btn btn-default btn-rounded">
						btn btn-default
					</button>
				</div>
				<div class="col-md-4 col-sm-12 well">
					<h2>Middle Column</h2>
					<p>Paragraph</p>
					<button class="btn btn-success">
						btn btn-success
					</button>
				</div>
				<div class="col-md-4 col-sm-12 well">
					<h2>Right Column</h2>
					<p>Paragraph</p>
					<button class="btn btn-warning">
						btn btn-warning
					</button>
				</div>
			</div>
		
		</div>
		
		<div id="expandedContent" class="container-fluid clearfix well">
		
			<div class="row">
<!-- 			Use for 3 module columns -->
				<div class="col-md-3 col-sm-12">
					<h2>1st Column</h2>
					<p>Paragraph</p>
					<button class="btn btn-danger btn-rounded btn-sm">
						btn btn-danger btn-sm
					</button>
				</div>
				<div class="col-md-3 col-sm-12">
					<h2>2nd Column</h2>
					<p>Paragraph</p>
					<button class="btn btn-primary btn-rounded btn-sm">
						btn btn-primary btn-xs
					</button>
				</div>
				<div class="col-md-3 col-sm-12">
					<h2>3rd Column</h2>
					<p>Paragraph</p>
					<button class="btn btn-success btn-rounded">
						<span class="glyphicon glyphicon-star"></span>
						Button w/ Icon
					</button>
				</div>
				<div class="col-md-3 col-sm-12">
					<h2>4th Column</h2>
					<p>Paragraph</p>
					<button class="btn btn-default">
						Button w/ Icon
						<span class="glyphicon glyphicon-star"></span>
					</button>
				</div>
			</div>
		
		</div>
	
<!-- 	END SITE CONTENT -->


<!-- 	FOOTER -->
<!-- 	////// -->

<!-- 	Bootstrap JS Components -->
		<script src="/DashCon/vendor/bootstrap/custom/js/bootstrap.js"></script>
	</body>
</html>