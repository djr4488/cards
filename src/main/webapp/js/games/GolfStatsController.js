/**
 * Created by djr4488 on 3/20/2014.
 */
var golfStatsControllers = angular.module('golfStatsControllers', []);

golfStatsControllers.controller('golfStatsCtrl', function ($scope, golfStatsSvc, $filter) {
// Constructor for this controller
    $scope.resp = {
        gameStats: {
            top10: [{}],
            userStats: {
                alias: "",
                rank: 0,
                totalGames: 0,
                wins: 0
            }
        }
    }
    init();
    function init() {
        golfStatsSvc.getGolfStats().then(
            function(data){
                console.log(data.data);
                $scope.resp = data.data;
                $scope.top10 = $scope.resp.gameStats.top10;
                $scope.userStats = $scope.resp.gameStats.userStats;
            }
        );
    }
});
