	
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
angular.module('profile.Router', ['ui.router'])
	.config(['$urlRouterProvider', '$stateProvider', profileRouter])
	
	
// the profile route configuration function
function profileRouter( $urlRouterProvider, $stateProvider ) {
	// set default route
	$urlRouterProvider.otherwise('/');
	// define all routes for our application
	$stateProvider
	// initial/home route
	.state('main', {
		url: '/',
		templateUrl: 'includes/MainTmpl.html',
		controller: 'profile.MainCtrl as main',
		resolve: {
			user: ['User', function( User ) {
				return User.getProfile().then( function( promise ) {
					return promise;
				})
			}],
			site: ['Site', function( Site ) {
				return Site.getSiteProfile().then( function( promise ) {
					return promise;
				})
			}]
		}
	})
	// profile edit route
	.state('edit', {
		url: '/edit',
		templateUrl: 'includes/EditTmpl.html',
		controller: 'profile.EditCtrl as profile',
		resolve: {
			user: ['User', function( User ) {
				return User.getProfile().then( function( promise ) {
					return promise;
				})
			}]
		}
	})
}