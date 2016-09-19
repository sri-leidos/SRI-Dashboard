
var licensePlateFeed = {
	'poll' : function() {
		new Ajax.Request('licensePlatePoll', {
			method : 'GET',
			onSuccess : licensePlateFeed.update
		});
	},
	'increment' : function() {
		new Ajax.Request('licensePlatePoll', {
			method : 'POST'
		});
	},
	'update' : function(req, json) {
		$('id').innerHTML = json.id;
		$('timestamp').innerHTML = json.timestamp;
		$('siteId').innerHTML = json.siteId;
		$('fileName').innerHTML = json.fileName;
		$('sequenceNumber').innerHTML = json.sequenceNumber;
		$('licensePlateNumber').innerHTML = json.licensePlateNumber;
		$('state').innerHTML = json.state;
		
		licensePlateFeed.poll();
	}
}

var rules = {
	'#increment': function(element) {
		element.onclick = function() {
			licensePlateFeed.increment();
		};
	}
};

Behaviour.register(rules);
Behaviour.addLoadEvent(licensePlateFeed.poll);
