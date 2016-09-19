

'use strict';


angular.module('sri.controller.StaticScale', [])
	.controller('StaticScaleCTRL', ['$rootScope', '$scope', '$http', '$timeout', function( $rootScope, $scope, $http, $timeout ) {
		$scope.truck = $scope.truck || {};
		$scope.scale = $scope.scale || {};
		$scope.scale.data= $scope.scale.data || {};
		
		$scope.$watch('truck.current', function( newValue, oldValue ) {
			// Disregard undefined values
			if( newValue == undefined ) return false;
			
			if( newValue !== oldValue ) {
				var apiUrl = newValue.timestamp + '/' + newValue.sequenceNumber + '/' + newValue.siteId;
				$http.get('/DashCon/resources/weight/static/' + apiUrl )
					.success( getScaleWeight )
					.then(function() {
						$timeout(function() {
							$scope.scale.loading = false;
						}, 1200);
					});
			}
		});
		
		$scope.$on('staticScale:new', function( event, data ) {
			console.info('Static Scale Entry: Detected!');
			var apiUrl = data.timestamp + '/' + data.sequenceNumber + '/' + data.siteId;
			$http.get('/DashCon/resources/weight/static/' + apiUrl ).success( getScaleWeight2 );
		});
		
		function getScaleWeight( response ) {
			$scope.scale.data = response;
			
			if( response == "" || response == undefined || response == null ) {
				return $scope.truck.current.hasScale = false;
			}
			
			$scope.truck.current.hasScale = true;
			
			$scope.scale.loading = true;
			
			$scope.scale.data.displayTime = moment(response.timestamp).format('MM-DD-YYYY H:mm:ss');
			
			if( response.status == 'P' ) {
				$scope.scale.data.passed = 'passed';
			} else {
				$scope.scale.data.passed = 'failed';
			}
		}	
		
		function getScaleWeight2( response ) {
			if( $scope.truck.current.sequenceNumber == response.sequenceNumber ) {
				$scope.scale.data = response;

				if( response == "" || response == undefined || response == null ) {
					return false;
				}

				$scope.scale.loading = true;

				$scope.scale.data.displayTime = moment(response.timestamp).format('MM-DD-YYYY H:mm:ss');

				if( response.status == 'P' ) {
					$scope.scale.data.passed = 'passed';
				} else {
					$scope.scale.data.passed = 'failed';
				}

				$rootScope.$broadcast('staticScale:entry', response.sequenceNumber );

				$timeout(function() {
					$scope.scale.loading = false;
				}, 1000);
			}
		}
		
	}]);