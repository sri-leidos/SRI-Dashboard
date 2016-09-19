

'use strict';

angular.module('sri.controller.rightPanelMenu', [])
	.controller('RightMenuCTRL', [ '$scope', 'service.Profile', function( $scope, Profile ) {
		
		var _this = this;
		
		Profile.getProfile().then( function( data ) {
			_this.user = data;
		});
		
		$scope.triggerFrame = function() {
			$('#frameContainer').trigger('openFrame');
		};
	}])