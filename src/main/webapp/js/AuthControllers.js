/**
 * Created by djr4488 on 3/11/14.
 */
var authControllers = angular.module('authControllers', []);

authControllers.controller('LoginCtrl', ['$scope', '$http',
    function ($scope, $http) {
        $scope.baseURL = 'http://djr2.dyndns.org:9074';
        $scope.method = 'POST';
        $scope.url = $scope.baseURL + '/cardsapi/login/submit';
        $scope.authForm = {
            loginForm: {
                emailAddress: "",
                password: ""
            }
        };
        $scope.loginResponse = {
            authResponse: {
                nextLanding: "",
                errorMsg: "",
                errorBold: "",
                token: ""
            }
        }
        $scope.login = function () {
            $scope.code = null;
            $scope.response = null;
            console.log("sending...");
            console.log($scope.authForm);
            $http.post($scope.url, $scope.authForm).success(
                function (data, status) {
                    console.log("posted - success");
                    console.log(data);
                    $scope.status = status;
                    $scope.loginResponse = data;
                    if ($scope.loginResponse.authResponse.errorMsg != null &&
                        $scope.loginResponse.authResponse.errorMsg.length > 0) {
                        console.log("In error page");
                        $scope.errorMsg = $scope.loginResponse.authResponse.errorMsg;
                        $scope.errorBold = $scope.loginResponse.authResponse.errorBold;
                    } else {
                        console.log("In redirect page");
                        console.log($scope.loginResponse.authResponse.nextLanding);
                        if ($scope.loginResponse.authResponse.nextLanding == 'selectGame') {
                            window.location.replace('#selectGame');
                        }
                    }
                }
            ).error(
                function (data, status) {
                    console.log("Failed request");
                    console.log(data);
                    console.log(status);
                    $scope.data = data || "Request failed.";
                    $scope.status = status;
                }
            )
        };

        $scope.updateModel = function (method, url) {
            $scope.method = method;
            $scope.url = url;
        }
    }
]).controller('CreateAcctCtrl', ['$scope', '$http', '$route',
    function ($scope, $http) {
        $scope.baseURL = 'http://djr2.dyndns.org:9074';
        $scope.method = 'POST';
        $scope.url = $scope.baseURL + '/cardsapi/createAccount/submit';
        $scope.authForm = {
            createAccountForm: {
                emailAddress: "",
                alias: "",
                password: "",
                confirmPassword: ""
            }
        };
        $scope.loginResponse = {
            authResponse: {
                nextLanding: "",
                errorMsg: "",
                errorBold: "",
                token: "",
                msg: "",
                msgBold: ""
            }
        }
        $scope.createAccount = function () {
            $scope.code = null;
            $scope.response = null;
            console.log("sending...");
            console.log($scope.authForm);
            $http.post($scope.url, $scope.authForm).success(
                function (data, status) {
                    console.log("posted - success");
                    console.log(data);
                    $scope.status = status;
                    $scope.loginResponse = data;
                    if ($scope.loginResponse.authResponse.errorMsg != null &&
                        $scope.loginResponse.authResponse.errorMsg.length > 0) {
                        console.log("In error page");
                        $scope.errorMsg = $scope.loginResponse.authResponse.errorMsg;
                        $scope.errorBold = $scope.loginResponse.authResponse.errorBold;
                    } else {
                        console.log("In redirect page");
                        console.log($scope.loginResponse.authResponse.nextLanding);
                        if ($scope.loginResponse.authResponse.nextLanding == 'login') {
                            $scope.msg = $scope.loginResponse.authResponse.msg;
                            $scope.msgBold = $scope.loginResponse.authResponse.msgBold;
                            window.location.replace('#login');
                        }
                    }
                }
            ).error(
                function (data, status) {
                    console.log("Failed request");
                    console.log(data);
                    console.log(status);
                    $scope.data = data || "Request failed.";
                    $scope.status = status;
                }
            )
        };

        $scope.updateModel = function (method, url) {
            $scope.method = method;
            $scope.url = url;
        }
    }
]).controller('ForgotPasswordCtrl', ['$scope', '$http', '$route',
    function ($scope, $http) {
        $scope.baseURL = 'http://djr2.dyndns.org:9074';
        $scope.method = 'POST';
        $scope.url = $scope.baseURL + '/cardsapi/forgotpassword/submit';
        $scope.authForm = {
            forgotPasswordForm: {
                emailAddress: ""
            }
        };
        $scope.loginResponse = {
            authResponse: {
                nextLanding: "",
                errorMsg: "",
                errorBold: "",
                token: ""
            }
        }
        $scope.forgotPassword = function () {
            $scope.code = null;
            $scope.response = null;
            console.log("sending...");
            console.log($scope.authForm);
            $http.post($scope.url, $scope.authForm).success(
                function (data, status) {
                    console.log("posted - success");
                    console.log(data);
                    $scope.status = status;
                    $scope.loginResponse = data;
                    if ($scope.loginResponse.authResponse.errorMsg != null &&
                        $scope.loginResponse.authResponse.errorMsg.length > 0) {
                        console.log("In error page");
                        $scope.errorMsg = $scope.loginResponse.authResponse.errorMsg;
                        $scope.errorBold = $scope.loginResponse.authResponse.errorBold;
                    } else {
                        console.log("In redirect page");
                        console.log($scope.loginResponse.authResponse.nextLanding);
                        if ($scope.loginResponse.authResponse.nextLanding == 'resetPassword') {
                            window.location.replace('#resetPassword');
                        }
                    }
                }
            ).error(
                function (data, status) {
                    console.log("Failed request");
                    console.log(data);
                    console.log(status);
                    $scope.data = data || "Request failed.";
                    $scope.status = status;
                }
            )
        };

        $scope.updateModel = function (method, url) {
            $scope.method = method;
            $scope.url = url;
        }
    }
]).controller('ResetPasswordCtrl', ['$scope', '$http', '$route',
    function ($scope, $http) {
        $scope.baseURL = 'http://djr2.dyndns.org:9074';
        $scope.method = 'POST';
        $scope.url = $scope.baseURL + '/cardsapi/resetpassword/submit';
        $scope.authForm = {
            resetPasswordForm: {
                emailAddress: "",
                securityCode: "",
                password: "",
                confirmPassword: ""
            }
        };
        $scope.loginResponse = {
            authResponse: {
                nextLanding: "",
                errorMsg: "",
                errorBold: "",
                token: "",
                msg: "",
                msgBold: ""
            }
        }
        $scope.resetPassword = function () {
            $scope.code = null;
            $scope.response = null;
            console.log("sending...");
            console.log($scope.authForm);
            $http.post($scope.url, $scope.authForm).success(
                function (data, status) {
                    console.log("posted - success");
                    console.log(data);
                    $scope.status = status;
                    $scope.loginResponse = data;
                    if ($scope.loginResponse.authResponse.errorMsg != null &&
                        $scope.loginResponse.authResponse.errorMsg.length > 0) {
                        console.log("In error page");
                        $scope.errorMsg = $scope.loginResponse.authResponse.errorMsg;
                        $scope.errorBold = $scope.loginResponse.authResponse.errorBold;
                    } else {
                        console.log("In redirect page");
                        console.log($scope.loginResponse.authResponse.nextLanding);
                        if ($scope.loginResponse.authResponse.nextLanding == 'login') {
                            $scope.msg = $scope.loginResponse.authResponse.msg;
                            $scope.msgBold = $scope.loginResponse.authResponse.msgBold;
                            window.location.replace('#login');
                        }
                    }
                }
            ).error(
                function (data, status) {
                    console.log("Failed request");
                    console.log(data);
                    console.log(status);
                    $scope.data = data || "Request failed.";
                    $scope.status = status;
                }
            )
        };

        $scope.updateModel = function (method, url) {
            $scope.method = method;
            $scope.url = url;
        }
    }
]);