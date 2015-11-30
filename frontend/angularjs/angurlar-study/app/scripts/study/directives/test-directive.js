(function() {
  'use strict';
define(['study/app'], function(study) {
 study.directive('dirParent', function() {
    return {
      restrict: 'E',
      transclude: true,
      scope: {},
      controller: ['$scope', function($scope) {
        $scope.scopePrint = function () {
          console.error('this is from parent - scope Print');
        };

        this.printLog = function() {
          console.error('this is from parent - print Log');
          $scope.scopePrint(); /// this can be run in the sub link function 
        }
      }],
      templateUrl: 'views/templates/study/dir-parent.html'
    };
  })
  .directive('dirChild', function() {
    return {
      require: '^dirParent', // it means the current diretive can 
                            // communicate with the parent directive by the 4th param in link function
                            // which will point to the parent's controller object (not the $scope)
      restrict: 'E',
      transclude: true,
      scope: {
        title: '@'
      },
      controller: function($scope, $injector, $controller) {
        $scope.scopePrint = function () {
          console.error('this is from local - scope Print');
        };

        this.printLog = function() {
          console.error('this is from local - print Log');
        }

        // $injector test start
        var testService = $injector.get('testService');
        testService.method();

        $injector.invoke(['testService',function(ts) {
          ts.method();
        }]);

        // console.error($injector.annotate('testService'));
        console.error($injector.has('testService'));

        // $injector test end 
        // 
        // $controller test start 
        // 
        console.log($injector.get('$rootScope').decoratedz_method_on_scope());
        
      },
      link: function(scope, element, attrs, ctrl) {
        ctrl.printLog(); // can run parent's function, even which invoke its scope funcation/variables
        // ctrl.scopePrint();
        
      },
      templateUrl: 'views/templates/study/dir-child.html'
    };
  });
});

})(this);