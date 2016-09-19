

'use strict';

angular.module('SRI.DASHBOARD')
.service('service.PingImage', ['$http', '$timeout', '$rootScope', pingImage]);



function pingImage( $http, $timeout, $rootScope ) {
	var _this = this;
	
	_this.get = function( data ) {
		if( data != '' ) {
			var id = data.id;
			var sequence = data.sequenceNumber;
			var imageSrc = '/DashCon/dashboard/images/lpr/' + id + '.jpg';
			$http.get( imageSrc )
			.success( function() {
				// this avoids the $digest already in progress error
				// since we're using $apply()
				$timeout(function(){
					_this.data.hasImage = true;
					_this.data.image = _this.data.id + '.jpg';
					$rootScope.$broadcast('licensePlateImage', _this.data);
				});

			})
			.error( function() {
				if( id === _this.data.id )
					$timeout( function() {
						pingImage( data );
					}, 1500)
				else
					return false;
			})
		}
	}
	
}