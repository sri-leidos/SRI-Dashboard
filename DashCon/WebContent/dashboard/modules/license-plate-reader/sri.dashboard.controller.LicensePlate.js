

'use strict';


angular.module('sri.controller.LicensePlateRdr', [])
	.controller('LicensePlateCtrl', ['$scope', '$rootScope', '$http', 'service.Profile', '$timeout',
     function( $scope, $rootScope, $http, Profile, $timeout ) {
		$scope.truck = $scope.truck || {};
		var _this = this;		
		// set default values for reset and reference
		var lprDefaults = {
			id: '',
			timestamp: null,
			siteId: null,
			fileName: '',
			sequenceNumber: null,
			licensePlateNumber: null,
			state: '',
			hasImage: false
		}
		// instantiate our license plate object
		_this.data = angular.extend({}, lprDefaults);

		Profile.getProfile().then( function( currentUser ) {
			$scope.userSiteId = currentUser.siteId;

			if( currentUser.siteId == '2' ) {
				pollLicensePlate( currentUser );
				// call the license plate image when user changes active truck
				$scope.$watch('truck.current', function( newValue, oldValue ) {
					// Disregard undefined values
					if( newValue == undefined ) return false;
					if( newValue !== oldValue ) {
						var path = newValue.siteId + '/' + newValue.sequenceNumber;
						$http.get('/DashCon/resources/lpr/' + path).then(function( promise ) {
							_this.data = promise.data;
							if( promise.data != '' ) {
								newValue.hasLPR = true;
								pingImage( _this.data, newValue );
							}
						}, function( err ) {
							_this.data = angular.extend({}, lprDefaults);
						});
					}
				});
			}
		});

		// long poll our truck feed servlet
		function pollLicensePlate( currentUser ) {
			$http.get('/DashCon/licensePlatePoll').success( function( data ) {
				// Disregard invalid entries
				if( data.timestamp !== null || data.timestamp !== "null" || data.timestamp !== undefined )
					// populate feeds based on current user's site id
					if( currentUser.siteId == data.siteId ) {
						var truck = $scope.truck.current;
						// since this is a long poll method we need to match the data id
						// to the active truck, especially when truck feed switching is
						// set to 'Locked'
						if( truck.sequenceNumber == data.sequenceNumber ) {
							// cache data to our controller
							_this.data = data;
						}
						// ping our image until it's found in the file system
						pingImage( data, truck );
						$rootScope.$broadcast('licensePlateAvailable', data);
					}
				pollLicensePlate(currentUser);
			}).error( function( err ) {
				console.log( err );
				_this.data = angular.extend({}, lprDefaults);
				pollLicensePlate(currentUser);
			});
		}
		
		function pingImage( data, truck ) {
			if( data != '' ) {
				var id = data.id;
				var imageSrc = '/DashCon/dashboard/images/lpr/' + id + '.jpg';
				$http.get(imageSrc)
				.success( function() {
					// this avoids the $digest already in progress error
					// since we're using $apply()
					$timeout(function() {
						$rootScope.$broadcast('licensePlateImage', data);
						if( data.sequenceNumber == truck.sequenceNumber ) {
							_this.data.hasImage = true;
							_this.data.image = _this.data.id + '.jpg';
						}
					});
	
				})
				.error( function() {
					$timeout( function() {
						pingImage( data, truck );
					}, 1500)
				})
			}
		}
		
		
	}]);