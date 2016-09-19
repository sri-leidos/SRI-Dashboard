
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