<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.statsTitle}</title>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
    <link href="../css/ui-darkness/jquery-ui-1.10.4.custom.css" rel="stylesheet">
    <script src="../js/jquery-1.10.2.js"></script>
    <script src="../js/jquery-ui-1.10.4.custom.js"></script>
    <script>
        $(function() {
            $( "#accordion" ).accordion({ heightStyle: "context" });
        })
    </script>
</head>
<body class="mainbody">
<div id="titlebox" class="titlediv">${sessionScope.statsTitle}</div>
<div id="stats" class="box-options">
    <ul>
        <li>
            <table width="420px" align="left">
                <caption><s:text name="game.stats.top10"/> ${sessionScope.gameType}</caption>
                <tr>
                    <th width="25%" align="left"><s:text name="game.stats.rank"/></th>
                    <th width="25%" align="left"><s:text name="game.stats.alias"/></th>
                    <th width="25%" align="left"><s:text name="game.stats.wins"/></th>
                    <th width="25%" align="left"><s:text name="game.stats.total.games"/></th>
                </tr>
                <s:iterator value="model.top10" var="player">
                    <tr>
                        <td><s:property value="#player.rank"/></td>
                        <td><s:property value="#player.alias"/></td>
                        <td><s:property value="#player.wins"/></td>
                        <td><s:property value="#player.totalGames"/></td>
                    </tr>
                </s:iterator>
                <tr>
                    <th colspan="4"><s:text name="game.stats.yours"/></th>
                </tr>
                <tr>
                    <td><s:property value="model.userStats.rank"/></td>
                    <td><s:property value="model.userStats.alias"/></td>
                    <td><s:property value="model.userStats.wins"/></td>
                    <td><s:property value="model.userStats.totalGames"/></td>
                </tr>
            </table>
        </li>
        <li class="last">
            <div id="accordion">
                <h3>Create A Game</h3>
                <div style="display: block; height: 140px">Create game will go here.</div>
                <h3>Join A Game</h3>
                <div style="display: block; height: 140px">Games not yet in progress/started will be listed here.</div>
                <h3>Your Current Games</h3>
                <div style="display: block; height: 140px">List of the games user is currently active in.</div>
            </div>
        </li>
    </ul>
</div>
</body>
</html>
