(function () {
  'use strict';
  define(['app', 'services/chat-http', 'services/xmpp'], function (smc) {
    return smc.controller('LaunchChatCtrl', ['$scope', '$compile', '$sce','$state','$timeout', '$http', 'SMC_CONSTANTS', 'ChatHttpService', 'XMPPService',
      function ($scope, $compile,$sce,$state,$timeout, $http, SMC_CONSTANTS, ChatHttpService, XMPPService) {
        $scope.isConnecting = false;
        $scope.question = '';
        $scope.onConnecting = function(scope, status) {
          if (status == Strophe.Status.CONNECTED) {
            $timeout(function(){
              $scope.isConnecting = false;
            },500);
          } else {
            //if disconnected, show error message
            $timeout(function(){
              $scope.isConnecting = true;
            },500);
          }
        };
        XMPPService.addConnectingStatusListener($scope.onConnecting, $scope);
        $scope.$on('$destroy', function(){
          XMPPService.removeConnectingStatusListener($scope.onConnecting, $scope);
          console.error('launch chat destroy');
        });

        $scope.getTitle = function() {
          var title, question = $scope.question;
          if(question && question.length <=50) {
            title = question;
          } else if (question && question.length > 50){
            title = question.substring(0, 50);
          } else {
            title = '';
          }
          return title;
        };

        $scope.getDescription = function()
        {
          var description = [];
          description.push($scope.question);
          return description;
        }

        //click startChat button
        $scope.startChat = function() {
          $scope.isStarting = true;
          if(console) {
            console.info("question:" + $scope.question);
          }

          $http({
            method: 'POST',
            url: '/chatservice/api/interactions/',
            data: {
              "Category":"incident",
              "Contact":"FALCON, JENNIFER",
              "Description": $scope.getDescription(),
              "EnteredByESS":true,
              "NotifyBy":"None",
              "ServiceRecipient":"FALCON, JENNIFER",
              "Title":$scope.getTitle()
            },
            headers: {
              'Content-Type':'application/json'
            }
          }).then(function successCallback(response) {
            if(console) {
              console.info('Interaction[CallID: %s] is successfully created.', response.data.CallID);
            }
          }, function errorCallback(response) {
             if(console) {
               console.error('Create interaction failed.')
             }
          });

          /*
          $timeout(function(){
            $scope.isStarting = false;
            alert('ticket is created\n'+$scope.question);
          },1000);
          */
        }
    }]);
  });
})(this);
