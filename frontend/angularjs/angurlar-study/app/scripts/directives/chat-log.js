(function() {
  'use strict';
  define(["app"], function(smc) {
    return smc.directive('chatLog', function() {
      return {
        restrict: 'AE',
        templateUrl: 'views/templates/chat-log.html',
        controller: function() {
          var vm = this;
          vm.name = "Vinson";

        }
      };
    });
  });
})(this);
