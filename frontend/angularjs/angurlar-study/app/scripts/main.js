'use strict';
/*jshint unused: vars */
var debugPath = {
  'jquery':'../bower_components/jquery/dist/jquery',
  'jquery-ui':'../bower_components/jquery-ui/jquery-ui',
  'angular': '../bower_components/angular/angular',
  'angular-ui-router': '../bower_components/angular-ui-router/release/angular-ui-router',
  'angular-route': '../bower_components/angular-route/angular-route',
  'angular-sanitize' : '../bower_components/angular-sanitize/angular-sanitize',
  'angular-cookies' : '../bower_components/angular-cookies/angular-cookies',
  'angular-resource' : '../bower_components/angular-resource/angular-resource',
  'angular-animate' : '../bower_components/angular-animate/angular-animate',
  'angular-touch' : '../bower_components/angular-touch/angular-touch',
  'bootstrap-all': '../bower_components/bootstrap/dist/js/bootstrap',
  'ui.bootstrap': '../bower_components/angular-bootstrap/ui-bootstrap-tpls',
  'i18next': '../bower_components/i18next/i18next',
  'ng-i18next': '../bower_components/ng-i18next/dist/ng-i18next',
  'strophe':'../lib/strophejs/strophe',
};

var runtimePath = {
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
};
var debugMode = false;
var path = debugMode ? debugPath: runtimePath;
require.config({
  // enforceDefine: true,
  waitSeconds: 0,
  paths: path,
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
    'ui.bootstrap': ['angular', 'bootstrap-all']
  },
  priority: [
    'angular'
  ]
});

//http://code.angularjs.org/1.2.1/docs/guide/bootstrap#overview_deferred-bootstrap
//window.name = 'NG_DEFER_BOOTSTRAP!';

require([
  'jquery',
  'angular',
  'router'
], function($,angular, router) {
  /* jshint ignore:start */
  //var $html = angular.element(document.getElementsByTagName('html')[0]);
  /* jshint ignore:end */
  // angular.element().ready(function() {
  //   angular.resumeBootstrap(['smc']);
  // });
  angular.element( document ).ready(function() {
        angular.bootstrap(document, ['smc']);
    });

});
