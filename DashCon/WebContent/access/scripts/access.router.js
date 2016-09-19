

'use strict';

;(function( undefined ) {
	angular.module('SRI.RETRIEVE')
	.config(['$stateProvider', '$urlRouterProvider', retrieveRouter]);
	
	function retrieveRouter($stateProvider, $urlRouterProvider) {
		// define our default route
		$urlRouterProvider.otherwise('/');
		// start defining our routes
		$stateProvider
		// create our default route
		.state('main', {
			url: '/',
			templateUrl: 'templates/Main.ng.html'
		})
		// create our forgot username state
		.state('username', {
			url: '/username',
			templateUrl: 'templates/Username.ng.html',
			controller: 'access.UsernameCtrl',
			controllerAs: 'username'
		})
		// create our forgotten password state
		.state('password', {
			url: '/password',
			templateUrl: 'templates/Password.ng.html',
			controller: 'access.PasswordCtrl',
			controllerAs: 'password'
		})
		// create our forgotten password state
		.state('newPassword', {
			url: '/password/:tempId',
			templateUrl: 'templates/NewPassword.ng.html',
			controller: 'access.NewPasswordCtrl',
			controllerAs: 'np'
		})
	}
})();