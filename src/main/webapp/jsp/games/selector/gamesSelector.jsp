<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><s:text name="selector.title"/></title>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
</head>
<body class="mainbody">
    <div id="titlebox" class="titlediv"><s:text name="selector.title"/></div>
    <s:form id="selector" method="post" action="gameStatsLanding">
        <s:select key="selector.list.label"
                  headerKey="-1" list="gameTypes"
                  name="gameSelector"/>
        <s:submit cssClass="buttonauth"/>
    </s:form>
</body>
</html>