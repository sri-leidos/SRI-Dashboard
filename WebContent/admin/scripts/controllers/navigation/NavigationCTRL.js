/*
 * author:	Robert Roth
 * desc:	Controller for our Top Menu Navigation.
 * params:
 *			$scope 	  - this is the scope of the controller.
 * 			User 	- a factory that provides an API for users.
 * 			stateList	- a resolved list of all states from a JSON file.
 */

angular.module('SRI.ADMIN')
.controller('sri.NavigationCTRL', ['$scope', '$state', '$location', Navigation])

function Navigation( $scope, $state, $location ) {
	// Initially, this is the first landing page.
	$scope.activeLink = 'Users';
	// Get the hash value of our current URL, this
	//helps set the active link on reload of the page	
	var url = window.location.hash;
	// Define the active link based on our url's hash value
	if( url.indexOf('/user') > -1 ) {
		$scope.activeLink = 'Users';
	} else if( url.indexOf('/site') > -1 ) {
		$scope.activeLink = 'Sites';
	} else if( url.indexOf('/devices') > -1 ) {
		$scope.activeLink = 'Devices';
	}
	console.log( $scope.activeLink );
	// Create our menu items for out TOP MENU
	$scope.menuItems = [{
		title: 'Users',
		state: 'listUsers()',
		glyph: 'user'
	},
	{
		title: 'Sites',
		state: 'listSites()',
		glyph: 'map-marker'
	},
	{
		title: 'Devices',
		state: 'listDevices()',
		glyph: 'th-large'
	}];
	// This sets a new active link when activated
	// primarily with a click event
	$scope.changeActiveLink = function() {
		$scope.activeLink = this.item.title;
	};
}