		
		  
		  
 <nav class="navbar navbar-inverse" role="navigation">
 <div class="container">
   <!-- Brand and toggle get grouped for better mobile display -->
   <div class="navbar-header">
     <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
       <span class="sr-only">Toggle navigation</span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
       <span class="icon-bar"></span>
     </button>
<!--      <a class="navbar-brand" href="/DashCon/admin/index.jsp"><span class="glyphicon glyphicon-cog"></span> Administration</a> -->
      <a class="navbar-brand" href="/DashCon/" title="Smart Roadside Initiative">
        <img src="/DashCon/images/sri-alt-logo.png"
         id="mainLogo"
         class="brand-logo"
         alt="Smart Roadside Initiative" />
      </a>
   </div>

   <!-- Collect the nav links, forms, and other content for toggling -->
   <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
     <ul class="nav navbar-nav">
       <li class="top-link ${ param.usersActive }" id="users">
       	<a href="/DashCon/admin/#/" title="Users">
       	<span class="glyphicon glyphicon-user"></span> 
       	<span class="">Administration</span>
       	</a>
      	</li>
       <li class="top-link ${ param.sitesActive }" id="">
	       <a href="/DashCon/dashboard" title="Dashboard">
	        <span class="glyphicon glyphicon-th-large"></span>
	        <span class="">Dashboard</span>
	       </a>
       </li>
     </ul>
     <ul class="nav navbar-nav navbar-right">
       <li class=""><a class="dropdown-toggle top-user-menu" data-toggle="dropdown">Hello, <%= request.getUserPrincipal().getName() %> <b class="caret"></b></a>
         <ul class="dropdown-menu" id="userOptions">
           <li><a href="/DashCon/profile" class="">Manage</a></li>
           <li><a href="/DashCon/logout.jsp" class="">Logout</a></li>
         </ul>
       </li>
     </ul>
   </div><!-- /.navbar-collapse -->
 </div><!-- /.container-fluid -->
</nav>