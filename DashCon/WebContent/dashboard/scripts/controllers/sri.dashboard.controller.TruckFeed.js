/**
* Truckfeed Module
*
* Description
* Controls the truckfeed portion of the SRI application.
* This gathers all weight reports, and trucks passing by
* the WIM in real time(latency involved).
* 
* Dependencies
* $rootScope is used here merely for $broadcast, which similar to jquery's trigger()
* $scope is the scope of our application SRI.DASHBOARD
* $http will be used to make ajax requests
* $filter is service used for accessing the angular filter 'orderBy' which maps our feed by order
* service.WeightReports (scripts/services/WeightReports.js) is a service for getting and building
* our weight reports from the database for user display
* sri.module.Effects (scripts/directives/sri.directive.MaterialEffect.js)
* 	= contains a directive for the Google Material click effect.
*/
'use strict';
angular.module('sri.module.Truckfeed', ['sri.module.Effects'])
// this directive allows us to press enter on a truck feed and trigger the click event for it
.directive("clickEnter", ['$parse', function($parse) {
    return function(scope, element, attr) {
      //grabbing the function from the attributes and parsing it (to get parameters I believe, this taken from the code above.
      var fn = $parse(attr['actionDirective']);

      //making the handler so it can be bound to different events without repeating again taken from source above
      var handler = function(event) {
          scope.$apply(function() {
           fn(scope, {$event:event});
          }
        )};

       //If clicked calling the handler
       element.bind('click', handler);
       //Checking first that it's the enter key "13" then calling the handler
       element.bind('keyup', function(event) { if(event.keyCode==13) handler(event)});
    }
}])
// add a controller to our module
.controller('TruckFeedCTRL',
// inject dependencies
['$rootScope', '$scope', '$http', '$filter','service.WeightReports', 'service.Profile', '$timeout',                           
// create our controller using an anonymous function
function( $rootScope, $scope, $http, $filter, WeightReports, Profile, $timeout ) {
	// these are the variables added to our scope
	$scope.trucks = [];
	// initialize our truck object
	$scope.truck = $scope.truck || {};
	$scope.truck.current = {};
	$scope.truck.scroll = true;
	// Truck sequence is a cached object for faster
	// locating of a truck using its sequence number.
	var truckSequence = {};
	// TODO: instead of being event driven, why not attach the
	// truck sequence to our scope and have other controllers have
	// access to it similar to truck.current
	$scope.truck.sequence = [];
	// get time two hours ago and use as a time limit
	var twoHoursAgo = moment( new Date() ).subtract(2, 'hours').format('YYYY-MM-DD H:mm:ss');
	// get all weight reports and passing a time limit
	// will populate within the time limit.
	WeightReports.getAllWeights( twoHoursAgo )
	.then( function( weightReports ) {
		var weights = weightReports.wims;
		var scales = weightReports.scales;

		angular.forEach( weights, function( weight ) {
			if( weight !== undefined )
				angular.forEach( scales, function( scale, index ) {
					if( scale !== undefined )
						if( weight.sequenceNumber == scale.sequenceNumber ) {
							weight.hasScale = true;
							scales.splice( index, 1 );
						}
				});
		});
		// match data from weight report to the truck phone data
		// using the sequence number
		if( weights != undefined ) {
			$http.get('/DashCon/resources/truck').then( function( promise ) {
				var data = promise.data;
				var trucks = [];
				var truckfeed = [];
				
				// check if we don't have any truck information sent
				// by from the SRI Mobile Application
				if( data !== null && data !== "null" ) {
					// since we have an irregular JSON response we need
					// to catch if there's only 1 entry from the database
					if( data.truckFeed.id !== undefined ) {
						trucks.push(data.truckFeed);
					} else {
						trucks = data.truckFeed;
					}
					
					// loop through each weight and attach any associated truck
					// information from the saved SRI Mobile Application feed
					for( var x = 0, len = weights.length; x < len; x++ ) {
						var weight = weights[x];
						if( weight != undefined ) {
							angular.forEach(trucks, function( truck, index ) {
								// Begin merging trucks with similar timestamp
								if( weight.sequenceNumber == truck.sequenceNumber && weight.siteId == truck.siteId ) {
									// Merge the weight's properties with the truck's
//									truck = angular.extend({}, weight );
									truck = angular.extend(truck, weight );
									// Adds a Phone Icon if it does have a truckfeed
									truck.hasPhone = true;
									truckfeed.push( truck );
									truckSequence[truck.sequenceNumber.toString()] = truck;
									trucks.splice(index, 1);
									weights.splice(x, 1);
								}
							});
						}
					} //end of for loop
				}//end of if data !== null
				
				// add any weight report that doesn't have any truck
				// information from our mobile application
				angular.forEach( weights, function( weight ) {
					truckfeed.push( weight );
					truckSequence[weight.sequenceNumber.toString()] = weight;
				});
				
				// Sort our truckfeed array to fix the "Current Truck" issue.
				truckfeed = $filter('orderBy')(truckfeed, 'timestamp', false);
				$scope.trucks = truckfeed;
				
				// get our user's site id first to screen the LPR functionality
				Profile.getProfile().then( function( currentUser ) {
					if( currentUser.siteId == '2' ) {
						// get LPR and show icon for each feed that has data for it
						$http.get('/DashCon/resources/lpr').then( function( promise ) {
							var data = promise.data;
							// check if response is a single object and not an
							// array objects
							if( data.id !== undefined ) {
								data.licensePlate = data;
							}
							angular.forEach(data.licensePlate, function( item ) {
								if( truckSequence[ item.sequenceNumber ] != undefined ) {
									truckSequence[ item.sequenceNumber ].hasLPR = true;
									truckSequence[ item.sequenceNumber ].lprImage = '/DashCon/dashboard/images/lpr/' + item.id + '.jpg';
								}
							});
							
							// Set current truck to the latest, which is the last entry
							if( truckfeed.length > 0 ) {
								$scope.truck.current = $scope.trucks[ $scope.trucks.length - 1 ];
							}
						});// end of LPR
					}
				});
				// get USDOT number and show icon for each feed that has data for it
				$http.get('/DashCon/resources/usdot').then( function( promise ) {
					var data = promise.data;
					// check if response is a single object and not an
					// array objects
					if( data.sequenceNumber !== undefined ) {
						data.usdot = data;
					}
					angular.forEach(data, function( item ) {
						if( truckSequence[ item.sequenceNumber ] != undefined ) {
							truckSequence[ item.sequenceNumber ].hasUSDOT = true;
							truckSequence[ item.sequenceNumber ].usdotNumber = item.usdotNumber;
						}
					});
				})
			});// end of resources/truck
		}// end of if weight is undefined
	})
	.then(function() {
		// fade out our pre-loader when truck feed is complete
		$timeout(function() {
			$('#mainLoader').fadeOut();
		});
	});// end of WeightReports.getAllWeights()
	
	// long poll our truck feed servlet
	function pollTrucksApp( currentUser ) {
		$http.get('/DashCon/truckFeedPoll').success( function( data ) {
			// Disregard invalid entries
			if( data.timestamp != null && data.timestamp != "null" ) {
			// populate feeds based on current user's site id
				if( currentUser.siteId == data.siteId ) {
					truckPhoneAppEntry( data, currentUser );
				}
			}
			pollTrucksApp(currentUser);
		}).error( function( err ) {
			console.log( err );
			pollTrucksApp(currentUser);
		});
	}
		
	// long poll our Mettler weight report servlet
	function pollTrucksMettler( currentUser ) {
		$http.get('/DashCon/mettPoll').success( function( data ) {
			// populate feeds based on current user's site id
			if( currentUser.siteId == data.siteId ) {
				if( data.scaleType == 'W' ) {
					populateFeedNew( data );
				} else if( data.scaleType == 'S' ) {
					staticScaleEntry( data.sequenceNumber );
					$rootScope.$broadcast('staticScale:new', data);
				}
			}
			pollTrucksMettler(currentUser);
		}).error( function( err ) {
			console.log( err );
			pollTrucksMettler(currentUser);
		});
	}
	// adding a truck into our truck feed/sidebar
	function populateFeedNew( data ) {
		// get the time two hours ago; Note: This is based on computer's current time
		var twoHoursAgo = moment( new Date() ).subtract(2, 'hours').format('MM-DD-YYYY H:mm:ss');
		// make sure data's timestamp is no later than two hours ago
		if( moment(data.timestamp, 'YYYY-MM-DD H:mm:ss').isAfter(moment(twoHoursAgo, 'YYYY-MM-DD H:mm:ss')) ) {					
			// create a user friendly format of our new feed's timestamp
			data.displayTime = moment(data.timestamp).format('MM-DD-YYYY H:mm:ss');
			// add a 'passed' attribute which is primarily used to affect CSS styling
			if( data.grossWeight != undefined ) {
				data.passed = ( data.status == 'P' ) ? 'passed' : 'failed';
			}
			// get our driver's license or commercial license
			// and attach it into our new feed
			if( data.driversLicense == undefined || data.driversLicense == null || data.driversLicense == "null" ) {
				data.truckLicense = data.commercialDriversLicense;
			} else {
				data.truckLicense = data.driversLicense;
			}
			// if this is the very first feed set it as our current and active truck
			// else if feed is not the same as the current feed, set the new feed as current
			if( $scope.truck.current == undefined || $scope.trucks.length == 0 ) {
				if( $scope.truck.scroll === true ) {
					$scope.truck.current = data;
				}
				$scope.trucks.push( data );
				truckSequence[data.sequenceNumber.toString()] = data;
			} else if( ! moment($scope.trucks[$scope.trucks.length -1].timestamp, 'YYYY-MM-DD H:mm:ss').isSame(moment(data.timestamp, 'YYYY-MM-DD H:mm:ss')) ) {	
				if( $scope.truck.scroll === true ) {
					$scope.truck.current = data;
				}
				$scope.trucks.push( data );
				truckSequence[data.sequenceNumber.toString()] = data;
			}
		}
	}
	// change active truck to the current event's truck
	$scope.loadValues = function() {
		// remove current truck as active truck
		$scope.truck.current.active = false;
		// set event's truck as active truck
		this.truck.active = true;
		// set active truck as our scope's current truck
		$scope.truck.current = this.truck;
		// trigger touch event
		$('#phoneSideBar').trigger('phone:touch');
	};
	// change active truck on current truck change
	$scope.$watch('truck.current', function( newValue, oldValue ) {
		if( newValue != undefined ) {
			if( newValue != oldValue && $scope.truck.scroll === true ) {
				if( oldValue != undefined ) {
					oldValue.active = false;
				}
				newValue.active = true;
			}
		}
	});
	// listen to license plate reader image load and populate it in our feed
	$scope.$on('licensePlateImage', function( $event, message ) {
		var sequence = message.sequenceNumber;
		var truck = truckSequence[ sequence ];
		if( truck != undefined) {
			truck.lprImage = '/DashCon/dashboard/images/lpr/' + message.id + '.jpg';
		}
	});
	// remove safer icon from our current truck's feed when this event is triggered
	$scope.$on('saferLookupUndefined', function( $event, message ) {
		var truck = $scope.truck.current;
		var sequenceNumber = truck.sequenceNumber;
		truck.hasUSDOT = false;
		truck.usdotNumber = undefined;
		if( truckSequence[ sequenceNumber ] != undefined ) {
			truckSequence[ sequenceNumber ].hasUSDOT = false;
		}
	});
	// add LPR icon to our current truck's feed when this even is triggered
	$scope.$on('licensePlateAvailable', function( $event, message ) {
		var sequenceNumber = message.sequenceNumber;
		if( truckSequence[ sequenceNumber ] != undefined ) {
			var truck = truckSequence[ sequenceNumber ];
			truck.hasLPR = true;
			if( message.licensePlateNumber != undefined ) {
				truck.licensePlate = message.licensePlateNumber;
			}
		}
	});
	// add static scale icon and set current truck to this sequence
	function staticScaleEntry( sequenceNumber ) {
		if( truckSequence[ sequenceNumber ] != undefined ) {
			truckSequence[ sequenceNumber ].hasScale = true;
			if( $scope.truck.scroll === true ) {
				$scope.truck.current = truckSequence[ sequenceNumber ];
			}
		}
	}
	// add phone app icon and broadcast phone data then validate USDOT
	// number attached to our phone data and add USDOT icon when valid
	function truckPhoneAppEntry( phoneData, currentUser ) {
		if( truckSequence[ phoneData.sequenceNumber ] != undefined ) {
			var truck = truckSequence[ phoneData.sequenceNumber ];
			truck.hasPhone = true;
			truck.id = phoneData.id;
			// verify usdot number works with safer
			$http.get('/DashCon/resources/safer/' + phoneData.usdotNumber )
			// add an icon if it is valid according to safer
			.then( function( promise ) {
				var data = promise.data;
				if( data != '' && data != 'null' && data.companyData != undefined ) {
					truck.hasUSDOT = true;
					truck.usdotNumber = phoneData.usdotNumber;
				}
			});
			// save the usdot number to our database
			phoneData.usdotNumber = String(phoneData.usdotNumber); // parse string
			phoneData.manualEntered = 0; // 0 means false
			phoneData.userId = currentUser.userId; // grab our current user's username
			$http.post('/DashCon/resources/usdot', phoneData); // make the post request
			// broadcast that we have a new SRI phone app data available for grabs
			// to other modules lsitening
			$rootScope.$broadcast('truckFeedNew', phoneData);
		}
	}
	
	// get current user's profile and run our long polls
	Profile.getProfile().then( function( currentUser ) {
		pollTrucksApp(currentUser);
		pollTrucksMettler(currentUser);
	});

	// TODO: a better solution for verifying truck phone data without
	// causing a hiccup when changing current trucks
	// remove phone icon from our current truck's feed when this event is triggered
//	$scope.$on('truckPhoneUndefined', function( $event, message ) {
//		var truck = $scope.truck.current;
//		if( truck.hasPhone != true && truck.id == undefined ) {
//			var sequenceNumber = truck.sequenceNumber;
//			$scope.truck.current.hasPhone = false;
//			if( truckSequence[ sequenceNumber ] != undefined ) {
//				truckSequence[ sequenceNumber ].hasPhone = false;
//			}
//		}
//	});
	
}]);