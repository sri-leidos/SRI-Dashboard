
var truckFeed = {
	'poll' : function() {
		new Ajax.Request('truckFeedPoll', {
			method : 'GET',
			onSuccess : truckFeed.update
		});
	},
	'increment' : function() {
		new Ajax.Request('truckFeedPoll', {
			method : 'POST'
		});
	},
	'update' : function(req, json) {
//		$('id').innerHTML = json.id;
		$('timestamp').innerHTML = json.timestamp;
		$('licensePlate').innerHTML = json.licensePlate;
//		$('driversLicense').innerHTML = json.driversLicense;
		$('commercialDriversLicense').innerHTML = json.commercialDriversLicense;
		$('vehicleId').innerHTML = json.vehicleId;
		$('latitude').innerHTML = json.latitude;
		$('longitude').innerHTML = json.longitude;

		truckFeed.poll();
	}
}

var rules = {
	'#increment': function(element) {
		element.onclick = function() {
			truckFeed.increment();
		};
	}
};

Behaviour.register(rules);
Behaviour.addLoadEvent(truckFeed.poll);
