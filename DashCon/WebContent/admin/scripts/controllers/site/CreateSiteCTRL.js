/*
 * author:	Robert Roth
 * desc:	Controller for creating sites.
 * params:
 *			$scope 	  - this is the scope of the controller.
 * 			Site 	- a factory that provides an API for sites.
 * 			stateList	- a resolved list of all states from a JSON file.
 */

sri.controller = sri.controller || {};

sri.controller.CreateSite = function( $scope, Site, stateList, siteTypes ) {
	// Add resolved state list to our scope
	$scope.states = stateList;
	$scope.selectedState = 'AL';
	// Init site as object, avoids undefined
	$scope.site = {};
	
	// Add siteTypes into our scope
	$scope.siteTypes = siteTypes;
	$scope.selectedType = siteTypes[0];
	
	// Methods:
	$scope.createSite = function() {
		// Set the selected state to our site object under
		// the stateId attribute
		$scope.site.stateId = $scope.selectedState;
		Site.create( $scope.site ).then( createSuccess, createFailed );
		
		function createSuccess() {
			// Notifyr ngDirective to display a success notification
			$scope.utility.showMessage({
				type: 'success',
				message: 'Site was successfully created!'
			});
			// reset our data
			$scope.resetForm();
		}
		
		function createFailed() {
			// Notifyr ngDirective to display an error notification
			$scope.utility.showMessage({
				type: 'error',
				message: 'Site creation failed!'
			});
		}
	}
	
	// Add a listener for any change to our siteId model
	// TODO: setup a validation for siteId from our database
	$scope.$watch('site.siteId', function() {
		
	});
	
	// Function: Resets our form by setting some of
	// our model's attributes to blank and setting the
	// form back to "prestine", which means the form is
	// back to being "untouched".
	$scope.resetForm = function() {
		$scope.createForm.$setPristine();
	};
}