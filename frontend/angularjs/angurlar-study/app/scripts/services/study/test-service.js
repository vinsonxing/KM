/**
 * Created by xingjia on 7/29/2015.
 */
(function() {
  'use strict';
  define(['app'], function(smc) {

    /**
     * @ngdoc service
     * @name smsForm.service:FormService
     * @description
     * `Service` to deal all form related operations
     */
    return smc.service('testService', [
      function() {
        var me = this;
        me.method = function() {
        	console.error('this is from test service');
        };
      }
    ]);
  });
})(this);
