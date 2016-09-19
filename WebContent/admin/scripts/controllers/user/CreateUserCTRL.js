/*
 * author:	Robert Roth
 * desc:	Controller for creating users.
 * params:
 *			$scope 	  - this is the scope of the controller.
 * 			User 	- a factory that provides an API for users.
 * 			Site 	- a factory that provides an API for sites.
 * 			stateList	- a resolved list of all states from a JSON file.
 */

'use strict';

angular.module('SRI.ADMIN')
.controller('sri.CreateUserCTRL', ['$scope', 'User', 'Site', 'stateList', 'currentAdmin', '$filter', CreateUser])

function CreateUser( $scope, User, Site, stateList, currentAdmin, $filter ) {
	// check whether our admin is a super user which has a lot
	// more control on all sites and accounts than a usual admin
	$scope.superUser = currentAdmin.userGroup.groupId == 'superuser';
	// Add resolved state list to our scope
	$scope.states = stateList;
	// Init user as object, avoids undefined
	var userDefaults = {
		userId: '',
		userPassword: '',
		firstName: '',
		lastName: '',
		email: '',
		siteId: ''
	};
	
	$scope.user = angular.extend({}, userDefaults);
	// Set default state option selected
	$scope.selectedState = 'AL';
	$scope.selectedSite = 'Click to select Site';
	// Set default user role value
	$scope.groupId = 'user';
	
	// Methods:
	$scope.createUser = function() {
		// Set the selected state to our user object under
		// the stateId attribute
		$scope.user.stateId = $scope.selectedState;
		// super users can select which site to assign users into,
		// normal site administrator just attach their site id to
		// the user they are creating
		if( $scope.superUser ) {
			$scope.user.siteId = $scope.selectedSite;
		} else {
			$scope.user.siteId = currentAdmin.siteId;
		}
		// Define our userGroup object and assign our
		// groupId model from our scope to it.
		$scope.user.userGroup = {};
		$scope.user.userGroup.groupId = $scope.groupId;
		
		User.create( $scope.user ).then( createSuccess, createFailed );
		
		function createSuccess() {
			// Notifyr ngDirective to display a success notification
			$scope.utility.showMessage({
				type: 'success',
				message: 'User was successfully created!'
			});
			// reset our data
			$scope.resetForm();
		}
		
		function createFailed() {
			// Notifyr ngDirective to display an error notification
			$scope.utility.showMessage({
				type: 'error',
				message: 'User creation failed!'
			});
		}
	}
	
	// Function: Resets our form by setting some of
	// our model's attributes to blank and setting the
	// form back to "prestine", which means the form is
	// back to being "untouched".
	$scope.resetForm = function() {
		$scope.user = angular.extend({}, userDefaults);
		$scope.selectedState = 'AL';
		$scope.createForm.$setPristine();
	};
	
	// Get all sites available from the database
	// and pass the promise to our callback
	Site.getAll().then( loadSiteOptions );

	// Get the sites from the promise's data
	// and add it to the scope
	function loadSiteOptions( sites ) {
		$scope.sites = $filter('orderBy')(sites, 'name', false);
		$scope.selectedSite = $scope.sites[0].id;
		return $scope;
	}
	
}



