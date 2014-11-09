'use strict';

/**
 * @ngdoc service
 * @name paybookApp.GlobalService
 * @description
 * # GlobalService
 * Service in the paybookApp.
 */
angular.module('paybookApp')
  .service('GlobalService', function ($http, $rootScope) {
    return {

      globalUser: null,
      globalFriends: null,

      setGlobalUser: function (user) {
        this.globalUser = user;
        console.log("Broadcasting set global user");
        $rootScope.$broadcast('Global-User');
      },

      setGlobalFriends: function (friendsList) {
        this.globalFriends = friendsList;
        $rootScope.$broadcast('Global-Friends');
      },

      fetchGlobalData: function () {
        // For now, 'login' as specific user
        var self = this;

        var userid = "1";
        this.getUser(userid).
          success(function (userData) {
            console.log(userData);
            self.setGlobalUser(userData);
          }).
          error(function (userErr) {
            console.log('user Error');
          });


      },


      // Online Services
      baseurl: "/api",
      user: "/user",

      getUsers: function () {
        return $http.get(this.baseurl + this.user + "/all");
      },
      /**
       * Gets data for a specific user
       * @param uid
       * @returns {HttpPromise}
       */
      getUser: function (uid) {
        var url = this.baseurl + this.user + "/" + uid + "/getuser";
        return $http.get(url);
      },

      getUserRequests: function (uid) {
        return $http.get(this.baseurl + this.user + "/" + uid + "/requests")
      },

      getFriends: function (uid) {
        var url = this.baseurl + this.user + "/" + uid + "/friends";
        console.log(url);
        return $http.get(url);
      },

      getFriendRequests: function (uid) {
        var url = this.baseurl + this.user + "/" + uid + "/friend_requests"
        return $http.get(url);
      }


    }
  });
