/**
 * Created by Kocsen on 10/19/2014.
 * Entry point of the Javascript Web Application
 */


var app = angular.module('PayBookApp', []);


app.factory('MainFactory', ['$http', function ($http) {
    return {
        baseurl: "http://localhost:8080",
        users: "/users",
        user: "/user",

        getUsers: function () {
            return $http.get(this.baseurl + this.users + "/all");
        },
        getUserRequests: function (uid) {
            return $http.get(this.baseurl + this.user + uid + "/requests")
        }


    };

}]);


app.controller('MainController', ['$scope', 'MainFactory', function ($scope, MainFactory) {

    MainFactory.getUsers().
        success(function (data) {
            $scope.users = data;
            setMainUser("Jay Owen")
        }).
        error(function (err) {
            console.log("ERROR!");
            console.log(err);
        });


    var setMainUser = function (uname) {
        angular.forEach($scope.users, function (user) {
            if (uname == user.name) {
                console.log('foundem');
                $scope.mainUser = user;
            }
        });
    };


    $('.ui.accordion').accordion().accordion('setting', {exclusive: false});
}]);