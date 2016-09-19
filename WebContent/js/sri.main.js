
'use strict';



(function($, window, document, undefined) {
	// This initializes the on load fade in effects of the page
	// This uses the wowJS vendor plugin
	new WOW().init();
	// This function hides the overlay loader once
	// All elements have been laoded	
	$( window ).on('load', function(a, b, c) {
		console.log(a);
		console.log(b);
		console.log(c);
		setTimeout(function() {
			$('.loader-mask').fadeOut();
		}, 100);
	});
})(jQuery, window, document, undefined);	