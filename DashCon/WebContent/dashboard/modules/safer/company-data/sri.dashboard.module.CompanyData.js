

'use strict';


angular.module('sri.controller.Safer.CompanyData', [])
	.controller('CompanyDataCTRL', ['$scope', '$http', '$timeout', '$rootScope', function( $scope, $http, $timeout, $rootScope ) {
		$scope.truck = $scope.truck || {};
		$scope.safer = $scope.safer || {};
		
		$scope.$on('truckPhoneActive', function( event, data ) {
			if( data == '' || data == 'null' || data == undefined || data.usdotNumber == undefined ) {
				loadSafer( data );
			}
		});

		$scope.$on('phoneAppUSDOTNumberAvailable', function ( event, message ) {
			var usdotNumber = ( message.usdotNumber ) ? message.usdotNumber : '';
			$scope.safer.loading = ( usdotNumber != '' && usdotNumber != 'null' && usdotNumber != undefined ) ? true : false;
			$http.get('/DashCon/resources/safer/' + usdotNumber )
				.then( loadSafer );
		})

		$scope.$watch('truck.current', function( newValue, oldValue ) {
			// Disregard undefined value
			if( newValue == undefined ) return false;
			// Remove background color initially
			// Check if the value is different from the previous one
			if( newValue !== oldValue ) {
				var usdotNumber = newValue.usdotNumber;
				if( typeof usdotNumber == 'object' ) {
					usdotNumber = usdotNumber.usdotNumber;
				}
				$scope.safer.loading = ( usdotNumber != '' || usdotNumber != 'null' ) ? true : false;
				$http.get('/DashCon/resources/safer/' + usdotNumber )
					.then( loadSafer )
			}
		});
		// when the safer response is successful and data is not empty
		// load our safer information to other safer modules
		$scope.$on('saferLookupSuccess', function( event, data ) {
			if( data != '' ) {
				$scope.safer.loading = true;
				loadSafer( data, true );
			}
		});
		// clear all our data when the clear button on the
		// safer lookup module is triggered
		$scope.$on('saferLookupCleared', function() {
			var truck = $scope.truck.current;
			$scope.safer.data = '';
			truck.usdotNumber = undefined;
			truck.hasUSDOT = undefined;
			$rootScope.$broadcast('saferDataLoaded', '');
		});
		
		function loadSafer( response, lookup ) {
			if( response == undefined ) return false;
			var data = ( response.data != undefined ) ? response.data : response;
			if( lookup == true ) {
				$scope.safer.data = data;
				$rootScope.$broadcast('saferDataLoaded', data);
			} else if( $scope.truck.current.hasUSDOT == true ){
				$scope.safer.data = data;
				$rootScope.$broadcast('saferDataLoaded', data);
			} else {
				$scope.safer.data = '';
				$rootScope.$broadcast('saferDataLoaded', '');
			}
			$scope.safer.loading = false;
		}
	}]);