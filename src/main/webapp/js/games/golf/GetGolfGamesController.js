/**
 * Created by djr4488 on 4/29/2014.
 */
var getGolfController = angular.module('getGolfController', []);
getGolfController.controller('GetGolfCtrl', function ($scope, getGolfGamesSvc, $filter) {
    $scope.resp = {
        gameResponse: {
            nextLanding: "",
            errorMsg: "",
            errorBold: "",
            gamesPlayerIsIn: new Array()
        }
    }
    init();
    function init() {
        getGolfGamesSvc.getGolfGames().then(
            function (data) {
                console.log(data.data);
                $scope.resp = data.data;
                $scope.gameOpts = $scope.resp.gameSelectionResponse.gameOpts;
            }
        );
    }
});
