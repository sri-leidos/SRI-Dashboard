

'use strict';


angular.module('sri.controller.Safer.USCrashData', [])
	.controller('USCrashDataCTRL', ['$scope', '$http', 'service.Profile', '$timeout', function( $scope, $http, Profile, $timeout ) {
		var _this = this;
		
		Profile.getProfile().then( function( data ) {
			_this.user = data;
		});
		
		$scope.$on('saferDataLoaded', function( event, data ) {
			if( typeof data == 'object' && data.usCrashData != undefined ) {
				_this.data = data.usCrashData;
			} else {
				_this.data = '';
			}
		});
		
	}]);