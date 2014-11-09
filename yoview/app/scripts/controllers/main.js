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
    initRequests();

    $scope.paymentForm = {
      type: {
        pay: true
      }
    };


    /**
     * Makes initial API calls and requests
     */
    function initRequests() {
      GlobalService.getUsers().
        success(function (data) {
          $scope.users = data
        }).
        error(function (err) {
          console.log('mega error');
        });

      GlobalService.getFriends($scope.mainUser.id).
        success(function (data) {
          $scope.friends = data;
        }).
        error(function (err) {
          console.log(err);
        });

      GlobalService.getUserRequests($scope.mainUser.id).
        success(function (data) {
          $scope.requests = data;
        }).
        error(function (err) {
          console.log(err);
        })
    }


    /**
     * Quick function to see who requests are from
     * @param request
     * @param iduser
     * @returns {string|*}
     */
    $scope.findWho = function (request, iduser) {
      var idRequester = request.sender;
      var idReceiever = request.reciever;
      var originator = "";

      if (idRequester == iduser) {
        originator = "You request from ";
        angular.forEach($scope.users, function (user) {
          if (idReceiever == user.id) {
            originator += user.name;
          }
        });
      } else {
        angular.forEach($scope.users, function (user) {
          if (idRequester == user.id) {
            originator = user.name;
          }
        })
      }
      return originator;
    };

    $('.ui.accordion').accordion().accordion('setting', {exclusive: false});

  });
