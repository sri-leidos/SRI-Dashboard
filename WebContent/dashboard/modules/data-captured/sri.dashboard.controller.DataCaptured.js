

'use strict';


angular.module('sri.controller.DataCaptured', [])
	.controller('DataCapturedCTRL', ['$scope', function( $scope ) {
		$scope.truck = $scope.truck || {};
		
		$scope.dataCaptured = $scope.dataCaptured || {};
		$scope.dataCaptured.id = 'dataCaptured';
		$scope.dataCaptured.title = 'Data Captured';

		var currentTruck = $scope.truck.current;
		
		$scope.dataCaptured.licensePlate = currentTruck.licensePlate;
		$scope.dataCaptured.dotNumber = currentTruck.dotNumber;
		$scope.dataCaptured.weight = currentTruck.weight;
		
		$scope.$watch('truck', function( newValue, oldValue ) {
			if( newValue !== oldValue ) {
				currentTruck = $scope.truck.current;
				$scope.dataCaptured.licensePlate = currentTruck.licensePlate;
				$scope.dataCaptured.dotNumber = currentTruck.dotNumber;
				$scope.dataCaptured.weight = currentTruck.weight;
			}
		}, true);
	}]);