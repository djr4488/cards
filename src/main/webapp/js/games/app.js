/**
 * Created by djr4488 on 3/14/14.
 */
var cardsApp = angular.module('gamesApp', [
    'ngRoute',
    'gameControllers',
    'golfStatsControllers'
]);

cardsApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/selectGame', {
                templateUrl: 'selector/gameSelector.html',
                controller: 'gameSelectCtrl'
            }).when('/placeHolder', {
                templateUrl: 'selector/placeHolder.html'
            }).when('/golfStats', {
                templateUrl: 'stats/golfStats.html',
                controller: 'golfStatsCtrl'
            }).otherwise({
                redirectTo: '/selectGame'
            });
    }
]);

cardsApp.service('gameSelectSvc', function ($http) {
    this.getGameOptions = function () {
        return $http.get('http://djr2.dyndns.org:9074/cardsapi/gameSelection/get');
    };
});

cardsApp.service('golfStatsSvc', function ($http) {
    this.getGolfStats = function () {
        return $http.get('http://djr2.dyndns.org:9074/cardsapi/golfStats/get');
    };
});