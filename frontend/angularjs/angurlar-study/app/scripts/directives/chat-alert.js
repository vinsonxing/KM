/**
 * Created by xingjia on 9/17/2015.
 */
(function() {
  'use strict';
  define(["app"], function(smc) {
    smc.directive('chatAlert', ['$rootScope', 'SMC_CONSTANTS', function($rootScope, SMC_CONSTANTS) {
      return {
        restrict: 'E',
        replace:true,
        templateUrl: 'views/templates/chat-alert.html',
        controllerAs: 'vm',
        controller: function($scope, $element) {
          var vm = this;
          vm.status = 'success';
          vm.messae = 'You got it !';

          function showAlert(closeable) {
            if (closeable) {
              $element.fadeIn(500);
            } else {
              $element.fadeIn(1000).delay(2000).slideUp(500);
            }

          }

          $rootScope.$on('showAlert', function(evt, status, message) {
            vm.status = status;
            vm.message = ' ' + message;
            switch (status) {
              case SMC_CONSTANTS.CHAT_ALERT_WARNING:
                showAlert(true);
                break;
              case SMC_CONSTANTS.CHAT_ALERT_ERROR:
                showAlert(true);
                break;
              case SMC_CONSTANTS.CHAT_ALERT_SUCCESS:
              case SMC_CONSTANTS.CHAT_ALERT_INFO:
                break;
              default:
                showAlert();
                break;
            }
          });
        },

        link: function postLink(/*$scope, elem, attr*/) {

        }
      };
    }]);
  });
})(this);
