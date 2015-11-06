'use strict';

var path = require('path');
var map = require('through2-map');

module.paths.unshift(path.join(process.cwd(), 'node_modules'));

module.exports = function() {
  return map.obj(function(m) {
    if (m.npmMeta) {
      if (!m.module) {
        m.module = require(m.npmMeta.path);
      }
    }
    return m;
  });
};
