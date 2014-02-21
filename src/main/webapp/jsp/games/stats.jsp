<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${sessionScope.statsTitle}</title>
    <link rel="stylesheet" href="../css/cards.css" type="text/css">
    <link href="../css/eggplant/jquery-ui-1.10.4.custom.css" rel="stylesheet">
    <script src="../js/jquery-1.10.2.js"></script>
    <script src="../js/jquery-ui-1.10.4.custom.js"></script>
    <script>
        $(function() {
            $( "#accordion" ).accordion({ heightStyle: "context" });
            $( "#tabs" ).tabs({ heightStyle: "context" });
            $( "#selectable" ).selectable({ heightStyle: "context", overflow: "auto" });
            $( "#button" ).button();
            $( "#radioset" ).buttonset();
        })

    </script>
</head>
<body class="mainbody">
<div id="titlebox" class="titlediv">${sessionScope.statsTitle}</div>
<s:if test="hasInlineError()">
<div class="ui-widget">
    <div class="ui-state-error ui-corner-all" style="margin-top: 20px; padding: 0 .7em;">
        <p><span class="ui-icon ui-icon-alert" style="float: left; margin-right: .3em;"></span>
            <strong><s:text name="game.stats.hey"/> </strong>${sessionScope.inlineError}</p>
    </div>
</div>
</s:if>
<div id="stats" class="box-options">
    <ul class="box">
        <li class="first">
            <table width="420px" align="left">
                <caption><s:text name="game.stats.top10"/> ${sessionScope.gameType}</caption>
                <tr style="background-image: linear-gradient(to bottom, #FFFFFF, #8866A3);">
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
                <tr style="background-image: linear-gradient(to bottom, #FFFFFF, #8866A3);">
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
           <div id="tabs">
                <ul>
                    <li><a href="#tabs-1">Create Game</a></li>
                    <li><a href="#tabs-2">Join Game</a></li>
                    <li><a href="#tabs-3">Your games</a></li>
                </ul>
                <div id="tabs-1">
                    <s:form id="create" method="post" action="gameCreate">
                        <s:hidden name="model.gameType" value="%{#session.gameType}"/>
                        <s:label cssStyle="color: #ffffff; font-size: 75%" value="%{gameNameLabel}"/>
                        <s:textfield name="model.gameName"/>
                        <s:label cssStyle="color: #ffffff; font-size: 75%" value="%{gamePasswordLabel}"/>
                        <s:textfield name="model.gamePassword"/>
                        <s:submit cssClass="buttonauth"/>
                    </s:form>
                </div>
                <div id="tabs-2">
                    <s:form id="join" method="post" action="gameJoin">
                        <s:hidden name="model.gameType" value="%{#session.gameType}"/>
                        <s:label cssStyle="color: #ffffff; font-size: 75%" value="%{gameNameLabel}"/>
                        <s:textfield name="model.gameName"/>
                        <s:label cssStyle="color: #ffffff; font-size: 75%" value="%{gamePasswordLabel}"/>
                        <s:textfield name="model.gamePassword"/>
                        <s:submit cssClass="buttonauth"/>
                    </s:form>
                </div>
                <div id="tabs-3">
                    <s:form id="yourGames" method="post" action="gamePlay">
                            <s:hidden name="model.gameType" value="%{#session.gameType}"/>
                            <div id="radioset">
                                <s:radio list="model.userStats.activeGames" name="model.gameName"/>
                            </div>
                            <s:submit cssClass="buttonauth"/>
                    </s:form>
                </div>
           </div>
        </li>
    </ul>
</div>
</body>
</html>
