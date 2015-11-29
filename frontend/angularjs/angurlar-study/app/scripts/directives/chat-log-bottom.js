/**
 * Created by xingjia on 9/15/2015.
 */
(function() {
  'use strict';
  define(["app"], function(smc) {
    smc.directive('chatLogBottom', ['$timeout',function($timeout) {
      return {
        restrict: 'C',
        link: function postLink(scope, elem) {
            if (scope.$last) {
              $timeout(function() {
                elem.get(0).scrollIntoView();
              }, 200);
            }
        }
      };
    }]);
  });
})(this);
