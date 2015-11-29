var gulp = require('gulp'),
    rimraf = require('gulp-rimraf'),
    path = require('path'),
    open = require('gulp-open'),
    jshint = require('gulp-jshint');



gulp.task('jshint', ['jshint-clean', 'jshint-generator', 'jshint-launcher']);

gulp.task('jshint-clean', function() {
  return gulp.src(__dirname + '/jshint-output.html').pipe(rimraf());
});

gulp.task('jshint-generator', function() {
  return gulp.src('./app/scripts/**/*.js')
    .pipe(jshint())
    // .pipe(jshint.reporter('default', { verbose: true }));
    .pipe(jshint.reporter('gulp-jshint-html-reporter', {
      filename: __dirname + '/jshint-output.html',
      createMissingFolders: false
    }));
});

gulp.task('jshint-launcher', function() {
  gulp.src(path.join(__filename))
    .pipe(open({
      app: 'chrome',
      uri: __dirname + '/jshint-output.html'
    }));
});