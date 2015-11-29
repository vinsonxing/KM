(function () {
  'use strict';
  define(["app",
    "directives/chat-header",
    "directives/chat-participant",
    "directives/chat-log",
    "directives/chat-submitter",
    "directives/chat-log-bottom",
    "directives/chat-question",
    "directives/chat-km-list",
    "directives/chat-loading",
    "directives/chat-alert",
    "directives/dir-test",
    "directives/chat-rate-star",
    "filters/first-uppercase",
    "filters/word-mapper"], function (smc) {
    smc.directive('chat', function () {
      var defObj = {
        replace: true,
        restrict: 'E',
        /*controller: ['$scope',  // co specify a controller with parameters
          function ($scope) {
          }
        ],*/
        templateUrl: 'views/chat.html'
      };
      return defObj;
    });
  });
})(this);
