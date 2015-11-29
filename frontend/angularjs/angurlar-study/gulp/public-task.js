var gulp = require('gulp'),
    less = require('gulp-less'),
	minifycss = require('gulp-minify-css'),
	rename = require('gulp-rename'),
	config = require('./config'),
	open = require('gulp-open');



gulp.task('less', function () {
  return gulp.src('./app/styles/less/_main.less')
    .pipe(less())
    .pipe(rename({basename: 'main'}))
    .pipe(gulp.dest('./app/styles/css'))
    .pipe(rename({suffix: '.min'}))
    .pipe(minifycss())
    .pipe(gulp.dest('./app/styles/css'));
});


gulp.task('open', function() {
	gulp.src(__filename)
		.pipe(open({
			app: 'chrome',
			uri: config.launchUrl || 'https://localhost:9999/#/launchbtn'
		}));
});