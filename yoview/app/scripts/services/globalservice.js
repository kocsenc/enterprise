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
        $rootScope.$broadcast('Global-User');
      },

      setGlobalFriends: function (friendsList) {
        this.globalFriends = friendsList;
        $rootScope.$broadcast('Global-Friends');
      },

      fetchGlobalData: function () {
        // For now, 'login' as specific user
        this.getUser("1").
          success(function (userData) {
            console.log(userData);
            this.setGlobalUser(userData);

            // Get friends of user
            this.getUsers().
              success(function (data) {
                // Setting friends as all users
                this.setGlobalFriends(data);
              }).
              error(function (err) {
                console.log('get friends error!');
              })


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
        return $http.get(this.baseurl + '/' + uid)
      },

      getUserRequests: function (uid) {
        return $http.get(this.baseurl + this.user + "/" + uid + "/requests")
      }


    }
  });
