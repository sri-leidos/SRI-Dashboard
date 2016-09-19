/*
 * @author 	Robert Roth
 * @desc   	Beginning of the Dashboard application.
 * 			Here we load the Module into the DOM first,
 * 			then initialize our app.
 */


(function(window, document, $, undefined) {
	
	/*
	 * This is experimental. This is how we customize the 
	 * dashboard and how we place it in the UI.
	 */
	
	var left = document.getElementById('left_panel');
	var middle = document.getElementById('middle_panel');
	var right = document.getElementById('right_panel');

	left.innerHTML = '<div ng-include="\'modules/wim/RampWIM.html\'"></div>' + 
					 '<div ng-include="\'modules/static-scale/StaticScale.html\'"></div>' +
					 '<div ng-include="\'modules/safer/us-inspections/Inspections.html\'"></div>';
	
	middle.innerHTML = 	'<div ng-include="\'modules/truck-phone/TruckPhoneInfo.html\'"></div>' +
						'<div ng-include="\'modules/safer/safety-rating/SafetyRating.html\'"></div>' +
						'<div ng-include="\'modules/safer/us-crash-data/CrashData.html\'"></div>' +
						'<div ng-include="\'modules/license-plate-reader/LicensePlateTmpl.html\'"></div>' +
						'<div ng-include="\'modules/safer/us-crash-data/CrashData2.html\'"></div>';

	right.innerHTML = 	'<div ng-include="\'modules/safer-lookup/SaferLookup.html\'"></div>' + 
						'<div ng-include="\'modules/safer/company-data/CompanyData.html\'"></div>' +
						'<div ng-include="\'modules/safer/safety-rating/SafetyRating2.html\'"></div>';

	angular.module('SRI.DASHBOARD', [
		 'sri.controller.Main',
		 'sri.module.Truckfeed',
		 'sri.controller.rightPanelMenu',
		 'sri.controller.LeftPanel',
		 'sri.controller.sriModules',
		 'sri.controller.truckfeedControls',
		 // This dependency contains the controllers
		 // for all modules done as a parent module.
		 'sri.container.Modules'
		 ]);
	
})(window, document, jQuery);
		 
		  

/*
 * @author rothr
 * @desc Holds scripts for mobile view interaction between the Truck Feed
 * 		 (Sidebar) and the Modules Container(Right Panel).
 */
(function(window, document, jQuery, undefined){
	$(document).ready(function() {
		
		new WOW().init();
		
		$('.fadeInLeft, .fadeInOut, .fadeInRight, .fadeOutRight').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
		      $(this).removeClass('fadeInDown');
		});
		
		var item = {
			show: function( device ) {
				device = ( device ) ? '-' + device : '';
				this.element.removeClass('hidden' + device);
				return this;
			},
			hide: function( device ) {
				device = ( device ) ? '-' + device : '';
				this.element.addClass('hidden' + device);
				return this;
			},
			fadeIn: function() {
				this.element.fadeIn();
				return this;
			},
			fadeOut: function() {
				this.element.fadeOut();
				return this;
			}
		};
		
		var mainContent = $.extend({}, item);
		mainContent.element = $('#mainContent');
		
		var frame = $.extend({}, item);
		frame.element = $('#frameContainer');
		
		frame.init = function() {
			var _this = this;
			$('#frameCloser').click(function(evnt) {
				evnt.preventDefault();
				_this.fadeOut();
				_this.element.parent().removeClass('modal-open');
			});
			_this.element.on('openFrame', function(evnt) {
				evnt.preventDefault();
				_this.show(0, _this.fadeIn());
				_this.element.parent().addClass('modal-open');
			});
		};
		
		frame.init();

		var sidebar = $.extend({}, item);
		sidebar.element = $('#sideBar');
		
		$('#phoneSideBar').click( function(e) {
			e.preventDefault();
			mainContent.show('xs');
			sidebar.hide('xs');
			mainContent.show('sm');
			sidebar.hide('sm');
			$(this).hide(0);
			$('#phoneModules').show(0)
		});

		
		$('#phoneSideBar').on('phone:touch', function(e) {
			e.preventDefault();
			setTimeout(function() {
				mainContent.show('xs');
				sidebar.hide('xs');
				mainContent.show('sm');
				sidebar.hide('sm');
			}, 200);
			$(this).hide(0);
			$('#phoneModules').show(0)
		});
		
		$('#phoneModules').click( function(e) {
			e.preventDefault();
			mainContent.hide('xs');
			sidebar.show('xs');
			mainContent.hide('sm');
			sidebar.show('sm');
			// fix a bug where vehicle entries shrink when saving
			// usdot numbers manually
			$('#vehicleEntries').height('100%');
			$('#vehicleEntries').height( $('#vehicleEntries').height() -29 );
			$(this).hide(0);
			$('#phoneSideBar').show(0)
		});
		
	});
})(window, document, $);
'use strict';
			
