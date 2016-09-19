
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