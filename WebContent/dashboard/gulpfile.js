
// DEPENDENCIES

var gulp = require('gulp');									// main gulp
var browserSync = require('browser-sync');	// autoreload of browsers
var concat = require('gulp-concat');				// concatinate files
var uglify = require('gulp-uglify');				// uglify javascripts
var minify = require('gulp-minify');				// minify CSS files
var less = require('gulp-less');						// LESS compiler
var plumber = require('gulp-plumber');			// continuous tasks even on errors
var sourcemaps = require('gulp-sourcemaps');// create sourcemaps for concatinated and minified files
var del = require('del');										// used for deleting files and folders

// STRUCTURE

var source = {
	angular: './scripts/**/*.js',
	ngModules: './modules/**/*.js',
	vendors: '../vendor/**/*.js',
	compiledJS: './build/scripts/**/*.js',
	less: './styles/less/**/*.less'
};

var dest = {
	js: './build/scripts/',
	mainApp: 'app.min.js',
	ngModules: 'modules.min.js',
	jsMin: 'scripts.min.js',
	css: './build/styles/',
	cssFile: 'main.min.css'
};

// GULP TASK AND WATCHERS

gulp.task('clean:css', function() {
	del(['./build/styles'])
});

gulp.task('clean:js', function() {
	del(['./build/scripts'])
});

// compile all our compiled javascripts
gulp.task('js:compile', ['clean:js'], function() {
	gulp.src( [source.angular, source.ngModules] )
	.pipe( plumber() )
	.pipe( sourcemaps.init() )
	.pipe( concat( dest.jsMin ))
	// .pipe( uglify() )
	.pipe( sourcemaps.write('./') )
	.pipe( gulp.dest( dest.js ))
});

gulp.task('css:compile', ['clean:css'], function() {
	gulp.src('./styles/less/main.less')
		.pipe( plumber() )
		.pipe( less() )
		.pipe( minify() )
		.pipe( gulp.dest( dest.css ) )
});

gulp.task('browser-sync', ['css:compile', 'js:compile'], function() {
    browserSync.init({
        proxy: "http://localhost:8080/DashCon/dashboard"
    });
});

gulp.task('build', ['css:compile', 'js:compile']);

gulp.task('default', ['build'], function() {
	// gulp.watch('./**/**.*', { interval: 1000 }).on('change', browserSync.reload);
	gulp.watch( [source.angular, source.ngModules], { interval: 1000 }, ['js:compile']);
	gulp.watch( source.less, { interval: 1000 }, ['css:compile']);
});


