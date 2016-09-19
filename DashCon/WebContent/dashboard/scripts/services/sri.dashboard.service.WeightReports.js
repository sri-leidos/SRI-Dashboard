

'use strict';

angular.module('SRI.DASHBOARD')
	.service('service.WeightReports', ['factory.Weight', 'service.Profile', function( Weight ) {
		var service = {};
		var api = Weight;
		// get all weights ran from the api and parses
		// within a time limit, if given.
		this.getAllWeights = function( time ) {
			return api.getAllBySiteId().then( function( response ) {
				if( response.data == 'null' ) return false;
				var weightReports = response.data.weightReport;
				var weights = [];
				var scales = [];
				// ensure the format of both time we'll compare matches
				var timeLimit = moment(time, 'YYYY-MM-DD H:mm:ss');
				// create an array if our response is not one, this
				// usually happens when only 1 weight report exist.
				if( ! angular.isArray(weightReports) ) {
					weightReports = [ weightReports ];
				}
				// parse our weight reports
				angular.forEach( weightReports, function( weight ) {
					// ensure the format of both time we'll compare matches
					var actualTime = moment(weight.timestamp, 'YYYY-MM-DD H:mm:ss');
					// compare weight report time to time limit
					if( actualTime.isAfter( timeLimit ) ) {
						// create a user readable format of timestamp
						weight.displayTime = moment( weight.timestamp ).format('MM-DD-YYYY H:mm:ss');
						// create our pass or fail class for the background
						// color of our weight related modules.
						if( weight.grossWeight != undefined ) {
							weight.passed = 'passed';
							if( weight.status == 'F' ) {
								weight.passed = 'failed';
							}
						}
						// separate WIM reports from Static Scale ones.
						if( weight.scaleType === 'W' ) {
							weights.push( weight );
						} else if ( weight.scaleType === 'S' ) {
							scales.push( weight );
						}
					}
				});

				return {
					all: weightReports,
					wims: weights,
					scales: scales
				}
			})
		};

		return this;
	}])