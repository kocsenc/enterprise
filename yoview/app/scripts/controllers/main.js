'use strict';

/**
 * @ngdoc function
 * @name paybookApp.controller:MainCtrl
 * @description
 * # MainCtrl
 * Controller of the paybookApp
 */
angular.module('paybookApp')
  .controller('MainCtrl', function ($scope, GlobalService) {

    $scope.mainUser = GlobalService.globalUser;


    GlobalService.getUsers().
      success(function (data) {
        $scope.users = data
      }).
      error(function (err) {
        console.log('mega error');
      });

    GlobalService.getFriends($scope.mainUser.id).
      success(function (data) {
        console.log(data);
      }).
      error(function (err) {
        console.log(err);
      });

    $('.ui.accordion').accordion().accordion('setting', {exclusive: false});

  });
