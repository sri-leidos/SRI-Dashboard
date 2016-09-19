/*
 * author:	Robert Roth
 * desc:	This is an Angular Factory that provides listing of all Users
 * 			saved in our database
 * params:
 * 			$http 	  - provides http requests.
 */

'user strict';

angular.module('SRI.ADMIN')
.factory('User', ['$http', UserFactory])
	
function UserFactory( $http ) {
	var services = {
		// @params String userId
		get: function( userId ) {
			return $http.get('/DashCon/resources/user/admin/' + userId).then(function( response ) {
				return response.data;
			});
		},
		getAll: function() { 
			return $http.get('/DashCon/resources/user/admin').then(function( response ) {
				return response.data.user;
			});
		},
		getAllBySiteId: function() { 
			return $http.get('/DashCon/resources/user/admin/site').then(function( response ) {
				return response.data.user;
			});
		},
		// @params Object user
		update: function( user ) {
			return $http.put('/DashCon/resources/user/admin', user ).then( function( response ) {
				return response;
			});
		},
		// @params Object user
		create: function( user ) {
			return $http.post('/DashCon/resources/user/admin', user).then( function( response ) {
				return response;
			});
		},
		// @params String userId
		remove: function( userId ) {
			return $http.delete('/DashCon/resources/user/admin/' + userId).then( function( response ) {
				return response;
			});
		}
	};
	
	return services;
};

