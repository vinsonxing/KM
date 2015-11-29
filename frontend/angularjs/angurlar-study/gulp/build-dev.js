var gulp = require('gulp'),
	// sass = require('gulp-ruby-sass'),
	// sass = require('gulp-sass')
	watch = require('gulp-watch'),
	connect = require('gulp-connect'),
	KarmaServer = require('karma').Server,
	config = require('./config');


gulp.task('watch', function() {
	// gulp.watch('./sass/**/*.scss', ['sass']);
	gulp.watch('./app/styles/less/**/*.less', ['less']);
	watch(['./**/*.html']).pipe(connect.reload());
	watch(['./app/scripts/*.js']).pipe(connect.reload());
	watch(['./app/styles/css/*.css']).pipe(connect.reload());
});


gulp.task('express.dev', function() {
	connect.server(config.serverOpts('app', true));
});

gulp.task('express', function() {
	connect.server(config.serverOpts('app', true));
});

gulp.task('test', function (done) {
  new KarmaServer({
    configFile: __dirname + '/../test/karma.conf.js',
    singleRun: false
  }, done).start();
});

// gulp.task('sass', function() {
//     gulp.src('./sass/**/*.scss')
//     .pipe(sass.sync().on('error', sass.logError))
//     .pipe(gulp.dest('./css'))
//     .pipe(rename({suffix: '.min'}))
//     .pipe(minifycss())
//     .pipe(gulp.dest('./css'));
// });

gulp.task('serve.dev', [ /*'sass',*/ 'less', 'express.dev', 'watch', 'open']);
gulp.task('serve', ['express']);