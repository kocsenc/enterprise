/**
 * Created by Kocsen on 10/19/2014.
 * Entry point of the Javascript Web Application
 */


var app = angular.module('PayBookApp', []);


app.factory('MainFactory', ['$http', function ($http) {
    return {
        baseurl: "http://localhost:8080",

        test: "/test",

        getUsers: function () {
            return $http.get(this.baseurl + this.test);

        }

    };

}]);


app.controller('MainController', ['$scope', 'MainFactory', function ($scope, MainFactory) {

    console.log("running request");
    console.log(MainFactory.test);
    MainFactory.getUsers().
        success(function (data) {
            $scope.users = data;
        }).
        error(function (err) {
            console.log("ERROR!");
            console.log(err);
        });

}]);