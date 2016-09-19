
/*
 * @author 		Robert Roth
 * @description Main application for account access
 * @controllers	RetrievalCtrl
 */

'use strict';


;(function(window, document, $, undefined) {  
  // bootstrap our angular application for access
  angular.module('SRI.RETRIEVE', ['ui.router'])
  // controller for retrieving username or password
  .controller('RetrievalCtrl', ['$scope', '$http', '$timeout', function( $scope, $http, $timeout ) {
  	// cache the controller to keep scope and use the controller as name
  	// just to have uniformity between markup and logic code
  	var rtv = this;
  	var retrieveUsername = false;
  	// 
  	rtv.emailEntry = false;
  	rtv.emailSent = false;
  	rtv.emailNotSent = false;
  	rtv.emailNotFound = undefined;
    // triggers when user select forgot username
    rtv.forgotUsername = function() {
      rtv.emailEntry = true;
      retrieveUsername = true;
      rtv.title = 'Forgot Username';
      focusInput('email_input');
    }
    // triggers when user select forgot password
    rtv.forgotPassword = function() {
      rtv.emailEntry = true;
      retrieveUsername = false;
      rtv.title = 'Forgot Password';
      focusInput('email_input');
    }
    // the cancel event
    rtv.cancel = function() {
      rtv.emailEntry = false;
      rtv.emailNotSent = false;
      rtv.emailSent = false;
      rtv.title = 'Account Retrieval';
    }
    // the submit event checks which should be retrieved using the
    // retrieveUsername boolean value and then proceed to do POST
    // requests to our web server which returns either 200 or 204 responses
    rtv.submit = function() {
  	  if( retrieveUsername ) {
  		  $http.post('/DashCon/resources/retrieve/username', rtv.email)
  		  .then(function( promise ) {
  			  if( promise.status == 200 ) {
			    // when we set email sent to be true we also have to
			    // set email not found and email not sent to false and
  				// this behavior is replicated when values are switched
			    rtv.emailSent = true;
			    rtv.emailNotFound = false;
  			    rtv.emailNotSent = false;
  			  } else if( promise.status == 204 ) {
  				rtv.emailNotFound = true;
  				rtv.emailSent = false;
  	  			rtv.emailNotSent = false;
  				shakeInput();
  			  }
  		  }, function( promise ) {
  			  rtv.emailNotSent = true;
  			  rtv.emailSent = false;
			  rtv.emailNotFound = false;
			  shakeInput();
  		  });
  	  } else {
        $http.post('/DashCon/resources/retrieve/password', rtv.email)
        .then(function( promise ) {
            if( promise.status == 200 ) {
			  // when we set email sent to be true we also have to
			  // set email not found and email not sent to false and
  			  // this behavior is replicated when values are switched
			  rtv.emailSent = true;
			  rtv.emailNotFound = false;
			  rtv.emailNotSent = false;
            } else if( promise.status == 204 ) {
              rtv.emailNotFound = true;
              rtv.emailSent = false;
  			  rtv.emailNotSent = false;
			  shakeInput();
            }
          }, function( promise ) {
  			  rtv.emailNotSent = true;
  			  rtv.emailSent = false;
			  rtv.emailNotFound = false;
			  shakeInput();
  		  });
  	  }
    }
    
    // focus our email input field when a user selects either username
    // or password, notice we need $timeout to allow angular to render
    // elements before focusing on the email input
    function focusInput( id ) {
        $timeout( function() {
          $('#' + id).focus();
        });
    }

	function shakeInput() {
	    $('#email_input').addClass('shake animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
	      $(this).removeClass('shake animated');
	    });
	  };
  	
  }])


	  
})(window, document, jQuery);



	// focus our email input field when a user selects either username
	// or password, notice we need $timeout to allow angular to render
	// elements before focusing on the email input
function focusInput( id ) {
    $timeout( function() {
      $('#' + id).focus();
    });
}

function shakeInput() {
	$('#email_input').addClass('shake animated').one('webkitAnimationEnd mozAnimationEnd MSAnimationEnd oanimationend animationend', function(){
		$(this).removeClass('shake animated');
	});
};


