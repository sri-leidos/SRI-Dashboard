


'use strict';


angular.module('SRI.DASHBOARD')
	.service('service.Mettler', ['$http', 'service.Profile', function( $http, Profile ) {
		var _this = this;
		var currentUser = Profile.getProfile();
		
		_this.pollTrucksApp = function() {
			$http.get('/DashCon/truckFeedPoll').success( function( data ) {
				// Disregard invalid entries
				if( data.timestamp == null || data.timestamp == "null" ) return false;
				// populate feeds based on current user's site id
				if( currentUser.siteId !== data.siteId ) return false;
				$rootScope.$broadcast('truckFeedNew', data);
				truckPhoneAppEntry( data.sequenceNumber );
				_this.pollTrucksApp();
			}).error( function( err ) {
				console.log( err );
				_this.pollTrucksApp();
			});
		};
		
		_this.pollTrucksMettler = function() {
			$http.get('/DashCon/mettPoll').success( function( data ) {
				// populate feeds based on current user's site id
				if( currentUser.siteId !== data.siteId ) return false;
				if( data.scaleType == 'W' ) {
					populateFeedNew( data );
				} else if( data.scaleType == 'S' ) {
					staticScaleEntry( data.sequenceNumber );
					$rootScope.$broadcast('staticScale:new', data);
				}
				_this.pollTrucksMettler();
			}).error( function( err ) {
				console.log( err );
				_this.pollTrucksMettler();
			});
		}
		
		_this.initialize = function() {
			_this.pollTrucksApp();
			_this.pollTrucksMettler();
		};
		
	}]);
	