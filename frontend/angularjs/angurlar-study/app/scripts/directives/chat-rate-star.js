/**
 * Created by xingjia on 9/18/2015.
 */
(function() {
  'use strict';
  define(["app"], function(smc) {
    return smc.directive('chatRateStar', function() {
      return {
        restrict: 'AE',
        templateUrl: 'views/templates/chat-rate-star.html',
        controllerAs: 'vm',
      /*  link: function postLink($scope, elem, attr) {

        },*/

        controller: function($scope) {
          var vm = this;
          vm.name = "Vinson";

          vm.doRate = function(evt) {
            var elem = $(evt.target);
            evt.stopPropagation();
            evt.preventDefault();
            var idx = parseInt(elem.attr('rate-id'));
            var rates = $('.chat-rate a[rate-id]');
            rates.each(function(i, el) {
              $(el).removeClass('selected');
              if (i < idx) {
                $(el).addClass('selected');
              }
            });
            vm.rate = idx;
          };

          vm.submitRate = function() {
            $scope.$emit('submitRate', {'rate': vm.rate, 'rateComments': vm.comments});
          };
        }
      };
    });
  });
})(this);

