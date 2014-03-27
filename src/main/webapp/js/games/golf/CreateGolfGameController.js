/**
 * Created by djr4488 on 3/25/2014.
 *
 * /cardsapi/gamesvc/create/submit
 */
var createGolfController = angular.module('createGolfController', []);
createGolfController.controller('CreateGolfCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.baseURL = 'http://djr2.dyndns.org:9074';
        $scope.method='POST';
        $scope.url = $scope.baseURL + '/cardsapi/gamesvc/create/submit';
        $scope.createGameRequest = {
            gameModel: {
                gameName: "",
                gamePassword: "",
                gameType: "Golf"
            }
        };
        $scope.resp = {
            gameResponse: {
                nextLanding: "",
                errorMsg: "",
                errorBold: "",
                gameId: 0
            }
        }
        $scope.createGame = function() {
            $scope.code = null;
            $scope.response = null;
            console.log("sending...");
            console.log($scope.createGameRequest);
            $http.post($scope.url, $scope.createGameRequest).success(
                function(data, status) {
                    console.log("posted - success");
                    console.log(data);
                    $scope.status = status;
                    $scope.resp = data;
                    if ($scope.resp.gameResponse.errorMsg != null &&
                        $scope.resp.gameResponse.errorMsg.length > 0) {
                        console.log("In error page");
                        $scope.errorMsg = $scope.resp.gameResponse.errorMsg;
                        $scope.errorBold = $scope.resp.gameResponse.errorBold;
                    } else {
                        console.log("In redirect page");
                        console.log($scope.resp.gameResponse.nextLanding);
                        if($scope.resp.gameResponse.nextLanding == 'golflanding') {
                            window.location.replace('#placeHolder');
                        }
                    }
                }
            ).error(
                function(data, status) {
                    console.log("Failed request");
                    console.log(data);
                    console.log(status);
                    $scope.data = data || "Request failed.";
                    $scope.status = status;
                }
            )
        };

        $scope.updateModel = function(method, url) {
            $scope.method = method;
            $scope.url = url;
        }
    }
]);