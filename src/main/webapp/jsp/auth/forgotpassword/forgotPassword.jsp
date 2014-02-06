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
    <title><s:text name="forgot.password.title"/></title>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
</head>
<body class="mainbody">
    <div id="titlebox" class="titlediv"><s:text name="forgot.password.title"/></div>
    <h2><s:text name="forgot.password.oops"/></h2>
    <ul>
        <li><s:text name="forgot.password.step1"/></li>
        <li><s:text name="forgot.password.step2"/></li>
        <li><s:text name="forgot.password.step3"/></li>
        <li><s:text name="forgot.password.step4"/></li>
        <li><s:text name="forgot.password.step5"/></li>
        <li><s:text name="forgot.password.step6"/></li>
    </ul>
    <s:form id="forgotPassword" method="post" action="forgotPassword">
        <s:textfield name="userName" key="forgotPassword.email"/>
        <s:submit cssClass="buttonauth"/>
    </s:form>
</body>
</html>