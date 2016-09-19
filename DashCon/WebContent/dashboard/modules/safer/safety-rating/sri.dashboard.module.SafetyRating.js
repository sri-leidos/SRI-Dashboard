

'use strict';


angular.module('sri.controller.Safer.SafetyRating', [])
	.controller('SafetyRatingCTRL', ['$scope', 'service.Profile', function( $scope, Profile ) {
		var _this = this
		
		Profile.getProfile().then( function( data ) {
			_this.user = data;
		});
		
		$scope.$on('saferDataLoaded', function( event, data ) {
			if( typeof data == 'object' && data.safetyRating != undefined ) {
				_this.data = data.safetyRating;
			} else {
				_this.data = '';
			}
		});
	}]);