(function () {
  'use strict';
  define(['app', 'services/chat-http', 'services/xmpp'], function (smc) {
    return smc.controller('ChatCtrl', ['$scope', '$compile', '$sce','$state','$timeout', '$http', 'SMC_CONSTANTS', 'ChatHttpService', 'XMPPService',
      function ($scope, $compile,$sce,$state,$timeout, $http, SMC_CONSTANTS, ChatHttpService, XMPPService) {
      var vm = this;
      vm.name = 'Vinson-Xing'; // TODO, change to the real data
      vm.title = 'Support Agent';
      vm.greeting = 'I am virtual agent Jane, what can I help?';
      vm.messages = [];
      var refreshMessages = [];

      vm.initMessages = function() {
        XMPPService.init('','','',999);
        XMPPService.connect('falcon','1','');

        vm.messages = [];
        refreshMessages = [];
        vm.addMsg('Hi, ' + vm.name + ' ' + vm.greeting, false);
        vm.addMsg(vm.initQues, true);
        vm.isLive = true;
        // add a KM list message
        var msg = 'chat-km-list';
        refreshMessages.push(msg);
        vm.addMsg('<div id="' + getHtmlizeId(msg) + '"/>', false, 'html');
        vm.refreshMessages(true);
      };

      vm.addMsg = function (msg, isSelf, type) {

        if (type === 'html') {
          msg = $sce.trustAsHtml(msg);
        }

        vm.messages.push({
          msg: msg,
          isSelf: isSelf,
          timestamp: new Date().getTime(),
          type: type || 'text',
          isSystem: false
        });
      };


      vm.refreshMessages = function(clickable) {
        $timeout(function() {
          refreshMessages.forEach(function(val/*, i*/) {
            var scope = $scope.$new();
            scope.clickable = clickable;
            var tpl = $compile('<' + val + '/>')(scope);
            var parent = angular.element('#' + getHtmlizeId(val)).parent();
            tpl.appendTo(parent[0]);
          });
        },500);

      };

      vm.viewArticle = function (kmId) {
        vm.isLive = false;
        $state.go('chat.main.question', {kmId: kmId});
      } ;

      vm.continueChat = function (/*clickable*/) {
        vm.isLive = true;
        vm.isEnd = false;
      };

      vm.finishChat = function (/*clickable*/) {
        vm.isLive = false;
        $state.go('chat.main.rate');
      };

      // private function
      function getHtmlizeId(msg) {
        return SMC_CONSTANTS.CHAT_HTMLLIZE_PREFIX + msg;
      }

      // $scope
      function stopEvent(evt) {
        evt.stopPropagation();
        evt.preventDefault();
      }

      $scope.parentMethod = function(arg) {
        console.log('parent method...' + arg);
      };

      $scope.foo = "vinson test";

      $scope.$on('submitQuestion', function(evt, qs, kmId, ooid) {
        stopEvent(evt);
        var answer = qs.map(function(i, val) {
          var tmp = angular.copy(val);
          delete tmp['para.description'];
          return tmp;
        });
        $http.post('/api/execute', ChatHttpService.populateOOExecuteRequestBody(kmId, ooid, answer)).then(function(resp) {
          var html = qs.map(function(val/*, i*/) {
            return '<label>' + val['para.description'] + '</label><div><label>' + val['para.value'] +'</label></div>';
          });
          $state.go('chat.main.log');
          vm.addMsg(html.join(''), true, 'html');
          //$scope.$emit('showAlert', SMC_CONSTANTS.CHAT_ALERT_SUCCESS , resp.data.data.message);
          $timeout(function() {
            vm.addMsg(resp.data.data.kmoo.message, false);
          }, 2000);

          vm.refreshMessages(false);
          vm.isLive = true;

          $timeout(function() {
             vm.addMsg("Do you have more questions?", false);
             vm.isEnd = true;
          }, 5000);
        },function(resp) {
          $scope.$emit('showAlert', SMC_CONSTANTS.CHAT_ALERT_ERROR , resp.data.data.message || 'Can not complete this operation !');
        });
      });

      $scope.$on('cancelQuestion', function(evt) {
        stopEvent(evt);
        vm.isLive = true;
        vm.refreshMessages(true);
      });

      $scope.$on('submitRate', function(evt, rate) {
        stopEvent(evt);
        vm.addMsg('Finished the rating.', true);

        $http.post('/api/rate', rate).then(function(resp) {
          vm.addMsg(resp.data.data.message, false);
          $state.go('chat.main.log');
          vm.refreshMessages(false);

        },function(resp) {
          $scope.$emit('showAlert', SMC_CONSTANTS.CHAT_ALERT_ERROR , resp.data.data.message || 'Can not complete this operation !');
        });
      });
    }]);
  });
})(this);
