


<div class="truck-entry-controls clearfix" ng-controller="TruckFeedControlsCTRL as controls" ng-cloak>

	<div class="btn-group dropdown col-xs-4">
	  <button type="button" class="btn btn-sm btn-primary dropdown-toggle btn-icon col-md-3 col-xs-12" data-toggle="dropdown">
	  	<span class="glyphicon glyphicon-eye-open"></span>
	  </button>
	  <button type="button" class="btn btn-sm btn-primary dropdown-toggle btn-value col-md-9 hidden-xs hidden-sm" data-toggle="dropdown">
	    <span id="statusText" class="option-text" ng-cloak>{{ controls.selectedStatus }}</span>
	    <span class="sr-only">Toggle Dropdown</span>
	  </button>
	  <ul id="truckStatusOption" class="dropdown-menu" role="menu">
	    <li ng-repeat="item in controls.statuses">
	    	<a href="#" ng-click="controls.statusChange( $event, item )">{{ item }}</a>
    	</li>
	  </ul>
	</div>
	
	<div class="btn-group dropdown col-xs-4">
	  <button type="button" class="btn btn-sm btn-primary dropdown-toggle btn-icon col-md-3 col-xs-12" data-toggle="dropdown">
	  	<span class="glyphicon glyphicon-time"></span>
	  </button>
	  <button type="button" class="btn btn-sm btn-primary dropdown-toggle btn-value col-md-9 hidden-xs hidden-sm" data-toggle="dropdown">
	    <span class="option-text" ng-cloak>{{ controls.selectedTime }} min</span>
	    <span class="sr-only">Toggle Dropdown</span>
	  </button>
	  <ul id="truckTime" class="dropdown-menu" role="menu">
	  	<li ng-repeat="item in controls.time">
	  		<a href="#" ng-click="controls.timeChange( $event, item )">{{ item }} min</a>
  		</li>
	  </ul>
	</div>
	
	<div class="btn-group dropdown col-xs-4" ng-click="controls.scrollingChange($event)">
	  <button type="button" class="btn btn-sm btn-primary dropdown-toggle btn-icon col-md-3 col-xs-12">
	  	<span id="scrollingIcon" class="{{ controls.selectedLock.icon }}"></span>
	  </button>
	  <button type="button" class="btn btn-sm btn-primary dropdown-toggle btn-value col-md-9 hidden-xs hidden-sm">
	    <span id="scrollingText" ng-cloak>{{ controls.selectedLock.text }}</span>
	    <span class="sr-only">Toggle Dropdown</span>
	  </button>
	</div>
	
</div>