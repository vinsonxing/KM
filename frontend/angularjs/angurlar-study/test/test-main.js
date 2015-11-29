var allTestFiles = [];
var TEST_REGEXP = /(spec|test)\.js$/i;

// Get a list of all the test files to include
Object.keys(window.__karma__.files).forEach(function(file) {
  if (TEST_REGEXP.test(file)) {
    // Normalize paths to RequireJS module names.
    // If you require sub-dependencies of test files to be loaded as-is (requiring file extension)
    // then do not normalize the paths
    var normalizedTestModule = file.replace(/^\/base\/|\.js$/g, '');
    normalizedTestModule = "../../" + normalizedTestModule;
    allTestFiles.push(normalizedTestModule);
  }
});

require.config({
  // Karma serves files under /base, which is the basePath from your config file
  baseUrl: '/base/app/scripts',
  
  // dynamically load all test files
  deps: allTestFiles,

  // we have to kickoff jasmine, as it is asynchronous
  callback: window.__karma__.start,waitSeconds: 0,
  paths: {
    'jquery':'../bower_components/jquery/dist/jquery.min',
  'jquery-ui':'../bower_components/jquery-ui/jquery-ui.min',
  'angular': '../bower_components/angular/angular.min',
  'angular-ui-router': '../bower_components/angular-ui-router/release/angular-ui-router.min',
  'angular-route': '../bower_components/angular-route/angular-route.min',
  'angular-sanitize' : '../bower_components/angular-sanitize/angular-sanitize.min',
  'angular-cookies' : '../bower_components/angular-cookies/angular-cookies.min',
  'angular-resource' : '../bower_components/angular-resource/angular-resource.min',
  'angular-animate' : '../bower_components/angular-animate/angular-animate.min',
  'angular-touch' : '../bower_components/angular-touch/angular-touch.min',
  'bootstrap-all': '../bower_components/bootstrap/dist/js/bootstrap.min',
  'ui.bootstrap': '../bower_components/angular-bootstrap/ui-bootstrap-tpls.min',
  'i18next': '../bower_components/i18next/i18next.min',
  'ng-i18next': '../bower_components/ng-i18next/dist/ng-i18next.min',
  'strophe':'../lib/strophejs/strophe.min',
  'angular-mocks': '../bower_components/angular-mocks/angular-mocks'

  },
  shim: {

    'jquery-ui': ['jquery'],
    'angular' : {'exports' : 'angular', deps: ['jquery']},
    'angular-bootstrap-simple-chat': ['angular'],
    'angular-ui-router': ['angular'],
    'angular-route': ['angular'],
    'angular-cookies': ['angular'],
    'angular-sanitize': ['angular'],
    'angular-resource': ['angular'],
    'angular-animate': ['angular'],
    'angular-touch': ['angular'],
    'angular-mocks': {
      'deps':['angular'],
      'exports':'angular.mock'
    },
    'i18next': {
      'deps': ['angular'],
      'exports': 'i18next'
    },
    'ng-i18next': {
      'deps': ['i18next'],
      'exports': 'ng-i18next'
    },
    'bootstrap-all': {
      'deps': ['jquery'],
      init: function (bs) {
        var tt = $.fn.tooltip.noConflict();
        $.fn.bstooltip = tt;
      }
    },
    'ui.bootstrap': ['angular', 'bootstrap-all'],
  },
  priority: [
    'angular'
  ]
});
