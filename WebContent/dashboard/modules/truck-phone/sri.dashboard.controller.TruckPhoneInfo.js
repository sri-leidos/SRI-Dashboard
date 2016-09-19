

'use strict';

// CREATE ANGULAR MODULE
angular.module('sri.controller.TruckPhoneInfo', [])
// CONTROLLER DEFINITION
.controller('TruckPhoneInfoCTRL', ['$scope', '$http', '$timeout', '$rootScope',
// CONTROLLER FUNCTION
function( $scope, $http, $timeout, $rootScope ) {
	$scope.truck = $scope.truck || {};
	$scope.truckInfo = $scope.truckInfo || {};
	$scope.truckInfo.data = $scope.truckInfo.data || {};
	
	// load data when current truck is changed
	$scope.$watch('truck.current', function( newValue, oldValue ) {
		// Disregard undefined value
		if( newValue == undefined ) return false;
		// Remove background color initially
		// Check if the value is different from the previous one
		if( newValue !== oldValue ) {
			var path = newValue.sequenceNumber + '/' + newValue.siteId;
			$http.get('/DashCon/resources/truck/' + path).then( getInfo );
		}
	});
	
	// grab data when a new SRI Mobile truck user's data
	//comes in from our truck phone poll
	$scope.$on('truckFeedNew', function(event, message) {
		// get data from the database to populate our feed
		var truck = $scope.truck.current;
		if( message.sequenceNumber == truck.sequenceNumber ) {
			$scope.truckInfo.loading = true;
			$scope.truckInfo.hasData = true;
			$scope.truckInfo.data = message;
			$timeout(function() {
				$scope.truckInfo.loading = false;
			}, 1000);
		}
	});

	// create a function for setting the data from our response param
	// response - is passed from any get request that uses this function
	function getInfo( response ) {
		var data = response.data;
		// Initiate loading bar to simulate a loading of data
		$scope.truckInfo.loading = ( data != '' ) ? true : false;
		// Change background color if data is available
		$scope.truckInfo.hasData = ( data != '' ) ? true : false;
		// Set this module's data to the response
		// NOTE: Make sure the API returns only 1 object
		$scope.truckInfo.data = data;
		$rootScope.$broadcast('truckPhoneActive', data);
		// simulate end of loading by removing the animation
		$timeout( function() {
			$scope.truckInfo.loading = false;
		}, 1000);
	}
	
}]);