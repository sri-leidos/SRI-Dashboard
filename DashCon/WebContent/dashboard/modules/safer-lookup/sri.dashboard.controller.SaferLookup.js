

'use strict';


angular.module('sri.controller.SaferLookup', ['sri.module.Effects'])
	.controller('SaferLookupCTRL', ['$scope', '$http', '$rootScope', '$timeout',
    function( $scope, $http, $rootScope, $timeout ) {
		// variables
		$scope.truck = $scope.truck || {};
		var _this = this;
		_this.hasUSDOT = false;
		_this.usdotNumber = _this.usdotNumber || null;
		// get USDOT number on truck active change
		$scope.$watch('truck.current', function( newValue, oldValue ) {
			if( newValue != undefined && newValue != oldValue ) {
				// reset our values
				_this.usdotNumber = '';
				_this.hasUSDOT = false;
				// make a get request using sequence number and site id
				var path = newValue.sequenceNumber + '/' + newValue.siteId;
				$http.get('/DashCon/resources/usdot/' + path)
					.then(function( promise ) {
						if( promise.data != undefined ) {
							var data = promise.data;			// promised data
							var truck = $scope.truck.current;	// current truck
							// check if data matches active truck feed
							if( data.sequenceNumber == truck.sequenceNumber ) {
								// set module data
								_this.usdotNumber = data.usdotNumber;
								// set truck feed icon and module validation to true
								truck.hasUSDOT = _this.hasUSDOT = true;
							}
						} else {
							// broadcast undefined USDOT number
							$rootScope.$broadcast('saferLookupUndefined', response.data);
						}
					})
			}
		});
		
		$scope.$on('truckFeedNew', function( $event, message ) {
			// reset our values
			_this.usdotNumber = '';
			_this.hasUSDOT = false;
			// make a get request using sequence number and site id
			var path = message.sequenceNumber + '/' + message.siteId;
			$http.get('/DashCon/resources/usdot/' + path)
				.then(function( promise ) {
					if( promise.data != undefined ) {
						var data = promise.data;			// promised data
						var truck = $scope.truck.current;	// current truck
						// check if data matches active truck feed
						if( data.sequenceNumber == truck.sequenceNumber ) {
							loadUSDOTNumber( data );
						}
					} else {
						// broadcast undefined USDOT number
						$rootScope.$broadcast('saferLookupUndefined', response.data);
					}
				})
		})
		
		// add the US DOT Number to the controller's scope as well
		// as adding it to the current active truck
		function loadUSDOTNumber( data ) {
			if( typeof data == 'object' && data.usdotNumber != undefined ) {
				_this.usdotNumber = data.usdotNumber;
				_this.hasUSDOT = true;
				$scope.truck.current.hasUSDOT = true;
				$scope.truck.current.usdotNumber = _this.usdotNumber;
			} else {
				_this.usdotNumber = undefined;
				_this.hasUSDOT = false;
				$scope.truck.current.hasUSDOT = false;
				$scope.truck.current.usdotNumber = undefined;
			}
			// assign true to signify this is triggered by lookup
			_this.searchSafer();
		}
		
		_this.searchSafer = function() {
			// cache active truck
			var truck = $scope.truck.current;
			// check if triggered by lookup or change of current truck			
		 	if( _this.usdotNumber != null && _this.usdotNumber != undefined && _this.usdotNumber != null ) {
			 	_this.loading = true;
				$http.get('/DashCon/resources/safer/' + _this.usdotNumber )
				.then( function( response ){
					loadSafer( response.data, truck );
					$timeout( function() {
				 		_this.loading = false;
					}, 1000);
				})
		 	}
		}
		
		function loadSafer( data, truck ) {
			// a bug happens when switching active truck quicker than
			// safer can respond and load safer values showing data
			// to the wrong truck feed, lookup != false helps prevent it
			// by ensuring the truck's usdot number is equal to the lookup
			if( typeof data == 'object' && data.companyData != undefined ) {
				_this.hasUSDOT = true;
				truck.hasUSDOT = true;
				$rootScope.$broadcast('saferLookupSuccess', data );
			} else {
				_this.hasUSDOT = false;
				truck.hasUSDOT = false;
			}
		}
		
		_this.clearInput = function( $event ) {
			if( $event ) $event.stopPropagation();
			_this.usdotNumber = null;
			_this.hasUSDOT = false;
			$http.delete('/DashCon/resources/usdot/' + $scope.truck.current.sequenceNumber)
			.success( function() {
				$scope.truck.current.usdotNumber = undefined;
				$scope.truck.current.hasUSDOT = false;
				$rootScope.$broadcast('saferLookupCleared', '');
			})
		}
		
		_this.saveUSDOTNumber = function( $event, manualEntered, userId ) {
			var saferLookup = _this;
			// cache current truck
			var truck = $scope.truck.current;
			// build our usdot object to be sent as part of our request
			var usdot= {};
			usdot.usdotNumber = saferLookup.usdotNumber;
			usdot.sequenceNumber = $scope.truck.current.sequenceNumber;
			usdot.manualEntered = manualEntered || 1;
			usdot.userId = userId || 'not implemented';
			usdot.siteId = truck.siteId;
			// send a post to saved our usdot to our database
			if( usdot.usdotNumber !== null ) {
				$http.post('/DashCon/resources/usdot', usdot)
				.success( function( response, status) {
					// 201 = if we created a new row in our usdot databse
					if( status == 201 ) {
						$scope.truck.current.hasUSDOT = true;
						truck.usdotNumber = usdot.usdotNumber;
						_this.usdotNumber = usdot.usdotNumber;
						_this.hasUSDOT = true;
//						TODO: Add notification for create USDOT Number
					} 
					// 202 = if we updated a row in our usdot database
					else if( status == 202 ) {
						truck.hasUSDOT = true;
						truck.usdotNumber = saferLookup.usdotNumber;
//						TODO: remove the commented block of code if proven to cause errors
//						truck.usdotNumber = undefined;
//						_this.usdotNumber = undefined;
						_this.hasUSDOT = true;
//						TODO: Add notification for updated USDOT Number
					}
				})
				.error( function( res ) {
					_this.clearInput();
				});
			}
		}

		return _this;
		
	}]);