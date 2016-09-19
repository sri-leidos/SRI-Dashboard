

'use strict';

angular.module('sri.directive.Module', [])
	.directive('dirModule', function() {
		return {
			restrict: 'EA',
			templateUrl: '/DashCon/dashboard/templates/ModuleTMPL.html',
			controller: function( $scope ) {
				$scope.currentTruck = $scope.currentTruck || {}; 
			}
		};
	});