/**
 * Created by xingjia on 9/16/2015.
 */
(function() {
  'use strict';
  define(['app', 'services/chat-http'], function(smc) {
    smc.directive('chatKmList', ['$http', 'ChatHttpService', function($http, ChatHttpService) {
      var chs = ChatHttpService;
      return {
        restrict: 'E',
        replace: true,
        templateUrl: 'views/templates/chat-km-list.html',
        /*controller: function($scope) {

        },*/
        link: function postLink($scope/*, elem*/) {
          var query = $scope.$parent.vm.initQues;

          $http.post('/api/kmList', chs.populateKMRequestBody(query)).then(function(response) {
            $scope.kms = response.data.data.kmoo.km;
          }, function(/*response*/) {
            console.error('Retrieve KM list failed');
          });
        }
      };
    }]);
  });
})(this);
