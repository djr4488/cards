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
    <title>Changing your password</title>
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
    Hey lets change your password... but first
    <ul>
        <li>Think you can prove who you said you were by providing the code I sent you in email?</li>
        <li>Then, if you do that for me, I'll let you change your password!</li>
    </ul>
    <s:form id="changePassword" method="post" action="changePassword">
        <s:textfield name="email" key="login.username"/>
        <s:textfield name="randomString" key="changePassword.proof"/>
        <s:password name="password" key="login.password"/>
        <s:password name="confirmPassword" key="login.confirm.password"/>
        <s:submit/>
    </s:form>
</body>
</html>