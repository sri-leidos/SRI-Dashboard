

'use strict';

angular.module('sri.directive.DataCaptured', [])
.directive('dirDataCaptured', function() {
	return {
		restrict: 'EA',
		scope: {},
		templateUrl: '/DashCon/dashboard/templates/modules/ModuleTMPL.html',
		link: function( scope, element, attrs ) {
			var currentTruck = scope.$parent.$parent.currentTruck;
			scope.title = 'Data Captured';
			scope.data = currentTruck.dataCaptured;
			scope.moduleColor = scope.data.Status || '';
		}
	}
});