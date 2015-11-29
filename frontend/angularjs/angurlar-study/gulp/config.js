
module.exports.bases = {
  app: 'app/',
  dist: 'dist/'
};

module.exports.launchUrl = 'https://localhost:9999/#/launchbtn';

module.exports.paths = {
  scripts: ['scripts/**/*.js'],
  libs: ['bower_components/**/*', '!bower_components/**/*.js', 'lib/requirejs/require.js'],
  styles: ['styles/css/*.css'],
  html: ['index.html', '404.html'],
  template: ['views/**/*.html'],
  images: ['images/*'],
  locales: ['resources/**/*'],
  extras: ['robots.txt', 'favicon.ico']
};

module.exports.serverOpts = function(root, livemode) {
  return {
    root: root,
    port: 9999,
    https: true,
    livereload: !!livemode,
    middleware: function () {
      return [
        require('../mockServer/appDev.js')
      ];
    }
  }
}


