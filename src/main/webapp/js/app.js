/**
 * Created by djr4488 on 3/11/14.
 */
var cardsApp = angular.module('cardsApp', [
    'ngRoute',
    'authControllers'
]);

cardsApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/home', {
                templateUrl: 'jsp/welcome.html',
                controller: 'WelcomeCtrl'
            }).
            when('/login', {
                templateUrl: 'auth/login/login.html',
                controller: 'LoginCtrl'
            }).
            otherwise({
                redirectTo: '/login'
            });
    }
]);

