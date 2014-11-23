'use strict';

/**
 * @ngdoc function
 * @name paybookApp.controller:LoginCtrl
 * @description
 * # LoginCtrl
 * Controller of the paybookApp
 */
angular.module('paybookApp')
  .controller('LoginCtrl', function ($scope, $http, $window) {


    $scope.loginForm = {
      email: "",
      password: ""
    };

    $scope.pushLogin = function () {

      $http
        .post('/api/login', $scope.loginForm)
        .success(function (data, status, headers, config) {
          $window.sessionStorage.token = data.token;
          $scope.message = 'Welcome';
        })
        .error(function (data, status, headers, config) {
          // Erase the token if the user fails to log in
          delete $window.sessionStorage.token;

          // Handle login errors here
          $scope.message = 'Error: Invalid user or password';
        });
    }
  });
