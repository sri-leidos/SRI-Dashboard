
'use strict';

angular.module('SRI.DASHBOARD')
	.factory('factory.Weight', ['$http', function( $http ){
		var Weight = {
			// get all weight reports
			getAll: function() {
				return $http.get('/DashCon/resources/weight/all');
			},
			// get all weight reports by site id
			getAllBySiteId: function() {
				return $http.get('/DashCon/resources/weight');
			},
			// get a specific weight report
			get: function( timestamp, sequenceNumber, siteId ) {
				var path = timestamp + '/' + sequenceNumber + '/' + siteId;
				return $http.get('/DashCon/resources/weight/' + url);
			}
		};

		return Weight;
	}])