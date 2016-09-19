
/*
 * @author Robert Roth
 * 
 * @desc An Angular based application mostly concerned with differentiating
 * guest to registered users.
 * 
 * @param Profile is an Angular Service that returns the currently logged in
 * user information.
 * 
 */

'use strict';

// build our controller function
function NavigationCtrl( $scope, Profile ) {
	// keep scope of the controller
	var _this = this;
	// get user information if logged in
	Profile.getProfile().then( function( promise ) {
		// pass promised data to our controller's scope
		_this.user = promise.data;
	});
};
// bootstrap our application with any dependency
angular.module('sri:downloads', ['sri.service.Profile'])
// assign any controller used
.controller('profile.NavigationCtrl', ['$scope', 'Profile', NavigationCtrl]);





