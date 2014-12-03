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
    'angular-loading-bar'
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
      .when('/login', {
        templateUrl: 'views/login.html',
        controller: 'LoginCtrl'
      })
      .when('/campaigns', {
        templateUrl: 'views/campaigns.html',
        controller: 'MainCtrl'
      })
      .when('/login', {
        templateUrl: 'views/profile.html',
        controller: 'ProfileCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  })
  .controller('GlobalCtrl', function ($scope, GlobalService) {
    //GlobalService.fetchGlobalData();
  });
