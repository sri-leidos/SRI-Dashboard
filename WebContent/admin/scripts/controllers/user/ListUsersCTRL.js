/*
 * @author	Robert Roth
 * @desc	Controller for the listing of users received from
 * 			the UserResource API
 * @params	$scope 	- this is the scope of the controller.
 * 			User 	- a factory that provides an API for users.
 * 			$state 	- provides page state properties and functions.
 * 			users	- a resolved list of users from our UserResource API.
 */

'use strict';

angular.module('SRI.ADMIN')
.controller('sri.ListUsersCTRL', ['$scope', 'User', 'Site', '$state', '$http', 'users', ListUsers])

function ListUsers( $scope, User, Site, $state, $http, users ) {
	/*
	 * Set our View's title
	 */ 
	$scope.title = 'Users';
	$scope.loaderShow = true;
	$scope.$root = 'user';
	/*
	 * Add the resolved users object to our scope
	 * NOTE: I added a terniary that checks if our response'
	 * data contains a child user that contains what we need
	 * from our UserResource API.
	 */
	users = users || {};
	$scope.users = ( users.user ) ? users.user : users;
	/*
	 * NOTE: Apparently our webservice returns the user attributes
	 * when only 1 user is available in the database. $scope.users
	 * requires to be an Array or Object with child objects. With that
	 * said, we need force $scope.users to be an Array, and push the
	 * lone data we have.
	 */
	if( $scope.users.length === undefined && $scope.users.userId !== undefined ) {
		var user = $scope.users;
		$scope.users = [];
		$scope.users.push( user );
	}
	Site.getAll().then( function( response ) {
		var sites = response;
		$http.get('/DashCon/resources/profile/').then( function( response ) {
			var currentUser = response.data;
			var currentSite = '';
			angular.forEach( sites, function( site ) {
				if( currentUser.siteId ==  site.id ) {
					return currentSite = site;
				}
			});
			angular.forEach( $scope.users, function( user ) {
				user.siteName = currentSite.name;
			});
			document.title = 'Admin: ' + currentSite.name;
		});
	});
	/*
	 * Check if we have users or not, if this is false
	 * a warning will show in our view notifying our user.
	 */
	$scope.noUsers =  $scope.users.length === undefined && $scope.users.userId === undefined;
	
	/*
	 * METHODS 
	 */
	$scope.deleteUser = function() {
		var user = this.user;
		// Add userId to the URL since that is a requirement
		// By our API
		var confirmDelete = confirm('Delete User?');
		if( confirmDelete ) {
			User.remove( user.userId ).then(function() {
				$state.reload().then( function() {
					// Show an alert message on top for our users
					$scope.utility.showMessage({
						type: 'success',
						delay: 3000,
						message: 'User <em>' + user.userId + '</em> is now deleted!'
					});
				});
			});
		}
	};
};