(function() {
	// resize the controls wrapper width according to sideBar width
	// this is done for child elements with "fixed" position
	var truckControlsResize = (function() {
		return function () {
			var width = $(".entry-wrapper").css("width");
			$(".truck-entry-controls").css('width', width);
		};					
	})();
	// this show truck entries according to selected status:
	// passed, warned, failed, otherwise shows all
	// this automatically add .active class on first entry
	var listTruckEntries = (function(status) {
		var vehicles = null;
		return function(status) {
			// get all entries to a variable
			vehicles = $('#vehicleEntries').find('.truck-entry-box');
			// map all vehicles and return ones with status
			if(status) {
				vehicles = $.map(vehicles, function(vehicle){
					$(vehicle).removeClass('active');
					if( $(vehicle).hasClass(status) ) {
						$(vehicle).removeClass('hidden');
						return $(vehicle);
					} else {
						$(vehicle).addClass('hidden');
					}
					
				});
			} else {
				vehicles = $.map(vehicles, function(vehicle){
					$(vehicle).removeClass('active').find('.glyphicon-chevron-right').addClass('hidden');
					$(vehicle).removeClass('hidden');
					return $(vehicle);
				});
			}
			// the first one is automatically active
			vehicles[0].addClass('active').find('.glyphicon-chevron-right').removeClass('hidden');
			
			console.log("Trigger: refresh-list");
			$('#mainLoader').addClass('hidden');
			App.Event.Vehicle.trigger("refresh-list");
		};
		
	})();
	//resize truck controls on init
	truckControlsResize();
	// bind truckControlsResize function to window resizing
	$(window).bind("resize", function() {
		truckControlsResize();
	});
	// gives the ability to change the text for .btn-value according
	// to the selected option; emulates listbox form element
	$('.truck-entry-controls > .btn-group > .dropdown-menu > li > a').bind('click', function(e) {
		e.preventDefault();
		var el = this;
		var id = $(el).closest('ul').siblings('.btn-value').find('.option-text');
		$(id).text($(el).text());
	});
	// this is for showing truck entries according to status
	// inside our sideBar
	$("#truckStatusOption > li > a").bind('click', function() {
		
		//$('.entry-wrapper').find('.truck-entry-box').addClass('hidden');
		var option = $(this).text();
		
		if (option === "Passed") {
			listTruckEntries('passed');
			//App.Event.Vehicle.trigger('pass');
		}
		else if (option === "Warned") {
			listTruckEntries('warned');
		}
		else if (option === "Failed") {
			listTruckEntries('failed');
		}
		else {
			listTruckEntries();
		}
		

		truckControlsResize();
		
	});
	
	$('.entryDate').text(moment().format("h:mm:ss a MM/DD/YYYY"));
	
})(jQuery);


(function(window, document, $, undefined) {
	// debouncing function from John Hann
	// http://unscriptable.com/index.php/2009/03/20/debouncing-javascript-methods/
	// ---
	// @desc this throttles the resize event for the window object.
	// this is a more efficient way of listening to window resize event.
	(function($,sr){
	  var debounce = function (func, threshold, execAsap) {
	      var timeout;
	      return function debounced () {
	          var obj = this, args = arguments;
	          function delayed () {
	              if (!execAsap)
	                  func.apply(obj, args);
	              timeout = null;
	          };
	          if (timeout)
	              clearTimeout(timeout);
	          else if (execAsap)
	              func.apply(obj, args);
	          timeout = setTimeout(delayed, threshold || 100);
	      };
	  }
	  // smartresize 
	  jQuery.fn[sr] = function(fn){  return fn ? this.bind('resize', debounce(fn)) : this.trigger(sr); };
	})(jQuery,'smartresize');
	//--
	
	// @author "rothr"
	// @desc "the following code handles the resizing of the vehicle entries
	// container to change its height depending on the size of its parent.
	// without this, the truckfeed would not fit properly, and no CSS can
	// help with this"

	// this is the height of the truckfeed controls container
	var MENU_HEIGHT = 29;
	// the select or id for the element to resize
	var ELEMENT_ID = '#vehicleEntries';
	
	var vehicleEntries = {};
	vehicleEntries.el = $(ELEMENT_ID);
	vehicleEntries.height = function( height ) {
		var _this = this;
		// if height is undefined give 100% as default height
		height = '100%';
		_this.el.height( height );
		_this.el.height( _this.el.height() - MENU_HEIGHT );
		return this;
	}
	// call the height resize function
	vehicleEntries.height();
	// cache window for better access
	var $window = $(window);
	// cache the current window height
	var winHeight = $window.height();
	// use our smartresize function
	$window.smartresize( function() {
		// change height if window height changed
		if( winHeight !== $window.height() ) {
			winHeight = $window.height();
			vehicleEntries.height();
		}
		
		// the following code are used to resize the license
		// plate reader module container done in jQuery since
		// it's the easiest and quickest way to do rather 
		// than creating another angular directive.
		var lprContainer = $('.lpr-container');
		var lprWidth = lprContainer.width();
		var ratio = 1.3317;
		var newHeight = lprWidth / ratio;
		lprContainer.height( newHeight );
	});
	
})(window, document, jQuery);


