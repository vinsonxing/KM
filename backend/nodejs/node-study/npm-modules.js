'use strict';

var fs = require('fs');
var assert = require('assert');
var path = require('path');
var Readable = require('stream').Readable;
var npm = require('npm');
var File = require('vinyl');
var _ = require('lodash');

module.exports = NpmModules;

var omissible = ['dependencies', 'devDependencies', 'peerDependencies', 'readme', 'parent'];

function parseModules(m) {
  if (typeof m !== 'object') {
    throw new Error('NPM Module descriptor"' + m + '" is invalid, it should be an object.');
  }

  var deps = m.dependencies;
  m = _.omit(m, function (v, k) {
    return (omissible.indexOf(k) !== -1 || /^_/.test(k));
  });
  return [m].concat(_(deps).map(parseModules).flatten().value());
}

function NpmModules(opts) {
  if (!(this instanceof NpmModules)) {
    return new NpmModules(opts);
  }

  Readable.call(this, {
    objectMode: true
  });

  this.opts = opts || {};
}

require('util').inherits(NpmModules, Readable);

NpmModules.prototype._read = function() {
  var self = this;

  function doRead() {
    var m = this.modules.shift();
    var v = new File({
      base: path.dirname(m.path),
      path: m.path,
      stat: fs.statSync(m.path)
    });
    v.npmMeta = m;

    this.push(v);
    if (this.modules.length === 0) {
      this.push(null);
    }
  }

  if (self.modules) {
    doRead.call(self);
  } else if (!self.npmLoaded) {
    npm.load(self.opts, function(er) {
      if (er) {
        throw new Error('NPM load failed: ' + er);
      }

      npm.commands.ls([], true, function(err, data) {
        if (err) {
          throw new Error('NPM command \'ls\' failed: ' + err);
        }

        self.modules = parseModules(data);
        doRead.call(self);
      });
    });
    self.npmLoaded = true;
  }
};
