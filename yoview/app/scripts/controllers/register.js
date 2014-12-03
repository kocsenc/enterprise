'use strict';

/**
 * @ngdoc function
 * @name paybookApp.controller:RegisterCtrl
 * @description
 * # RegisterCtrl
 * Controller of the paybookApp
 */
angular.module('paybookApp')
  .controller('RegisterCtrl', function ($scope, $http, $location) {
    $scope.registerForm = {
      name: "",
      email: ""
    };

    $scope.error = false;


    $scope.registerUser = function () {
      var url = "/api/user/register";

      $http.post(url, $scope.registerForm).
        success(function (data) {
          console.log('success');
          $location.path("/profile")

        }).
        error(function (err) {
          $scope.error = true;
        });


    };
  });