'use strict';


angular.module('sri.controller.LeftPanel', [])
	.controller('LeftPanelCTRL', ['$scope', function( $scope ) {
		$scope.truck = $scope.truck || {};
	}]);


'use strict';


angular.module('sri.controller.Main', [])
	.controller('MainCTRL', ['$scope', function( $scope ) {
		$scope.truck = $scope.truck || {};
		$scope.feed = $scope.feed || {};
	}]);


angular.module('sri.controller.sriModules', [])
	.controller('ModuleCTRL', ['$scope', function( $scope ) {	
		
	}]);


'use strict';

angular.module('sri.controller.rightPanelMenu', [])
	.controller('RightMenuCTRL', [ '$scope', 'service.Profile', function( $scope, Profile ) {
		
		var _this = this;
		
		Profile.getProfile().then( function( data ) {
			_this.user = data;
		});
		
		$scope.triggerFrame = function() {
			$('#frameContainer').trigger('openFrame');
		};
	}])
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
								if( weight.sequenceNumber == truck.sequenceNumber ) {
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
		truck.lprImage = '/DashCon/dashboard/images/lpr/' + message.id + '.jpg';
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

'use strict';


angular.module('sri.controller.truckfeedControls', [])
	.controller('TruckFeedControlsCTRL', ['$scope', function( $scope ) {
		// get the global scope's feed object
		$scope.feed = $scope.feed || {};
		// cache this controller
		var _this = this;
		// Populate our filters, these are items in buttons
		// that are used to filter the truck feed.
		_this.selectedTime = 120;
		_this.selectedStatus = 'All';
		_this.selectedLock = {
			text: 'Unlocked',
			icon: 'fa fa-unlock'
		}
		
		// These are the filters we use and populate as buttons
		// for the users
		_this.statuses = ['All', 'Passed', 'Failed', 'Active', 'SRI Mobile'];
		_this.time = [5, 30, 60, 120];
		_this.scrolling = ['Unlocked', 'Locked'];
		
		// This is the function that changes filters
		_this.statusChange = function( $event, status ) {
			_this.selectedStatus = status;
			if( status !== 'All' ) {
				if( status == 'Active' ) {
					$scope.feed.active = true;
					$scope.feed.hasPhone = undefined;
					$scope.feed.statusFilter = undefined;
				} else if( status == 'SRI Mobile' ) {
					$scope.feed.hasPhone = true;
					$scope.feed.active = undefined;
					$scope.feed.statusFilter = undefined;
				} else {
					_this.statusFilter = status.toLowerCase();
					$scope.feed.statusFilter = status.toLowerCase();
					$scope.feed.active = undefined;
					$scope.feed.hasPhone = undefined;
				}
			} else {
				$scope.feed.statusFilter = undefined;
				$scope.feed.active = undefined;
				$scope.feed.hasPhone = undefined;
			}
		};

		_this.timeChange = function( evnt, time ) {
			_this.selectedTime = time;
			$scope.feed.timeFilter = time;
		};
		
		// This is the function that changes filters
		_this.scrollingChange = function( $event ) {
			var displayText = document.getElementById('scrollingText');
			var scrollingIcon = document.getElementById('scrollingIcon');
			
			var value = displayText.innerHTML;
			
			if( value == 'Locked' ) {
				$scope.truck.scroll = true;
				scrollingIcon.className = 'fa fa-unlock';
				displayText.innerHTML = 'Unlocked';
			} else {
				$scope.truck.scroll = false;
				scrollingIcon.className = 'fa fa-lock';
				displayText.innerHTML = 'Locked';
			}
			$event.currentTarget.blur();
		};
		
	}])


'use strict';

angular.module('sri.directive.Module', [])
	.directive('dirModule', function() {
		return {
			restrict: 'EA',
			templateUrl: '/DashCon/dashboard/templates/ModuleTMPL.html',
			controller: function( $scope ) {
				$scope.currentTruck = $scope.currentTruck || {}; 
			}
		};
	});
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



'use strict';

angular.module('SRI.DASHBOARD')
/*
 * @author Robert Roth
 * @desc This directive replaces an img src
 * on error with the given url link attribute 
 */
.directive('defaultImage', function() {
    return {
        link: function( scope, element, attrs ) {
          element.bind('error', function() {
//            if ( attrs.src != attrs.defaultImage ) {
              attrs.$set( 'src', attrs.defaultImage );
//            }
          });
        }
    }
});


angular.module('sri.module.Effects', [])
	.directive('ngMaterial', function() {
		return {
			restrict: 'A',
			link: function( scope, element, attr ) {
				var container, material, diameter, x, y;
				container = element;
				element.on('click', function( evnt ) {
					container.css({
						'overflow': 'hidden',
						'position': 'relative'
					});
					// create the ripple if it doesn't exist
					if( container.find('.material').length == 0 ) {
						container.prepend('<span class="material"></span>');
					}
					material = container.find('.material');
					// catch multiple clicks and just reanimate
					material.removeClass('animate-material');
					// set size of our ripple
					if( !material.height() && !material.width() )
					{
						// to create the circle, get the larger between height and width and use for our ripple
						diameter = Math.max(container.outerWidth(), container.outerHeight());
						material.css({height: diameter, width: diameter});
					}
					// get click coordinates
					x = evnt.pageX - container.offset().left - material.width()/2;
					y = evnt.pageY - container.offset().top - material.height()/2;
					// set the ripple's position and animate
					material.css({top: y+'px', left: x+'px'}).addClass('animate-material');
					// this is a fix for revealers like wowJS and scrollrevealJS
					material.on('animationend webkitAnimationEnd oanimationend MSAnimationEnd', function() {
						material.removeClass('animate-material');
						material.remove();
					})
				})
			}
		}
	})

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

/*
 * @author Robert Roth
 * @desc This is an Angular Filter that filters items by time.
 * a time limit is given e.g. 120 minutes and as long as the timestamp
 * of the item is within the 120 minutes from the computer's clock then
 * the item will not be filtered out.
 */

'use strict';

angular.module('SRI.DASHBOARD')
//
.filter('filterTime', function() {
	if( ! moment ) {
		console.info('MomentJS not found');
		return false;
	}

	function filterTime( items, options ) {
		var time = ( typeof options == 'number' ) ? options : options.time;
		var timelimit = moment( new Date() ).subtract( time, 'minutes').format('YYYY-MM-DD H:mm:ss');
		var filteredItems = [], timestamp = null;
		if( items != undefined && items.length > 0 )
			angular.forEach( items, function( item ) {
				timestamp = moment( item.timestamp ).format('YYYY-MM-DD H:mm:ss');
				if( moment(timestamp, 'YYYY-MM-DD H:mm:ss').isAfter( moment(timelimit, 'YYYY-MM-DD H:mm:ss') ) ) filteredItems.push( item );
			})
		return filteredItems;
	}

	return filterTime;
})



'use strict';


angular.module('SRI.DASHBOARD')
	.service('service.Mettler', ['$http', 'service.Profile', function( $http, Profile ) {
		var _this = this;
		var currentUser = Profile.getProfile();
		
		_this.pollTrucksApp = function() {
			$http.get('/DashCon/truckFeedPoll').success( function( data ) {
				// Disregard invalid entries
				if( data.timestamp == null || data.timestamp == "null" ) return false;
				// populate feeds based on current user's site id
				if( currentUser.siteId !== data.siteId ) return false;
				$rootScope.$broadcast('truckFeedNew', data);
				truckPhoneAppEntry( data.sequenceNumber );
				_this.pollTrucksApp();
			}).error( function( err ) {
				console.log( err );
				_this.pollTrucksApp();
			});
		};
		
		_this.pollTrucksMettler = function() {
			$http.get('/DashCon/mettPoll').success( function( data ) {
				// populate feeds based on current user's site id
				if( currentUser.siteId !== data.siteId ) return false;
				if( data.scaleType == 'W' ) {
					populateFeedNew( data );
				} else if( data.scaleType == 'S' ) {
					staticScaleEntry( data.sequenceNumber );
					$rootScope.$broadcast('staticScale:new', data);
				}
				_this.pollTrucksMettler();
			}).error( function( err ) {
				console.log( err );
				_this.pollTrucksMettler();
			});
		}
		
		_this.initialize = function() {
			_this.pollTrucksApp();
			_this.pollTrucksMettler();
		};
		
	}]);
	


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



/*
 * author:	Robert Roth
 * desc:	This is an Angular Service for interfacing with
 * 			the Profile Resource web service.
 * params:  $http - provides http requests.
 */

'user strict';

angular.module('SRI.DASHBOARD')
	.service('service.Profile', ['$http', function( $http ) {
		var _this = this;
		this.getProfile = function() {
			return $http.get('/DashCon/resources/profile/').then(function( response ) {
				return response.data;
			});
		};
		
		this.updateProfile = function( user ) {
			return $http.put('/DashCon/resources/profile', user ).then( function( response ) {
				return response;
			});			
		};
		
		this.updateEmail = function( email ) {
			return $http.put('/DashCon/resources/profile/email', email).then(function( response ) {
				return response;
			});
		};
		
		this.updatePassword = function( user ) {
			return $http.put('/DashCon/resources/profile/password', user).then(function( response ) {
				return response;
			});
		};
		
	}]);




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


'use strict';

angular.module('sri.directive.DataCaptured', [])
.directive('dirDataCaptured', function() {
	return {
		restrict: 'EA',
		scope: {},
		templateUrl: '/DashCon/dashboard/templates/modules/ModuleTMPL.html',
		link: function( scope, element, attrs ) {
			var currentTruck = scope.$parent.$parent.currentTruck;
			scope.title = 'Data Captured';
			scope.data = currentTruck.dataCaptured;
			scope.moduleColor = scope.data.Status || '';
		}
	}
});


'use strict';

angular.module('sri.directive.MainlineWIM', [])
.directive('dirWim', function() {
	return {
		restrict: 'EAC',
		scope: {
			truck: '='
		},
		replace: true,
		templateUrl: '/DashCon/dashboard/templates/modules/ModuleTMPL.html',
		link: function( scope ) {
			// Get current truck from the parent
			//scope.currentTruck = scope.$parent.$parent.currentTruck;
			scope.title = 'Mainline Wim';
			
			scope.$watch('truck', function( newValue, oldValue ) {
				if( newValue !== oldValue ) {
					scope.data = scope.truck.current.wim;
					console.log( scope.data );
				}
			}, true);
			
		}
	};
});


'use strict';


angular.module('sri.container.Modules', [
     	'sri.controller.RampWIM',
     	'sri.controller.TruckPhoneInfo',
     	'sri.controller.Safer.CompanyData',
     	'sri.controller.Safer.USCrashData',
     	'sri.controller.Safer.SafetyRating',
     	'sri.controller.Safer.USInspections',
     	'sri.controller.StaticScale',
     	'sri.controller.SaferLookup',
     	'sri.controller.LicensePlateRdr'
     	])


'use strict';


angular.module('sri.controller.DataCaptured', [])
	.controller('DataCapturedCTRL', ['$scope', function( $scope ) {
		$scope.truck = $scope.truck || {};
		
		$scope.dataCaptured = $scope.dataCaptured || {};
		$scope.dataCaptured.id = 'dataCaptured';
		$scope.dataCaptured.title = 'Data Captured';

		var currentTruck = $scope.truck.current;
		
		$scope.dataCaptured.licensePlate = currentTruck.licensePlate;
		$scope.dataCaptured.dotNumber = currentTruck.dotNumber;
		$scope.dataCaptured.weight = currentTruck.weight;
		
		$scope.$watch('truck', function( newValue, oldValue ) {
			if( newValue !== oldValue ) {
				currentTruck = $scope.truck.current;
				$scope.dataCaptured.licensePlate = currentTruck.licensePlate;
				$scope.dataCaptured.dotNumber = currentTruck.dotNumber;
				$scope.dataCaptured.weight = currentTruck.weight;
			}
		}, true);
	}]);


'use strict';


angular.module('sri.controller.LicensePlateRdr', [])
	.controller('LicensePlateCtrl', ['$scope', '$rootScope', '$http', 'service.Profile', '$timeout',
     function( $scope, $rootScope, $http, Profile, $timeout ) {
		$scope.truck = $scope.truck || {};
		var _this = this;		
		// set default values for reset and reference
		var lprDefaults = {
			id: '',
			timestamp: null,
			siteId: null,
			fileName: '',
			sequenceNumber: null,
			licensePlateNumber: null,
			state: '',
			hasImage: false
		}
		// instantiate our license plate object
		_this.data = angular.extend({}, lprDefaults);

		Profile.getProfile().then( function( currentUser ) {
			$scope.userSiteId = currentUser.siteId;

			if( currentUser.siteId == '2' ) {
				pollLicensePlate( currentUser );
				// call the license plate image when user changes active truck
				$scope.$watch('truck.current', function( newValue, oldValue ) {
					// Disregard undefined values
					if( newValue == undefined ) return false;
					if( newValue !== oldValue ) {
						var path = newValue.siteId + '/' + newValue.sequenceNumber;
						$http.get('/DashCon/resources/lpr/' + path).then(function( promise ) {
							_this.data = promise.data;
							if( promise.data != '' ) {
								newValue.hasLPR = true;
								pingImage( _this.data, newValue );
							}
						}, function( err ) {
							_this.data = angular.extend({}, lprDefaults);
						});
					}
				});
			}
		});

		// long poll our truck feed servlet
		function pollLicensePlate( currentUser ) {
			$http.get('/DashCon/licensePlatePoll').success( function( data ) {
				// Disregard invalid entries
				if( data.timestamp !== null || data.timestamp !== "null" || data.timestamp !== undefined )
					// populate feeds based on current user's site id
					if( currentUser.siteId == data.siteId ) {
						var truck = $scope.truck.current;
						// since this is a long poll method we need to match the data id
						// to the active truck, especially when truck feed switching is
						// set to 'Locked'
						if( truck.sequenceNumber == data.sequenceNumber ) {
							// cache data to our controller
							_this.data = data;
						}
						// ping our image until it's found in the file system
						pingImage( data, truck );
						$rootScope.$broadcast('licensePlateAvailable', data);
					}
				pollLicensePlate(currentUser);
			}).error( function( err ) {
				console.log( err );
				_this.data = angular.extend({}, lprDefaults);
				pollLicensePlate(currentUser);
			});
		}
		
		function pingImage( data, truck ) {
			if( data != '' ) {
				var id = data.id;
				var imageSrc = '/DashCon/dashboard/images/lpr/' + id + '.jpg';
				$http.get(imageSrc)
				.success( function() {
					// this avoids the $digest already in progress error
					// since we're using $apply()
					$timeout(function() {
						$rootScope.$broadcast('licensePlateImage', data);
						if( data.sequenceNumber == truck.sequenceNumber ) {
							_this.data.hasImage = true;
							_this.data.image = _this.data.id + '.jpg';
						}
					});
	
				})
				.error( function() {
					$timeout( function() {
						pingImage( data, truck );
					}, 1500)
				})
			}
		}
		
		
	}]);


'use strict';


angular.module('sri.controller.SaferLookup', ['sri.module.Effects'])
	.controller('SaferLookupCTRL', ['$scope', '$http', '$rootScope', '$timeout',
    function( $scope, $http, $rootScope, $timeout ) {
		// variables
		$scope.truck = $scope.truck || {};
		var _this = this;
		_this.hasUSDOT = false;
		_this.usdotNumber = _this.usdotNumber || null;
		// get USDOT number on truck active change
		$scope.$watch('truck.current', function( newValue, oldValue ) {
			if( newValue != undefined && newValue != oldValue ) {
				// reset our values
				_this.usdotNumber = '';
				_this.hasUSDOT = false;
				// make a get request using sequence number and site id
				var path = newValue.sequenceNumber + '/' + newValue.siteId;
				$http.get('/DashCon/resources/usdot/' + path)
					.then(function( promise ) {
						if( promise.data != undefined ) {
							var data = promise.data;			// promised data
							var truck = $scope.truck.current;	// current truck
							// check if data matches active truck feed
							if( data.sequenceNumber == truck.sequenceNumber ) {
								// set module data
								_this.usdotNumber = data.usdotNumber;
								// set truck feed icon and module validation to true
								truck.hasUSDOT = _this.hasUSDOT = true;
							}
						} else {
							// broadcast undefined USDOT number
							$rootScope.$broadcast('saferLookupUndefined', response.data);
						}
					})
			}
		});
		
		$scope.$on('truckFeedNew', function( $event, message ) {
			// reset our values
			_this.usdotNumber = '';
			_this.hasUSDOT = false;
			// make a get request using sequence number and site id
			var path = message.sequenceNumber + '/' + message.siteId;
			$http.get('/DashCon/resources/usdot/' + path)
				.then(function( promise ) {
					if( promise.data != undefined ) {
						var data = promise.data;			// promised data
						var truck = $scope.truck.current;	// current truck
						// check if data matches active truck feed
						if( data.sequenceNumber == truck.sequenceNumber ) {
							loadUSDOTNumber( data );
						}
					} else {
						// broadcast undefined USDOT number
						$rootScope.$broadcast('saferLookupUndefined', response.data);
					}
				})
		})
		
		// add the US DOT Number to the controller's scope as well
		// as adding it to the current active truck
		function loadUSDOTNumber( data ) {
			if( typeof data == 'object' && data.usdotNumber != undefined ) {
				_this.usdotNumber = data.usdotNumber;
				_this.hasUSDOT = true;
				$scope.truck.current.hasUSDOT = true;
				$scope.truck.current.usdotNumber = _this.usdotNumber;
			} else {
				_this.usdotNumber = undefined;
				_this.hasUSDOT = false;
				$scope.truck.current.hasUSDOT = false;
				$scope.truck.current.usdotNumber = undefined;
			}
			// assign true to signify this is triggered by lookup
			_this.searchSafer();
		}
		
		_this.searchSafer = function() {
			// cache active truck
			var truck = $scope.truck.current;
			// check if triggered by lookup or change of current truck			
		 	if( _this.usdotNumber != null && _this.usdotNumber != undefined && _this.usdotNumber != null ) {
			 	_this.loading = true;
				$http.get('/DashCon/resources/safer/' + _this.usdotNumber )
				.then( function( response ){
					loadSafer( response.data, truck );
					$timeout( function() {
				 		_this.loading = false;
					}, 1000);
				})
		 	}
		}
		
		function loadSafer( data, truck ) {
			// a bug happens when switching active truck quicker than
			// safer can respond and load safer values showing data
			// to the wrong truck feed, lookup != false helps prevent it
			// by ensuring the truck's usdot number is equal to the lookup
			if( typeof data == 'object' && data.companyData != undefined ) {
				_this.hasUSDOT = true;
				truck.hasUSDOT = true;
				$rootScope.$broadcast('saferLookupSuccess', data );
			} else {
				_this.hasUSDOT = false;
				truck.hasUSDOT = false;
			}
		}
		
		_this.clearInput = function( $event ) {
			if( $event ) $event.stopPropagation();
			_this.usdotNumber = null;
			_this.hasUSDOT = false;
			$http.delete('/DashCon/resources/usdot/' + $scope.truck.current.sequenceNumber)
			.success( function() {
				$scope.truck.current.usdotNumber = undefined;
				$scope.truck.current.hasUSDOT = false;
				$rootScope.$broadcast('saferLookupCleared', '');
			})
		}
		
		_this.saveUSDOTNumber = function( $event, manualEntered, userId ) {
			var saferLookup = _this;
			// cache current truck
			var truck = $scope.truck.current;
			// build our usdot object to be sent as part of our request
			var usdot= {};
			usdot.usdotNumber = saferLookup.usdotNumber;
			usdot.sequenceNumber = $scope.truck.current.sequenceNumber;
			usdot.manualEntered = manualEntered || 1;
			usdot.userId = userId || 'not implemented';
			usdot.siteId = truck.siteId;
			// send a post to saved our usdot to our database
			if( usdot.usdotNumber !== null ) {
				$http.post('/DashCon/resources/usdot', usdot)
				.success( function( response, status) {
					// 201 = if we created a new row in our usdot databse
					if( status == 201 ) {
						$scope.truck.current.hasUSDOT = true;
						truck.usdotNumber = usdot.usdotNumber;
						_this.usdotNumber = usdot.usdotNumber;
						_this.hasUSDOT = true;
//						TODO: Add notification for create USDOT Number
					} 
					// 202 = if we updated a row in our usdot database
					else if( status == 202 ) {
						truck.hasUSDOT = true;
						truck.usdotNumber = saferLookup.usdotNumber;
//						TODO: remove the commented block of code if proven to cause errors
//						truck.usdotNumber = undefined;
//						_this.usdotNumber = undefined;
						_this.hasUSDOT = true;
//						TODO: Add notification for updated USDOT Number
					}
				})
				.error( function( res ) {
					_this.clearInput();
				});
			}
		}

		return _this;
		
	}]);


'use strict';


angular.module('sri.controller.StaticScale', [])
	.controller('StaticScaleCTRL', ['$rootScope', '$scope', '$http', '$timeout', function( $rootScope, $scope, $http, $timeout ) {
		$scope.truck = $scope.truck || {};
		$scope.scale = $scope.scale || {};
		$scope.scale.data= $scope.scale.data || {};
		
		$scope.$watch('truck.current', function( newValue, oldValue ) {
			// Disregard undefined values
			if( newValue == undefined ) return false;
			
			if( newValue !== oldValue ) {
				var apiUrl = newValue.timestamp + '/' + newValue.sequenceNumber + '/' + newValue.siteId;
				$http.get('/DashCon/resources/weight/static/' + apiUrl )
					.success( getScaleWeight )
					.then(function() {
						$timeout(function() {
							$scope.scale.loading = false;
						}, 1200);
					});
			}
		});
		
		$scope.$on('staticScale:new', function( event, data ) {
			console.info('Static Scale Entry: Detected!');
			var apiUrl = data.timestamp + '/' + data.sequenceNumber + '/' + data.siteId;
			$http.get('/DashCon/resources/weight/static/' + apiUrl ).success( getScaleWeight2 );
		});
		
		function getScaleWeight( response ) {
			$scope.scale.data = response;
			
			if( response == "" || response == undefined || response == null ) {
				return $scope.truck.current.hasScale = false;
			}
			
			$scope.truck.current.hasScale = true;
			
			$scope.scale.loading = true;
			
			$scope.scale.data.displayTime = moment(response.timestamp).format('MM-DD-YYYY H:mm:ss');
			
			if( response.status == 'P' ) {
				$scope.scale.data.passed = 'passed';
			} else {
				$scope.scale.data.passed = 'failed';
			}
		}	
		
		function getScaleWeight2( response ) {
			if( $scope.truck.current.sequenceNumber == response.sequenceNumber ) {
				$scope.scale.data = response;

				if( response == "" || response == undefined || response == null ) {
					return false;
				}

				$scope.scale.loading = true;

				$scope.scale.data.displayTime = moment(response.timestamp).format('MM-DD-YYYY H:mm:ss');

				if( response.status == 'P' ) {
					$scope.scale.data.passed = 'passed';
				} else {
					$scope.scale.data.passed = 'failed';
				}

				$rootScope.$broadcast('staticScale:entry', response.sequenceNumber );

				$timeout(function() {
					$scope.scale.loading = false;
				}, 1000);
			}
		}
		
	}]);


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


'use strict';


angular.module('sri.controller.RampWIM', [])
	.controller('RampWIMCTRL', ['$rootScope', '$scope', '$http', '$timeout', function( $rootScope, $scope, $http, $timeout ) {
		$scope.truck = $scope.truck || {};
		$scope.wim = $scope.wim || {};
		$scope.wim.id = 'wim';
		$scope.wim.title = 'Mainline WIM';
		$scope.wim.data = $scope.wim.data || {};
		
		$scope.$watch('truck.current', function( newValue, oldValue ) {
			// Disregard undefined values
			if( newValue == undefined ) return false;
			
			if( newValue !== oldValue ) {
				var apiUrl = newValue.timestamp + '/' + newValue.sequenceNumber + '/' + newValue.siteId;
				$http.get('/DashCon/resources/weight/wim/' + apiUrl ).then( getWeight ).then(function() {
					$timeout(function() {
						$scope.wim.loading = false;
					}, 1200);
				});
			}
		});
		
		function getWeight( promise ) {
			var data = promise.data;
			$scope.wim.data = data;
			
			if( data != '' ) {
			
				$scope.wim.loading = true;
				
				$scope.wim.data.displayTime = moment(data.timestamp).format('MM-DD-YYYY H:mm:ss') || '';
				
				if( data.status == 'P' ) {
					$scope.wim.data.passed = 'passed';
				} else {
					$scope.wim.data.passed = 'failed';
				}
				
				$rootScope.$broadcast('newTruck', $scope.wim.data.timestamp);
			}
		}
		
	}]);


'use strict';


angular.module('sri.controller.Safer.CompanyData', [])
	.controller('CompanyDataCTRL', ['$scope', '$http', '$timeout', '$rootScope', function( $scope, $http, $timeout, $rootScope ) {
		$scope.truck = $scope.truck || {};
		$scope.safer = $scope.safer || {};
		
		$scope.$on('truckPhoneActive', function( event, data ) {
			if( data == '' || data == 'null' || data == undefined || data.usdotNumber == undefined ) {
				loadSafer( data );
			}
		});

		$scope.$on('phoneAppUSDOTNumberAvailable', function ( event, message ) {
			var usdotNumber = ( message.usdotNumber ) ? message.usdotNumber : '';
			$scope.safer.loading = ( usdotNumber != '' && usdotNumber != 'null' && usdotNumber != undefined ) ? true : false;
			$http.get('/DashCon/resources/safer/' + usdotNumber )
				.then( loadSafer );
		})

		$scope.$watch('truck.current', function( newValue, oldValue ) {
			// Disregard undefined value
			if( newValue == undefined ) return false;
			// Remove background color initially
			// Check if the value is different from the previous one
			if( newValue !== oldValue ) {
				var usdotNumber = newValue.usdotNumber;
				if( typeof usdotNumber == 'object' ) {
					usdotNumber = usdotNumber.usdotNumber;
				}
				$scope.safer.loading = ( usdotNumber != '' || usdotNumber != 'null' ) ? true : false;
				$http.get('/DashCon/resources/safer/' + usdotNumber )
					.then( loadSafer )
			}
		});
		// when the safer response is successful and data is not empty
		// load our safer information to other safer modules
		$scope.$on('saferLookupSuccess', function( event, data ) {
			if( data != '' ) {
				$scope.safer.loading = true;
				loadSafer( data, true );
			}
		});
		// clear all our data when the clear button on the
		// safer lookup module is triggered
		$scope.$on('saferLookupCleared', function() {
			var truck = $scope.truck.current;
			$scope.safer.data = '';
			truck.usdotNumber = undefined;
			truck.hasUSDOT = undefined;
			$rootScope.$broadcast('saferDataLoaded', '');
		});
		
		function loadSafer( response, lookup ) {
			if( response == undefined ) return false;
			var data = ( response.data != undefined ) ? response.data : response;
			if( lookup == true ) {
				$scope.safer.data = data;
				$rootScope.$broadcast('saferDataLoaded', data);
			} else if( $scope.truck.current.hasUSDOT == true ){
				$scope.safer.data = data;
				$rootScope.$broadcast('saferDataLoaded', data);
			} else {
				$scope.safer.data = '';
				$rootScope.$broadcast('saferDataLoaded', '');
			}
			$scope.safer.loading = false;
		}
	}]);


'use strict';


angular.module('sri.controller.Safer.SafetyRating', [])
	.controller('SafetyRatingCTRL', ['$scope', 'service.Profile', function( $scope, Profile ) {
		var _this = this
		
		Profile.getProfile().then( function( data ) {
			_this.user = data;
		});
		
		$scope.$on('saferDataLoaded', function( event, data ) {
			if( typeof data == 'object' && data.safetyRating != undefined ) {
				_this.data = data.safetyRating;
			} else {
				_this.data = '';
			}
		});
	}]);


'use strict';


angular.module('sri.controller.Safer.USCrashData', [])
	.controller('USCrashDataCTRL', ['$scope', '$http', 'service.Profile', '$timeout', function( $scope, $http, Profile, $timeout ) {
		var _this = this;
		
		Profile.getProfile().then( function( data ) {
			_this.user = data;
		});
		
		$scope.$on('saferDataLoaded', function( event, data ) {
			if( typeof data == 'object' && data.usCrashData != undefined ) {
				_this.data = data.usCrashData;
			} else {
				_this.data = '';
			}
		});
		
	}]);


'use strict';


angular.module('sri.controller.Safer.USInspections', [])
	.controller('USInspectionsCTRL', ['$scope', function( $scope ) {
		var _this = this;
		$scope.$on('saferDataLoaded', function( event, data ) {
			if( data != undefined && data != '' && data !== null )
				_this.data = ( data.usInspectionData ) ? data.usInspectionData.inspections : {};
			else
				_this.data = {};
		});
	}]);
//# sourceMappingURL=scripts.min.js.map