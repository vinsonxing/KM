var gulp = require('gulp');

require('./gulp/public-task');
require('./gulp/build-dev');
require('./gulp/build-dist');
require('./gulp/code-check');




// watch must be prior to open, then the livereload tag can be inject into the index.html

gulp.task('default', ['serve.dev'], function () {

});
