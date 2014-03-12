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
    <link rel="stylesheet" src="../../../css/ui-darkness/jquery-ui-1.10.4.custom.css"/>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
    <title>Cards</title>
</head>
<body>
    <div id="titlebox" class="titlediv">Cards</div>
    <div ng-view></div>
</body>
</html>
