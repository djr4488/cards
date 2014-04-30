/**
 * Created by djr4488 on 3/11/14.
 */
var cardsApp = angular.module('cardsApp', [
    'ngRoute',
    'authControllers',
    'gameControllers',
    'golfStatsControllers',
    'createGolfController',
    'joinGolfController'
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
            when('/resetPassword', {
                templateUrl: 'auth/reset/resetPassword.html',
                controller: 'ResetPasswordCtrl'
            }).when('/error', {
                templateUrl: 'error.html'
            }).when('/selectGame', {
                templateUrl: 'games/selector/gameSelector.html',
                controller: 'gameSelectCtrl'
            }).when('/placeHolder', {
                templateUrl: 'games/selector/placeHolder.html'
            }).when('/golfStats', {
                templateUrl: 'games/stats/golfStats.html',
                controller: 'golfStatsCtrl'
            }).when('/createGolf', {
                templateUrl: 'games/golf/createGolfGame.html',
                controller: 'CreateGolfCtrl'
            }).when('/joinGolf', {
                templateUrl: 'games/golf/joinGolfGame.html',
                controller: 'JoinGolfCtrl'
            }).when('/golfGame', {
                templateUrl: 'games/selector/placeHolder.html'
            }).otherwise({
                redirectTo: '/home'
            });
    }
]);

cardsApp.service('gameSelectSvc', function ($http) {
    this.getGameOptions = function () {
        return $http.get('http://djr2.asuscomm.com:9074/cardsapi/gameSelection/get');
    };
});

cardsApp.service('golfStatsSvc', function ($http) {
    this.getGolfStats = function () {
        return $http.get('http://djr2.asuscomm.com:9074/cardsapi/golfStats/get').success(
            function(data, status) {
                return data;
            }
        ).error(
            function(data, status) {
                window.location.replace('/cards/index.html#/error');
            }
        );
    };
});

cardsApp.service('getGolfGamesSvc', function($http) {
    this.getGolfGames = function() {
        return $http.get('http://djr2.asuscomm.com:9074/cardsapi/gamesvc/playlist/get').success(
            function(data, status) {
                return data;
            }
        ).error(
            function(data, status) {
                window.location.replace('/cards/index.html#/error');
            }
        );
    }
});

