/*
 * author:	Robert Roth
 * desc:	This is an Angular Factory that provides listing of all Sites
 * 			saved in our database
 * params:
 * 			$http 	  - provides http requests.
 */

'use strict';

//sri.factory = sri.factory || {};

angular.module('sri.factory.Site', [])
	.factory('Site', ['$http', function( $http ) {
		return {
			// This is to be used in the Profile page
			getSiteProfile: function() {
				return $http.get('/DashCon/resources/site/').then(function( promise ) {
					return promise;
				});
			},
			// @params String siteId
			get: function( siteId ) {
				return $http.get('/DashCon/resources/site/admin/' + siteId).then(function( response ) {
					return response.data;
				});
			},
			getAll: function() { 
				return $http.get('/DashCon/resources/site/admin').then(function( response ) {
					return response.data.site;
				});
			},
			// @params Object site
			update: function( site ) {
				return $http.put('/DashCon/resources/site/admin', site ).then( function( response ) {
					return response;
				});
			},
			// @params Object site
			create: function( site ) {
				return $http.post('/DashCon/resources/site/admin', site).then( function( response ) {
					return response;
				});
			},
			// @params String siteId
			remove: function( siteId ) {
				return $http.delete('/DashCon/resources/site/admin/' + siteId).then( function( response ) {
					return response;
				});
			},
			
			// The following are for Site Types
			getAllTypes: function() {
				return $http.get('/DashCon/resources/site/admin/types').then(function( response ) {
					return response.data;
				});
			}			
		}
	}]);

