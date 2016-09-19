	
/*
 * @author		Robert Roth
 * @description	Configure our routes for the 'user page'
 * @dependency	ui.router from angular-ui-router.js
 * @params		$urlRouteProvider - contains functions for our app's URLs
 * 				$stateProvider - functions for creating 'states' of our app
 * 					where we can establish a url path of our state, a templateUrl
 * 					which provides our view from a template, and the Controller
 * 					for that view.
 */
angular.module('SRI.ADMIN')
// define our router and its dependencies
.config(['$urlRouterProvider', '$stateProvider', userRouteConfig]);
// build our router function
function userRouteConfig($urlRouterProvider, $stateProvider) {
	// First define a default route for our app
	// This means any route used other than what we define
	// here will load this route; set a default route.
	$urlRouterProvider.otherwise('/user');
	// Define our 'states' aka routes
	$stateProvider
	// State for Listing all users
	.state('listUsers', {
		url: '/user',
		templateUrl: 'admin/templates/user/list-user.html',
		controller: 'sri.ListUsersCTRL',
		resolve: {
			users: ['User', function( User ) {
				return User.getAllBySiteId().then( function( response ) {
					return response;
				});
			}]
		}
	})
	// State for editing specific users
	.state('editUser', {
		url: '/user/edit/:userId',
		templateUrl: 'admin/templates/user/edit-user.html',
		controller: 'sri.EditUserCTRL',
		resolve: {
			user: ['User', '$stateParams', function( User, $stateParams ) {
				return User.get( $stateParams.userId ).then(function( response ) {
					return response;
				});
			}]
		}
	})
	// State for creating users
	.state('createUser', {
		url: '/user/create',
		templateUrl: 'admin/templates/user/create-user.html',
		controller: 'sri.CreateUserCTRL',
		resolve: {
			stateList: ['$http', function( $http ) {
				return $http.get('/DashCon/admin/resources/states.json').then(function( response ) {
					return response.data;
				});
			}],
			currentAdmin: ['$http', function( $http ) {
				return $http.get('/DashCon/resources/profile/').then(function( promise ) {
					return promise.data;
				});
			}]
		}
	})
}