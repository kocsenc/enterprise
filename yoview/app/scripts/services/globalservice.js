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
        return $http.get(url).then(function (result) {
          return result.data;
        });
      },

      getUserRequests: function (uid) {
        return $http.get(this.baseurl + this.user + "/" + uid + "/requests")
      },

      getFriends: function (uid) {
        var url = this.baseurl + this.user + "/" + uid + "/friends";
        return $http.get(url);
      },

      getFriendRequests: function (uid) {
        var url = this.baseurl + this.user + "/" + uid + "/friendrequests"
        return $http.get(url);
      },

      getPaymentTypes: function (uid) {
        var muid = "/" + uid;
        var url = this.baseurl + this.user + muid + "/pay_types";
        return $http.get(url);
      },


      /* POSTING STUFF */

      /**
       * ccnum:
       * ccv:
       * exp: YYYY-MM-DD
       * @param uid
       * @param data - The data to push
       */
      pushPaymentType: function (uid, data) {
        var muid = "/" + uid;
        if (data.ccv) {
          var url = this.baseurl + this.user + muid + "/pay_type/credit";
          return $http.post(url, data);

        }
        //else {
        //}
      },

      pushFriendRequestAccept: function (uid, friendEmail, data) {
        var muid = "/" + uid;
        var url = this.baseurl + this.user + muid + "/acceptFriendRequest/" + friendEmail;
        return $http.post(url, data);
      }

    }
  });
