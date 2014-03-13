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
                templateUrl: 'home.html'
            }).
            when('/about', {
                templateUrl: 'about.html'
            }).
            when('/login', {
                templateUrl: 'auth/login/login.html',
                controller: 'LoginCtrl'
            }).
            when('/createAccount', {
                templateUrl: 'auth/create/createAccount.html',
                controller: 'CreateAcctCtrl'
            }).
            otherwise({
                redirectTo: '/home'
            });
    }
]);

