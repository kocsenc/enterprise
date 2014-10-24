/**
 * Created by Kocsen on 10/19/2014.
 * Entry point of the Javascript Web Application
 */


var app = angular.module('PayBookApp', []);


app.factory('MainFactory', function ($http) {
    return {
        apiurl: "https://apiurl"
    };

});


var mainController = function ($scope) {
    $scope.yourName = null;


};

app.controller('MainController', mainController);