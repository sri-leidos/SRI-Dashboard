

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