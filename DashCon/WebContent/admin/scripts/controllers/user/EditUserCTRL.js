/*
 * author:	Robert Roth
 * desc:	Controller for editting of user accounts.
 * params:
 *			$scope 	- this is the scope of the controller.
 * 			$http 	- provides http requests.
 * 			$state 	- provides page state properties and functions.
 * 			user		- a resolved user loaded from our API.
 */

'use strict';

angular.module('SRI.ADMIN')
.controller('sri.EditUserCTRL', ['$scope', 'User', 'user', EditUser])

function EditUser( $scope, User, user ) {
	// Assign the resolved user data to our scope
	$scope.user = user;
	// Methods:
	// Sends a PUT request to update our user
	$scope.updateUser = function() {
		User.update( $scope.user ).then( updateSuccess, updateFailed );
		
		function updateSuccess() {
			// Show an alert message on top for our users
			$scope.utility.showMessage({
				type: 'success',
				message: 'Successfully Updated!'
			});
		}
		
		function updateFailed() {
			// Show an alert message on top for our users
			$scope.utility.showMessage({
				type: 'error',
				message: 'Update Failed! Duplicate email address.'
			});
		}
	};
	
};