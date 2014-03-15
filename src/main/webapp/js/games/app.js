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
                controller: 'SelectGameCtrl'
            }).otherwise({
                redirectTo: '/selectGame'
            });
    }
]);
