


/*
 * author:	Robert Roth
 * desc:	This is an Angular Service for interfacing with
 * 			the Profile Resource web service.
 * params:  $http - provides http requests.
 */

'user strict';

angular.module('SRI.DASHBOARD')
	.service('service.Profile', ['$http', function( $http ) {
		var _this = this;
		this.getProfile = function() {
			return $http.get('/DashCon/resources/profile/').then(function( response ) {
				return response.data;
			});
		};
		
		this.updateProfile = function( user ) {
			return $http.put('/DashCon/resources/profile', user ).then( function( response ) {
				return response;
			});			
		};
		
		this.updateEmail = function( email ) {
			return $http.put('/DashCon/resources/profile/email', email).then(function( response ) {
				return response;
			});
		};
		
		this.updatePassword = function( user ) {
			return $http.put('/DashCon/resources/profile/password', user).then(function( response ) {
				return response;
			});
		};
		
	}]);

