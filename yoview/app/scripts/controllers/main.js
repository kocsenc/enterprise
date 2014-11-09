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
    $scope.yolo = GlobalService.yolo;

    $('.ui.accordion').accordion().accordion('setting', {exclusive: false});

  });
