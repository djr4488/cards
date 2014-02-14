<%@ page import="com.djr.cards.games.stats.model.PlayerStats" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.statsTitle}</title>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
</head>
<body class="mainbody">
<div id="titlebox" class="titlediv">${sessionScope.statsTitle}</div>
<div id="stats" class="box-options">
    <ul>
        <li>
            <table>
                <caption>Top 10 Players in ${sessionScope.gameType}</caption>
                <tr><th>rank</th><th>alias</th><th>wins</th><th>total games</th></tr>

            </table>
        </li>
        <li class="last">
            Something else will go here
        </li>
    </ul>
</div>
</body>
</html>
