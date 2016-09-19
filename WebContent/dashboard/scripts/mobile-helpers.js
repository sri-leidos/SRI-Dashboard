
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