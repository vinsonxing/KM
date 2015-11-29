/**
 * Created by xingjia on 9/16/2015.
 */
(function() {
  'use strict';
  define(["app"], function(smc) {
    smc.directive('chatLoading', ['$rootScope',function($rootScope) {
      return {
        restrict: 'E',
        replace: true,
        templateUrl: 'views/templates/chat-loading.html',
        /*controller: function($scope) {
          var vm = this;

        },*/
        link: function($scope, elem, attr){
          $rootScope.$on('showLoading', function(/*evt, pos*/) {
            elem.css('display', 'block');
          });

          $rootScope.$on('hideLoading', function(evt) {
            elem.css('display', 'none');
          });
        }

      };
    }]);
  });
})(this);
