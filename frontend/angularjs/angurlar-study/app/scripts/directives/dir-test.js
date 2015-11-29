(function() {
  'use strict';
  define(["app"], function(smc) {
    smc.directive('dirTest', function($timeout) {
        return {
          restrict: 'E',
          templateUrl: 'views/templates/dir-test.html',
          scope: {
            'a': '@isoAttr', // a,b,c are the internal variables, 
                            //the variables after the flags(@&=) are the attribute names used for 
                            //receive the values/method outside.
            'b': '=isoBind',
            'c':'&isoMethod'
          },
          link: function postLink($scope, elem, attr) {
            $timeout(function() {
              $scope.b = "binded"; //
            },2000);
          },
          controller: function($scope) {
            $scope.selfMethod=function(arg) {
              console.log('self method = ' + arg);
            }
          }
        };
      });
  });
})(this);
