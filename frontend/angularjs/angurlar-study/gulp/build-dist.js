var gulp = require('gulp'),
  connect = require('gulp-connect'),
  KarmaServer = require('karma').Server,
  concat = require('gulp-concat'),
  requirejsOptimize = require('gulp-requirejs-optimize'),
  sourcemaps = require('gulp-sourcemaps'),
  ngAnnotate = require('gulp-ng-annotate'),
  gulpSequence = require('gulp-sequence'),
  rimraf = require('gulp-rimraf'),
  config = require('./config');


gulp.task('test-one', function (done) {
  new KarmaServer({
    configFile: __dirname + '/../test/karma.conf.js',
    singleRun: true
  }, done).start();
});

// Delete the dist directory
gulp.task('clean', function () {
  return gulp.src(config.bases.dist, {read: false})
    .pipe(rimraf());
});


gulp.task('cleanTemp', function () {
  return gulp.src(config.bases.app + 'temp', {read: false})
    .pipe(rimraf());
});

// Copy all other files to dist directly
gulp.task('copy', function () {
  // Copy lib scripts, maintaining the original directory structure
  gulp.src(config.paths.libs, {cwd: 'app/**'})
    .pipe(gulp.dest(config.bases.dist));

  // Copy html
  gulp.src(config.paths.html, {cwd: config.bases.app})
    .pipe(gulp.dest(config.bases.dist));

  // Copy styles
  gulp.src(config.paths.styles, {cwd: 'app/**'})
    .pipe(gulp.dest(config.bases.dist));

  //copy images
  gulp.src(config.paths.images, {cwd: config.bases.app})
    .pipe(gulp.dest(config.bases.dist + 'images'));

  //copy locale
  gulp.src(config.paths.locales, {cwd: 'app/**'})
    .pipe(gulp.dest(config.bases.dist));

  //copy view
  gulp.src(config.paths.template, {cwd: 'app/**'})
    .pipe(gulp.dest(config.bases.dist));


  // Copy extra html5bp files
  gulp.src(config.paths.extras, {cwd: config.bases.app})
    .pipe(gulp.dest(config.bases.dist));
});

gulp.task('compress-source', ['clean', 'copy'], function () {
  return gulp.src(config.bases.app + 'temp/main.js')
    .pipe(sourcemaps.init())
    .pipe(requirejsOptimize(
      {
        name: 'main',
        baseUrl: config.bases.app + 'temp',
        mainConfigFile: config.bases.app + 'temp/config.js'
      }
    ))
    .pipe(concat('main.js'))
    .pipe(sourcemaps.write('./'))
    .pipe(gulp.dest(config.bases.dist + 'scripts/'));
});


gulp.task('compress', function () {
  return gulp.src(config.bases.app + 'temp/main.js')
    .pipe(requirejsOptimize(
      {
        name: 'main',
        baseUrl: config.bases.app + 'temp',
        mainConfigFile: config.bases.app + 'temp/config.js'
      }
    ))
    .pipe(concat('main.js'))
    .pipe(gulp.dest(config.bases.dist + 'scripts/'));
});


gulp.task('inject', function () {
  return gulp.src(config.bases.app + 'scripts/**/*.js')
    .pipe(ngAnnotate({
      add: true
    }))
    .pipe(gulp.dest(config.bases.app + 'temp'));
});


gulp.task('express-dist', function () {
  connect.server(config.serverOpts('dist'));
});


gulp.task('dist', gulpSequence('cleanTemp', 'test-one', ['clean', 'less', 'inject'], 'copy', 'compress', 'cleanTemp'));

gulp.task('product', gulpSequence('dist','express-dist', 'open'));
