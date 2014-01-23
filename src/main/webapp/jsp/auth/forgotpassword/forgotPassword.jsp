<%@ taglib prefix="s" uri="/struts-tags" %>
<%--
  Created by IntelliJ IDEA.
  User: djr4488
  Date: 1/7/14
  Time: 8:51 AM
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cards - Login</title>
    <style type="text/css">
        .errors {
            background-color:#FFCCCC;
            border:1px solid #CC0000;
            width:400px;
            margin-bottom:8px;
        }
        .errors li{
            list-style: none;
        }
    </style>
</head>
<body>
    <div id="titlebox" style="padding: 10px 40px; border:2px solid #a1a1a1; border-radius:25px; background:#dddddd; width:300px; box-shadow: 10px 10px 5px #888888;">
        Password Recovery Kickoff
    </div>
    First off, I'm sorry you forgot your password.
    <ul>
        <li>First, lets have you identify yourself, using your email address.</li>
        <li>Then I'll do something crazy like send you an email(if you are registered).</li>
        <li>Then, I'll redirect you to the page asking you type in something I sent you.</li>
        <li>You'll type that in, or paste it, or whatever you do.</li>
        <li>If all of that is correct, I'll be nice and let you change that password Sparky!</li>
        <li>Ready to get started?  Great!</li>
    </ul>
    <s:form id="forgotPassword" method="post" action="forgotPassword">
        <s:textfield name="userName" key="forgotPassword.email"/>
        <s:submit/>
    </s:form>
</body>
</html>