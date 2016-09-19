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
		 
		  