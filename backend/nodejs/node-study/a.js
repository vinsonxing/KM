var EventEmitter = require('events');

module.exports = new EventEmitter();

// Do some work, and after some time emit
// the 'ready' event from the module itself.
setTimeout(function() {
  // module.exports = new EventEmitter(); // module.exports must be run immediately, can not be done in any callback
  module.exports.emit('ready');
}, 1000);
