define(['require',
        'angular',
        'angular-route',
        'jquery',
        'app',
        'scripts/router-sample'
       ],function(require,angular){
            'use strict';
            require(['domReady!'],function(document){
                angular.bootstrap(document,['webapp']);
            });
        });
