		
		<nav class="navbar navbar-inverse navbar-fixed-top" id="scrollNav" role="navigation" style="border-radius: 0">
		  <div class="container-fluid">
		    <!-- Brand and toggle get grouped for better mobile display -->
		    <div class="navbar-header">
		      <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
		        <span class="sr-only">Toggle navigation</span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		        <span class="icon-bar"></span>
		      </button>
		      <a class="navbar-brand" href="/DashCon/dashboard/">
		      	<img src="../images/sri-alt-logo.png" alt="Smart Roadside Initiative logo">
		      	SRI Truck Screening
		      </a>
		    </div>
		
		    <!-- Collect the nav links, forms, and other content for toggling -->
		    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
<!-- 		      <ul class="nav navbar-nav">
		        <li class="top-link active" id="users">
		        	<a href="#" class="" title="Users">
			        	<span class="glyphicon glyphicon-home"></span>
			        	<span class="">Home</span>
		        	</a>
	        	</li>
      		</ul> -->
          <ul class="nav navbar-nav navbar-right">
		        <li class="">
		        	<a href="#" class="dropdown-toggle" data-toggle="dropdown">
			        	Hello, <% if(request.getUserPrincipal() == null) { %>
			        		Guest
			        	<% } else { %>
							<span><%= request.getUserPrincipal().getName() %></span>
			        	<% } %>
						<b class="caret"></b>
		        	</a>
		          <ul class="dropdown-menu" id="userOptions">
		            <% if(request.getUserPrincipal() == null) { %>
			            <li><a href="../login.jsp" class="">Login</a></li>
		            <% } else { %>
			            <li><a href="/DashCon/profile" class="">Manage</a></li>
			            <li><a href="../logout.jsp" class="">Logout</a></li>
		            <% } %>
		          </ul>
		        </li>
		      </ul>
		    </div><!-- /.navbar-collapse -->
		  </div><!-- /.container-fluid -->
		</nav>