'use strict';

/**
 * @ngdoc service
 * @name paybookApp.MainFactory
 * @description
 * # MainFactory
 * Factory in the paybookApp.
 */
angular.module('paybookApp')
  .factory('MainFactory', function ($http) {

    // API Endpoints
    return {
      baseurl: "/api",
      user: "/user",

      getUsers: function () {
        return $http.get(this.baseurl + this.user + "/all");
      },
      getUserRequests: function (uid) {
        return $http.get(this.baseurl + this.user + "/" + uid + "/requests")
      }
    };
  });
