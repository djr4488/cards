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
            when('/about-me', {
                templateUrl: 'aboutMe.html'
            }).
            when('/login', {
                templateUrl: 'auth/login/login.html',
                controller: 'LoginCtrl'
            }).
            when('/createAccount', {
                templateUrl: 'auth/create/createAccount.html',
                controller: 'CreateAcctCtrl'
            }).
            when('/forgotPassword', {
                templateUrl: 'auth/forgot/forgotPassword.html',
                controller: 'ForgotPasswordCtrl'
            }).
            otherwise({
                redirectTo: '/home'
            });
    }
]);

