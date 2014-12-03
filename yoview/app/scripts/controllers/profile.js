'use strict';

/**
 * @ngdoc function
 * @name paybookApp.controller:ProfileCtrl
 * @description
 * # ProfileCtrl
 * Controller of the paybookApp
 */
angular.module('paybookApp')
  .controller('ProfileCtrl', function ($scope, GlobalService, $location) {

    $scope.loginForm = {
      email: "",
      pass: ""
    };

    $scope.error = false;

    $scope.attemptLogin = function () {
      var userEmail = $scope.loginForm.user;

      GlobalService.getUsers().
        success(function (data) {
          var found = false;
          angular.forEach(data, function (user) {
            if (user.email === userEmail) {
              found = true;
              console.log(user);
              $scope.$broadcast('Login');
              GlobalService.setGlobalUser(user);
              $location.path("#/main");
            }
          });

          if (!found) {
            $scope.error = true;
          }

        })

    }
  });
