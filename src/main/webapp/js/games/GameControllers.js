/**
 * Created by djr4488 on 3/14/14.
 */
var gameControllers = angular.module('gameControllers', []);

gameControllers.controller('SelectGameCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.baseURL = 'http://djr2.dyndns.org:9074';
        $scope.method='POST';
        $scope.url = $scope.baseURL + '/cardsapi/gameSelection/get';
        $scope.resp = {
            gameSelectionResponse: {
                gameOpts: [],
                errorMsg: "",
                errorBold: ""
            }
        }
        $scope.getGameOpts = function() {
            $scope.code = null;
            $scope.response = null;
            $http.get($scope.url).success(
                function(data, status) {
                    $scope.resp = data;
                    $scope.gameOpts = resp.gameSelectionResponse.gameOpts;
                    $scope.errorMsg = $scope.resp.errorMsg;
                    $scope.errorBold = $scope.resp.errorBold;
                }
            ).error(
                function(data, status) {
                    $scope.errorMsg = "And it will kill us all now!";
                    $scope.errorBold = "It was just a gazeebo!";
                }
            )
        };

        $scope.updateModel = function(method, url) {
            $scope.method = method;
            $scope.url = url;
        }
    }
]);

var init
