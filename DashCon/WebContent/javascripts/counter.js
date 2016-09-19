
var counter = {
      'poll' : function() {
         new Ajax.Request('long_poll', {
            method : 'GET',
            onSuccess : counter.update
         });
      },
      'increment' : function() {
         new Ajax.Request('long_poll', {
            method : 'POST'
         });
      },
      'update' : function(req, json) {
         $('count').innerHTML = json.counter;
         $('singer').innerHTML = json.singer;
         $('song').innerHTML = json.song;
         counter.poll();
      }
}

var rules = {
      '#increment': function(element) {
         element.onclick = function() {
            counter.increment();
         };
      }
};

Behaviour.register(rules);
Behaviour.addLoadEvent(counter.poll);
