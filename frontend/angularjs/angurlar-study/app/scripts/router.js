(function () {
  'use strict';
  define(['app', 'angular', 'directives/chat', 'controllers/chat', 'controllers/connectOF', 'controllers/launchchat'], function (smc, angular) {
    smc.config(function ($stateProvider, $urlRouterProvider) {
      // if there is no specific URL, go the the main window
      $urlRouterProvider.when('/', '/chat/chat-pre');
      $urlRouterProvider.when('/chat', '/chat/chat-pre');
      $urlRouterProvider.when('/chat/', '/chat/chat-pre');

      $stateProvider
        .state('launchbtn', {
          url:'/launchbtn',
          templateUrl: 'views/launch-btn.html',
          onEnter: ['$timeout', function($timeout){
            $timeout(function() {
              var width = $('#showChatBtn').outerWidth();
              var height = $('#showChatBtn').outerHeight();
              window.parent && window.parent.postMessage(["setSize", (width+30)+"#"+height], "*"); 
            }, 500);
          }]
        })
        .state('launchconnecting', {
          url:'/launchconnecting',
          templateUrl: 'views/launch-connecting.html',
          controller: 'ConnectOFCtrl',
          constrollerAs: 'vm',
          onEnter: ['$timeout', function($timeout){
            $timeout(function() {
              var width = $('#showChatBtn').outerWidth();
              var height = $('#showChatBtn').outerHeight();
              window.parent && window.parent.postMessage(["setSize", (350)+"#"+280], "*"); 
            }, 500);
          }]
        })
        .state('launchstart', {
          url:'/launchstart',
          templateUrl: 'views/launch-start.html',
          controller: 'LaunchChatCtrl',
          constrollerAs: 'vm',
          onEnter: ['$timeout', function($timeout){
            $timeout(function() {
              var width = $('#launchChatPanel').outerWidth();
              var height = $('#launchChatPanel').outerHeight();
              window.parent && window.parent.postMessage(["setSize", (350)+"#"+280], "*"); 
            }, 500);
          }]
        })
        .state('chat', { // inject into the ui-view without name by default
          url: '/chat',
          /*views: {
            'chat': {
              templateUrl: 'views/chat.html',
              controller: 'ChatCtrl',
              controllerAs: 'vm'
            }
          },*/
          templateUrl: 'views/chat.html',
          controller: 'ChatCtrl',
          controllerAs: 'vm'
        })
        // here ' chat.main' is just a id
        .state('chat.pre', {
          url: '/chat-pre',
          views: {
            // the 'chat-main' here is the name of the ui-view
            'chat-pre': {
              templateUrl: 'views/chat-pre.html',
            }
          }
        })
        // here ' chat.main' is just a id
        .state('chat.main', {
          url: '/chat-main',

          views: {
            // the 'chat-main' here is the name of the ui-view
            'chat-main': {
              templateUrl: 'views/chat-main.html'
            }

          }
        })
        .state('chat.main.log', {
          url: '/chat-log', //The URL here should be append to its parent, here it is chat/chat-main/***/chat-log, if
                            // this state doesn't declare a controller, use its parent's
          views: {
            // the 'chat-main' here is the name of the ui-view
            'chat-log': {
              templateUrl: 'views/chat-log.html',
              //controller: 'ChatMainCtrl', //follow the templateURL, then the scope is right
              //controllerAs:'vm' // this name should be same with the one in ChatCtrl, var vm = this;
            }}
        })
        .state('chat.main.question', {
          url: '/chat-question/:kmId', //The URL here should be append to its parent, here it is chat/chat-main/***/chat-log, if
                            // this state doesn't declare a controller, use its parent's
          views: {
            // the 'chat-main' here is the name of the ui-view
            'chat-log': {
              templateUrl: 'views/chat-question.html'
            }}
        })
        .state('chat.main.rate', {
          url: '/chat-rate', //The URL here should be append to its parent, here it is chat/chat-main/***/chat-log, if
          // this state doesn't declare a controller, use its parent's
          views: {
            // the 'chat-main' here is the name of the ui-view
            'chat-log': {
              templateUrl: 'views/chat-rate.html'
            }}
        })

    });
  });
})(this);

