
var weightFeed = {
	'poll' : function() {
		new Ajax.Request('mettPoll', {
			method : 'GET',
			onSuccess : weightFeed.update
		});
	},
	'increment' : function() {
		new Ajax.Request('mettPoll', {
			method : 'POST'
		});
	},
	'update' : function(req, json) {
		$('siteId').innerHTML = json.siteId;
		$('scaleType').innerHTML = json.scaleType;
		$('weightTimestamp').innerHTML = json.timestamp;
		$('sequenceNumber').innerHTML = json.sequenceNumber;
		$('axleCount').innerHTML = json.axleCount;
		$('grossWeight').innerHTML = json.grossWeight;
		$('massUnit').innerHTML = json.massUnit;
		$('status').innerHTML = json.status;
		$('reason').innerHTML = json.reason;
		
		weightFeed.poll();
	}
}

var rules = {
	'#increment': function(element) {
		element.onclick = function() {
			weightFeed.increment();
		};
	}
};

Behaviour.register(rules);
Behaviour.addLoadEvent(weightFeed.poll);
