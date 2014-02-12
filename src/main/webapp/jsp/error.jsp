<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="error.title"/></title>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
</head>
<body class="mainbody">
    <div id="titlebox" class="titlediv"><s:text name="error.title"/></div>
    <br/>
    <h3>${requestScope.msgbold}</h3>
    <p>${requestScope.msgtext}</p>
</body>
</html>