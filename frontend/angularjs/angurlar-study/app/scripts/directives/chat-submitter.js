/**
 * Created by xingjia on 7/29/2015.
 */
(function() {
  'use strict';
  define(["app", '../services/template'], function(smc) {
    return smc.directive('chatSubmitter',['$timeout', function($timeout) {
      return {
        restrict: 'AE',
        templateUrl: 'views/templates/chat-submitter.html',
        link: function postLink($scope, elem) {

          $timeout(function () {
            elem.keypress(function(evt) {
              if (evt.which === 13) {
                evt.preventDefault();
                // send this message to openfire and add it to chat window
                $scope.vm.addMsg($scope.vm.typedMessage, true);
                $scope.$apply();
                elem.find('textarea').val('');
              }
            });
          }, 200);

          /*$scope.$watch('vm.isLive', function(newV, oldV) {
             //elem.find('textarea').attr('disabled', !newV);
          });*/

        }
      };
    }]);
  });
})(this);
