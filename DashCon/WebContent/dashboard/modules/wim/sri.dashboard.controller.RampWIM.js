

'use strict';


angular.module('sri.controller.RampWIM', [])
	.controller('RampWIMCTRL', ['$rootScope', '$scope', '$http', '$timeout', function( $rootScope, $scope, $http, $timeout ) {
		$scope.truck = $scope.truck || {};
		$scope.wim = $scope.wim || {};
		$scope.wim.id = 'wim';
		$scope.wim.title = 'Mainline WIM';
		$scope.wim.data = $scope.wim.data || {};
		
		$scope.$watch('truck.current', function( newValue, oldValue ) {
			// Disregard undefined values
			if( newValue == undefined ) return false;
			
			if( newValue !== oldValue ) {
				var apiUrl = newValue.timestamp + '/' + newValue.sequenceNumber + '/' + newValue.siteId;
				$http.get('/DashCon/resources/weight/wim/' + apiUrl ).then( getWeight ).then(function() {
					$timeout(function() {
						$scope.wim.loading = false;
					}, 1200);
				});
			}
		});
		
		function getWeight( promise ) {
			var data = promise.data;
			$scope.wim.data = data;
			
			if( data != '' ) {
			
				$scope.wim.loading = true;
				
				$scope.wim.data.displayTime = moment(data.timestamp).format('MM-DD-YYYY H:mm:ss') || '';
				
				if( data.status == 'P' ) {
					$scope.wim.data.passed = 'passed';
				} else {
					$scope.wim.data.passed = 'failed';
				}
				
				$rootScope.$broadcast('newTruck', $scope.wim.data.timestamp);
			}
		}
		
	}]);