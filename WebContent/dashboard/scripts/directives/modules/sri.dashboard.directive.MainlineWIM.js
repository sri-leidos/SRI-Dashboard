

'use strict';

angular.module('sri.directive.MainlineWIM', [])
.directive('dirWim', function() {
	return {
		restrict: 'EAC',
		scope: {
			truck: '='
		},
		replace: true,
		templateUrl: '/DashCon/dashboard/templates/modules/ModuleTMPL.html',
		link: function( scope ) {
			// Get current truck from the parent
			//scope.currentTruck = scope.$parent.$parent.currentTruck;
			scope.title = 'Mainline Wim';
			
			scope.$watch('truck', function( newValue, oldValue ) {
				if( newValue !== oldValue ) {
					scope.data = scope.truck.current.wim;
					console.log( scope.data );
				}
			}, true);
			
		}
	};
});