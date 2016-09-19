
/*
 * @author rothr
 * @desc a directive for notifying user with messages using a pop up alert
 */

'use strict';

angular.module('SRI.ADMIN')
// create our directive
.directive('notifyr', function() {
	return {
		restrict: 'EA',
		template: '<span class="notifyr"></span>',
		controller: function( $scope ) {
			
			$scope.utility = $scope.utility || {};
			$scope.utility.showMessage = function( options ) {
				options = options || {};
				var type = options.type || 'info';
				var message = options.message || 'Error: Alert Message Empty';
				var delay = options.delay || 2000;
				var alertMsg = document.querySelector('.notifyr');
				
				if( type === 'info' ) {
					alertMsg.className += ' blue ';
					alertMsg.innerHTML = '<span class="glyphicon glyphicon-info-sign"></span> ';
				} else if( type === 'error' ) {
					alertMsg.className += ' red ';
					alertMsg.innerHTML = '<span class="glyphicon glyphicon-remove-sign"></span> ';
				} else if( type === 'success' ) {
					alertMsg.className += ' green ';
					alertMsg.innerHTML = '<span class="glyphicon glyphicon-ok-sign"></span> ';
				}
				
				alertMsg.innerHTML += message;
				alertMsg.className += ' message-alert ';
				
				setTimeout(function() {
					alertMsg.innerHTML += '';
					alertMsg.className = 'notifyr';
				}, delay);
			};
		}
	};
});