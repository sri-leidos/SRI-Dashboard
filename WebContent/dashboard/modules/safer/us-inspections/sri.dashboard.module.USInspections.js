

'use strict';


angular.module('sri.controller.Safer.USInspections', [])
	.controller('USInspectionsCTRL', ['$scope', function( $scope ) {
		var _this = this;
		$scope.$on('saferDataLoaded', function( event, data ) {
			if( data != undefined && data != '' && data !== null )
				_this.data = ( data.usInspectionData ) ? data.usInspectionData.inspections : {};
			else
				_this.data = {};
		});
	}]);