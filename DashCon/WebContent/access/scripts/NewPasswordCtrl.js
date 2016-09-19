
// controller for the password recovery
angular.module('SRI.RETRIEVE')
.controller('access.NewPasswordCtrl', ['$scope', '$http', '$stateParams', function($scope, $http, $stateParams) {
	var _this = this;
	// submit
	var tempId = $stateParams.tempId;
	_this.submit = function() {
		if( _this.newPassword == _this.confirmPassword )
		$http.post('/DashCon/resources/retrieve/password/new', _this.newPassword)
	}
	
	setTimeout(function() {
		$('#newPassword').focus();
	});
}])