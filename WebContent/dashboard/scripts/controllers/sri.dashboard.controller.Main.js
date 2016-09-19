

'use strict';


angular.module('sri.controller.Main', [])
	.controller('MainCTRL', ['$scope', function( $scope ) {
		$scope.truck = $scope.truck || {};
		$scope.feed = $scope.feed || {};
	}]);