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
    };
    $scope.gameOptSelected = "";
    $scope.selectGame = function() {
        console.log("gameOptSelected: " + $scope.gameOptSelected);
        if ($scope.gameOptSelected == "Golf") {
            window.location.replace('#golfStats');
        } else {
            window.location.replace('#placeHolder');
        }
    };
    init();
    function init() {
        gameSelectSvc.getGameOptions().then(
            function(data){
                console.log(data.data);
                $scope.resp = data.data;
                $scope.gameOpts = $scope.resp.gameSelectionResponse.gameOpts;
            }
        );
    }
});