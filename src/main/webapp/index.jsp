<%@ page import="com.tictactoe.Sign" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
<head>
    <title>Tic-Tac-Toe</title>
    <link href="static/main.css" rel="stylesheet">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
<div>
    <h1>Tic-Tac-Toe</h1>

    <table>
        <tr>
            <td onclick="window.location='logic?click=0'">${data.get(0).getCh()}</td>
            <td onclick="window.location='logic?click=1'">${data.get(1).getCh()}</td>
            <td onclick="window.location='logic?click=2'">${data.get(2).getCh()}</td>
        </tr>
        <tr>
            <td onclick="window.location='logic?click=3'">${data.get(3).getCh()}</td>
            <td onclick="window.location='logic?click=4'">${data.get(4).getCh()}</td>
            <td onclick="window.location='logic?click=5'">${data.get(5).getCh()}</td>
        </tr>
        <tr>
            <td onclick="window.location='logic?click=6'">${data.get(6).getCh()}</td>
            <td onclick="window.location='logic?click=7'">${data.get(7).getCh()}</td>
            <td onclick="window.location='logic?click=8'">${data.get(8).getCh()}</td>
        </tr>
    </table>
    <hr>
<%--    what the hell is this language become
        procedural html :/
--%>
    <c:set var="CROSSES" value="<%=Sign.CROSS%>"/>
    <c:set var="NOUGHTS" value="<%=Sign.NOUGHT%>"/>

    <c:if test="${draw}">
        <h2>Draw</h2>
        <br>
        <button onclick="restart()">Start again</button>
    </c:if>
    <c:if test="${winner == CROSSES}">
        <h2>Crosses win</h2>
        <button onclick="restart()">Start again</button>
    </c:if>
    <c:if test="${winner == NOUGHTS}">
        <h2>Noughts win</h2>
        <button onclick="restart()">Start again</button>
    </c:if>


    <script>
        function restart() {
            $.ajax({
                url: "/restart",
                type: 'POST',
                contentType: 'application/json;charset=UTF-8',
                async: false,
                success: function () {
                    location.reload();
                }
            })
        }
    </script>
</div>

</body>
</html>