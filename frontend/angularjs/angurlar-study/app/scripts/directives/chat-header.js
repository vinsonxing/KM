(function() {
  'use strict';
  define(["app"], function(smc) {
    smc.directive('chatHeader', function() {
        return {
          restrict: 'E',
          templateUrl: 'views/templates/chat-header.html',
          controller: function($scope) {
            var vm = $scope;
            vm.name = "Vinson";
          }
        };
      });
  });
})(this);
