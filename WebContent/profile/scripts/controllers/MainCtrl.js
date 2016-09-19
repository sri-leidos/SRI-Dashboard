



'use strict';


function MainCtrl( $scope, user, site ) {
	// cache our controller
	var _this = this;
	// pass our resolved user promise's data to our controller's scope
	_this.user = user.data;
	// get our user's site information
	_this.site = site.data;
}