/*
{
	Author : Robert M. Roth,
	Website : www.rothattack.com,
	Version : 1.0.2
	Date : February 19, 2014,
	Requires : jQuery('Tested with v.1.11.0')
}

*/

(function ( $ ) { 

console.log('Dash Validate initiated.');

$.fn.validate = function(options) {

		var defaults = {
			required : true,
			minLength : 8,
			maxLength : 12,
			lettersOnly: true,
			noSpaces : true,
			regex : null,
			success : null,
			error : null
		};

		var plugin = this;
		plugin.el = this;
		plugin.settings = {};
					
		// Default function if successful
		var hasSuccess = function() {
			// plugin setting success is null by default
			if ( plugin.settings.success === null ) {
				console.log( 'Success: Validation passed.\nReminder: It is better to set your own "success" callback in the paramater section.');
			} else {
				plugin.settings.success(plugin);
			}
		};// end of onSuccess
		
		// Validations has errors
		var hasError = function( msg, errorCode ) {
			if ( plugin.settings.error === null ) {
				console.log( 'Error: ' + msg + '\nReminder: It is better to set your own "error" callback in the paramater section.');
			} else {			
				// if user provided call for error, return the error code for distinction of error	
				console.log('User error function triggered.');
				return plugin.settings.error( errorCode, plugin );
			}
		};// end of onError

		var validateRequired = function() {
			// Begin validation of value starting with validating requirement of value
				if (plugin.settings.required === true) {
					// Check if element is blank
					// TODO: Check if === is applicable
					if (plugin.el.val() == '' || plugin.el.val() == null || plugin.el.val() == undefined) {
						// call error if true
						hasError('This cannot be blank. Disable this feature by setting required to false.', 'required');						
					} else {
						// proceed to next validation
						validateRegex();
					} 
				} else {
					// proceed to next validation
					validateRegex();
				}// end of validating requirement of value
		}; // end of validateRequired
				
		var validateRegex = function() {
			// default setting for regex is null
			if (plugin.settings.regex !== null) {
				// test if value passes the regex given by user
				if (plugin.settings.regex.test(plugin.el.val()) === false) {
					hasError('Failed regular expression test. Disable this feature by setting "regex" to "null".', 'regex');
				} else {					
					validateSpaces();
				}
			} else {
				validateSpaces();
			}
		};// end of validateRegex()
				
		var validateSpaces = function() {
			// default setting for noSpaces is true
			if (plugin.settings.noSpaces === true) {
				// validate spaces
				// TODO: Check if this is fastest way to validate spaces
				if(plugin.el.val().indexOf(' ') >= 0) {
					hasError('Spaces are not allowed. Disable this feature by setting "noSpaces" to "false".', 'noSpaces');
				} else {
					// next validation
					validateLettersOnly();
				}
			} else {
				// next validation
				validateLettersOnly();
			}
		};// end of validateSpaces()
				
		var validateLettersOnly = function() {
			if (plugin.settings.lettersOnly === true && plugin.settings.regex === null) {
				if (/^[a-zA-Z]*$/.test(plugin.el.val()) === false) {
				    hasError('Only letters are allowed. Disable this feature by setting "lettersOnly" to "false".', 'lettersOnly');
				} else {
				  // next validation
					validateMinLength();
				}
			} else {
				// next validation
				validateMinLength();
			}
		};// end of validateLettersOnly()
				
		var validateMinLength = function() {
			// if minLength is 0 it means skip minLength
			if ( plugin.settings.maxLength !== 0 ) {
				if ( plugin.el.val().length < plugin.settings.minLength ) {
					hasError('Minimun length not reached. Disable this feature by setting "minLength" to "0".', 'minLength');
				} else {
					validateMaxLength();
				}
			} else {
				validateMaxLength();
			}
		};// end of validateMinLength()
				
		var validateMaxLength = function() {
			if (plugin.settings.maxLength !== 0) {
				if ( plugin.el.val().length > plugin.settings.maxLength ) {
					hasError('Maximum length reached. Disable this feature by setting "maxLength" to "0".', 'maxLength');
				} else {
					hasSuccess();
				}
			} else {
					hasSuccess();
			}
		};// end of validateMaxLength()

		// initialization function
		var init = function() {
			plugin.settings = $.extend({}, defaults, options );
			// call initial validation function
			validateRequired();
			};
		// initialize plugin
		init();

	};// end of plugin

$.ltrim = function( str ) {
	return str.replace( /^\s+/, "" );
};

$.rtrim = function( str ) {
	return str.replace( /\s+$/, "" );
};

}( jQuery ));