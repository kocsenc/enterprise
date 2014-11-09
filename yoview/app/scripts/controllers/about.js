'use strict';

/**
 * @ngdoc function
 * @name paybookApp.controller:AboutCtrl
 * @description
 * # AboutCtrl
 * Controller of the paybookApp
 */
angular.module('paybookApp')
  .controller('AboutCtrl', function ($scope) {
    $scope.awesomeThings = [
      'HTML5 Boilerplate',
      'AngularJS',
      'Karma'
    ];
  });
