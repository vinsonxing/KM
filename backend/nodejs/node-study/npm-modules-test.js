var NpmModules = require('./npm-modules');
var through = require('through2');
var requireModule = require('./npm-modules-require-module');
var filter = require('gulp-filter');

var serverFilter = filter(function(file) {
    if (!file.npmMeta || !file.npmMeta.keywords || file.npmMeta.keywords.length === 0) {
      return false;
    }
    return file.npmMeta.keywords.indexOf('myindex') !== -1; // match the keywords in package.json (saved in each module.)
  });
  

var nm = NpmModules({depth:0})
.pipe(serverFilter) // filter
.pipe(requireModule())
.pipe(through.obj(function(m, enc, cb) { // retrieve the matched file/module
	console.log(m);
}));

