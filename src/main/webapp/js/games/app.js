/**
 * Created by djr4488 on 3/14/14.
 */
var cardsApp = angular.module('gamesApp', [
    'ngRoute',
    'gameControllers'
]);

cardsApp.config(['$routeProvider',
    function($routeProvider) {
        $routeProvider.
            when('/selectGame', {
                templateUrl: 'selector/gameSelector.html',
                controller: 'gameSelectCtrl'
            }).when('/selectedGame', {
                templateUrl: 'selector/placeHolder.html'
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