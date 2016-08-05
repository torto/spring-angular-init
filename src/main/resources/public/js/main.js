(function() {
  'use strict';

  angular.module('automation', ['ngRoute']).config(['$routeProvider', '$locationProvider', function($routeProvider, $locationProvider) {
    $routeProvider.when('/', {
      templateUrl: 'js/index/Index.html',
      controller: 'IndexController'
    });

    $routeProvider.otherwise({
      redirectTo: '/'
    });

  }]);

  window.automation = angular.module('automation');
}());
