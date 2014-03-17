/**
 * Created by djr4488 on 3/14/14.
 */
var gameControllers = angular.module('gameControllers', []);

gameControllers.controller('SelectGameCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.baseURL = 'http://djr2.dyndns.org:9074';
        $scope.method='GET';
        $scope.url = $scope.baseURL + '/cardsapi/gameSelection/get';
        $scope.resp = {
            gameSelectionResponse: {
                gameOpts: [],
                errorMsg: "",
                errorBold: ""
            }
        }
        $http.get($scope.url, function(data) {
            console.log("data - " + data);
            $scope.resp = data;
            $scope.gameOpts = resp.gameSelectionResponse.gameOpts;
        });
    }
]);
