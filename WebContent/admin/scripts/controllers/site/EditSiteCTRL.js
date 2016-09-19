/*
 * author:	Robert Roth
 * desc:	Controller for editting of site accounts.
 * params:
 *			$scope 	- this is the scope of the controller.
 * 			$http 	- provides http requests.
 * 			$state 	- provides page state properties and functions.
 * 			site		- a resolved site loaded from our API.
 */

'use strict';

sri.controller = sri.controller || {};

sri.controller.EditSite = function( $scope, Site, site ) {
	// Assign the resolved site data to our scope
	$scope.site = site;
	// Methods:
	// Sends a PUT request to update our site
	$scope.updateSite = function() {
		Site.update( $scope.site ).then( updateSuccess, updateFailed );
		
		function updateSuccess() {
			// Show an alert message on top for our sites
			$scope.utility.showMessage({
				type: 'success',
				message: 'Successfully Updated!'
			});
		}
		
		function updateFailed() {
			// Show an alert message on top for our sites
			$scope.utility.showMessage({
				type: 'error',
				message: 'Update Failed! Duplicate email address.'
			});
		}
	};
	
};