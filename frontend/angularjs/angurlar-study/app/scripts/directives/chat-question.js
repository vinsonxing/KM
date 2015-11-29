/**
 * Created by xingjia on 9/15/2015.
 */
(function() {
  'use strict';
  define(['app', 'services/chat-http'], function(smc) {
    smc.directive('chatQuestion', ['$http', '$stateParams', 'ChatHttpService', function($http, $stateParams, ChatHttpService) {
      var chs = ChatHttpService;
      return {
        restrict: 'E',
        replace: true,
        templateUrl: 'views/templates/chat-question.html',
        controllerAs: 'quesVM',
        controller: function($scope) {
          var vm = this;

          vm.retrieveParams = function() {
            $http.post('/api/kmParamList', chs.populateKMParamRequestBody($stateParams.kmId)).then(function(response) {
              vm.questions = response.data.data.kmoo.ooparameter;
              vm.ooid = response.data.data.kmoo.ooid;
              vm.kmid = response.data.data.kmoo.id;
              vm.kmContent = response.data.data.kmoo.kmContent;
            }, function() {
              console.error('Retrieve KM Param list failed');
            });

          };

          vm.submitAnswers = function() {
            var questions = angular.element('.question-form .question');
            var answers = angular.element('.question-form .answer');
            var qPairs = [];
            answers.each(function(i, q) {
              var val = q.value;
              if (q.type === 'password') {
                val = '***********';
              }
              qPairs.push({'para.name': q.name, 'para.value': val, 'para.description': questions[i].innerHTML});
            });
            $scope.$emit('submitQuestion', qPairs, vm.id, vm.ooid);
          };

          vm.cancel = function() {
            $scope.$emit('cancelQuestion');
          };

          vm.retrieveParams();

        },
        link: function postLink() {
          console.log('test elem...');
        }
      };
    }]);
  });
})(this);
