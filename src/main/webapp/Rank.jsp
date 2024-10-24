<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@include file="DBConnection.jsp" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Flappy Bird Game</title>
        <title>Rank</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style_Ranking.css">
         <link href="https://fonts.googleapis.com/css2?family=Press+Start+2P&display=swap" rel="stylesheet">
    </head>
    <body>
        <div class="Back">
            <button class="back-button" onclick="window.location.href = '/FlappyShork/Home'">Back</button>
        </div>
        <sql:query var="result" dataSource="${DBC}">
            SELECT a.nickname, s.score
            FROM account a
            JOIN score s ON a.id = s.account_id
            ORDER BY s.score DESC;
        </sql:query>

        <div class="leaderboard">
            <h1>LEADERBOARD</h1>
            <div class="top3">
                <c:forEach var="user" items="${result.rows}" varStatus="status">
                    <c:choose>
                        <c:when test="${status.index == 0}">
                            <div class="first">
                                <span><c:out value="${status.index + 1}"/></span>
                                <span><c:out value="${user.nickname}"/></span>
                                <span><c:out value="${user.score}"/></span>
                            </div>
                        </c:when>
                        <c:when test="${status.index == 1}">
                            <div class="second">
                                <span><c:out value="${status.index + 1}"/></span>
                                <span><c:out value="${user.nickname}"/></span>
                                <span><c:out value="${user.score}"/></span>
                            </div>
                        </c:when>
                        <c:when test="${status.index == 2}">
                            <div class="third">
                                <span><c:out value="${status.index + 1}"/></span>
                                <span><c:out value="${user.nickname}"/></span>
                                <span><c:out value="${user.score}"/></span>
                            </div>
                        </c:when>
                    </c:choose>
                </c:forEach>
            </div>
            <div class="overflow">
                <c:forEach var="user" items="${result.rows}" varStatus="status">
                    <c:if test="${status.index >= 3}">
                        <div class="leaderboard-item">
                            <span><c:out value="${status.index + 1}"/>. <c:out value="${user.nickname}"/></span>
                            <span><c:out value="${user.score}"/></span>
                        </div>
                    </c:if>
                </c:forEach>
            </div>
        </div>
    </body>
</html>
