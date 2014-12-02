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

        getUserDependantData();
      });


    }


    $scope.$on('Update-All', function () {
      if ($scope.mainUser) {
        console.log('Updating All');
        getUserDependantData();
      }
    });
    /**
     * Gets data from the backend using the global service.
     * Called after
     */
    function getUserDependantData() {
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
          $scope.requests = {
            toMe: [],
            fromMe: []
          };
          var r = $scope.requests;
          angular.forEach(data, function (request) {
            if ($scope.mainUser.id == request.sender) {
              // Means main user sent it
              r.fromMe.push(request);
            } else {
              r.toMe.push(request);
            }
          });
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
          $scope.paymentTypes.card = data.creditCard;
          $scope.paymentTypes.baccount = data.bankAccount;
        });

      $scope.campaigns = {
        mine: [],
        friends: [],
        contributed: []
      };

      GlobalService.getMyCampaigns($scope.mainUser.id).
        success(function (data) {
          $scope.campaigns.mine = data
        });

      GlobalService.getFriendsCampaigns($scope.mainUser.id).
        success(function (data) {
          $scope.campaigns.friends = data;
        });

      GlobalService.getContributedCampaigns($scope.mainUser.id).
        success(function (data) {
          $scope.campaigns.contributed = data;
        });


    }

    $scope.paymentForm = {
      type: {
        pay: true
      },
      friendSearch: "",
      amount: "",
      description: "",
      loading: false
    };

    $scope.payFriend = function (friend) {
      document.body.scrollTop = document.documentElement.scrollTop = 0;

      $scope.paymentForm.type.pay = true;
      $scope.paymentForm.friendSearch = friend.email;
      $scope.paymentForm.amount = "";
      $scope.paymentForm.description = "What did you do with " + friend.name + "?";

    };

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


    /**
     * Submitting a payment request
     */
    $scope.submitPaymentRequest = function () {
      //// TODO: Remove, bogus waiting time
      //$scope.paymentForm.loading = true;
      //setTimeout(function () {
      //  $scope.paymentForm.loading = false;
      //  $scope.$apply();
      //}, 500);
      //// END TODO


      var data = $scope.paymentForm;

      var pushData = {
        receiver: data.friendSearch,
        amount: data.amount,
        description: data.description
      };

      console.log(pushData);
      //Now you can submit depending on payment form
      if (data.type.pay) {
        GlobalService.pushPay($scope.mainUser.id, pushData).
          success(function (data) {
            console.log("success pay!");
            $scope.$broadcast('Update-All');
          });
      } else {
        // Post to request
        GlobalService.pushRequestMoney($scope.mainUser.id, pushData).
          success(function (data) {
            console.log("success pay!");
            $scope.$broadcast('Update-All');
          });
      }

    };

    $scope.pushAcceptFriendReq = function (user) {
      var pushObj = {
        email: user.email,
        name: user.name
      };

      GlobalService.pushFriendRequestAccept($scope.mainUser.id, pushObj).
        success(function () {
          $scope.$broadcast('Update-All');
          console.log('YAY');
        });
    };

    /**
     * Helper function to get the last 4 digits of anything
     * @param num
     * @returns {*}
     */
    $scope.lastFour = function (num) {
      if (typeof(num) != 'undefined') {
        var str = num.toString();
        return str.slice(-4);
      }

    };

    /** #############################################
     * Actions related to payment modal
     * ##########################################
     */
    $scope.showPaymentModal = function () {
      $scope.paymentTypeForm = {
        help: false,
        type: {
          card: false,
          baccount: false
        },

        cardData: {
          name: "",
          number: "",
          ccv: "",
          expiration: ""
        },

        accData: {
          routing: "",
          number: ""
        }
      };
      $('#paymentTypeModal')
        .modal('setting', 'autofocus', false)
        .modal('show');
    };

    /**
     * Network operation.
     * Help push a payment type.
     *
     * CURRENTLY ONLY WORKS FOR CCARDS
     */
    $scope.pushPaymentType = function () {
      var data = $scope.paymentTypeForm;
      var pushObj = {};

      if (data.type.card) {
        var cdata = data.cardData;
        pushObj.ccv = cdata.ccv;
        pushObj.ccnum = cdata.number;
        var expiration = cdata.expiration.split('/');
        pushObj.exp = expiration[1] + "-" + expiration[0] + "-01";
      } else {
        var baccData = data.accData;
      }

      console.log(pushObj);
      GlobalService.pushPaymentType($scope.mainUser.id, pushObj).
        success(function () {
          $scope.$broadcast('Update-All');
          $('#paymentTypeModal').modal('hide');
        });
    };

    $scope.friendRequest = function (userObject) {
      userObject.requested = true;
      GlobalService.pushAddFriend($scope.mainUser.id, userObject).
        success(function () {
          $scope.$broadcast('Update-All');
        })
    };


    $('.ui.accordion').accordion().accordion('setting', {exclusive: false});
  });
