/**
 * Created by Kocsen on 10/19/2014.
 * Entry point of the Javascript Web Application
 */


var app = angular.module('PayBookApp', []);

app.config(function ($httpProvider) {
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common['X-Requested-With'];
});

app.factory('MainFactory', ['$http', function ($http) {
    return {
        baseurl: "/api",
        users: "/users",
        user: "/user",

        getUsers: function () {
            return $http.get(this.baseurl + this.user + "/all");
        },
        getUserRequests: function (uid) {
            return $http.get(this.baseurl + this.user + "/" + uid + "/requests")
        },
        getPaymentTypes: function (uid) {
            return $http.get(this.baseurl + this.user + "/" + uid + "/pay_types")
        }


    };

}]);


app.controller('MainController', ['$scope', 'MainFactory', function ($scope, MainFactory) {

    // Main facotry remote calls
    MainFactory.getUsers().
        success(function (data) {
            $scope.users = data;
            setMainUser("Jay Owen")
        }).
        error(function (err) {
            console.log("ERROR!");
            console.log(err);
        });

    MainFactory.getUserRequests("1").
        success(function (data) {
            $scope.requests = data;
        });


    var setMainUser = function (uname) {
        angular.forEach($scope.users, function (user) {
            if (uname == user.name) {
                $scope.mainUser = user;
            }
        });
    };


    /**
     * Payment functionality form data
     * @type {{type: {pay: boolean, request: boolean}, friendSearch: string, friend: string, amount: string}}
     */
    $scope.paymentForm = {
        type: {
            pay: true,
            request: false
        },
        friendSearch: "",
        friend: "",
        amount: ""
    };

    $scope.accountType = {
        type: {
            checking: true,
            saving: false
        }
    };


    $('.ui.selection.dropdown')
        .dropdown()
    ;

    /**
     * Quick function to see who requests are from
     * @param request
     * @param iduser
     * @returns {string|*}
     */
    $scope.findWho = function (request, iduser) {
        idRequester = request.sender;
        idReceiever = request.reciever;
        originator = "";

        if (idRequester == iduser) {
            originator = "You requesting from ";
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
}]);