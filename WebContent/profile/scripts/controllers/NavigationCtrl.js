


'use strict';


function NavigationCtrl( $scope, User ) {
	
	var _this = this;
	
	User.getProfile().then( function( promise ) {
		_this.user = promise.data;
	});
	
	// this is an accessibility helper for opening our
	// navigation's dropdown on keyboard focus
	_this.openDropdown = function( $event ) {
		_this.eventCatch = $event;
		$event.stopPropagation();
		$event.preventDefault();
		var parent = document.getElementById('rightMenu');
		parent.className = "open";
	}
	// this fixes opening the dropdown on click now that
	// we added a focus event for accessibility
	_this.toggleDropdown = function( $event ) {
		$event.stopPropagation();
		var parent = document.getElementById('rightMenu');
		if( _this.eventCatch !== null ) {
			parent.className = 'open';
			_this.eventCatch = null;
			return false;
		} else {
			parent.className = ( parent.className == '' ) ? "open" : '';
		}
	}
}