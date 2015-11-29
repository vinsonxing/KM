(function() {
  'use strict';
  define(["app"], function(smc) {
    return smc.directive('chatParticipant', function() {
      return {
        restrict: 'AE',
        templateUrl: 'views/templates/chat-participant.html',
        controller: function() {
          var vm = this;
          vm.name = "Vinson";
        }
      };
    });
  });
})(this);
