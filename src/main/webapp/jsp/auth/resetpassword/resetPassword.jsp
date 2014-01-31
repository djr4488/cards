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
    <title><s:text name="reset.title"/></title>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
</head>
<body class="mainbody">
    <div id="titlebox" class="titlediv"><s:text name="reset.title"/></div>
    <h2><s:text name="reset.hey.lets.change"/></h2>
    <ul>
        <li><s:text name="reset.think.you.can.tell"/></li>
        <li><s:text name="reset.i.will.let.you"/></li>
    </ul>
    <s:form id="changePassword" method="post" action="changePassword">
        <s:textfield name="userName" key="reset.confirmation.username"/>
        <s:textfield name="randomString" key="reset.confirmation.code"/>
        <s:password name="password" key="reset.confirmation.password"/>
        <s:password name="confirmPassword" key="reset.confirmation.confirm.password"/>
        <s:submit/>
    </s:form>
</body>
</html>