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
    <title><s:text name="create.title"/></title>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
</head>
<body class="mainbody">
<div id="titlebox" class="titlediv"><s:text name="create.title"/></div>
<s:if test="hasActionErrors()">
    <div class="errors">
        <s:actionerror/>
    </div>
</s:if>
<br>
<br>
<div id="loginbox">
    <s:form id="login" method="post" action="createAccount" validate="true">
        <s:textfield name="userName" key="create.username"/>
        <s:textfield name="alias" key="create.alias"/>
        <s:password name="password" key="create.password"/>
        <s:password name="confirmPassword" key="create.confirm.password"/>
        <s:submit cssClass="button"/>
    </s:form>
</div>
</body>
</html>