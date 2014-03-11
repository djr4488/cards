<%--
  Created by IntelliJ IDEA.
  User: djr4488
  Date: 2/24/14
  Time: 7:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html ng-app>
    <head>
        <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.14/angular.min.js"></script>
        <link rel="stylesheet" src="../../../css/ui-darkness/jquery-ui-1.10.4.custom.css"/>
        <title>Rest Login</title>
        <script>
            function LoginController($scope, $http) {
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
        </script>
    </head>
    <body>
        <div ng-controller="LoginController">
            <strong>Email Address</strong><br/>
            <input type="text" ng-model="authForm.loginForm.emailAddress" style="margin-left:5em"/><br/>
            <strong>Password</strong><br/>
            <input type="password" ng-model="authForm.loginForm.password" style="margin-left:5em"/><br/>
            <button id="login" ng-click="login()">Login</button>
            <pre>{{errorMsg}}</pre>
            <pre>{{loginResponse}}</pre>
        </div>
    </body>
</html>