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