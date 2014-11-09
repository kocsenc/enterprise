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

    $scope.friendRequests = [];
    $scope.paymentTypes = {
      card: [],
      baccount: []
    };
    initRequests();


    /**
     * Makes initial API calls and requests
     */
    function initRequests() {

      var promise = GlobalService.getUser("1");
      promise.then(function (result) {
        GlobalService.setGlobalUser(result);
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
          });

        GlobalService.getFriendRequests($scope.mainUser.id).
          success(function (data) {
            $scope.friendRequests = data;
          }).
          error(function (err) {
            console.log(err);
          });


        GlobalService.getPaymentTypes($scope.mainUser.id).
          success(function (data) {
            console.log(data);
            $scope.paymentTypes.card.push(data.creditCard);
            $scope.paymentTypes.baccount.push(data.bankAccount);
          })


      });


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


    $scope.paymentForm = {
      type: {
        pay: true
      },
      friendSearch: "",
      amount: ""
    };

    $scope.submitPaymentRequest = function () {
      var data = $scope.paymentForm;

      var pushData = {
        receiver: data.friendSearch,
        amount: data.amount,
        description: data.description
      };

      console.log(pushData);
      //Now you can submit depending on payment form
      if (data.type.pay) {
        // Post to payment
      } else {
        // Post to request
      }

    };

    $('.ui.accordion').accordion().accordion('setting', {exclusive: false});

  });
