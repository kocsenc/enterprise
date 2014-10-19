/**
 * Created by Kocsen on 10/19/2014.
 * Entry point of the Javascript Web Application
 */


var app = angular.module('PayBookApp', []);

app.controller('MainController', function($scope){
    $scope.yourName = null;


    $('#topbar').sidebar('show');

});