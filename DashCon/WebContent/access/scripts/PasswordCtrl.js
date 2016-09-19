
// controller for the password recovery
angular.module('SRI.RETRIEVE')
.controller('access.PasswordCtrl', ['$scope', '$http', '$q', function($scope, $http, $q) {
	var _this = this;
	// submit
	_this.submit = function() {
		$http.post('/DashCon/resources/retrieve/password', _this.email)
	  	.then(function( promise ) {
	  		if( promise.status == 200 ) {
			// when we set email sent to be true we also have to
			// set email not found and email not sent to false and
	  		// this behavior is replicated when values are switched
			_this.emailSent = true;
			    _this.emailNotFound = false;
	  		    _this.emailNotSent = false;
	  		} else if( promise.status == 204 ) {
	  			_this.emailNotFound = true;
	  			_this.emailSent = false;
	  	  		_this.emailNotSent = false;
	  			shakeInput();
	  		}
	  	}, function( promise ) {
	  		_this.emailNotSent = true;
	  		_this.emailSent = false;
			_this.emailNotFound = false;
			shakeInput();
	  	});
	}
}])