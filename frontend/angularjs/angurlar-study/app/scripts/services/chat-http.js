/**
 * Created by xingjia on 7/29/2015.
 */
(function () {
  'use strict';
  define(['app'], function (smc) {

    /**
     * @ngdoc service
     * @name smc.service:chatHttpService
     * @description
     * `Service` to deal all form related operations
     */
    return smc.service('ChatHttpService', /*['$rootScope',*/
      function (/*$rootScope*/) {
        var me = this;
        me.populateKMRequestBody = function (query) {
          return {"kmoo": {"query": query}};
        };

        me.populateKMParamRequestBody = function(kmId) {
          return {"kmoo": {"id" : kmId}};
        };

        // params, an array
        me.populateOOExecuteRequestBody = function(kmId, ooid, params) {
          return {"kmoo": {"id": kmId, "ooid": ooid, "ooparameter": params}};
        };
      }
    /*]*/);
  });
})(this);
