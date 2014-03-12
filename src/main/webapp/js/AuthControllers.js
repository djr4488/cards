/**
 * Created by djr4488 on 3/11/14.
 */
var authControllers = angular.module('authControllers', []);

authControllers.controller('LoginCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.method='POST';
        $scope.url = 'http://djr2.dyndns.org:9074/cards/login/submit';
        $scope.authForm = {
            loginForm: {
                emailAddress: "youremail@email.com",
                password: "password"
            }
        };
        $scope.loginResponse = {
            loginResponse: {
                nextLanding: "",
                errorMsg: "",
                errorBold: "",
                token: ""
            }
        }
        $scope.login = function() {
            $scope.code = null;
            $scope.response = null;
            console.log("sending...");
            console.log($scope.authForm);
            $http.post($scope.url, $scope.authForm).success(
                function(data, status) {
                    console.log(data);
                    $scope.status = status;
                    $scope.loginResponse = data;
                    if ($scope.loginResponse.loginResponse.errorMsg != null) {
                        console.log("In error page");
                        console.log($scope.loginResponse.loginResponse.nextLanding);
                        $scope.errorMsg = $scope.loginResponse.loginResponse.errorMsg;
                    } else {
                        console.log("In redirect page");
                        console.log($scope.loginResponse.loginResponse.nextLanding);
                        if($scope.loginResponse.loginResponse.nextLanding == 'selectGame') {
                            window.location.replace('http://djr2.dyndns.org:9074/cards/displayGames.do');
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