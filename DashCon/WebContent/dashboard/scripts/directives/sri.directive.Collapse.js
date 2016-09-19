/*
 * @author Robert Roth
 * @description This directive controls the collapsing and
 * expanding of any module's well, the container where most,
 * if not all, data are displayed. A directive is done to
 * make this easily reusable by any module.
 */



'use strict';
// assign to the main module we will use this directive in
angular.module('SRI.DASHBOARD')
// build our directive, for now we restrict it to attributes only
.directive('moduleCollapse', ['$compile', function( $compile ) {
	return {
		restrict: 'A',
		link: function( scope, element, attrs ) {
			// this function does the collapsing and expanding of the wells
			// of any module this directive is installed into.
			function toggleModule( evnt ) {
				// we require jQuery for this not just jQLite
				var element = $( evnt.currentTarget );
				var parent = element.parent();
				var well = parent.siblings('.well');
				// do toggling of hidden class
				if( well.hasClass('hidden') ) {
					well.removeClass('hidden');
					element.find('.fa-caret-square-o-down')
					.removeClass('fa-caret-square-o-down')
					.addClass('fa-caret-square-o-up')
				} else {
					well.addClass('hidden');
					element.find('.fa-caret-square-o-up')
					.removeClass('fa-caret-square-o-up')
					.addClass('fa-caret-square-o-down')
				}
			}
			// bind our toggle module function to this element's click event
			element.bind('click', toggleModule);
		}
	}
}])