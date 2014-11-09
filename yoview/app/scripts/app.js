'use strict';

/**
 * @ngdoc overview
 * @name paybookApp
 * @description
 * # paybookApp
 *
 * Main module of the application.
 */
angular
  .module('paybookApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch',
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .when('/about', {
        templateUrl: 'views/about.html',
        controller: 'AboutCtrl'
      })
      .when('/profile', {
        templateUrl: 'views/profile.html',
        controller: 'ProfileCtrl'
      })
      .when('/register', {
        templateUrl: 'views/register.html',
        controller: 'RegisterCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .controller('GlobalCtrl', function($scope, GlobalService) {
    console.log("global ctrl");

    GlobalService.fetchGlobalData();


  });