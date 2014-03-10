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
                $scope.url = 'http://localhost:8080/cards/cards/login/submit';
                $scope.authForm = {
                    loginForm: {
                        emailAddress: "youremail@email.com",
                        password: "password"
                    }
                };
                $scope.login = function() {
                    $scope.code = null;
                    $scope.response = null;
                    console.log("sending...");
                    console.log($scope.authForm);
                    $http.post($scope.url, $scope.authForm).success(
                            function(data, status) {
                                $scope.status = status;
                                $scope.data = data;
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
            <pre>http status - {{status}}</pre>
            <pre>http response - {{data}}</pre>
            <pre>method - {{method}}</pre>
        </div>
    </body>
</html>