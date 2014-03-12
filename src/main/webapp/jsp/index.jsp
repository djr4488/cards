<%--
  Created by IntelliJ IDEA.
  User: djr4488
  Date: 3/11/14
  Time: 8:09 PM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en" ng-app="cardsApp">
<head>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.14/angular.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.2.14/angular-route.js"></script>
    <script src="../js/app.js"></script>
    <script src="../js/AuthControllers.js"></script>
    <script src="../bootstrap-3.1.1-dist/js/bootstrap.min.js"></script>
    <script src="../js/jquery-1.10.2.js"></script>
    <link rel="stylesheet" href="../bootstrap-3.1.1-dist/css/bootstrap.min.css"/>
    <link rel="stylesheet" href="../bootstrap-3.1.1-dist/css/bootstrap-theme.min.css"/>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
    <title>Cards</title>
</head>
<body>
    <div id="titlebox" class="titlediv">Cards</div>
    <div ng-view></div>
</body>
</html>
