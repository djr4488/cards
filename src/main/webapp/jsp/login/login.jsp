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
    Login Page </div>
<br>
<br>
<s:if test="hasActionErrors()">
    <div class="errors">
        <s:actionerror/>
    </div>
</s:if>
<div id="loginbox">
    <s:form id="login" method="post" action="login">
        <s:textfield name="userName" key="login.username"/>
        <s:password name="password" key="login.password"/>
        <s:submit/>
    </s:form>
    <strong>Don't have an account?  <a href="createAccountLanding.do">Create one</a></strong>
    <br/>
    <a href="forgotPasswordLanding.do">Forgot your password?</a>
</div>
</body>
</html>