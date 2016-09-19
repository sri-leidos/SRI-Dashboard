/*
 * @author	Robert Roth
 * @desc	Controller for the listing of sites received from
 * 			the SiteResource API
 * @params	$scope 	- this is the scope of the controller.
 * 			Site 	- a factory that provides an API for sites.
 * 			$state 	- provides page state properties and functions.
 * 			sites	- a resolved list of sites from our SiteResource API.
 */

'use strict';

sri.controller = sri.controller || {};

sri.controller.ListSites = function( $scope, Site, $state, sites ) {
	/*
	 * Set our View's title
	 */ 
	$scope.title = 'Sites';
	$scope.loaderShow = true;
	/*
	 * Add the resolved sites object to our scope
	 * NOTE: I added a terniary that checks if our response'
	 * data contains a child site that contains what we need
	 * from our SiteResource API.
	 */
	sites = sites || {};
	$scope.sites = ( sites.site ) ? sites.site : sites;
	/*
	 * NOTE: Apparently our webservice returns the site attributes
	 * when only 1 site is available in the database. $scope.sites
	 * requires to be an Array or Object with child objects. With that
	 * said, we need force $scope.sites to be an Array, and push the
	 * lone data we have.
	 */
	if( $scope.sites.length === undefined && $scope.sites.id !== undefined ) {
		var site = $scope.sites;
		$scope.sites = [];
		$scope.sites.push( site );
	}
	/*
	 * Check if we have sites or not, if this is false
	 * a warning will show in our view notifying our site.
	 */
	$scope.noSites =  $scope.sites.length === undefined && $scope.sites.siteId === undefined;
	
	/*
	 * METHODS 
	 */
	$scope.deleteSite = function() {
		var site = this.site;
		// Add siteId to the URL since that is a requirement
		// By our API
		var confirmDelete = confirm('Delete Site?');
		if( confirmDelete ) {
			Site.remove( site.id ).then(function() {
				$state.reload().then( function() {
					// Show an alert message on top for our sites
					$scope.utility.showMessage({
						type: 'success',
						delay: 3000,
						message: 'Site <em>' + site.siteId + '</em> is now deleted!'
					});
				});
			});
		}
	};
};