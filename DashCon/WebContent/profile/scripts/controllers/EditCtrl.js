/*
 * author:	Robert Roth
 * desc:	Controller for editting of user accounts.
 * params:
 *			$scope 	- this is the scope of the controller.
 * 			User 	- a factory for our User Resource.
 * 			user	- a resolved promise loaded from our User factory.
 */

'use strict';


function EditProfileCtrl( $scope, Profile, user ) {
	// cache our controller
	var _this = this;
	// Assign the resolved user data to our scope
	_this.menu = {
		active: 'email'
	}
	_this.eventCatch = null;
	_this.password = {};
	
	_this.initialize = function() {
		Profile.getProfile()
		.then( function( response ) {
			// add data to our controller's local scope
			_this.user = response.data;
			_this.confirmEmail = _this.user.email;
		});
	}
	
	// Methods:
	// Sends a PUT request to update our user
	_this.updateUser = function() {
		Profile.updateProfile( _this.user ).then( updateSuccess, updateFailed );
	};
	
	_this.updateEmail = function() {
		if( _this.confirmEmail === _this.user.email ) {
			Profile.updateEmail( _this.user.email ).then( updateSuccess, updateFailed );
		} else {
			// Show an alert message on top for our users
			$scope.utility.showMessage({
				type: 'error',
				message: 'Update Failed! Email and Confirm Email did not match.'
			});
		}		
	}
	
	_this.updatePassword = function() {
		if( _this.password.replace === _this.password.confirm ) {
			var dummyUser = {
				userId: _this.user.userId,
				userPassword: _this.password.current,
				newPassword: _this.password.replace
			};
			Profile.updatePassword( dummyUser ).then( updateSuccess, updateFailed );
		} else {
			// Show an alert message on top for our users
			$scope.utility.showMessage({
				type: 'error',
				message: 'Update Failed! Password and Confirm should match and be valid.'
			});
		}		
	}
	
	_this.passwordReset = function() {
		_this.password = {
			current: '',
			replace: '',
			confirm: ''
		};
		$scope.passwordUpdater.$setPristine();
	}
	
	_this.emailReset = function() {
		_this.initialize();
		$scope.emailUpdater.$setPristine();
	}
	
	_this.nameReset = function() {
		_this.initialize();
		$scope.nameUpdater.$setPristine();
	}
	
	// success and error functions for the notifyr directive
	function updateSuccess() {
		// Show an alert message on top for our users
		$scope.utility.showMessage({
			type: 'success',
			message: 'Successfully updated profile!'
		});
	}
	
	function updateFailed() {
		// Show an alert message on top for our users
		$scope.utility.showMessage({
			type: 'error',
			message: 'Update Failed! Try again or contact administrator.'
		});
	}

	// initialize our controller
	_this.initialize();
	
};