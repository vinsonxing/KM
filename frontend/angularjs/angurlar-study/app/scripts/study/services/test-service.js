/**
 * Created by xingjia on 7/29/2015.
 */
(function() {
  'use strict';
  define(['study/app'], function(study) {

    /**
     * @ngdoc service
     * @name smsForm.service:FormService
     * @description
     * `Service` to deal all form related operations
     */
    return study.service('testService', [
      function() {
        var me = this;
        me.method = function() {
        	console.error('this is from test service');
        };
      }
    ]);
  });
})(this);
