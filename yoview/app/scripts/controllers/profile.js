'use strict';

/**
 * @ngdoc function
 * @name paybookApp.controller:ProfileCtrl
 * @description
 * # ProfileCtrl
 * Controller of the paybookApp
 */
angular.module('paybookApp')
  .controller('ProfileCtrl', function ($scope, GlobalService, $http) {

    $scope.loginForm = {
      user: "",
      pass: ""
    };

    $scope.attemptLogin = function () {


    }
  });
