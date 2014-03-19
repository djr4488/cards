/**
 * Created by djr4488 on 3/18/14.
 */
var gameControllers = angular.module('gameControllers', []);

gameControllers.controller('gameSelectCtrl', function ($scope, gameSelectSvc, $filter) {
// Constructor for this controller
    $scope.resp = {
        gameSelectionResponse: {
            gameOpts: new Array(),
            nextLanding: "",
            errorMsg: "",
            errorBold: ""
        }
    }
    init();
    function init() {
        gameSelectSvc.getGameOptions().then(
            function(data){
                console.log(data.data);
                $scope.resp = data.data;
                console.log($scope.resp);
                console.log($scope.gameSelectionResponse);
                console.log($scope.resp.gameSelectionResponse.gameOpts);
                $scope.gameOpts = $scope.resp.gameSelectionResponse.gameOpts;
            }
        );
    }
});