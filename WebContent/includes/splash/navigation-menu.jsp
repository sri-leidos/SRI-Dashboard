		
		
		<nav class="navbar navbar-${param.class} wow fadeInDown" data-wow-delay="1s" id="mainNav" role="navigation">
		  <div class="container">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="/DashCon/" title="Smart Roadside Initiative">
		      	<img src="/DashCon/images/sri-alt-logo.png" id="mainLogo" class="brand-logo" alt="Smart Roadside Initiative" />
		      	<img src="/DashCon/images/sri-logo-dark.png" id="mainLogo2" class="brand-logo hidden" alt="Smart Roadside Initiative" />
		      </a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-right">
		        <li class="line-parent top-link active" id="users">
		        	<a href="/DashCon/" class="home material-effect" title="Home">
			        	HOME
		        	</a>
		        	<span class="line top"></span>
		        	<span class="line bottom"></span>
		        	<span class="line right"></span>
		        	<span class="line left"></span>
	        	</li>
<!-- 		        <li class="line-parent top-link" id="sites"> -->
<!-- 		        	<a href="#/about" class="about material-effect" title="About"> -->
<!-- 		        		ABOUT -->
<!-- 		        	</a> -->
<!-- 		        	<span class="line top"></span> -->
<!-- 		        	<span class="line bottom"></span> -->
<!-- 		        	<span class="line right"></span> -->
<!-- 		        	<span class="line left"></span> -->
<!-- 	        	</li> -->
		        <li class="line-parent top-link" id="devices">
		        	<a href="/DashCon/downloads" class="mobile material-effect" title="SRI Download Page">
		        		DOWNLOADS
	        		</a>
		        	<span class="line top"></span>
		        	<span class="line bottom"></span>
		        	<span class="line right"></span>
		        	<span class="line left"></span>
        		</li>
	        	<% if( request.getUserPrincipal() == null ) { %>
		        	<li class="line-parent ">
 		        		<a href="/DashCon/profile" title="Login" class="login material-effect">
		        			LOGIN
		        		</a>
			        	<span class="line top"></span>
			        	<span class="line bottom"></span>
			        	<span class="line right"></span>
			        	<span class="line left"></span>
	        		</li>
	        	<% } else { %>
		        	<li class="line-parent">
		        		<a href="/DashCon/profile" title="Profile" class="profile material-effect">
		        			PROFILE
	        			</a>
			        	<span class="line top"></span>
			        	<span class="line bottom"></span>
			        	<span class="line right"></span>
			        	<span class="line left"></span>
		        	</li>
		        	<li class="line-parent logout">
		        		<a href="/DashCon/logout.jsp" title="Logout" class="login link material-effect">
		        			LOGOUT
	        			</a>
			        	<span class="line top"></span>
			        	<span class="line bottom"></span>
			        	<span class="line right"></span>
			        	<span class="line left"></span>
		       		</li>
        		<% } %>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>		
		
		<nav class="navbar navbar-default wow fadeInDown navbar-fixed-top hidden" id="scrollNav" role="navigation">
		  <div class="container">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="/DashCon/">Smart Roadside Initiative</a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
		      <ul class="nav navbar-nav navbar-right">
		        <li class="top-link active" id="users"><a href="#/users" class="home" title="Users"><span class="">HOME</span></a></li>
		        <li class="top-link" id="sites"><a href="#/sites" class="about" title="Sites"><span class="">ABOUT</span></a></li>
		        <li class="top-link" id="devices"><a href="#/devices" class="dashcon" title="Devices"><span class="">DASHCON</span></a></li>
<!-- 		        <li class="top-link" id="messages"><a href="#/messages" title="Messages"><span class="glyphicon glyphicon-envelope"></span> <span class="">Messages</span></a></li> -->
<!-- 		      <form class="navbar-form navbar-left" role="search"> -->
<!-- 		        <div class="form-group"> -->
<!-- 		          <input type="text" class="form-control" placeholder="Search User"> -->
<!-- 		        </div> -->
<!-- 		        <button type="submit" class="btn btn-info"><span class="glyphicon glyphicon-search"></span></button> -->
<!-- 		      </form> -->
		        <li class=""><a href="/DashCon/login" class="login">
		        	<span>LOGIN</span>
		        </a></li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>